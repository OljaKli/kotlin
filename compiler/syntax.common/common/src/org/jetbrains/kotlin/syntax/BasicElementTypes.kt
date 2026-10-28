/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.syntax

const val NO_ID: Int = 0

object BasicElementTypes {
    const val WHITE_SPACE_ID: Int = NO_ID + 1
    const val BAD_CHARACTER_ID: Int = WHITE_SPACE_ID + 1
    const val ERROR_ELEMENT_ID: Int = BAD_CHARACTER_ID + 1
    /**
     * Remember to update first const in [KtTokens] after adding new tokens here ^^^
     */
}
