/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:OptIn(SuspiciousFakeSourceCheck::class)

package org.jetbrains.kotlin

import com.intellij.lang.LighterASTNode
import com.intellij.lang.TreeBackedLighterAST
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.tree.IElementType
import com.intellij.util.diff.FlyweightCapableTreeStructure
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.util.OperatorNameConventions
import org.jetbrains.kotlin.utils.getElementTextWithContext
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater

sealed class AbstractKtSourceElement {
    abstract val startOffset: Int
    abstract val endOffset: Int
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractKtSourceElement) return false

        if (startOffset != other.startOffset) return false
        if (endOffset != other.endOffset) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startOffset
        result = 31 * result + endOffset
        return result
    }
}

class KtOffsetsOnlySourceElement(
    override val startOffset: Int,
    override val endOffset: Int,
) : AbstractKtSourceElement()

object KtMissingSourceElement : AbstractKtSourceElement() {
    override val startOffset: Int
        get() = -1
    override val endOffset: Int
        get() = -1
}

/**
 * [KtSourceElement] represents the AST element associated with a specific location in the source code. It allows the compiler to map back
 * to the source file.
 *
 * A source element can be either real or fake:
 *
 * - Real source elements are backed directly by a corresponding AST element (see [KtRealSourceElementKind]).
 * - Fake source elements are a combination of a real AST element and a [fake source element kind][KtFakeSourceElementKind].
 *
 * The two current implementations of [KtSourceElement] cover [light tree][KtLightSourceElement] and [PSI][KtPsiSourceElement] source
 * elements.
 *
 * ### Distinct source elements
 *
 * **Constraint:** To support the unambiguous source-based equality of FIR symbols, each FIR declaration should have a distinct
 * `(realSource, sourceElementKind)` pair as its source element. This includes local declarations.
 *
 * This constraint can be checked per file because source elements from different files can never be equal, as real source nodes are
 * distinct between files.
 *
 * When the constraint is violated, we can have two different FIR declarations with the same source element. Source-based equality would
 * then break, because these FIR declarations would be unexpectedly equal.
 *
 * This constraint does not (yet) apply to non-declaration FIR elements, so, for example, it is currently legal for multiple FIR expressions
 * to share the same source element.
 *
 * #### Compiler plugin-generated declarations
 *
 * Compiler plugin-generated FIR declarations are exempt from the distinctness constraint. We cannot rely on third-party compiler plugins to
 * test for distinct source elements, nor for them to have exhaustive test data. Nor is it good to place such a burden on compiler plugin
 * developers, who may not have an intricate knowledge of FIR internals.
 *
 * Due to this exemption, FIR file/declaration caches which rely on source-based equality must not contain plugin-generated declarations.
 * This is currently satisfied as follows:
 *
 * - `FirExtensionDeclarationsSymbolProvider` stores plugin-generated FIR declarations uniquely and does not rely on source-based equality.
 * - Additionally, in compiler mode, plugin-generated FIR declarations are materialzied into new FIR files, but FIR declarations remain
 *   unique. Thus, we have no caches which rely on source-based equality.
 * - In Analysis API mode, plugin-generated FIR declarations are only materialized for FIR dump checking in tests. Hence, plugin-generated
 *   declarations are not cached outside `FirExtensionDeclarationsSymbolProvider`.
 *
 * Local declarations generated into *existing* FIR files with `FirFunctionCallRefinementExtension` are an exception to this rule. Compiler
 * plugins must provide distinct source elements in this case, as we generally need all declarations in a source FIR file to have distinct
 * source elements. See the KDoc of `FirFunctionCallRefinementExtension` for more information.
 */
// TODO: consider renaming to something like AstBasedSourceElement
sealed class KtSourceElement : AbstractKtSourceElement() {
    abstract val elementType: IElementType?
    abstract val kind: KtSourceElementKind
    abstract val lighterASTNode: LighterASTNode
    abstract val treeStructure: FlyweightCapableTreeStructure<LighterASTNode>

    abstract fun getElementTextInContextForDebug(): String

    /** Implementation must compute the hashcode from the source element. */
    abstract override fun hashCode(): Int

    /** Elements of the same source should be considered equal. */
    abstract override fun equals(other: Any?): Boolean

    abstract fun fakeElement(
        newKind: KtFakeSourceElementKind,
        offsetStrategy: KtSourceElementOffsetStrategy = KtSourceElementOffsetStrategy.Default,
    ): KtSourceElement

