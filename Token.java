

package com.company;

public class Token
{
    String lexeme;
    TokenType tokenType;
    int sourceLine;
    int column;

    public Token(String lexeme, TokenType tokenType, int sourceLine, int column)
    {
        this.lexeme = lexeme;
        this.tokenType = tokenType;
        this.sourceLine = sourceLine;
        this.column = column;
    }

    public String grabLexeme()
    {
        return lexeme;
    }

    public TokenType grabTokenType()
    {
        return tokenType;
    }

    public int sourceLine()
    {
        return sourceLine;
    }

    public int column()
    {
        return column;
    }
}
