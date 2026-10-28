/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kmp.parser

import com.intellij.platform.syntax.SyntaxElementType
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.kotlin.kmp.lexer.KtTokens
import org.jetbrains.kotlin.kmp.utils.SyntaxElementTypesWithIds
import org.jetbrains.kotlin.syntax.KtNodeTypes.ANNOTATED_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ANNOTATION_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ANNOTATION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ANNOTATION_TARGET_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ARRAY_ACCESS_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.BACKING_FIELD_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.BINARY_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.BINARY_WITH_TYPE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.BLOCK_CODE_FRAGMENT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.BLOCK_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.BODY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.BOOLEAN_CONSTANT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.BREAK_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CALLABLE_REFERENCE_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CALL_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CATCH_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CHARACTER_CONSTANT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CLASS_BODY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CLASS_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CLASS_INITIALIZER_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CLASS_LITERAL_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.COLLECTION_LITERAL_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.COMPANION_BLOCK_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONDITION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONSTRUCTOR_CALLEE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONSTRUCTOR_DELEGATION_CALL_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONSTRUCTOR_DELEGATION_REFERENCE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONTEXT_PARAMETER_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONTEXT_RECEIVER_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONTINUE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONTRACT_EFFECT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.CONTRACT_EFFECT_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.DELEGATED_SUPER_TYPE_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.DESTRUCTURING_DECLARATION_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.DESTRUCTURING_DECLARATION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.DOT_QUALIFIED_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.DO_WHILE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.DYNAMIC_TYPE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ELSE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ENUM_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ENUM_ENTRY_SUPERCLASS_REFERENCE_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ERROR_SAFE_ACCESS_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.ESCAPE_STRING_TEMPLATE_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.EXPRESSION_CODE_FRAGMENT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FILE_ANNOTATION_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FILE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FINALLY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FLOAT_CONSTANT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FOR_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FUNCTION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FUNCTION_LITERAL_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FUNCTION_TYPE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.FUNCTION_TYPE_RECEIVER_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.IF_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.IMPORT_ALIAS_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.IMPORT_DIRECTIVE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.IMPORT_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.INDICES_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.INITIALIZER_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.INTEGER_CONSTANT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.INTERSECTION_TYPE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.IS_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.LABELED_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.LABEL_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.LABEL_QUALIFIER_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.LAMBDA_ARGUMENT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.LAMBDA_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.LITERAL_STRING_TEMPLATE_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.LONG_STRING_TEMPLATE_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.LOOP_RANGE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.MODIFIER_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.NULLABLE_TYPE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.NULL_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.OBJECT_DECLARATION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.OBJECT_LITERAL_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.OPERATION_REFERENCE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.PACKAGE_DIRECTIVE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.PARENTHESIZED_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.POSTFIX_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.PREFIX_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.PRIMARY_CONSTRUCTOR_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.PROPERTY_ACCESSOR_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.PROPERTY_DELEGATE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.PROPERTY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.REFERENCE_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.RETURN_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SAFE_ACCESS_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SCRIPT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SCRIPT_INITIALIZER_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SECONDARY_CONSTRUCTOR_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SHORT_STRING_TEMPLATE_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.STRING_INTERPOLATION_PREFIX_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.STRING_TEMPLATE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SUPER_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SUPER_TYPE_CALL_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SUPER_TYPE_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.SUPER_TYPE_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.THEN_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.THIS_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.THROW_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPEALIAS_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPE_ARGUMENT_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPE_CODE_FRAGMENT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPE_CONSTRAINT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPE_CONSTRAINT_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPE_PARAMETER_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPE_PARAMETER_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPE_PROJECTION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.TYPE_REFERENCE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.UNION_TYPE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.USER_TYPE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.VALUE_ARGUMENT_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.VALUE_ARGUMENT_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.VALUE_ARGUMENT_NAME_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.VALUE_PARAMETER_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.VALUE_PARAMETER_LIST_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.WHEN_CONDITION_EXPRESSION_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.WHEN_CONDITION_IN_RANGE_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.WHEN_CONDITION_IS_PATTERN_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.WHEN_ENTRY_GUARD_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.WHEN_ENTRY_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.WHEN_ID
import org.jetbrains.kotlin.syntax.KtNodeTypes.WHILE_ID

@ApiStatus.Experimental
object KtNodeTypes : SyntaxElementTypesWithIds() {
    val FILE: SyntaxElementType = register(FILE_ID, "kotlin.FILE")

    // Start of stub element types