    abstract fun realElement(): KtSourceElement

    abstract val text: CharSequence
}

// NB: in certain situations, psi.node could be null (see e.g. KT-44152)
// Potentially exceptions can be provoked by elementType / lighterASTNode
sealed class KtPsiSourceElement(val psi: PsiElement) : KtSourceElement() {
    companion object {
        @JvmStatic
        private val lighterASTNodeUpdater = AtomicReferenceFieldUpdater.newUpdater(
            KtPsiSourceElement::class.java,
            LighterASTNode::class.java,
            "_lighterASTNode"
        )

        @JvmStatic
        private val treeStructureNodeUpdater = AtomicReferenceFieldUpdater.newUpdater(
            KtPsiSourceElement::class.java,
            FlyweightCapableTreeStructure::class.java,
            "_treeStructure"
        )
    }

    override val elementType: IElementType?
        get() = psi.node?.elementType

    override val startOffset: Int
        get() = psi.textRange.startOffset

    override val endOffset: Int
        get() = psi.textRange.endOffset

    @Volatile
    private var _lighterASTNode: LighterASTNode? = null
    final override val lighterASTNode: LighterASTNode
        get() {
            _lighterASTNode?.let { return it }
            lighterASTNodeUpdater.compareAndSet(
                /* obj = */ this,
                /* expect = */ null,
                /* update = */ TreeBackedLighterAST.wrap(psi.node)
            )
            return _lighterASTNode!!
        }

    @Volatile
    private var _treeStructure: FlyweightCapableTreeStructure<LighterASTNode>? = null
    final override val treeStructure: FlyweightCapableTreeStructure<LighterASTNode>
        get() {
            _treeStructure?.let { return it }
            treeStructureNodeUpdater.compareAndSet(
                /* obj = */ this,
                /* expect = */ null,
                /* update = */ WrappedTreeStructure(psi.containingFile)
            )
            return _treeStructure!!
        }

    override fun getElementTextInContextForDebug(): String {
        return getElementTextWithContext(psi)
    }

    internal class WrappedTreeStructure(file: PsiFile) : FlyweightCapableTreeStructure<LighterASTNode> {
        private val lighterAST = TreeBackedLighterAST(file.node)

        fun unwrap(node: LighterASTNode) = lighterAST.unwrap(node)

        override fun toString(node: LighterASTNode): CharSequence = unwrap(node).text

        override fun getRoot(): LighterASTNode = lighterAST.root

        override fun getParent(node: LighterASTNode): LighterASTNode? =
            unwrap(node).psi.parent?.node?.let { TreeBackedLighterAST.wrap(it) }

        override fun getChildren(node: LighterASTNode, nodesRef: Ref<Array<LighterASTNode>>): Int {
            val psi = unwrap(node).psi
            val children = mutableListOf<PsiElement>()
            var child = psi.firstChild
            while (child != null) {
                children += child
                child = child.nextSibling
            }
            if (children.isEmpty()) {
                nodesRef.set(LighterASTNode.EMPTY_ARRAY)
            } else {
                nodesRef.set(children.map { TreeBackedLighterAST.wrap(it.node) }.toTypedArray())
            }
            return children.size
        }

        override fun disposeChildren(p0: Array<out LighterASTNode>?, p1: Int) {
        }

        override fun getStartOffset(node: LighterASTNode): Int {
            return getStartOffset(unwrap(node).psi)
        }

        private fun getStartOffset(element: PsiElement): Int {
            var child = element.firstChild
            if (child != null) {
                while (child is PsiComment || child is PsiWhiteSpace) {
                    child = child.nextSibling
                }
                if (child != null) {
                    return getStartOffset(child)
                }
            }
            return element.textRange.startOffset
        }

        override fun getEndOffset(node: LighterASTNode): Int {
            return getEndOffset(unwrap(node).psi)
        }

        private fun getEndOffset(element: PsiElement): Int {
            var child = element.lastChild
            if (child != null) {
                while (child is PsiComment || child is PsiWhiteSpace) {
                    child = child.prevSibling
                }
                if (child != null) {
                    return getEndOffset(child)
                }
            }
            return element.textRange.endOffset
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KtPsiSourceElement

        if (psi != other.psi) return false

        return true
    }

    override fun hashCode(): Int {
        return psi.hashCode()
    }

