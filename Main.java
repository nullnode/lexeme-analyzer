

/* changelog:
 ##[ 1.02 ]
 -Added a column variable to the token class to keep track of which index each lex/token is on
 -Changed sample test input to the one provided in class for Lua

 ##[ 1.01 ]
 -Added  variable sourceLine to class Token to keep track of which line each lexeme/token is on.
 -Small changes to grammar/syntax based on rules provided in 4308-Project-Julia.doc

 ##[ 1.0 ]
 -Implemented basic functionality of scanning/analyzing and categorizing
- Added a class for tokens which consists of a lexeme and tokentype based on the TokenType class
 */

package com.company;
import java.io.*;
import java.lang.*;
public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
	    Lexer lex = new Lexer("test.lua");
	    lex.print();
    }
}