    val CLASS: SyntaxElementType = register(CLASS_ID, "CLASS")
    val FUN: SyntaxElementType = register(FUNCTION_ID, "FUNCTION")
    val PROPERTY: SyntaxElementType = register(PROPERTY_ID, "PROPERTY")
    val PROPERTY_ACCESSOR: SyntaxElementType = register(PROPERTY_ACCESSOR_ID, "PROPERTY_ACCESSOR")
    val BACKING_FIELD: SyntaxElementType = register(BACKING_FIELD_ID, "BACKING_FIELD")
    val TYPEALIAS: SyntaxElementType = register(TYPEALIAS_ID, "TYPEALIAS")

    val ENUM_ENTRY: SyntaxElementType = register(ENUM_ENTRY_ID, "ENUM_ENTRY")
    val OBJECT_DECLARATION: SyntaxElementType = register(OBJECT_DECLARATION_ID, "OBJECT_DECLARATION")
    val CLASS_INITIALIZER: SyntaxElementType = register(CLASS_INITIALIZER_ID, "CLASS_INITIALIZER")
    val SECONDARY_CONSTRUCTOR: SyntaxElementType = register(SECONDARY_CONSTRUCTOR_ID, "SECONDARY_CONSTRUCTOR")
    val PRIMARY_CONSTRUCTOR: SyntaxElementType = register(PRIMARY_CONSTRUCTOR_ID, "PRIMARY_CONSTRUCTOR")

    val VALUE_PARAMETER: SyntaxElementType = register(VALUE_PARAMETER_ID, "VALUE_PARAMETER")
    val VALUE_PARAMETER_LIST: SyntaxElementType = register(VALUE_PARAMETER_LIST_ID, "VALUE_PARAMETER_LIST")

    val TYPE_PARAMETER: SyntaxElementType = register(TYPE_PARAMETER_ID, "TYPE_PARAMETER")
    val TYPE_PARAMETER_LIST: SyntaxElementType = register(TYPE_PARAMETER_LIST_ID, "TYPE_PARAMETER_LIST")

    val ANNOTATION_ENTRY: SyntaxElementType = register(ANNOTATION_ENTRY_ID, "ANNOTATION_ENTRY")
    val ANNOTATION: SyntaxElementType = register(ANNOTATION_ID, "ANNOTATION")

    val ANNOTATION_TARGET: SyntaxElementType = register(ANNOTATION_TARGET_ID, "ANNOTATION_TARGET")

    val CLASS_BODY: SyntaxElementType = register(CLASS_BODY_ID, "CLASS_BODY")

    val COMPANION_BLOCK: SyntaxElementType = register(COMPANION_BLOCK_ID, "COMPANION_BLOCK")

    val IMPORT_LIST: SyntaxElementType = register(IMPORT_LIST_ID, "IMPORT_LIST")

    val FILE_ANNOTATION_LIST: SyntaxElementType = register(FILE_ANNOTATION_LIST_ID, "FILE_ANNOTATION_LIST")

    val IMPORT_DIRECTIVE: SyntaxElementType = register(IMPORT_DIRECTIVE_ID, "IMPORT_DIRECTIVE")

    val IMPORT_ALIAS: SyntaxElementType = register(IMPORT_ALIAS_ID, "IMPORT_ALIAS")

    val PACKAGE_DIRECTIVE: SyntaxElementType = register(PACKAGE_DIRECTIVE_ID, "PACKAGE_DIRECTIVE")

    val MODIFIER_LIST: SyntaxElementType = register(MODIFIER_LIST_ID, "MODIFIER_LIST")

    val TYPE_CONSTRAINT_LIST: SyntaxElementType = register(TYPE_CONSTRAINT_LIST_ID, "TYPE_CONSTRAINT_LIST")

    val TYPE_CONSTRAINT: SyntaxElementType = register(TYPE_CONSTRAINT_ID, "TYPE_CONSTRAINT")

    val NULLABLE_TYPE: SyntaxElementType = register(NULLABLE_TYPE_ID, "NULLABLE_TYPE")

    val INTERSECTION_TYPE: SyntaxElementType = register(INTERSECTION_TYPE_ID, "INTERSECTION_TYPE")
    val UNION_TYPE: SyntaxElementType = register(UNION_TYPE_ID, "UNION_TYPE")

    val TYPE_REFERENCE: SyntaxElementType = register(TYPE_REFERENCE_ID, "TYPE_REFERENCE")

    val USER_TYPE: SyntaxElementType = register(USER_TYPE_ID, "USER_TYPE")
    val DYNAMIC_TYPE: SyntaxElementType = register(DYNAMIC_TYPE_ID, "DYNAMIC_TYPE")

