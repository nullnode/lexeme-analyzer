
package com.company;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Lexer
{
    List<Token> tokens;

    public Lexer(String file) throws FileNotFoundException
    {
        tokens = new ArrayList<Token>(); // array list to hold all of our tokens
        Scanner julia = new Scanner (new File (file)); // scanner to read our file
        int sourceLine = 0;

        while (julia.hasNext()) // as long as something exists in our file, we'll save it to a string and send it off to grab the lexemes/tokens
        {
            String line = julia.nextLine();
            analyzeLine(line, sourceLine);
            sourceLine++;
        }
        //tokens.add(new Token ("FINISH", TokenType.FINISH, sourceLine, 0)); // final token to note the end of our source code...not necessary?
        julia.close();
    }

    public void analyzeLine(String line, int sourceLine)
    {
        int index = 0;
        index = killWhiteSpace(line, index); // removing white space before processing just in case there are spaces at the beginning of each line

        while (index < line.length()) // line is processed below to the end of the lines length
        {
            String lexeme = findLexeme(line, index); // grabbing a lexeme from the line using getLexeme()
            TokenType token = getToken(lexeme); // throwing our lexeme into the getToken() function to categorize it and saving it as a TokenType object
            tokens.add(new Token(lexeme, token, sourceLine + 1, index + 1)); // creating a new token based off our returned data from the previous 2 methods, then inserting it in our tokens array list

            index += lexeme.length(); // moving to the next lexeme by setting our index to "jump" over the lexeme we just analyzed
            index = killWhiteSpace(line, index); // skipping any white space in between iterations
            //analyzeLine(line, index += lexeme.length()); // recursive call processes certain lines more than once, using iteration in a while loop works fine
        }
    }

    public String findLexeme(String line, int index)
    {
        int n = index;
        while (n < line.length() && !Character.isWhitespace(line.charAt(n)))
            {
            n++;
        }
        return line.substring(index, n);
    }

    // skips past any white space in our line
    public int killWhiteSpace(String line, int index)
    {
        while (index < line.length() && Character.isWhitespace(line.charAt(index)))
        {
            index++;
        }
        return index;
    }

    public TokenType getToken(String lexeme) {

        TokenType token = null;

        if (Character.isLetter(lexeme.charAt(0))) // checking to see if the first char in our lexeme is a letter
        {
            if(lexeme.length() == 1) // checking to see if our lexemes length is 1
            {
                token = TokenType.ID; // if our lexeme is a 1 letter char, we know its an ID for a var
            }
        }
        else if(Character.isDigit(lexeme.charAt(0))) // checking if the first char in our lexeme is a digit
        {
            if (allNumbers(lexeme)) // throwing our lexeme into the allNumbers function to see if every char is a digit
            {
                token = TokenType.LITERAL_INTEGER; // if our lexeme consists entirely of digits, we know its a constant
            }
        }

        // switch statement to match/categorize each token, may need to add more operators and keywords depending on the requirements for our tests
        switch (lexeme) {
            case "if": // may need to add another case for "if("
                token = TokenType.IF;
                return token;
            case "function":
                token = TokenType.FUNCTION;
                return token;
            case "end":
                token = TokenType.END;
                return token;
            case "else":
                token = TokenType.ELSE;
                return token;
            case "for":
                token = TokenType.FOR;
                return token;
            case "while":
                token = TokenType.WHILE;
                return token;
            case "print":
                token = TokenType.PRINT;
                return token;
            case "+":
                token = TokenType.ADD_OPERATOR;
                return token;
            case "-":
                token = TokenType.SUB_OPERATOR;
                return token;
            case "*":
                token = TokenType.MULT_OPERATOR;
                return token;
            case "/":
                token = TokenType.DIV_OPERATOR;
                return token;
            case "%":
                token = TokenType.MOD_OPERATOR;
                return token;
            case "=":
                token = TokenType.ASSIGN_OPERATOR;
                return token;
            case "(":
                token = TokenType.LEFT_PAREN;
                return token;
            case ")":
                token = TokenType.RIGHT_PAREN;
                return token;
            case "==":
                token = TokenType.EQ_OPERATOR;
                return token;
            case ">":
                token = TokenType.GT_OPERATOR;
                return token;
            case "<":
                token = TokenType.LT_OPERATOR;
                return token;
            case "~=": // altered from != to fit the syntax
                token = TokenType.NE_OPERATOR;
                return token;
            case "<=":
                token = TokenType.LE_OPERATOR;
                return token;
            case ">=":
                token = TokenType.GE_OPERATOR;
                return token;
            case "until":
                token = TokenType.UNTIL;
                return token;
            case "repeat":
                token = TokenType.REPEAT;
                return token;
            case "do":
                token = TokenType.DO;
                return token;
        }
        return token;
    }

    // iterates through a helpful char array of our lexeme, testing each element for digits
    public boolean allNumbers(String test)
    {
        for (char c: test.toCharArray())
        {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    // prints out each token with its corresponding lexeme
    public void print()
    {
        int index = 0;
        while (index < tokens.size())
        {
            TokenType type = tokens.get(index).grabTokenType();
            String lexeme = tokens.get(index).grabLexeme();
            int line = tokens.get(index).sourceLine();
            int col = tokens.get(index).column();
            System.out.println("Token: " + type + "   Lexeme: " + lexeme + "   Line: " + line + "   index: " + col);
            index++;
        }
    }
}
