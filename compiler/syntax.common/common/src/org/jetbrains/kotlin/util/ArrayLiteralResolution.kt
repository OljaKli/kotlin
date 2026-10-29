/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.util

/**
 * Declaration is only relevant for old (pre-CL) resolution of array literals for annotations.
 *
 * A special case of `OnlyForDefaultLanguageFeatureDisabled` for `LanguageFeature.CollectionLiteralsBasedAnnotationResolution`.
 *
 * It's declared here rather than next to `OnlyForDefaultLanguageFeatureDisabled`
 * because it's used by [org.jetbrains.kotlin.KtFakeSourceElementKind].
 */
@RequiresOptIn
annotation class ArrayLiteralResolution
