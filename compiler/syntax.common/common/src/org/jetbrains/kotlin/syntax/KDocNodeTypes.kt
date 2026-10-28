/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.syntax

object KDocNodeTypes {
    const val KDOC_SECTION_ID: Int = KDocTokens.CODE_SPAN_TEXT_ID + 1
    const val KDOC_TAG_ID: Int = KDOC_SECTION_ID + 1
    const val KDOC_NAME_ID: Int = KDOC_TAG_ID + 1
}
