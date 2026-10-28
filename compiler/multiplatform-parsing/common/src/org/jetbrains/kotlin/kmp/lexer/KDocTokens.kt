/*
 * Copyright 2010-2025 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kmp.lexer

import com.intellij.platform.syntax.SyntaxElementType
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.kotlin.kmp.utils.SyntaxElementTypesWithIds
import org.jetbrains.kotlin.syntax.KDocTokens.CODE_BLOCK_TEXT_ID
import org.jetbrains.kotlin.syntax.KDocTokens.CODE_SPAN_TEXT_ID
import org.jetbrains.kotlin.syntax.KDocTokens.END_ID
import org.jetbrains.kotlin.syntax.KDocTokens.KDOC_LPAR_ID
import org.jetbrains.kotlin.syntax.KDocTokens.KDOC_RPAR_ID
import org.jetbrains.kotlin.syntax.KDocTokens.LEADING_ASTERISK_ID
import org.jetbrains.kotlin.syntax.KDocTokens.MARKDOWN_ESCAPED_CHAR_ID
import org.jetbrains.kotlin.syntax.KDocTokens.MARKDOWN_LINK_ID
import org.jetbrains.kotlin.syntax.KDocTokens.START_ID
import org.jetbrains.kotlin.syntax.KDocTokens.TAG_NAME_ID
import org.jetbrains.kotlin.syntax.KDocTokens.TEXT_ID

@ApiStatus.Experimental
object KDocTokens : SyntaxElementTypesWithIds() {
    val START: SyntaxElementType = register(START_ID, "KDOC_START")
    val END: SyntaxElementType = register(END_ID, "KDOC_END")
    val LEADING_ASTERISK: SyntaxElementType = register(LEADING_ASTERISK_ID, "KDOC_LEADING_ASTERISK")

    val TEXT: SyntaxElementType = register(TEXT_ID, "KDOC_TEXT")
    val CODE_BLOCK_TEXT: SyntaxElementType = register(CODE_BLOCK_TEXT_ID, "KDOC_CODE_BLOCK_TEXT")
    val CODE_SPAN_TEXT: SyntaxElementType = register(CODE_SPAN_TEXT_ID, "KDOC_CODE_SPAN_TEXT")

    val TAG_NAME: SyntaxElementType = register(TAG_NAME_ID, "KDOC_TAG_NAME")
    val MARKDOWN_LINK: SyntaxElementType = register(MARKDOWN_LINK_ID, "KDOC_MARKDOWN_LINK")
    val MARKDOWN_ESCAPED_CHAR: SyntaxElementType = register(MARKDOWN_ESCAPED_CHAR_ID, "KDOC_MARKDOWN_ESCAPED_CHAR")

    val KDOC_LPAR: SyntaxElementType = register(KDOC_LPAR_ID, "KDOC_LPAR")
    val KDOC_RPAR: SyntaxElementType = register(KDOC_RPAR_ID, "KDOC_RPAR")
}
