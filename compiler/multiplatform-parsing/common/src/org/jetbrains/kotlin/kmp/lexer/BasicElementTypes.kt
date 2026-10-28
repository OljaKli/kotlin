/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kmp.lexer

import com.intellij.platform.syntax.SyntaxElementType
import com.intellij.platform.syntax.element.SyntaxTokenTypes
import org.jetbrains.kotlin.kmp.utils.SyntaxElementTypesWithIds
import org.jetbrains.kotlin.syntax.BasicElementTypes.BAD_CHARACTER_ID
import org.jetbrains.kotlin.syntax.BasicElementTypes.ERROR_ELEMENT_ID
import org.jetbrains.kotlin.syntax.BasicElementTypes.WHITE_SPACE_ID

object BasicElementTypes : SyntaxElementTypesWithIds() {
    val WHITE_SPACE: SyntaxElementType = register(WHITE_SPACE_ID, SyntaxTokenTypes.WHITE_SPACE)
    val BAD_CHARACTER: SyntaxElementType = register(BAD_CHARACTER_ID, SyntaxTokenTypes.BAD_CHARACTER)
    val ERROR_ELEMENT: SyntaxElementType = register(ERROR_ELEMENT_ID, SyntaxTokenTypes.ERROR_ELEMENT)
}