    val FUNCTION_TYPE: SyntaxElementType = register(FUNCTION_TYPE_ID, "FUNCTION_TYPE")

    val TYPE_CODE_FRAGMENT: SyntaxElementType = register(TYPE_CODE_FRAGMENT_ID, "kotlin.TYPE_CODE_FRAGMENT")
    val EXPRESSION_CODE_FRAGMENT: SyntaxElementType = register(EXPRESSION_CODE_FRAGMENT_ID, "kotlin.EXPRESSION_CODE_FRAGMENT")
    val BLOCK_CODE_FRAGMENT: SyntaxElementType = register(BLOCK_CODE_FRAGMENT_ID, "kotlin.BLOCK_CODE_FRAGMENT")

    val TYPE_PROJECTION: SyntaxElementType = register(TYPE_PROJECTION_ID, "TYPE_PROJECTION")

    val FUNCTION_TYPE_RECEIVER: SyntaxElementType = register(FUNCTION_TYPE_RECEIVER_ID, "FUNCTION_TYPE_RECEIVER")

    val REFERENCE_EXPRESSION: SyntaxElementType = register(REFERENCE_EXPRESSION_ID, "REFERENCE_EXPRESSION")
    val DOT_QUALIFIED_EXPRESSION: SyntaxElementType = register(DOT_QUALIFIED_EXPRESSION_ID, "DOT_QUALIFIED_EXPRESSION")
    val ENUM_ENTRY_SUPERCLASS_REFERENCE_EXPRESSION: SyntaxElementType =
        register(ENUM_ENTRY_SUPERCLASS_REFERENCE_EXPRESSION_ID, "ENUM_ENTRY_SUPERCLASS_REFERENCE_EXPRESSION")
    val TYPE_ARGUMENT_LIST: SyntaxElementType = register(TYPE_ARGUMENT_LIST_ID, "TYPE_ARGUMENT_LIST")

    val VALUE_ARGUMENT_LIST: SyntaxElementType = register(VALUE_ARGUMENT_LIST_ID, "VALUE_ARGUMENT_LIST")

    val VALUE_ARGUMENT: SyntaxElementType = register(VALUE_ARGUMENT_ID, "VALUE_ARGUMENT")

    val CONTRACT_EFFECT_LIST: SyntaxElementType = register(CONTRACT_EFFECT_LIST_ID, "CONTRACT_EFFECT_LIST")

    val CONTRACT_EFFECT: SyntaxElementType = register(CONTRACT_EFFECT_ID, "CONTRACT_EFFECT")

    val LAMBDA_ARGUMENT: SyntaxElementType = register(LAMBDA_ARGUMENT_ID, "LAMBDA_ARGUMENT")

    val VALUE_ARGUMENT_NAME: SyntaxElementType = register(VALUE_ARGUMENT_NAME_ID, "VALUE_ARGUMENT_NAME")

    val SUPER_TYPE_LIST: SyntaxElementType = register(SUPER_TYPE_LIST_ID, "SUPER_TYPE_LIST")

    val INITIALIZER_LIST: SyntaxElementType = register(INITIALIZER_LIST_ID, "INITIALIZER_LIST")

    val DELEGATED_SUPER_TYPE_ENTRY: SyntaxElementType = register(DELEGATED_SUPER_TYPE_ENTRY_ID, "DELEGATED_SUPER_TYPE_ENTRY")

    val SUPER_TYPE_CALL_ENTRY: SyntaxElementType = register(SUPER_TYPE_CALL_ENTRY_ID, "SUPER_TYPE_CALL_ENTRY")
    val SUPER_TYPE_ENTRY: SyntaxElementType = register(SUPER_TYPE_ENTRY_ID, "SUPER_TYPE_ENTRY")
    val CONSTRUCTOR_CALLEE: SyntaxElementType = register(CONSTRUCTOR_CALLEE_ID, "CONSTRUCTOR_CALLEE")

    val CONTEXT_RECEIVER: SyntaxElementType = register(CONTEXT_RECEIVER_ID, "CONTEXT_RECEIVER")
    val CONTEXT_PARAMETER_LIST: SyntaxElementType = register(CONTEXT_PARAMETER_LIST_ID, "CONTEXT_PARAMETER_LIST")

