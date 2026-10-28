/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.syntax

import org.jetbrains.kotlin.syntax.BasicElementTypes.ERROR_ELEMENT_ID

object KtTokens {
    const val DOC_COMMENT_ID: Int = ERROR_ELEMENT_ID + 1

    const val EOF_ID: Int = DOC_COMMENT_ID + 1
    const val RESERVED_ID: Int = EOF_ID + 1
    const val BLOCK_COMMENT_ID: Int = RESERVED_ID + 1
    const val EOL_COMMENT_ID: Int = BLOCK_COMMENT_ID + 1
    const val SHEBANG_COMMENT_ID: Int = EOL_COMMENT_ID + 1

    const val INTEGER_LITERAL_ID: Int = SHEBANG_COMMENT_ID + 1
    const val FLOAT_LITERAL_ID: Int = INTEGER_LITERAL_ID + 1
    const val CHARACTER_LITERAL_ID: Int = FLOAT_LITERAL_ID + 1

    const val CLOSING_QUOTE_ID: Int = CHARACTER_LITERAL_ID + 1
    const val OPEN_QUOTE_ID: Int = CLOSING_QUOTE_ID + 1
    const val REGULAR_STRING_PART_ID: Int = OPEN_QUOTE_ID + 1
    const val ESCAPE_SEQUENCE_ID: Int = REGULAR_STRING_PART_ID + 1
    const val SHORT_TEMPLATE_ENTRY_START_ID: Int = ESCAPE_SEQUENCE_ID + 1
    const val LONG_TEMPLATE_ENTRY_START_ID: Int = SHORT_TEMPLATE_ENTRY_START_ID + 1
    const val LONG_TEMPLATE_ENTRY_END_ID: Int = LONG_TEMPLATE_ENTRY_START_ID + 1
    const val DANGLING_NEWLINE_ID: Int = LONG_TEMPLATE_ENTRY_END_ID + 1

    const val PACKAGE_KEYWORD_ID: Int = DANGLING_NEWLINE_ID + 1
    const val AS_KEYWORD_ID: Int = PACKAGE_KEYWORD_ID + 1
    const val TYPE_ALIAS_KEYWORD_ID: Int = AS_KEYWORD_ID + 1
    const val CLASS_KEYWORD_ID: Int = TYPE_ALIAS_KEYWORD_ID + 1
    const val THIS_KEYWORD_ID: Int = CLASS_KEYWORD_ID + 1
    const val SUPER_KEYWORD_ID: Int = THIS_KEYWORD_ID + 1
    const val VAL_KEYWORD_ID: Int = SUPER_KEYWORD_ID + 1
    const val VAR_KEYWORD_ID: Int = VAL_KEYWORD_ID + 1
    const val FUN_MODIFIER_ID: Int = VAR_KEYWORD_ID + 1
    const val FOR_KEYWORD_ID: Int = FUN_MODIFIER_ID + 1
    const val NULL_KEYWORD_ID: Int = FOR_KEYWORD_ID + 1
    const val TRUE_KEYWORD_ID: Int = NULL_KEYWORD_ID + 1
    const val FALSE_KEYWORD_ID: Int = TRUE_KEYWORD_ID + 1
    const val IS_KEYWORD_ID: Int = FALSE_KEYWORD_ID + 1
    const val IN_MODIFIER_ID: Int = IS_KEYWORD_ID + 1
    const val THROW_KEYWORD_ID: Int = IN_MODIFIER_ID + 1
    const val RETURN_KEYWORD_ID: Int = THROW_KEYWORD_ID + 1
    const val BREAK_KEYWORD_ID: Int = RETURN_KEYWORD_ID + 1
    const val CONTINUE_KEYWORD_ID: Int = BREAK_KEYWORD_ID + 1
    const val OBJECT_KEYWORD_ID: Int = CONTINUE_KEYWORD_ID + 1
    const val IF_KEYWORD_ID: Int = OBJECT_KEYWORD_ID + 1
    const val TRY_KEYWORD_ID: Int = IF_KEYWORD_ID + 1
    const val ELSE_KEYWORD_ID: Int = TRY_KEYWORD_ID + 1
    const val WHILE_KEYWORD_ID: Int = ELSE_KEYWORD_ID + 1
    const val DO_KEYWORD_ID: Int = WHILE_KEYWORD_ID + 1
    const val WHEN_KEYWORD_ID: Int = DO_KEYWORD_ID + 1
    const val INTERFACE_KEYWORD_ID: Int = WHEN_KEYWORD_ID + 1

    const val TYPEOF_KEYWORD_ID: Int = INTERFACE_KEYWORD_ID + 1

    const val AS_SAFE_ID: Int = TYPEOF_KEYWORD_ID + 1

