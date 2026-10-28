/*
 * Copyright 2010-2025 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kmp.lexer

import com.intellij.platform.syntax.SyntaxElementType
import com.intellij.platform.syntax.SyntaxElementTypeSet
import com.intellij.platform.syntax.asSyntaxElementTypeSet
import com.intellij.platform.syntax.element.SyntaxTokenTypes
import com.intellij.platform.syntax.syntaxElementTypeSetOf
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.kotlin.kmp.utils.SyntaxElementTypesWithIds
import org.jetbrains.kotlin.syntax.KtTokens.ABSTRACT_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.ACTUAL_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.ALL_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.ANDAND_ID
import org.jetbrains.kotlin.syntax.KtTokens.AND_ID
import org.jetbrains.kotlin.syntax.KtTokens.ANNOTATION_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.ARROW_ID
import org.jetbrains.kotlin.syntax.KtTokens.AS_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.AS_SAFE_ID
import org.jetbrains.kotlin.syntax.KtTokens.AT_ID
import org.jetbrains.kotlin.syntax.KtTokens.BLOCK_COMMENT_ID
import org.jetbrains.kotlin.syntax.KtTokens.BREAK_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.BY_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.CATCH_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.CHARACTER_LITERAL_ID
import org.jetbrains.kotlin.syntax.KtTokens.CLASS_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.CLOSING_QUOTE_ID
import org.jetbrains.kotlin.syntax.KtTokens.COLONCOLON_ID
import org.jetbrains.kotlin.syntax.KtTokens.COLON_ID
import org.jetbrains.kotlin.syntax.KtTokens.COMMA_ID
import org.jetbrains.kotlin.syntax.KtTokens.COMPANION_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.CONSTRUCTOR_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.CONST_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.CONTEXT_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.CONTINUE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.CONTRACT_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.CROSSINLINE_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.DANGLING_NEWLINE_ID
import org.jetbrains.kotlin.syntax.KtTokens.DATA_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.DELEGATE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.DIVEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.DIV_ID
import org.jetbrains.kotlin.syntax.KtTokens.DOC_COMMENT_ID
import org.jetbrains.kotlin.syntax.KtTokens.DOT_ID
import org.jetbrains.kotlin.syntax.KtTokens.DOUBLE_ARROW_ID
import org.jetbrains.kotlin.syntax.KtTokens.DOUBLE_SEMICOLON_ID
import org.jetbrains.kotlin.syntax.KtTokens.DO_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.DYNAMIC_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.ELSE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.ELVIS_ID
import org.jetbrains.kotlin.syntax.KtTokens.ENUM_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.EOF_ID
import org.jetbrains.kotlin.syntax.KtTokens.EOL_COMMENT_ID
import org.jetbrains.kotlin.syntax.KtTokens.EOL_OR_SEMICOLON_ID
import org.jetbrains.kotlin.syntax.KtTokens.EQEQEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.EQEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.EQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.ERROR_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.ERROR_SAFE_ACCESS_ID
import org.jetbrains.kotlin.syntax.KtTokens.ESCAPE_SEQUENCE_ID
import org.jetbrains.kotlin.syntax.KtTokens.EXCLEQEQEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.EXCLEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.EXCLEXCL_ID
import org.jetbrains.kotlin.syntax.KtTokens.EXCL_ID
import org.jetbrains.kotlin.syntax.KtTokens.EXPECT_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.EXTERNAL_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.FALSE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.FIELD_IDENTIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.FIELD_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.FILE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.FINALLY_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.FINAL_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.FLOAT_LITERAL_ID
import org.jetbrains.kotlin.syntax.KtTokens.FOR_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.FUN_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.GET_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.GTEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.GT_ID
import org.jetbrains.kotlin.syntax.KtTokens.HASH_ID
import org.jetbrains.kotlin.syntax.KtTokens.IDENTIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.IF_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.IMPORT_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.INFIX_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.INIT_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.INLINE_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.INNER_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.INTEGER_LITERAL_ID
import org.jetbrains.kotlin.syntax.KtTokens.INTERFACE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.INTERNAL_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.INTERPOLATION_PREFIX_ID
import org.jetbrains.kotlin.syntax.KtTokens.IN_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.IS_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.LATEINIT_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.LBRACE_ID
import org.jetbrains.kotlin.syntax.KtTokens.LBRACKET_ID
import org.jetbrains.kotlin.syntax.KtTokens.LONG_TEMPLATE_ENTRY_END_ID
import org.jetbrains.kotlin.syntax.KtTokens.LONG_TEMPLATE_ENTRY_START_ID
import org.jetbrains.kotlin.syntax.KtTokens.LPAR_ID
import org.jetbrains.kotlin.syntax.KtTokens.LTEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.LT_ID
import org.jetbrains.kotlin.syntax.KtTokens.MINUSEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.MINUSMINUS_ID
import org.jetbrains.kotlin.syntax.KtTokens.MINUS_ID
import org.jetbrains.kotlin.syntax.KtTokens.MULTEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.MUL_ID
import org.jetbrains.kotlin.syntax.KtTokens.NOINLINE_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.NOT_IN_ID
import org.jetbrains.kotlin.syntax.KtTokens.NOT_IS_ID
import org.jetbrains.kotlin.syntax.KtTokens.NULL_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.OBJECT_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.OPEN_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.OPEN_QUOTE_ID
import org.jetbrains.kotlin.syntax.KtTokens.OPERATOR_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.OROR_ID
import org.jetbrains.kotlin.syntax.KtTokens.OR_ID
import org.jetbrains.kotlin.syntax.KtTokens.OUT_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.OVERRIDE_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.PACKAGE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.PARAM_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.PERCEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.PERC_ID
import org.jetbrains.kotlin.syntax.KtTokens.PLUSEQ_ID
import org.jetbrains.kotlin.syntax.KtTokens.PLUSPLUS_ID
import org.jetbrains.kotlin.syntax.KtTokens.PLUS_ID
import org.jetbrains.kotlin.syntax.KtTokens.PRIVATE_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.PROPERTY_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.PROTECTED_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.PUBLIC_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.QUEST_ID
import org.jetbrains.kotlin.syntax.KtTokens.RANGE_ID
import org.jetbrains.kotlin.syntax.KtTokens.RANGE_UNTIL_ID
import org.jetbrains.kotlin.syntax.KtTokens.RBRACE_ID
import org.jetbrains.kotlin.syntax.KtTokens.RBRACKET_ID
import org.jetbrains.kotlin.syntax.KtTokens.RECEIVER_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.REGULAR_STRING_PART_ID
import org.jetbrains.kotlin.syntax.KtTokens.REIFIED_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.RESERVED_ID
import org.jetbrains.kotlin.syntax.KtTokens.RETURN_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.RPAR_ID
import org.jetbrains.kotlin.syntax.KtTokens.SAFE_ACCESS_ID
import org.jetbrains.kotlin.syntax.KtTokens.SEALED_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.SEMICOLON_ID
import org.jetbrains.kotlin.syntax.KtTokens.SETPARAM_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.SET_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.SHEBANG_COMMENT_ID
import org.jetbrains.kotlin.syntax.KtTokens.SHORT_TEMPLATE_ENTRY_START_ID
import org.jetbrains.kotlin.syntax.KtTokens.SUPER_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.SUSPEND_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.TAILREC_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.THIS_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.THROW_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.TRUE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.TRY_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.TYPEOF_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.TYPE_ALIAS_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.VALUE_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.VAL_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.VARARG_MODIFIER_ID
import org.jetbrains.kotlin.syntax.KtTokens.VAR_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.WHEN_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.WHERE_KEYWORD_ID
import org.jetbrains.kotlin.syntax.KtTokens.WHILE_KEYWORD_ID

@ApiStatus.Experimental
object KtTokens : SyntaxElementTypesWithIds() {
    private val softKeywordsAndModifiers: MutableSet<SyntaxElementType> = mutableSetOf()
    private val hardKeywordsAndModifiers: MutableSet<SyntaxElementType> = mutableSetOf()
    private val allModifiers: MutableSet<SyntaxElementType> = mutableSetOf()

    val EOF: SyntaxElementType = register(EOF_ID, "EOF")

    val RESERVED: SyntaxElementType = register(RESERVED_ID, "RESERVED")

    val BLOCK_COMMENT: SyntaxElementType = register(BLOCK_COMMENT_ID, "BLOCK_COMMENT")
    val EOL_COMMENT: SyntaxElementType = register(EOL_COMMENT_ID, "EOL_COMMENT")
    val SHEBANG_COMMENT: SyntaxElementType = register(SHEBANG_COMMENT_ID, "SHEBANG_COMMENT")

    val DOC_COMMENT: SyntaxElementType = register(DOC_COMMENT_ID, "KDoc")

    val WHITE_SPACE: SyntaxElementType = SyntaxTokenTypes.WHITE_SPACE

    val INTEGER_LITERAL: SyntaxElementType = register(INTEGER_LITERAL_ID, "INTEGER_LITERAL")
    val FLOAT_LITERAL: SyntaxElementType = register(FLOAT_LITERAL_ID, "FLOAT_CONSTANT")
    val CHARACTER_LITERAL: SyntaxElementType = register(CHARACTER_LITERAL_ID, "CHARACTER_LITERAL")

    val INTERPOLATION_PREFIX: SyntaxElementType = register(INTERPOLATION_PREFIX_ID, "INTERPOLATION_PREFIX")
    val CLOSING_QUOTE: SyntaxElementType = register(CLOSING_QUOTE_ID, "CLOSING_QUOTE")
    val OPEN_QUOTE: SyntaxElementType = register(OPEN_QUOTE_ID, "OPEN_QUOTE")
    val REGULAR_STRING_PART: SyntaxElementType = register(REGULAR_STRING_PART_ID, "REGULAR_STRING_PART")
    val ESCAPE_SEQUENCE: SyntaxElementType = register(ESCAPE_SEQUENCE_ID, "ESCAPE_SEQUENCE")
    val SHORT_TEMPLATE_ENTRY_START: SyntaxElementType = register(SHORT_TEMPLATE_ENTRY_START_ID, "SHORT_TEMPLATE_ENTRY_START")
    val LONG_TEMPLATE_ENTRY_START: SyntaxElementType = register(LONG_TEMPLATE_ENTRY_START_ID, "LONG_TEMPLATE_ENTRY_START")
    val LONG_TEMPLATE_ENTRY_END: SyntaxElementType = register(LONG_TEMPLATE_ENTRY_END_ID, "LONG_TEMPLATE_ENTRY_END")
    val DANGLING_NEWLINE: SyntaxElementType = register(DANGLING_NEWLINE_ID, "DANGLING_NEWLINE")

    val PACKAGE_KEYWORD: SyntaxElementType = registerToken(PACKAGE_KEYWORD_ID, "package", soft = false, modifier = false)
    val AS_KEYWORD: SyntaxElementType = registerToken(AS_KEYWORD_ID, "as", soft = false, modifier = false)
    val TYPE_ALIAS_KEYWORD: SyntaxElementType = registerToken(TYPE_ALIAS_KEYWORD_ID, "typealias", soft = false, modifier = false)
    val CLASS_KEYWORD: SyntaxElementType = registerToken(CLASS_KEYWORD_ID, "class", soft = false, modifier = false)
    val THIS_KEYWORD: SyntaxElementType = registerToken(THIS_KEYWORD_ID, "this", soft = false, modifier = false)
    val SUPER_KEYWORD: SyntaxElementType = registerToken(SUPER_KEYWORD_ID, "super", soft = false, modifier = false)
    val VAL_KEYWORD: SyntaxElementType = registerToken(VAL_KEYWORD_ID, "val", soft = false, modifier = false)
    val VAR_KEYWORD: SyntaxElementType = registerToken(VAR_KEYWORD_ID, "var", soft = false, modifier = false)

    val FUN_MODIFIER: SyntaxElementType = registerToken(FUN_MODIFIER_ID, "fun", soft = false, modifier = true)

    val FOR_KEYWORD: SyntaxElementType = registerToken(FOR_KEYWORD_ID, "for", soft = false, modifier = false)
    val NULL_KEYWORD: SyntaxElementType = registerToken(NULL_KEYWORD_ID, "null", soft = false, modifier = false)
    val TRUE_KEYWORD: SyntaxElementType = registerToken(TRUE_KEYWORD_ID, "true", soft = false, modifier = false)
    val FALSE_KEYWORD: SyntaxElementType = registerToken(FALSE_KEYWORD_ID, "false", soft = false, modifier = false)
    val IS_KEYWORD: SyntaxElementType = registerToken(IS_KEYWORD_ID, "is", soft = false, modifier = false)

    val IN_MODIFIER: SyntaxElementType = registerToken(IN_MODIFIER_ID, "in", soft = false, modifier = true)

    val THROW_KEYWORD: SyntaxElementType = registerToken(THROW_KEYWORD_ID, "throw", soft = false, modifier = false)
    val RETURN_KEYWORD: SyntaxElementType = registerToken(RETURN_KEYWORD_ID, "return", soft = false, modifier = false)
    val BREAK_KEYWORD: SyntaxElementType = registerToken(BREAK_KEYWORD_ID, "break", soft = false, modifier = false)
    val CONTINUE_KEYWORD: SyntaxElementType = registerToken(CONTINUE_KEYWORD_ID, "continue", soft = false, modifier = false)
    val OBJECT_KEYWORD: SyntaxElementType = registerToken(OBJECT_KEYWORD_ID, "object", soft = false, modifier = false)
    val IF_KEYWORD: SyntaxElementType = registerToken(IF_KEYWORD_ID, "if", soft = false, modifier = false)
    val TRY_KEYWORD: SyntaxElementType = registerToken(TRY_KEYWORD_ID, "try", soft = false, modifier = false)
    val ELSE_KEYWORD: SyntaxElementType = registerToken(ELSE_KEYWORD_ID, "else", soft = false, modifier = false)
    val WHILE_KEYWORD: SyntaxElementType = registerToken(WHILE_KEYWORD_ID, "while", soft = false, modifier = false)
    val DO_KEYWORD: SyntaxElementType = registerToken(DO_KEYWORD_ID, "do", soft = false, modifier = false)
    val WHEN_KEYWORD: SyntaxElementType = registerToken(WHEN_KEYWORD_ID, "when", soft = false, modifier = false)
    val INTERFACE_KEYWORD: SyntaxElementType = registerToken(INTERFACE_KEYWORD_ID, "interface", soft = false, modifier = false)

    // Reserved for future use:
    val TYPEOF_KEYWORD: SyntaxElementType = registerToken(TYPEOF_KEYWORD_ID, "typeof", soft = false, modifier = false)

    val AS_SAFE: SyntaxElementType = registerToken(AS_SAFE_ID, "AS_SAFE", soft = false, modifier = false)

    val IDENTIFIER: SyntaxElementType = register(IDENTIFIER_ID, "IDENTIFIER")

    val FIELD_IDENTIFIER: SyntaxElementType = register(FIELD_IDENTIFIER_ID, "FIELD_IDENTIFIER")
    val LBRACKET: SyntaxElementType = register(LBRACKET_ID, "LBRACKET")
    val RBRACKET: SyntaxElementType = register(RBRACKET_ID, "RBRACKET")
    val LBRACE: SyntaxElementType = register(LBRACE_ID, "LBRACE")
    val RBRACE: SyntaxElementType = register(RBRACE_ID, "RBRACE")
    val LPAR: SyntaxElementType = register(LPAR_ID, "LPAR")
    val RPAR: SyntaxElementType = register(RPAR_ID, "RPAR")
    val DOT: SyntaxElementType = register(DOT_ID, "DOT")
    val PLUSPLUS: SyntaxElementType = register(PLUSPLUS_ID, "PLUSPLUS")
    val MINUSMINUS: SyntaxElementType = register(MINUSMINUS_ID, "MINUSMINUS")
    val MUL: SyntaxElementType = register(MUL_ID, "MUL")
    val PLUS: SyntaxElementType = register(PLUS_ID, "PLUS")
    val MINUS: SyntaxElementType = register(MINUS_ID, "MINUS")
    val EXCL: SyntaxElementType = register(EXCL_ID, "EXCL")
    val DIV: SyntaxElementType = register(DIV_ID, "DIV")
    val PERC: SyntaxElementType = register(PERC_ID, "PERC")
    val LT: SyntaxElementType = register(LT_ID, "LT")
    val GT: SyntaxElementType = register(GT_ID, "GT")
    val LTEQ: SyntaxElementType = register(LTEQ_ID, "LTEQ")
    val GTEQ: SyntaxElementType = register(GTEQ_ID, "GTEQ")
    val EQEQEQ: SyntaxElementType = register(EQEQEQ_ID, "EQEQEQ")
    val ARROW: SyntaxElementType = register(ARROW_ID, "ARROW")
    val DOUBLE_ARROW: SyntaxElementType = register(DOUBLE_ARROW_ID, "DOUBLE_ARROW")
    val EXCLEQEQEQ: SyntaxElementType = register(EXCLEQEQEQ_ID, "EXCLEQEQEQ")
    val EQEQ: SyntaxElementType = register(EQEQ_ID, "EQEQ")
    val EXCLEQ: SyntaxElementType = register(EXCLEQ_ID, "EXCLEQ")
    val EXCLEXCL: SyntaxElementType = register(EXCLEXCL_ID, "EXCLEXCL")
    val ANDAND: SyntaxElementType = register(ANDAND_ID, "ANDAND")
    val AND: SyntaxElementType = register(AND_ID, "AND")
    val OROR: SyntaxElementType = register(OROR_ID, "OROR")
    val OR: SyntaxElementType = register(OR_ID, "OR")
    val SAFE_ACCESS: SyntaxElementType = register(SAFE_ACCESS_ID, "SAFE_ACCESS")
    val ERROR_SAFE_ACCESS: SyntaxElementType = register(ERROR_SAFE_ACCESS_ID, "ERROR_SAFE_ACCESS")
    val ELVIS: SyntaxElementType = register(ELVIS_ID, "ELVIS")
    val QUEST: SyntaxElementType = register(QUEST_ID, "QUEST")
    val COLONCOLON: SyntaxElementType = register(COLONCOLON_ID, "COLONCOLON")
    val COLON: SyntaxElementType = register(COLON_ID, "COLON")
    val SEMICOLON: SyntaxElementType = register(SEMICOLON_ID, "SEMICOLON")
    val DOUBLE_SEMICOLON: SyntaxElementType = register(DOUBLE_SEMICOLON_ID, "DOUBLE_SEMICOLON")
    val RANGE: SyntaxElementType = register(RANGE_ID, "RANGE")
    val RANGE_UNTIL: SyntaxElementType = register(RANGE_UNTIL_ID, "RANGE_UNTIL")
    val EQ: SyntaxElementType = register(EQ_ID, "EQ")
    val MULTEQ: SyntaxElementType = register(MULTEQ_ID, "MULTEQ")
    val DIVEQ: SyntaxElementType = register(DIVEQ_ID, "DIVEQ")
    val PERCEQ: SyntaxElementType = register(PERCEQ_ID, "PERCEQ")
    val PLUSEQ: SyntaxElementType = register(PLUSEQ_ID, "PLUSEQ")
    val MINUSEQ: SyntaxElementType = register(MINUSEQ_ID, "MINUSEQ")

    val NOT_IN: SyntaxElementType = registerToken(NOT_IN_ID, "NOT_IN", soft = false, modifier = false)
    val NOT_IS: SyntaxElementType = registerToken(NOT_IS_ID, "NOT_IS", soft = false, modifier = false)

    val HASH: SyntaxElementType = register(HASH_ID, "HASH")
    val AT: SyntaxElementType = register(AT_ID, "AT")

    val COMMA: SyntaxElementType = register(COMMA_ID, "COMMA")

    val EOL_OR_SEMICOLON: SyntaxElementType = register(EOL_OR_SEMICOLON_ID, "EOL_OR_SEMICOLON")

    val ALL_KEYWORD: SyntaxElementType = registerToken(ALL_KEYWORD_ID, "all", soft = true, modifier = false)
    val FILE_KEYWORD: SyntaxElementType = registerToken(FILE_KEYWORD_ID, "file", soft = true, modifier = false)
    val FIELD_KEYWORD: SyntaxElementType = registerToken(FIELD_KEYWORD_ID, "field", soft = true, modifier = false)
    val PROPERTY_KEYWORD: SyntaxElementType = registerToken(PROPERTY_KEYWORD_ID, "property", soft = true, modifier = false)
    val RECEIVER_KEYWORD: SyntaxElementType = registerToken(RECEIVER_KEYWORD_ID, "receiver", soft = true, modifier = false)
    val PARAM_KEYWORD: SyntaxElementType = registerToken(PARAM_KEYWORD_ID, "param", soft = true, modifier = false)
    val SETPARAM_KEYWORD: SyntaxElementType = registerToken(SETPARAM_KEYWORD_ID, "setparam", soft = true, modifier = false)
    val DELEGATE_KEYWORD: SyntaxElementType = registerToken(DELEGATE_KEYWORD_ID, "delegate", soft = true, modifier = false)
    val IMPORT_KEYWORD: SyntaxElementType = registerToken(IMPORT_KEYWORD_ID, "import", soft = true, modifier = false)
    val WHERE_KEYWORD: SyntaxElementType = registerToken(WHERE_KEYWORD_ID, "where", soft = true, modifier = false)
    val BY_KEYWORD: SyntaxElementType = registerToken(BY_KEYWORD_ID, "by", soft = true, modifier = false)
    val GET_KEYWORD: SyntaxElementType = registerToken(GET_KEYWORD_ID, "get", soft = true, modifier = false)
    val SET_KEYWORD: SyntaxElementType = registerToken(SET_KEYWORD_ID, "set", soft = true, modifier = false)
    val CONSTRUCTOR_KEYWORD: SyntaxElementType = registerToken(CONSTRUCTOR_KEYWORD_ID, "constructor", soft = true, modifier = false)
    val INIT_KEYWORD: SyntaxElementType = registerToken(INIT_KEYWORD_ID, "init", soft = true, modifier = false)
    val CONTEXT_KEYWORD: SyntaxElementType = registerToken(CONTEXT_KEYWORD_ID, "context", soft = true, modifier = false)

    val ABSTRACT_MODIFIER: SyntaxElementType = registerToken(ABSTRACT_MODIFIER_ID, "abstract", soft = true, modifier = true)
    val ENUM_MODIFIER: SyntaxElementType = registerToken(ENUM_MODIFIER_ID, "enum", soft = true, modifier = true)
    val CONTRACT_MODIFIER: SyntaxElementType = registerToken(CONTRACT_MODIFIER_ID, "contract", soft = true, modifier = true)
    val OPEN_MODIFIER: SyntaxElementType = registerToken(OPEN_MODIFIER_ID, "open", soft = true, modifier = true)
    val INNER_MODIFIER: SyntaxElementType = registerToken(INNER_MODIFIER_ID, "inner", soft = true, modifier = true)
    val OVERRIDE_MODIFIER: SyntaxElementType = registerToken(OVERRIDE_MODIFIER_ID, "override", soft = true, modifier = true)
    val PRIVATE_MODIFIER: SyntaxElementType = registerToken(PRIVATE_MODIFIER_ID, "private", soft = true, modifier = true)
    val PUBLIC_MODIFIER: SyntaxElementType = registerToken(PUBLIC_MODIFIER_ID, "public", soft = true, modifier = true)
    val INTERNAL_MODIFIER: SyntaxElementType = registerToken(INTERNAL_MODIFIER_ID, "internal", soft = true, modifier = true)
    val PROTECTED_MODIFIER: SyntaxElementType = registerToken(PROTECTED_MODIFIER_ID, "protected", soft = true, modifier = true)

    val CATCH_KEYWORD: SyntaxElementType = registerToken(CATCH_KEYWORD_ID, "catch", soft = true, modifier = false)

    val OUT_MODIFIER: SyntaxElementType = registerToken(OUT_MODIFIER_ID, "out", soft = true, modifier = true)
    val VARARG_MODIFIER: SyntaxElementType = registerToken(VARARG_MODIFIER_ID, "vararg", soft = true, modifier = true)
    val REIFIED_MODIFIER: SyntaxElementType = registerToken(REIFIED_MODIFIER_ID, "reified", soft = true, modifier = true)

    val DYNAMIC_KEYWORD: SyntaxElementType = registerToken(DYNAMIC_KEYWORD_ID, "dynamic", soft = true, modifier = false)

    val COMPANION_MODIFIER: SyntaxElementType = registerToken(COMPANION_MODIFIER_ID, "companion", soft = true, modifier = true)
    val SEALED_MODIFIER: SyntaxElementType = registerToken(SEALED_MODIFIER_ID, "sealed", soft = true, modifier = true)

    val FINALLY_KEYWORD: SyntaxElementType = registerToken(FINALLY_KEYWORD_ID, "finally", soft = true, modifier = false)
    val FINAL_MODIFIER: SyntaxElementType = registerToken(FINAL_MODIFIER_ID, "final", soft = true, modifier = true)

    val LATEINIT_MODIFIER: SyntaxElementType = registerToken(LATEINIT_MODIFIER_ID, "lateinit", soft = true, modifier = true)

    val DATA_MODIFIER: SyntaxElementType = registerToken(DATA_MODIFIER_ID, "data", soft = true, modifier = true)
    val VALUE_MODIFIER: SyntaxElementType = registerToken(VALUE_MODIFIER_ID, "value", soft = true, modifier = true)
    val INLINE_MODIFIER: SyntaxElementType = registerToken(INLINE_MODIFIER_ID, "inline", soft = true, modifier = true)
    val NOINLINE_MODIFIER: SyntaxElementType = registerToken(NOINLINE_MODIFIER_ID, "noinline", soft = true, modifier = true)
    val TAILREC_MODIFIER: SyntaxElementType = registerToken(TAILREC_MODIFIER_ID, "tailrec", soft = true, modifier = true)
    val EXTERNAL_MODIFIER: SyntaxElementType = registerToken(EXTERNAL_MODIFIER_ID, "external", soft = true, modifier = true)
    val ANNOTATION_MODIFIER: SyntaxElementType = registerToken(ANNOTATION_MODIFIER_ID, "annotation", soft = true, modifier = true)
    val CROSSINLINE_MODIFIER: SyntaxElementType = registerToken(CROSSINLINE_MODIFIER_ID, "crossinline", soft = true, modifier = true)
    val OPERATOR_MODIFIER: SyntaxElementType = registerToken(OPERATOR_MODIFIER_ID, "operator", soft = true, modifier = true)
    val INFIX_MODIFIER: SyntaxElementType = registerToken(INFIX_MODIFIER_ID, "infix", soft = true, modifier = true)
    val ERROR_MODIFIER: SyntaxElementType = registerToken(ERROR_MODIFIER_ID, "error", soft = true, modifier = true)

    val CONST_MODIFIER: SyntaxElementType = registerToken(CONST_MODIFIER_ID, "const", soft = true, modifier = true)

    val SUSPEND_MODIFIER: SyntaxElementType = registerToken(SUSPEND_MODIFIER_ID, "suspend", soft = true, modifier = true)

    val EXPECT_MODIFIER: SyntaxElementType = registerToken(EXPECT_MODIFIER_ID, "expect", soft = true, modifier = true)
    val ACTUAL_MODIFIER: SyntaxElementType = registerToken(ACTUAL_MODIFIER_ID, "actual", soft = true, modifier = true)

    private fun registerToken(id: Int, name: String, soft: Boolean, modifier: Boolean): SyntaxElementType {
        return register(id, name).also {
            val keywordsAndModifiersList = if (soft) softKeywordsAndModifiers else hardKeywordsAndModifiers
            keywordsAndModifiersList.add(it)
            if (modifier) {
                allModifiers.add(it)
            }
        }
    }

    val SOFT_KEYWORDS_AND_MODIFIERS: SyntaxElementTypeSet = softKeywordsAndModifiers.asSyntaxElementTypeSet()
    val HARD_KEYWORDS_AND_MODIFIERS: SyntaxElementTypeSet = hardKeywordsAndModifiers.asSyntaxElementTypeSet()
    val MODIFIERS: SyntaxElementTypeSet = allModifiers.asSyntaxElementTypeSet()

    val HARD_KEYWORDS_AND_MODIFIERS_MAP: Map<String, SyntaxElementType> = HARD_KEYWORDS_AND_MODIFIERS.associateBy { it.toString() }

    val SOFT_KEYWORDS_AND_MODIFIERS_MAP: Map<String, SyntaxElementType> = SOFT_KEYWORDS_AND_MODIFIERS.associateBy { it.toString() }

    fun getHardKeywordOrModifier(elementText: String?): SyntaxElementType? {
        return elementText?.let { HARD_KEYWORDS_AND_MODIFIERS_MAP[it] }
    }

    val TYPE_MODIFIER_KEYWORDS: SyntaxElementTypeSet = syntaxElementTypeSetOf(SUSPEND_MODIFIER)
    val TYPE_ARGUMENT_MODIFIER_KEYWORDS: SyntaxElementTypeSet = syntaxElementTypeSetOf(IN_MODIFIER, OUT_MODIFIER)
    val RESERVED_VALUE_PARAMETER_MODIFIER_KEYWORDS: SyntaxElementTypeSet = syntaxElementTypeSetOf(OUT_MODIFIER, VARARG_MODIFIER)

    val WHITESPACES: SyntaxElementTypeSet = syntaxElementTypeSetOf(SyntaxTokenTypes.WHITE_SPACE)

    /**
     * Don't add KDocTokens to COMMENTS SyntaxElementTypeSet, because it is used in KotlinParserDefinition.getCommentTokens(),
     * and therefore all COMMENTS tokens will be ignored by SyntaxBuilder.
     */
    val COMMENTS: SyntaxElementTypeSet = syntaxElementTypeSetOf(EOL_COMMENT, BLOCK_COMMENT, DOC_COMMENT, SHEBANG_COMMENT)
    val WHITE_SPACE_OR_COMMENT_BIT_SET: SyntaxElementTypeSet = COMMENTS + WHITESPACES

    val VAL_VAR: SyntaxElementTypeSet = syntaxElementTypeSetOf(VAL_KEYWORD, VAR_KEYWORD)
}