    val NULL: SyntaxElementType = register(NULL_ID, "NULL")
    val BOOLEAN_CONSTANT: SyntaxElementType = register(BOOLEAN_CONSTANT_ID, "BOOLEAN_CONSTANT")
    val FLOAT_CONSTANT: SyntaxElementType = register(FLOAT_CONSTANT_ID, "FLOAT_CONSTANT")
    val CHARACTER_CONSTANT: SyntaxElementType = register(CHARACTER_CONSTANT_ID, "CHARACTER_CONSTANT")
    val INTEGER_CONSTANT: SyntaxElementType = register(INTEGER_CONSTANT_ID, "INTEGER_CONSTANT")
    val CLASS_LITERAL_EXPRESSION: SyntaxElementType = register(CLASS_LITERAL_EXPRESSION_ID, "CLASS_LITERAL_EXPRESSION")
    val COLLECTION_LITERAL_EXPRESSION: SyntaxElementType = register(COLLECTION_LITERAL_EXPRESSION_ID, "COLLECTION_LITERAL_EXPRESSION")

    val STRING_TEMPLATE: SyntaxElementType = register(STRING_TEMPLATE_ID, "STRING_TEMPLATE")

    val LONG_STRING_TEMPLATE_ENTRY: SyntaxElementType = register(LONG_STRING_TEMPLATE_ENTRY_ID, "LONG_STRING_TEMPLATE_ENTRY")

    val SHORT_STRING_TEMPLATE_ENTRY: SyntaxElementType = register(SHORT_STRING_TEMPLATE_ENTRY_ID, "SHORT_STRING_TEMPLATE_ENTRY")

    val LITERAL_STRING_TEMPLATE_ENTRY: SyntaxElementType = register(LITERAL_STRING_TEMPLATE_ENTRY_ID, "LITERAL_STRING_TEMPLATE_ENTRY")

    val ESCAPE_STRING_TEMPLATE_ENTRY: SyntaxElementType = register(ESCAPE_STRING_TEMPLATE_ENTRY_ID, "ESCAPE_STRING_TEMPLATE_ENTRY")

    val SCRIPT: SyntaxElementType = register(SCRIPT_ID, "SCRIPT")

    val STRING_INTERPOLATION_PREFIX: SyntaxElementType = register(STRING_INTERPOLATION_PREFIX_ID, "STRING_INTERPOLATION_PREFIX")

    // End of Stub element types

    val DESTRUCTURING_DECLARATION: SyntaxElementType = register(DESTRUCTURING_DECLARATION_ID, "DESTRUCTURING_DECLARATION")
    val DESTRUCTURING_DECLARATION_ENTRY: SyntaxElementType =
        register(DESTRUCTURING_DECLARATION_ENTRY_ID, "DESTRUCTURING_DECLARATION_ENTRY")
    val SCRIPT_INITIALIZER: SyntaxElementType = register(SCRIPT_INITIALIZER_ID, "SCRIPT_INITIALIZER")
    val PROPERTY_DELEGATE: SyntaxElementType = register(PROPERTY_DELEGATE_ID, "PROPERTY_DELEGATE")
    val CONSTRUCTOR_DELEGATION_CALL: SyntaxElementType = register(CONSTRUCTOR_DELEGATION_CALL_ID, "CONSTRUCTOR_DELEGATION_CALL")
    val CONSTRUCTOR_DELEGATION_REFERENCE: SyntaxElementType =
        register(CONSTRUCTOR_DELEGATION_REFERENCE_ID, "CONSTRUCTOR_DELEGATION_REFERENCE")

    val PARENTHESIZED: SyntaxElementType = register(PARENTHESIZED_ID, "PARENTHESIZED")
    val RETURN: SyntaxElementType = register(RETURN_ID, "RETURN")
    val THROW: SyntaxElementType = register(THROW_ID, "THROW")
    val CONTINUE: SyntaxElementType = register(CONTINUE_ID, "CONTINUE")
    val BREAK: SyntaxElementType = register(BREAK_ID, "BREAK")
    val IF: SyntaxElementType = register(IF_ID, "IF")
    val CONDITION: SyntaxElementType = register(CONDITION_ID, "CONDITION")
    val THEN: SyntaxElementType = register(THEN_ID, "THEN")
    val ELSE: SyntaxElementType = register(ELSE_ID, "ELSE")
    val TRY: SyntaxElementType = register(TRY_ID, "TRY")
    val CATCH: SyntaxElementType = register(CATCH_ID, "CATCH")
    val FINALLY: SyntaxElementType = register(FINALLY_ID, "FINALLY")
    val FOR: SyntaxElementType = register(FOR_ID, "FOR")
    val WHILE: SyntaxElementType = register(WHILE_ID, "WHILE")
    val DO_WHILE: SyntaxElementType = register(DO_WHILE_ID, "DO_WHILE")
    val LOOP_RANGE: SyntaxElementType = register(LOOP_RANGE_ID, "LOOP_RANGE")
    val BODY: SyntaxElementType = register(BODY_ID, "BODY")