    const val IDENTIFIER_ID: Int = AS_SAFE_ID + 1
    const val FIELD_IDENTIFIER_ID: Int = IDENTIFIER_ID + 1
    const val LBRACKET_ID: Int = FIELD_IDENTIFIER_ID + 1
    const val RBRACKET_ID: Int = LBRACKET_ID + 1
    const val LBRACE_ID: Int = RBRACKET_ID + 1
    const val RBRACE_ID: Int = LBRACE_ID + 1
    const val LPAR_ID: Int = RBRACE_ID + 1
    const val RPAR_ID: Int = LPAR_ID + 1
    const val DOT_ID: Int = RPAR_ID + 1
    const val PLUSPLUS_ID: Int = DOT_ID + 1
    const val MINUSMINUS_ID: Int = PLUSPLUS_ID + 1
    const val MUL_ID: Int = MINUSMINUS_ID + 1
    const val PLUS_ID: Int = MUL_ID + 1
    const val MINUS_ID: Int = PLUS_ID + 1
    const val EXCL_ID: Int = MINUS_ID + 1
    const val DIV_ID: Int = EXCL_ID + 1
    const val PERC_ID: Int = DIV_ID + 1
    const val LT_ID: Int = PERC_ID + 1
    const val GT_ID: Int = LT_ID + 1
    const val LTEQ_ID: Int = GT_ID + 1
    const val GTEQ_ID: Int = LTEQ_ID + 1
    const val EQEQEQ_ID: Int = GTEQ_ID + 1
    const val ARROW_ID: Int = EQEQEQ_ID + 1
    const val DOUBLE_ARROW_ID: Int = ARROW_ID + 1
    const val EXCLEQEQEQ_ID: Int = DOUBLE_ARROW_ID + 1
    const val EQEQ_ID: Int = EXCLEQEQEQ_ID + 1
    const val EXCLEQ_ID: Int = EQEQ_ID + 1
    const val EXCLEXCL_ID: Int = EXCLEQ_ID + 1
    const val ANDAND_ID: Int = EXCLEXCL_ID + 1
    const val AND_ID: Int = ANDAND_ID + 1
    const val OROR_ID: Int = AND_ID + 1
    const val SAFE_ACCESS_ID: Int = OROR_ID + 1
    const val ELVIS_ID: Int = SAFE_ACCESS_ID + 1
    const val QUEST_ID: Int = ELVIS_ID + 1
    const val COLONCOLON_ID: Int = QUEST_ID + 1
    const val COLON_ID: Int = COLONCOLON_ID + 1
    const val SEMICOLON_ID: Int = COLON_ID + 1
    const val DOUBLE_SEMICOLON_ID: Int = SEMICOLON_ID + 1
    const val RANGE_ID: Int = DOUBLE_SEMICOLON_ID + 1
    const val RANGE_UNTIL_ID: Int = RANGE_ID + 1
    const val EQ_ID: Int = RANGE_UNTIL_ID + 1
    const val MULTEQ_ID: Int = EQ_ID + 1
    const val DIVEQ_ID: Int = MULTEQ_ID + 1
    const val PERCEQ_ID: Int = DIVEQ_ID + 1
    const val PLUSEQ_ID: Int = PERCEQ_ID + 1
    const val MINUSEQ_ID: Int = PLUSEQ_ID + 1
    const val NOT_IN_ID: Int = MINUSEQ_ID + 1
    const val NOT_IS_ID: Int = NOT_IN_ID + 1
    const val HASH_ID: Int = NOT_IS_ID + 1
    const val AT_ID: Int = HASH_ID + 1
    const val COMMA_ID: Int = AT_ID + 1
    const val EOL_OR_SEMICOLON_ID: Int = COMMA_ID + 1
    const val FILE_KEYWORD_ID: Int = EOL_OR_SEMICOLON_ID + 1
    const val FIELD_KEYWORD_ID: Int = FILE_KEYWORD_ID + 1
    const val PROPERTY_KEYWORD_ID: Int = FIELD_KEYWORD_ID + 1
    const val RECEIVER_KEYWORD_ID: Int = PROPERTY_KEYWORD_ID + 1
    const val PARAM_KEYWORD_ID: Int = RECEIVER_KEYWORD_ID + 1
    const val SETPARAM_KEYWORD_ID: Int = PARAM_KEYWORD_ID + 1
    const val DELEGATE_KEYWORD_ID: Int = SETPARAM_KEYWORD_ID + 1
    const val IMPORT_KEYWORD_ID: Int = DELEGATE_KEYWORD_ID + 1
    const val WHERE_KEYWORD_ID: Int = IMPORT_KEYWORD_ID + 1
    const val BY_KEYWORD_ID: Int = WHERE_KEYWORD_ID + 1
    const val GET_KEYWORD_ID: Int = BY_KEYWORD_ID + 1
    const val SET_KEYWORD_ID: Int = GET_KEYWORD_ID + 1
    const val CONSTRUCTOR_KEYWORD_ID: Int = SET_KEYWORD_ID + 1
    const val INIT_KEYWORD_ID: Int = CONSTRUCTOR_KEYWORD_ID + 1
    const val CONTEXT_KEYWORD_ID: Int = INIT_KEYWORD_ID + 1
    const val CATCH_KEYWORD_ID: Int = CONTEXT_KEYWORD_ID + 1
    const val DYNAMIC_KEYWORD_ID: Int = CATCH_KEYWORD_ID + 1
    const val FINALLY_KEYWORD_ID: Int = DYNAMIC_KEYWORD_ID + 1

