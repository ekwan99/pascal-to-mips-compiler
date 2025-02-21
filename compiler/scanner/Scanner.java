package scanner;
import java.io.*;
/**
 * Scanner is a simple scanner for Compilers and Interpreters
 * @author Emily Kwan
 * @version 9/5/23
 *  
 * Usage:
 * Scanner takes in each character of the input string and tokenizes each lexeme, 
 * producing a string of tokens.
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(file name);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }

    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }

    /**
     * Sets currentChar to the value read from the input stream.
     */
    private void getNextChar()
    {
        int nextVal=0;
        try
        {
            nextVal = in.read();
        } catch (IOException e)
        {
            System.exit(-1);
            e.printStackTrace();
        }
        if (nextVal == -1 || nextVal== 46)
        {
            eof = true;
        }
        else
        {
            currentChar = (char)(nextVal);
        }
    }

    /**
     * Takes in a char value representing the expected value of currentChar
     * 
     * @param expected the char being eaten
     */

    private void eat(char expected) throws ScanErrorException
    {
        if (currentChar != expected)
        {
            throw(new ScanErrorException("“Illegal character - expected " 
                    + currentChar + " and found " + expected));
        }
        else
        {
            getNextChar();
        }
    }

    /**
     * Returns if there is a next char
     * @return false if the input stream is at end of file
     *          else, true
     */
    public boolean hasNext()
    {
        return !eof;
    }

    /**
     * Checks if the given character is a digit (0-9)
     * @param inputChar the character to be checked
     * @return true if the given character is a digit, false otherwise
     */
    public static boolean isDigit(char inputChar)
    {
        return inputChar >= '0' && inputChar <= '9';
    }

    /**
     * Checks if the given character is a letter, without regard to case
     * @param inputChar the character to be checked
     * @return true if the given character is a letter, false otherwise
     */
    public static boolean isLetter(char inputChar)
    {
        return (inputChar >= 65 && inputChar <= 90) || (inputChar >= 97 && inputChar <= 122);
    }

    /**
     * Checks if the given character is white space
     * @param inputChar the character to be checked
     * @return true if the given character is whitespace, false otherwise
     */
    public static boolean isWhiteSpace(char inputChar) 
    {
        return inputChar == ' ' || inputChar == '\t' || inputChar == '\n' || inputChar == '\r'; 
    }

    /**
     * Reads in a digit using its regex definition digit(digit)*
     * @return the String representation of the lexeme
     * @throws ScanErrorException thrown if no lexeme is recognized
     */
    private String scanNumber() throws ScanErrorException
    {
        String lexStr = "";
        while(hasNext() && isDigit(currentChar))
        {
            lexStr += currentChar;
            eat(currentChar);
        }
        return lexStr;
    }

    /**
     * Reads in an identifier using its regex definition letter(letter|digit)*
     * @return the String representing the identifier
     * @throws ScanErrorException thrown if the regex definition is not satisfied
     */
    private String scanIdentifier() throws ScanErrorException
    {
        String lexStr = "";
        while(hasNext() && (isDigit(currentChar) || isLetter(currentChar)))
        {
            lexStr += currentChar;
            eat(currentChar);
        }
        return lexStr;
    }

    /**
     * Reads in an operand using its regex definition [‘=’ ‘+’ ‘-‘ ‘*’ ‘/’ ‘%’ ‘(‘ ‘)’]
     * @return the String representing operand read in
     * @throws ScanErrorException thrown if the regex definition is not satisfied
     */
    private String scanOperand() throws ScanErrorException
    {
        String lexStr = "" + currentChar;
        String[] operands = {"=", ",", "+", "-", "*", "/", "%", "(", ")", "<", ">", ";", ":", "."};
        boolean isOperand = false;
        for(String operand: operands)
        {
            if(lexStr.equals(operand))
            {
                isOperand = true;
            }
        }
        String returnString=""+currentChar;
        eat(currentChar);
        if (lexStr.equals("<") || lexStr.equals(">") || lexStr.equals(":"))
        {
            if (hasNext() && (currentChar == '='||currentChar == '>'))
            {
                returnString+=currentChar;
                eat(currentChar);
            }           
        }
        if(!isOperand)
        {
            throw new ScanErrorException("Expected valid operand "+ currentChar);   
        }
        return returnString;
    }

    /**
     * Skips white space and assesses currentChar, 
     * using the different scan methods to scan the next token in the input stream
     * @return the String representation of the currentChar
     */
    public String nextToken() throws ScanErrorException
    {
        while(hasNext() && isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }
        if (currentChar == '/')
        {
            //if(hasNext())
            //{
            eat(currentChar);
            //}
            if (currentChar == '/')
            {
                while(hasNext() && currentChar!= '\n')
                {
                    eat(currentChar);
                }
                eat(currentChar); // eats the new line
            }
            else
            {
                return "/";
            }
        }
        while(hasNext() && isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }

        if (isDigit(currentChar))
        {
            return scanNumber();
        }
        else if (isLetter(currentChar))
        {
            return scanIdentifier();
        }
        else if (!hasNext())
        {
            return "EOF";
        }
        else
        {
            return scanOperand();
        }
    }    
}