    val BLOCK: SyntaxElementType = register(BLOCK_ID, "BLOCK")
    val LAMBDA_EXPRESSION: SyntaxElementType = register(LAMBDA_EXPRESSION_ID, "LAMBDA_EXPRESSION")
    val FUNCTION_LITERAL: SyntaxElementType = register(FUNCTION_LITERAL_ID, "FUNCTION_LITERAL")
    val ANNOTATED_EXPRESSION: SyntaxElementType = register(ANNOTATED_EXPRESSION_ID, "ANNOTATED_EXPRESSION")
    val OPERATION_REFERENCE: SyntaxElementType = register(OPERATION_REFERENCE_ID, "OPERATION_REFERENCE")
    val LABEL: SyntaxElementType = register(LABEL_ID, "LABEL")
    val LABEL_QUALIFIER: SyntaxElementType = register(LABEL_QUALIFIER_ID, "LABEL_QUALIFIER")

    val THIS_EXPRESSION: SyntaxElementType = register(THIS_EXPRESSION_ID, "THIS_EXPRESSION")
    val SUPER_EXPRESSION: SyntaxElementType = register(SUPER_EXPRESSION_ID, "SUPER_EXPRESSION")
    val BINARY_EXPRESSION: SyntaxElementType = register(BINARY_EXPRESSION_ID, "BINARY_EXPRESSION")
    val BINARY_WITH_TYPE: SyntaxElementType = register(BINARY_WITH_TYPE_ID, "BINARY_WITH_TYPE")
    val IS_EXPRESSION: SyntaxElementType = register(IS_EXPRESSION_ID, "IS_EXPRESSION")
    val PREFIX_EXPRESSION: SyntaxElementType = register(PREFIX_EXPRESSION_ID, "PREFIX_EXPRESSION")
    val POSTFIX_EXPRESSION: SyntaxElementType = register(POSTFIX_EXPRESSION_ID, "POSTFIX_EXPRESSION")
    val LABELED_EXPRESSION: SyntaxElementType = register(LABELED_EXPRESSION_ID, "LABELED_EXPRESSION")
    val CALL_EXPRESSION: SyntaxElementType = register(CALL_EXPRESSION_ID, "CALL_EXPRESSION")
    val ARRAY_ACCESS_EXPRESSION: SyntaxElementType = register(ARRAY_ACCESS_EXPRESSION_ID, "ARRAY_ACCESS_EXPRESSION")
    val INDICES: SyntaxElementType = register(INDICES_ID, "INDICES")
    val CALLABLE_REFERENCE_EXPRESSION: SyntaxElementType =
        register(CALLABLE_REFERENCE_EXPRESSION_ID, "CALLABLE_REFERENCE_EXPRESSION")
    val SAFE_ACCESS_EXPRESSION: SyntaxElementType = register(SAFE_ACCESS_EXPRESSION_ID, "SAFE_ACCESS_EXPRESSION")
    val ERROR_SAFE_ACCESS_EXPRESSION: SyntaxElementType = register(ERROR_SAFE_ACCESS_EXPRESSION_ID, "ERROR_SAFE_ACCESS_EXPRESSION")

    val OBJECT_LITERAL: SyntaxElementType = register(OBJECT_LITERAL_ID, "OBJECT_LITERAL")
    val WHEN: SyntaxElementType = register(WHEN_ID, "WHEN")
    val WHEN_ENTRY: SyntaxElementType = register(WHEN_ENTRY_ID, "WHEN_ENTRY")
    val WHEN_ENTRY_GUARD: SyntaxElementType = register(WHEN_ENTRY_GUARD_ID, "WHEN_ENTRY_GUARD")
    val WHEN_CONDITION_IN_RANGE: SyntaxElementType = register(WHEN_CONDITION_IN_RANGE_ID, "WHEN_CONDITION_IN_RANGE")
    val WHEN_CONDITION_IS_PATTERN: SyntaxElementType = register(WHEN_CONDITION_IS_PATTERN_ID, "WHEN_CONDITION_IS_PATTERN")
    val WHEN_CONDITION_EXPRESSION: SyntaxElementType = register(WHEN_CONDITION_EXPRESSION_ID, "WHEN_CONDITION_WITH_EXPRESSION")
}