    const val ABSTRACT_MODIFIER_ID: Int = FINALLY_KEYWORD_ID + 1
    const val ENUM_MODIFIER_ID: Int = ABSTRACT_MODIFIER_ID + 1
    const val CONTRACT_MODIFIER_ID: Int = ENUM_MODIFIER_ID + 1
    const val OPEN_MODIFIER_ID: Int = CONTRACT_MODIFIER_ID + 1
    const val INNER_MODIFIER_ID: Int = OPEN_MODIFIER_ID + 1
    const val OVERRIDE_MODIFIER_ID: Int = INNER_MODIFIER_ID + 1
    const val PRIVATE_MODIFIER_ID: Int = OVERRIDE_MODIFIER_ID + 1
    const val PUBLIC_MODIFIER_ID: Int = PRIVATE_MODIFIER_ID + 1
    const val INTERNAL_MODIFIER_ID: Int = PUBLIC_MODIFIER_ID + 1
    const val PROTECTED_MODIFIER_ID: Int = INTERNAL_MODIFIER_ID + 1
    const val OUT_MODIFIER_ID: Int = PROTECTED_MODIFIER_ID + 1
    const val VARARG_MODIFIER_ID: Int = OUT_MODIFIER_ID + 1
    const val REIFIED_MODIFIER_ID: Int = VARARG_MODIFIER_ID + 1
    const val COMPANION_MODIFIER_ID: Int = REIFIED_MODIFIER_ID + 1
    const val SEALED_MODIFIER_ID: Int = COMPANION_MODIFIER_ID + 1
    const val FINAL_MODIFIER_ID: Int = SEALED_MODIFIER_ID + 1
    const val LATEINIT_MODIFIER_ID: Int = FINAL_MODIFIER_ID + 1
    const val DATA_MODIFIER_ID: Int = LATEINIT_MODIFIER_ID + 1
    const val VALUE_MODIFIER_ID: Int = DATA_MODIFIER_ID + 1
    const val INLINE_MODIFIER_ID: Int = VALUE_MODIFIER_ID + 1
    const val NOINLINE_MODIFIER_ID: Int = INLINE_MODIFIER_ID + 1
    const val TAILREC_MODIFIER_ID: Int = NOINLINE_MODIFIER_ID + 1
    const val EXTERNAL_MODIFIER_ID: Int = TAILREC_MODIFIER_ID + 1
    const val ANNOTATION_MODIFIER_ID: Int = EXTERNAL_MODIFIER_ID + 1
    const val CROSSINLINE_MODIFIER_ID: Int = ANNOTATION_MODIFIER_ID + 1
    const val OPERATOR_MODIFIER_ID: Int = CROSSINLINE_MODIFIER_ID + 1
    const val INFIX_MODIFIER_ID: Int = OPERATOR_MODIFIER_ID + 1
    const val CONST_MODIFIER_ID: Int = INFIX_MODIFIER_ID + 1
    const val SUSPEND_MODIFIER_ID: Int = CONST_MODIFIER_ID + 1
    const val EXPECT_MODIFIER_ID: Int = SUSPEND_MODIFIER_ID + 1
    const val ACTUAL_MODIFIER_ID: Int = EXPECT_MODIFIER_ID + 1
    const val INTERPOLATION_PREFIX_ID: Int = ACTUAL_MODIFIER_ID + 1
    const val ALL_KEYWORD_ID: Int = INTERPOLATION_PREFIX_ID + 1
    const val ERROR_MODIFIER_ID: Int = ALL_KEYWORD_ID + 1
    const val OR_ID: Int = ERROR_MODIFIER_ID + 1
    const val ERROR_SAFE_ACCESS_ID: Int = OR_ID + 1
    /**
     * Remember to update first const in [KtNodeTypes] after adding new tokens here ^^^
     */
}