    override fun toString(): String = buildString {
        append(this@KtPsiSourceElement::class.simpleName)
        append('(')
        append(psi::class.simpleName)
        if (kind is KtFakeSourceElementKind) {
            append(", ").append(kind)
        }
        append(", ").append(startOffset).append("..").append(endOffset)
        append(')')
    }

    override fun fakeElement(newKind: KtFakeSourceElementKind, offsetStrategy: KtSourceElementOffsetStrategy): KtSourceElement {
        if (kind == newKind) return this
        return when (offsetStrategy) {
            is KtSourceElementOffsetStrategy.Default -> KtFakePsiSourceElement(psi, newKind)
            is KtSourceElementOffsetStrategy.Custom -> KtFakePsiSourceElementWithCustomOffsetStrategy(psi, newKind, offsetStrategy)
        }
    }

    override fun realElement(): KtSourceElement {
        return KtRealPsiSourceElement(psi)
    }

    override val text: CharSequence
        get() = psi.text
}

class KtRealPsiSourceElement(psi: PsiElement) : KtPsiSourceElement(psi) {
    override val kind: KtSourceElementKind get() = KtRealSourceElementKind
}

/**
 * Checking for [KtFakePsiSourceElement] only works for PSI sources.
 *
 * To check for a fake source regardless of source type, check if [KtSourceElement.kind] is a [KtFakeSourceElementKind].
 */
@RequiresOptIn
annotation class SuspiciousFakeSourceCheck

@SuspiciousFakeSourceCheck
open class KtFakePsiSourceElement(
    psi: PsiElement,
    override val kind: KtFakeSourceElementKind,
) : KtPsiSourceElement(psi) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as KtFakePsiSourceElement

        if (kind != other.kind) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + kind.hashCode()
        return result
    }
}

@SuspiciousFakeSourceCheck
class KtFakePsiSourceElementWithCustomOffsetStrategy(
    psi: PsiElement,
    kind: KtFakeSourceElementKind,
    val strategy: KtSourceElementOffsetStrategy.Custom,
) : KtFakePsiSourceElement(psi, kind) {
    override val startOffset: Int
        get() = strategy.startOffset

    override val endOffset: Int
        get() = strategy.endOffset

    override fun equals(other: Any?): Boolean = this === other ||
            other is KtFakePsiSourceElementWithCustomOffsetStrategy &&
            super.equals(other) &&
            strategy == other.strategy

    override fun hashCode(): Int = 31 * super.hashCode() + strategy.hashCode()
}

/**
 * Represents a strategy for getting offsets for [KtSourceElement]s.
 *
 * @see fakeElement
 */
sealed class KtSourceElementOffsetStrategy {
    /**
     * The default strategy, which uses offsets from the original element.
     */
    data object Default : KtSourceElementOffsetStrategy()

    /**
     * Represents a strategy with custom [startOffset] and [endOffset]s.
     */
    sealed class Custom : KtSourceElementOffsetStrategy() {
        abstract val startOffset: Int
        abstract val endOffset: Int

        class Initialized(override val startOffset: Int, override val endOffset: Int) : Custom() {
            override fun equals(other: Any?): Boolean = this === other ||
                    other is Initialized &&
                    startOffset == other.startOffset &&
                    endOffset == other.endOffset

            override fun hashCode(): Int = 31 * startOffset.hashCode() + endOffset.hashCode()
        }

        class Delegated(val startOffsetAnchor: KtSourceElement, val endOffsetAnchor: KtSourceElement) : Custom() {
            override val startOffset: Int
                get() = startOffsetAnchor.startOffset

            override val endOffset: Int
                get() = endOffsetAnchor.endOffset

            override fun equals(other: Any?): Boolean = this === other ||
                    other is Delegated &&
                    startOffsetAnchor == other.startOffsetAnchor &&
                    endOffsetAnchor == other.endOffsetAnchor

            override fun hashCode(): Int = 31 * startOffsetAnchor.hashCode() + endOffsetAnchor.hashCode()
        }
    }
}

