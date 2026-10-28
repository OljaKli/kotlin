/*
 * Copyright 2010-2025 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kmp.parser

import com.intellij.platform.syntax.SyntaxElementType
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.kotlin.kmp.utils.SyntaxElementTypesWithIds
import org.jetbrains.kotlin.syntax.KDocNodeTypes.KDOC_NAME_ID
import org.jetbrains.kotlin.syntax.KDocNodeTypes.KDOC_SECTION_ID
import org.jetbrains.kotlin.syntax.KDocNodeTypes.KDOC_TAG_ID

@ApiStatus.Experimental
object KDocParseNodes : SyntaxElementTypesWithIds() {
    val KDOC_SECTION: SyntaxElementType = register(KDOC_SECTION_ID, "KDOC_SECTION")
    val KDOC_TAG: SyntaxElementType = register(KDOC_TAG_ID, "KDOC_TAG")
    val KDOC_NAME: SyntaxElementType = register(KDOC_NAME_ID, "KDOC_NAME")
}
