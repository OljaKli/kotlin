/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.syntax

object KDocTokens {
    const val START_ID: Int = KtNodeTypes.BLOCK_CODE_FRAGMENT_ID + 1
    const val END_ID: Int = START_ID + 1
    const val LEADING_ASTERISK_ID: Int = END_ID + 1
    const val TEXT_ID: Int = LEADING_ASTERISK_ID + 1
    const val CODE_BLOCK_TEXT_ID: Int = TEXT_ID + 1
    const val TAG_NAME_ID: Int = CODE_BLOCK_TEXT_ID + 1
    const val MARKDOWN_LINK_ID: Int = TAG_NAME_ID + 1
    const val MARKDOWN_ESCAPED_CHAR_ID: Int = MARKDOWN_LINK_ID + 1
    const val KDOC_LPAR_ID: Int = MARKDOWN_ESCAPED_CHAR_ID + 1
    const val KDOC_RPAR_ID: Int = KDOC_LPAR_ID + 1
    const val CODE_SPAN_TEXT_ID: Int = KDOC_RPAR_ID + 1
    /**
     * Remember to update first const in [KDocNodeTypes] after adding new tokens here ^^^
     */
}
