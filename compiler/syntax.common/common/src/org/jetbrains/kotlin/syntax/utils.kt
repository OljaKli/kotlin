/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.syntax

/**
 * A multiplatform counterpart of `java.util.Objects.hash`.
 */
internal object Objects {
    fun hash(vararg elements: Any?): Int {
        var result = 1

        for (element in elements) {
            result = 31 * result + element.hashCode()
        }

        return result
    }
}
