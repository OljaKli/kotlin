/*
 * Copyright 2010-2025 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.kotlin.kdoc.lexer.KDocTokens;
import org.jetbrains.kotlin.psi.KtPsiUtil;
import org.jetbrains.annotations.NotNull;

import static org.jetbrains.kotlin.KtNodeTypes.*;
import static org.jetbrains.kotlin.syntax.KtTokens.*;

public interface KtTokens {
    @NotNull KtToken EOF   = new KtToken("EOF", EOF_ID);

    @NotNull KtToken RESERVED    = new KtToken("RESERVED", RESERVED_ID);

    @NotNull KtToken BLOCK_COMMENT     = new KtToken("BLOCK_COMMENT", BLOCK_COMMENT_ID);
    @NotNull KtToken EOL_COMMENT       = new KtToken("EOL_COMMENT", EOL_COMMENT_ID);
    @NotNull KtToken SHEBANG_COMMENT   = new KtToken("SHEBANG_COMMENT", SHEBANG_COMMENT_ID);

    @NotNull IElementType DOC_COMMENT   = KDocTokens.KDOC;

    @NotNull IElementType WHITE_SPACE = TokenType.WHITE_SPACE;

    @NotNull KtToken INTEGER_LITERAL    = new KtToken("INTEGER_LITERAL", INTEGER_LITERAL_ID);
    @NotNull KtToken FLOAT_LITERAL      = new KtToken("FLOAT_CONSTANT", FLOAT_LITERAL_ID);
    @NotNull KtToken CHARACTER_LITERAL  = new KtToken("CHARACTER_LITERAL", CHARACTER_LITERAL_ID);

    @NotNull KtToken INTERPOLATION_PREFIX = new KtToken("INTERPOLATION_PREFIX", INTERPOLATION_PREFIX_ID);
    @NotNull KtToken CLOSING_QUOTE = new KtToken("CLOSING_QUOTE", CLOSING_QUOTE_ID);
    @NotNull KtToken OPEN_QUOTE = new KtToken("OPEN_QUOTE", OPEN_QUOTE_ID);
    @NotNull KtToken REGULAR_STRING_PART = new KtToken("REGULAR_STRING_PART", REGULAR_STRING_PART_ID);
    @NotNull KtToken ESCAPE_SEQUENCE = new KtToken("ESCAPE_SEQUENCE", ESCAPE_SEQUENCE_ID);
    @NotNull KtToken SHORT_TEMPLATE_ENTRY_START = new KtToken("SHORT_TEMPLATE_ENTRY_START", SHORT_TEMPLATE_ENTRY_START_ID);
    @NotNull KtToken LONG_TEMPLATE_ENTRY_START = new KtToken("LONG_TEMPLATE_ENTRY_START", LONG_TEMPLATE_ENTRY_START_ID);
    @NotNull KtToken LONG_TEMPLATE_ENTRY_END = new KtToken("LONG_TEMPLATE_ENTRY_END", LONG_TEMPLATE_ENTRY_END_ID);
    @NotNull KtToken DANGLING_NEWLINE = new KtToken("DANGLING_NEWLINE", DANGLING_NEWLINE_ID);

    @NotNull KtKeywordToken PACKAGE_KEYWORD          = KtKeywordToken.keyword("package", PACKAGE_KEYWORD_ID);
    @NotNull KtKeywordToken AS_KEYWORD               = KtKeywordToken.keyword("as", AS_KEYWORD_ID);
    @NotNull KtKeywordToken TYPE_ALIAS_KEYWORD       = KtKeywordToken.keyword("typealias", TYPE_ALIAS_KEYWORD_ID);
    @NotNull KtKeywordToken CLASS_KEYWORD            = KtKeywordToken.keyword("class", CLASS_KEYWORD_ID);
    @NotNull KtKeywordToken THIS_KEYWORD             = KtKeywordToken.keyword("this", THIS_KEYWORD_ID);
    @NotNull KtKeywordToken SUPER_KEYWORD            = KtKeywordToken.keyword("super", SUPER_KEYWORD_ID);
    @NotNull KtKeywordToken VAL_KEYWORD              = KtKeywordToken.keyword("val", VAL_KEYWORD_ID);
    @NotNull KtKeywordToken VAR_KEYWORD              = KtKeywordToken.keyword("var", VAR_KEYWORD_ID);
    @NotNull KtModifierKeywordToken FUN_KEYWORD      = KtModifierKeywordToken.keywordModifier("fun", FUN_MODIFIER_ID);
    @NotNull KtKeywordToken FOR_KEYWORD              = KtKeywordToken.keyword("for", FOR_KEYWORD_ID);
    @NotNull KtKeywordToken NULL_KEYWORD             = KtKeywordToken.keyword("null", NULL_KEYWORD_ID);
    @NotNull KtKeywordToken TRUE_KEYWORD             = KtKeywordToken.keyword("true", TRUE_KEYWORD_ID);
    @NotNull KtKeywordToken FALSE_KEYWORD            = KtKeywordToken.keyword("false", FALSE_KEYWORD_ID);
    @NotNull KtKeywordToken IS_KEYWORD               = KtKeywordToken.keyword("is", IS_KEYWORD_ID);
    @NotNull KtModifierKeywordToken IN_KEYWORD       = KtModifierKeywordToken.keywordModifier("in", IN_MODIFIER_ID);
    @NotNull KtKeywordToken THROW_KEYWORD            = KtKeywordToken.keyword("throw", THROW_KEYWORD_ID);
    @NotNull KtKeywordToken RETURN_KEYWORD           = KtKeywordToken.keyword("return", RETURN_KEYWORD_ID);
    @NotNull KtKeywordToken BREAK_KEYWORD            = KtKeywordToken.keyword("break", BREAK_KEYWORD_ID);
    @NotNull KtKeywordToken CONTINUE_KEYWORD         = KtKeywordToken.keyword("continue", CONTINUE_KEYWORD_ID);
    @NotNull KtKeywordToken OBJECT_KEYWORD           = KtKeywordToken.keyword("object", OBJECT_KEYWORD_ID);
    @NotNull KtKeywordToken IF_KEYWORD               = KtKeywordToken.keyword("if", IF_KEYWORD_ID);
    @NotNull KtKeywordToken TRY_KEYWORD              = KtKeywordToken.keyword("try", TRY_KEYWORD_ID);
    @NotNull KtKeywordToken ELSE_KEYWORD             = KtKeywordToken.keyword("else", ELSE_KEYWORD_ID);
    @NotNull KtKeywordToken WHILE_KEYWORD            = KtKeywordToken.keyword("while", WHILE_KEYWORD_ID);
    @NotNull KtKeywordToken DO_KEYWORD               = KtKeywordToken.keyword("do", DO_KEYWORD_ID);
    @NotNull KtKeywordToken WHEN_KEYWORD             = KtKeywordToken.keyword("when", WHEN_KEYWORD_ID);
    @NotNull KtKeywordToken INTERFACE_KEYWORD        = KtKeywordToken.keyword("interface", INTERFACE_KEYWORD_ID);

    // Reserved for future use:
    @NotNull KtKeywordToken TYPEOF_KEYWORD           = KtKeywordToken.keyword("typeof", TYPEOF_KEYWORD_ID);

    @NotNull KtToken AS_SAFE = KtKeywordToken.keyword("AS_SAFE", "as?", AS_SAFE_ID);

    @NotNull KtToken IDENTIFIER = new KtToken("IDENTIFIER", IDENTIFIER_ID);

    @NotNull KtToken FIELD_IDENTIFIER = new KtToken("FIELD_IDENTIFIER", FIELD_IDENTIFIER_ID);
    @NotNull KtSingleValueToken LBRACKET    = new KtSingleValueToken("LBRACKET", "[", LBRACKET_ID);
    @NotNull KtSingleValueToken RBRACKET    = new KtSingleValueToken("RBRACKET", "]", RBRACKET_ID);
    @NotNull KtSingleValueToken LBRACE      = new KtSingleValueToken("LBRACE", "{", LBRACE_ID);
    @NotNull KtSingleValueToken RBRACE      = new KtSingleValueToken("RBRACE", "}", RBRACE_ID);
    @NotNull KtSingleValueToken LPAR        = new KtSingleValueToken("LPAR", "(", LPAR_ID);
    @NotNull KtSingleValueToken RPAR        = new KtSingleValueToken("RPAR", ")", RPAR_ID);
    @NotNull KtSingleValueToken DOT         = new KtSingleValueToken("DOT", ".", DOT_ID);
    @NotNull KtSingleValueToken PLUSPLUS    = new KtSingleValueToken("PLUSPLUS", "++", PLUSPLUS_ID);
    @NotNull KtSingleValueToken MINUSMINUS  = new KtSingleValueToken("MINUSMINUS", "--", MINUSMINUS_ID);
    @NotNull KtSingleValueToken MUL         = new KtSingleValueToken("MUL", "*", MUL_ID);
    @NotNull KtSingleValueToken PLUS        = new KtSingleValueToken("PLUS", "+", PLUS_ID);
    @NotNull KtSingleValueToken MINUS       = new KtSingleValueToken("MINUS", "-", MINUS_ID);
    @NotNull KtSingleValueToken EXCL        = new KtSingleValueToken("EXCL", "!", EXCL_ID);
    @NotNull KtSingleValueToken DIV         = new KtSingleValueToken("DIV", "/", DIV_ID);
    @NotNull KtSingleValueToken PERC        = new KtSingleValueToken("PERC", "%", PERC_ID);
    @NotNull KtSingleValueToken LT          = new KtSingleValueToken("LT", "<", LT_ID);
    @NotNull KtSingleValueToken GT          = new KtSingleValueToken("GT", ">", GT_ID);
    @NotNull KtSingleValueToken LTEQ        = new KtSingleValueToken("LTEQ", "<=", LTEQ_ID);
    @NotNull KtSingleValueToken GTEQ        = new KtSingleValueToken("GTEQ", ">=", GTEQ_ID);
    @NotNull KtSingleValueToken EQEQEQ      = new KtSingleValueToken("EQEQEQ", "===", EQEQEQ_ID);
    @NotNull KtSingleValueToken ARROW       = new KtSingleValueToken("ARROW", "->", ARROW_ID);
    @NotNull KtSingleValueToken DOUBLE_ARROW       = new KtSingleValueToken("DOUBLE_ARROW", "=>", DOUBLE_ARROW_ID);
    @NotNull KtSingleValueToken EXCLEQEQEQ  = new KtSingleValueToken("EXCLEQEQEQ", "!==", EXCLEQEQEQ_ID);
    @NotNull KtSingleValueToken EQEQ        = new KtSingleValueToken("EQEQ", "==", EQEQ_ID);
    @NotNull KtSingleValueToken EXCLEQ      = new KtSingleValueToken("EXCLEQ", "!=", EXCLEQ_ID);
    @NotNull KtSingleValueToken EXCLEXCL    = new KtSingleValueToken("EXCLEXCL", "!!", EXCLEXCL_ID);
    @NotNull KtSingleValueToken ANDAND      = new KtSingleValueToken("ANDAND", "&&", ANDAND_ID);
    @NotNull KtSingleValueToken AND         = new KtSingleValueToken("AND", "&", AND_ID);
    @NotNull KtSingleValueToken OROR        = new KtSingleValueToken("OROR", "||", OROR_ID);
    @NotNull KtSingleValueToken OR          = new KtSingleValueToken("OR", "|", OR_ID);
    @NotNull KtSingleValueToken SAFE_ACCESS = new KtSingleValueToken("SAFE_ACCESS", "?.", SAFE_ACCESS_ID);
    @NotNull KtSingleValueToken ERROR_SAFE_ACCESS = new KtSingleValueToken("ERROR_SAFE_ACCESS", "|.", ERROR_SAFE_ACCESS_ID);
    @NotNull KtSingleValueToken ELVIS       = new KtSingleValueToken("ELVIS", "?:", ELVIS_ID);
    @NotNull KtSingleValueToken QUEST       = new KtSingleValueToken("QUEST", "?", QUEST_ID);
    @NotNull KtSingleValueToken COLONCOLON  = new KtSingleValueToken("COLONCOLON", "::", COLONCOLON_ID);
    @NotNull KtSingleValueToken COLON       = new KtSingleValueToken("COLON", ":", COLON_ID);
    @NotNull KtSingleValueToken SEMICOLON   = new KtSingleValueToken("SEMICOLON", ";", SEMICOLON_ID);
    @NotNull KtSingleValueToken DOUBLE_SEMICOLON   = new KtSingleValueToken("DOUBLE_SEMICOLON", ";;", DOUBLE_SEMICOLON_ID);
    @NotNull KtSingleValueToken RANGE       = new KtSingleValueToken("RANGE", "..", RANGE_ID);
    @NotNull KtSingleValueToken RANGE_UNTIL       = new KtSingleValueToken("RANGE_UNTIL", "..<", RANGE_UNTIL_ID);
    @NotNull KtSingleValueToken EQ          = new KtSingleValueToken("EQ", "=", EQ_ID);
    @NotNull KtSingleValueToken MULTEQ      = new KtSingleValueToken("MULTEQ", "*=", MULTEQ_ID);
    @NotNull KtSingleValueToken DIVEQ       = new KtSingleValueToken("DIVEQ", "/=", DIVEQ_ID);
    @NotNull KtSingleValueToken PERCEQ      = new KtSingleValueToken("PERCEQ", "%=", PERCEQ_ID);
    @NotNull KtSingleValueToken PLUSEQ      = new KtSingleValueToken("PLUSEQ", "+=", PLUSEQ_ID);
    @NotNull KtSingleValueToken MINUSEQ     = new KtSingleValueToken("MINUSEQ", "-=", MINUSEQ_ID);
    @NotNull KtKeywordToken NOT_IN      = KtKeywordToken.keyword("NOT_IN", "!in", NOT_IN_ID);
    @NotNull KtKeywordToken NOT_IS      = KtKeywordToken.keyword("NOT_IS", "!is", NOT_IS_ID);
    @NotNull KtSingleValueToken HASH        = new KtSingleValueToken("HASH", "#", HASH_ID);
    @NotNull KtSingleValueToken AT          = new KtSingleValueToken("AT", "@", AT_ID);

    @NotNull KtSingleValueToken COMMA       = new KtSingleValueToken("COMMA", ",", COMMA_ID);

    @NotNull KtToken EOL_OR_SEMICOLON   = new KtToken("EOL_OR_SEMICOLON", EOL_OR_SEMICOLON_ID);
    @NotNull KtKeywordToken ALL_KEYWORD    = KtKeywordToken.softKeyword("all", ALL_KEYWORD_ID);
    @NotNull KtKeywordToken FILE_KEYWORD    = KtKeywordToken.softKeyword("file", FILE_KEYWORD_ID);
    @NotNull KtKeywordToken FIELD_KEYWORD     = KtKeywordToken.softKeyword("field", FIELD_KEYWORD_ID);
    @NotNull KtKeywordToken PROPERTY_KEYWORD     = KtKeywordToken.softKeyword("property", PROPERTY_KEYWORD_ID);
    @NotNull KtKeywordToken RECEIVER_KEYWORD     = KtKeywordToken.softKeyword("receiver", RECEIVER_KEYWORD_ID);
    @NotNull KtKeywordToken PARAM_KEYWORD     = KtKeywordToken.softKeyword("param", PARAM_KEYWORD_ID);
    @NotNull KtKeywordToken SETPARAM_KEYWORD  = KtKeywordToken.softKeyword("setparam", SETPARAM_KEYWORD_ID);
    @NotNull KtKeywordToken DELEGATE_KEYWORD  = KtKeywordToken.softKeyword("delegate", DELEGATE_KEYWORD_ID);
    @NotNull KtKeywordToken IMPORT_KEYWORD    = KtKeywordToken.softKeyword("import", IMPORT_KEYWORD_ID);
    @NotNull KtKeywordToken WHERE_KEYWORD     = KtKeywordToken.softKeyword("where", WHERE_KEYWORD_ID);
    @NotNull KtKeywordToken BY_KEYWORD        = KtKeywordToken.softKeyword("by", BY_KEYWORD_ID);
    @NotNull KtKeywordToken GET_KEYWORD       = KtKeywordToken.softKeyword("get", GET_KEYWORD_ID);
    @NotNull KtKeywordToken SET_KEYWORD       = KtKeywordToken.softKeyword("set", SET_KEYWORD_ID);
    @NotNull KtKeywordToken CONSTRUCTOR_KEYWORD = KtKeywordToken.softKeyword("constructor", CONSTRUCTOR_KEYWORD_ID);
    @NotNull KtKeywordToken INIT_KEYWORD        = KtKeywordToken.softKeyword("init", INIT_KEYWORD_ID);
    @NotNull KtKeywordToken CONTEXT_KEYWORD     = KtKeywordToken.softKeyword("context", CONTEXT_KEYWORD_ID);
    @NotNull KtKeywordToken CATCH_KEYWORD     = KtKeywordToken.softKeyword("catch", CATCH_KEYWORD_ID);
    @NotNull KtKeywordToken DYNAMIC_KEYWORD   = KtKeywordToken.softKeyword("dynamic", DYNAMIC_KEYWORD_ID);
    @NotNull KtKeywordToken FINALLY_KEYWORD   = KtKeywordToken.softKeyword("finally", FINALLY_KEYWORD_ID);

    @NotNull KtModifierKeywordToken ABSTRACT_KEYWORD  = KtModifierKeywordToken.softKeywordModifier("abstract", ABSTRACT_MODIFIER_ID);
    @NotNull KtModifierKeywordToken ENUM_KEYWORD      = KtModifierKeywordToken.softKeywordModifier("enum", ENUM_MODIFIER_ID);
    @NotNull KtModifierKeywordToken CONTRACT_KEYWORD  = KtModifierKeywordToken.softKeywordModifier("contract", CONTRACT_MODIFIER_ID);
    @NotNull KtModifierKeywordToken OPEN_KEYWORD      = KtModifierKeywordToken.softKeywordModifier("open", OPEN_MODIFIER_ID);
    @NotNull KtModifierKeywordToken INNER_KEYWORD     = KtModifierKeywordToken.softKeywordModifier("inner", INNER_MODIFIER_ID);
    @NotNull KtModifierKeywordToken OVERRIDE_KEYWORD  = KtModifierKeywordToken.softKeywordModifier("override", OVERRIDE_MODIFIER_ID);
    @NotNull KtModifierKeywordToken PRIVATE_KEYWORD   = KtModifierKeywordToken.softKeywordModifier("private", PRIVATE_MODIFIER_ID);
    @NotNull KtModifierKeywordToken PUBLIC_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("public", PUBLIC_MODIFIER_ID);
    @NotNull KtModifierKeywordToken INTERNAL_KEYWORD  = KtModifierKeywordToken.softKeywordModifier("internal", INTERNAL_MODIFIER_ID);
    @NotNull KtModifierKeywordToken PROTECTED_KEYWORD = KtModifierKeywordToken.softKeywordModifier("protected", PROTECTED_MODIFIER_ID);
    @NotNull KtModifierKeywordToken OUT_KEYWORD       = KtModifierKeywordToken.softKeywordModifier("out", OUT_MODIFIER_ID);
    @NotNull KtModifierKeywordToken VARARG_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("vararg", VARARG_MODIFIER_ID);
    @NotNull KtModifierKeywordToken REIFIED_KEYWORD   = KtModifierKeywordToken.softKeywordModifier("reified", REIFIED_MODIFIER_ID);
    @NotNull KtModifierKeywordToken COMPANION_KEYWORD = KtModifierKeywordToken.softKeywordModifier("companion", COMPANION_MODIFIER_ID);
    @NotNull KtModifierKeywordToken SEALED_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("sealed", SEALED_MODIFIER_ID);
    @NotNull KtModifierKeywordToken ERROR_KEYWORD     = KtModifierKeywordToken.softKeywordModifier("error", ERROR_MODIFIER_ID);

    @Deprecated @NotNull KtModifierKeywordToken DEFAULT_VISIBILITY_KEYWORD = PUBLIC_KEYWORD;

    @NotNull KtModifierKeywordToken FINAL_KEYWORD     = KtModifierKeywordToken.softKeywordModifier("final", FINAL_MODIFIER_ID);

    @NotNull KtModifierKeywordToken LATEINIT_KEYWORD = KtModifierKeywordToken.softKeywordModifier("lateinit", LATEINIT_MODIFIER_ID);

    @NotNull KtModifierKeywordToken DATA_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("data", DATA_MODIFIER_ID);
    @NotNull KtModifierKeywordToken VALUE_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("value", VALUE_MODIFIER_ID);
    @NotNull KtModifierKeywordToken INLINE_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("inline", INLINE_MODIFIER_ID);
    @NotNull KtModifierKeywordToken NOINLINE_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("noinline", NOINLINE_MODIFIER_ID);
    @NotNull KtModifierKeywordToken TAILREC_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("tailrec", TAILREC_MODIFIER_ID);
    @NotNull KtModifierKeywordToken EXTERNAL_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("external", EXTERNAL_MODIFIER_ID);
    @NotNull KtModifierKeywordToken ANNOTATION_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("annotation", ANNOTATION_MODIFIER_ID);
    @NotNull KtModifierKeywordToken CROSSINLINE_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("crossinline", CROSSINLINE_MODIFIER_ID);
    @NotNull KtModifierKeywordToken OPERATOR_KEYWORD = KtModifierKeywordToken.softKeywordModifier("operator", OPERATOR_MODIFIER_ID);
    @NotNull KtModifierKeywordToken INFIX_KEYWORD = KtModifierKeywordToken.softKeywordModifier("infix", INFIX_MODIFIER_ID);

    @NotNull KtModifierKeywordToken CONST_KEYWORD = KtModifierKeywordToken.softKeywordModifier("const", CONST_MODIFIER_ID);

    @NotNull KtModifierKeywordToken SUSPEND_KEYWORD = KtModifierKeywordToken.softKeywordModifier("suspend", SUSPEND_MODIFIER_ID);

    @NotNull KtModifierKeywordToken EXPECT_KEYWORD = KtModifierKeywordToken.softKeywordModifier("expect", EXPECT_MODIFIER_ID);
    @NotNull KtModifierKeywordToken ACTUAL_KEYWORD = KtModifierKeywordToken.softKeywordModifier("actual", ACTUAL_MODIFIER_ID);


    @NotNull
    TokenSet KEYWORDS = TokenSet.create(PACKAGE_KEYWORD, AS_KEYWORD, TYPE_ALIAS_KEYWORD, CLASS_KEYWORD, INTERFACE_KEYWORD,
                                        THIS_KEYWORD, SUPER_KEYWORD, VAL_KEYWORD, VAR_KEYWORD, FUN_KEYWORD, FOR_KEYWORD,
                                        NULL_KEYWORD,
                                        TRUE_KEYWORD, FALSE_KEYWORD, IS_KEYWORD,
                                        IN_KEYWORD, THROW_KEYWORD, RETURN_KEYWORD, BREAK_KEYWORD, CONTINUE_KEYWORD, OBJECT_KEYWORD, IF_KEYWORD,
                                        ELSE_KEYWORD, WHILE_KEYWORD, DO_KEYWORD, TRY_KEYWORD, WHEN_KEYWORD,
                                        NOT_IN, NOT_IS, AS_SAFE,
                                        TYPEOF_KEYWORD
    );

    @NotNull
    TokenSet SOFT_KEYWORDS = TokenSet.create(FILE_KEYWORD, IMPORT_KEYWORD, WHERE_KEYWORD, BY_KEYWORD, GET_KEYWORD,
                                             SET_KEYWORD, ABSTRACT_KEYWORD, ENUM_KEYWORD, CONTRACT_KEYWORD, OPEN_KEYWORD, INNER_KEYWORD,
                                             OVERRIDE_KEYWORD, PRIVATE_KEYWORD, PUBLIC_KEYWORD, INTERNAL_KEYWORD, PROTECTED_KEYWORD,
                                             CATCH_KEYWORD, FINALLY_KEYWORD, OUT_KEYWORD, FINAL_KEYWORD, VARARG_KEYWORD, REIFIED_KEYWORD,
                                             DYNAMIC_KEYWORD, COMPANION_KEYWORD, CONSTRUCTOR_KEYWORD, INIT_KEYWORD, SEALED_KEYWORD,
                                             FIELD_KEYWORD, PROPERTY_KEYWORD, RECEIVER_KEYWORD, PARAM_KEYWORD, SETPARAM_KEYWORD,
                                             DELEGATE_KEYWORD, ALL_KEYWORD,
                                             LATEINIT_KEYWORD,
                                             DATA_KEYWORD, INLINE_KEYWORD, NOINLINE_KEYWORD, TAILREC_KEYWORD, EXTERNAL_KEYWORD,
                                             ANNOTATION_KEYWORD, CROSSINLINE_KEYWORD, CONST_KEYWORD, OPERATOR_KEYWORD, INFIX_KEYWORD,
                                             SUSPEND_KEYWORD, EXPECT_KEYWORD, ACTUAL_KEYWORD,
                                             VALUE_KEYWORD, CONTEXT_KEYWORD, ERROR_KEYWORD
    );

    /**
     * Canonically ordered array of all modifier keywords.
     * <p>
     * <b>IMPORTANT:</b> modifiers are used in stub serialization, so
     * <ul>
     *   <li>Do <b>NOT</b> change the order of this array unless necessary</li>
     *   <li>If you add an entry or rearrange the order, be sure to increase {@link org.jetbrains.kotlin.psi.stubs.KotlinStubVersions the stub version}.</li>
     * </ul>
     *
     * @see org.jetbrains.kotlin.psi.stubs.KotlinStubVersions
     */
    KtModifierKeywordToken[] MODIFIER_KEYWORDS_ARRAY =
            new KtModifierKeywordToken[] {
                    PUBLIC_KEYWORD, PROTECTED_KEYWORD, PRIVATE_KEYWORD, INTERNAL_KEYWORD,
                    EXPECT_KEYWORD, ACTUAL_KEYWORD,
                    FINAL_KEYWORD, OPEN_KEYWORD, ABSTRACT_KEYWORD, SEALED_KEYWORD,
                    CONST_KEYWORD,
                    EXTERNAL_KEYWORD,
                    OVERRIDE_KEYWORD,
                    LATEINIT_KEYWORD,
                    TAILREC_KEYWORD,
                    VARARG_KEYWORD,
                    SUSPEND_KEYWORD,
                    INNER_KEYWORD,
                    ENUM_KEYWORD, ANNOTATION_KEYWORD, FUN_KEYWORD,
                    COMPANION_KEYWORD,
                    INLINE_KEYWORD,
                    VALUE_KEYWORD,
                    INFIX_KEYWORD,
                    OPERATOR_KEYWORD,
                    DATA_KEYWORD,
                    ERROR_KEYWORD,

                    OUT_KEYWORD, IN_KEYWORD,
                    REIFIED_KEYWORD,
                    NOINLINE_KEYWORD,
                    CROSSINLINE_KEYWORD,
            };

    @NotNull TokenSet MODIFIER_KEYWORDS = TokenSet.create(MODIFIER_KEYWORDS_ARRAY);

    @NotNull TokenSet TYPE_MODIFIER_KEYWORDS = TokenSet.create(SUSPEND_KEYWORD);
    @NotNull TokenSet TYPE_ARGUMENT_MODIFIER_KEYWORDS = TokenSet.create(IN_KEYWORD, OUT_KEYWORD);
    @NotNull TokenSet RESERVED_VALUE_PARAMETER_MODIFIER_KEYWORDS = TokenSet.create(OUT_KEYWORD, VARARG_KEYWORD);

    @NotNull TokenSet VISIBILITY_MODIFIERS = TokenSet.create(PRIVATE_KEYWORD, PUBLIC_KEYWORD, INTERNAL_KEYWORD, PROTECTED_KEYWORD);
    @NotNull TokenSet MODALITY_MODIFIERS = TokenSet.create(ABSTRACT_KEYWORD, FINAL_KEYWORD, SEALED_KEYWORD, OPEN_KEYWORD);

    @NotNull TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE);

    /**
     * Don't add KDocTokens to COMMENTS TokenSet, because it is used in KotlinParserDefinition.getCommentTokens(),
     * and therefor all COMMENTS tokens will be ignored by PsiBuilder.
     *
     * @see KtPsiUtil#isInComment(com.intellij.psi.PsiElement)
     */
    @NotNull TokenSet COMMENTS = TokenSet.create(EOL_COMMENT, BLOCK_COMMENT, DOC_COMMENT, SHEBANG_COMMENT);
    @NotNull TokenSet WHITE_SPACE_OR_COMMENT_BIT_SET = TokenSet.orSet(COMMENTS, WHITESPACES);

    @NotNull TokenSet STRINGS = TokenSet.create(CHARACTER_LITERAL, REGULAR_STRING_PART);

    @NotNull
    TokenSet OPERATIONS = TokenSet.create(AS_KEYWORD, AS_SAFE, IS_KEYWORD, IN_KEYWORD, DOT, PLUSPLUS, MINUSMINUS, EXCLEXCL, MUL, PLUS,
                                          MINUS, EXCL, DIV, PERC, LT, GT, LTEQ, GTEQ, EQEQEQ, EXCLEQEQEQ, EQEQ, EXCLEQ, ANDAND, OROR,
                                          SAFE_ACCESS, ERROR_SAFE_ACCESS, ELVIS,
                                          RANGE, RANGE_UNTIL, EQ, MULTEQ, DIVEQ, PERCEQ, PLUSEQ, MINUSEQ,
                                          NOT_IN, NOT_IS,
                                          IDENTIFIER);

    @NotNull TokenSet AUGMENTED_ASSIGNMENTS = TokenSet.create(PLUSEQ, MINUSEQ, MULTEQ, PERCEQ, DIVEQ);
    @NotNull TokenSet ALL_ASSIGNMENTS = TokenSet.create(EQ, PLUSEQ, MINUSEQ, MULTEQ, PERCEQ, DIVEQ);
    @NotNull TokenSet INCREMENT_AND_DECREMENT = TokenSet.create(PLUSPLUS, MINUSMINUS);
    @NotNull TokenSet QUALIFIED_ACCESS = TokenSet.create(DOT_QUALIFIED_EXPRESSION, SAFE_ACCESS_EXPRESSION, ERROR_SAFE_ACCESS_EXPRESSION);
    @NotNull TokenSet VAL_VAR = TokenSet.create(VAL_KEYWORD, VAR_KEYWORD);
    @NotNull TokenSet ALL_SAFE_ACCESSES = TokenSet.create(SAFE_ACCESS, ERROR_SAFE_ACCESS);
}