class KtLightSourceElement(
    override val lighterASTNode: LighterASTNode,
    override val startOffset: Int,
    override val endOffset: Int,
    override val treeStructure: FlyweightCapableTreeStructure<LighterASTNode>,
    override val kind: KtSourceElementKind = KtRealSourceElementKind,
) : KtSourceElement() {
    override val elementType: IElementType
        get() = lighterASTNode.tokenType

    /**
     * We can create a [KtLightSourceElement] from a [KtPsiSourceElement] by using [KtPsiSourceElement.lighterASTNode];
     * [unwrapToKtPsiSourceElement] allows to get original [KtPsiSourceElement] in such case.
     *
     * If it is `pure` [KtLightSourceElement], i.e, compiler created it in light tree mode, then return [unwrapToKtPsiSourceElement] `null`.
     * Otherwise, return some not-null result.
     */
    fun unwrapToKtPsiSourceElement(): KtPsiSourceElement? {
        if (treeStructure !is KtPsiSourceElement.WrappedTreeStructure) return null
        val node = treeStructure.unwrap(lighterASTNode)
        return node.psi?.toKtPsiSourceElement(kind)
    }

    override fun getElementTextInContextForDebug(): String {
        return treeStructure.toString(lighterASTNode).toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KtLightSourceElement

        if (lighterASTNode != other.lighterASTNode) return false
        if (startOffset != other.startOffset) return false
        if (endOffset != other.endOffset) return false
        if (treeStructure != other.treeStructure) return false
        if (kind != other.kind) return false

        return true
    }

    override fun hashCode(): Int {
        var result = lighterASTNode.hashCode()
        result = 31 * result + startOffset
        result = 31 * result + endOffset
        result = 31 * result + treeStructure.hashCode()
        result = 31 * result + kind.hashCode()
        return result
    }

    override fun toString(): String = buildString {
        append(this@KtLightSourceElement::class.simpleName)
        append("(")
        append(elementType)
        if (kind is KtFakeSourceElementKind) {
            append(", ").append(kind)
        }
        append(", ").append(startOffset).append("..").append(endOffset)
        append(')')
    }

    override fun fakeElement(
        newKind: KtFakeSourceElementKind,
        offsetStrategy: KtSourceElementOffsetStrategy,
    ): KtLightSourceElement {
        if (kind == newKind) return this
        val [startOffset, endOffset] = if (offsetStrategy is KtSourceElementOffsetStrategy.Custom) {
            offsetStrategy.startOffset to offsetStrategy.endOffset
        } else {
            startOffset to endOffset
        }

        return KtLightSourceElement(
            lighterASTNode,
            startOffset,
            endOffset,
            treeStructure,
            newKind
        )
    }

    override fun realElement(): KtSourceElement {
        return KtLightSourceElement(lighterASTNode, startOffset, endOffset, treeStructure, KtRealSourceElementKind)
    }

    override val text: CharSequence
        get() = treeStructure.toString(lighterASTNode)
}

val AbstractKtSourceElement?.psi: PsiElement? get() = (this as? KtPsiSourceElement)?.psi

@Suppress("NOTHING_TO_INLINE")
inline fun PsiElement.toKtPsiSourceElement(kind: KtSourceElementKind = KtRealSourceElementKind): KtPsiSourceElement = when (kind) {
    is KtRealSourceElementKind -> KtRealPsiSourceElement(this)
    is KtFakeSourceElementKind -> KtFakePsiSourceElement(this, kind)
}

val KtSourceElement?.text: CharSequence?
    get() = this?.text

@Suppress("NOTHING_TO_INLINE")
inline fun LighterASTNode.toKtLightSourceElement(
    tree: FlyweightCapableTreeStructure<LighterASTNode>,
    kind: KtSourceElementKind = KtRealSourceElementKind,
    startOffset: Int = this.startOffset,
    endOffset: Int = this.endOffset,
): KtLightSourceElement = KtLightSourceElement(this, startOffset, endOffset, tree, kind)

fun sourceKindForIncOrDec(operation: Name, isPrefix: Boolean) = when (operation) {
    OperatorNameConventions.INC -> if (isPrefix) {
        KtFakeSourceElementKind.DesugaredIncrementOrDecrement.PrefixInc()
    } else {
        KtFakeSourceElementKind.DesugaredIncrementOrDecrement.PostfixInc()
    }
    OperatorNameConventions.DEC -> if (isPrefix) {
        KtFakeSourceElementKind.DesugaredIncrementOrDecrement.PrefixDec()
    } else {
        KtFakeSourceElementKind.DesugaredIncrementOrDecrement.PostfixDec()
    }
    else -> error("Unexpected operator: ${operation.identifier}")
}
