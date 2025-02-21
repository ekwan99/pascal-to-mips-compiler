package parser;
import scanner.Scanner;
import scanner.ScannerTester;
import scanner.ScanErrorException;
import java.util.*;
import java.util.Map;
import java.util.HashMap;
import ast.*;

/**
 * The Parser class parses statements, factors, expressions, and numbers.
 * Assigned variables are stored in a Map with String keys and Integers values.
 *
 * @author Emily Kwan
 * @version 10/4/23
 */
public class Parser
{
    private Scanner scanner;
    private String currentToken;
    private Map<String, Integer> map;

    /**
     * Constructs parser based on the given scanner
     * 
     * @param scanner the given scanner
     */
    public Parser(Scanner scanner) throws ScanErrorException
    {
        this.scanner = scanner;
        currentToken = scanner.nextToken();
        map = new HashMap<String, Integer>();
    }

    /**
     * Consumes the next token after checking if the current token matches the next.
     * If not, an Illegal Argument Exception is thrown.
     * 
     * @param expected the expected token
     * 
     */
    private void eat(String expected) throws ScanErrorException
    {
        if(expected.equals(currentToken))
        {
            currentToken = scanner.nextToken();
        }
        else
        {
            throw(new IllegalArgumentException(expected 
                    + " was expected. " + currentToken + " was found.")); 
        }
    }

    /**
     * Parses the current token as an integer, eats it, then returns it.
     * 
     * @return the number parsed
     */
    private Expression parseNumber() throws ScanErrorException
    {
        int n = Integer.parseInt(currentToken);
        eat(currentToken);
        return new ast.Number(n);
    }

    /**
     * Parses statements with WRITELN, BEGIN, END, IF, WHILE, and EOF tokens by parsing
     * the corresponding object type. Contains instructions for variable assignment as well.
     *
     *  @return the statement that is parsed
     */
    public Statement parseStatement() throws ScanErrorException
    {
        if(currentToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(exp);
        }
        else if(currentToken.equals("BEGIN"))
        {
            eat("BEGIN");
            ArrayList<Statement>list = new ArrayList<>();
            while(!currentToken.equals("END"))
            {
                list.add(parseStatement());
            }
            Block block = new Block(list);
            eat("END");
            eat(";");
            return block;
        }

        else if(currentToken.equals("IF"))
        {
            eat("IF");
            Conditional cond = parseConditional();
            eat("THEN");
            Statement stat = parseStatement();
            return new If(cond, stat);
        }
        else if(currentToken.equals("WHILE"))
        {
            eat("WHILE");
            Conditional cond = parseConditional();
            eat("DO");
            Statement stat = parseStatement();
            return new While(cond, stat);
        }
        else
        {
            String curr = currentToken;
            eat(curr);
            eat(":=");
            Assignment assignment = new Assignment(curr, parseExpression());
            eat(";");
            return assignment;
        }

    }

    /**
     * Parses factors based on the specified factor grammar. Creates and returns the corresponding
     * Expression subclass objects based on the current token that is eaten.
     * 
     * @return the parsed factor
     */
    public Expression parseFactor() throws ScanErrorException
    {
        if(currentToken.equals("("))
        {
            eat("(");
            Expression expr = parseExpression();
            eat(")");
            return expr;
        }
        else if(currentToken.equals("-"))
        {
            eat("-");
            return new BinOp("-", new ast.Number(0), parseFactor());
        }
        else
        {
            String token = currentToken;
            try
            {
                int val = Integer.parseInt(currentToken);
                eat(currentToken);
                ast.Number num = new ast.Number(val);
                return num;
            } catch(Exception e)
            {
                eat(currentToken);
                if(currentToken.equals("("))
                {
                    eat("(");
                    ArrayList<Expression> params = new ArrayList();
                    while(!currentToken.equals(")"))
                    {
                        params.add(parseExpression());
                        if(currentToken.equals(","))
                        {
                            eat(",");
                        }
                    }
                    eat(")");
                    return new ProcedureCall(token, params);
                }
                else
                {
                    return new Variable(token);
                }
            }
        }
    }

    /**
     * Returns a BinOp object that represents the basic arithmetic process
     * of division and multiplication
     * 
     * @return the BinOp object representing the arithmetic processes
     * 
     */
    private Expression parseTerm() throws ScanErrorException
    {
        Expression val = parseFactor();
        while(currentToken.equals("/") || currentToken.equals("*"))
        {
            if(currentToken.equals("/"))
            {
                eat("/");
                val = new BinOp("/", val, parseFactor());
            }
            else if(currentToken.equals("*"))
            {
                eat("*");
                val = new BinOp("*", val, parseFactor());
            }

        }
        return val;
    }

    /**
     * Returns a BinOp object that represents the basic arithmetic process
     * of subtraction and addition
     * 
     * @return the BinOp object representing the arithmetic processes
     */
    private Expression parseExpression() throws ScanErrorException
    {
        Expression val = parseTerm();
        while(currentToken.equals("+") || currentToken.equals("-"))
        {
            if(currentToken.equals("+"))
            {
                eat("+");
                val = new BinOp("+", val, parseTerm());
            }
            else if(currentToken.equals("-"))
            {
                eat("-");
                val = new BinOp("-", val, parseTerm());
            }
        }
        return val;
    }

    /**
     * Parses the block before the END token by adding statements that are parsed
     * to an ArrayList and creating a Block based on the ArrayList
     * 
     * @return the block that is parsed
     */    
    private Block parseBlock() throws ScanErrorException
    {
        ArrayList<Statement> a =new ArrayList<Statement>();
        while(!currentToken.equals("END"))
        {
            a.add(parseStatement());
        }
        return new Block(a);
    }

    /**
     * Instantiates and Returns a Conditional object with the parsed 
     * expressions and comparative operator
     * 
     * @return the conditional obj made with the parsed expression and comp op
     */
    public Conditional parseConditional() throws ScanErrorException
    {
        Expression exp1 = parseExpression();
        String compOp = parseCompOp();
        Expression exp2 = parseExpression();
        return new Conditional(compOp, exp1, exp2);
    }

    /**
     * Eats the comparative operator and returns the string version of it
     * @return the string version of the comparative operator
     */
    public String parseCompOp() throws ScanErrorException
    {
        if(currentToken.equals("="))
        {
            eat("=");
            return "=";
        }
        else if(currentToken.equals("<>"))
        {
            eat("<>");
            return "<>";
        }
        else if(currentToken.equals("<"))
        {
            eat("<");
            return "<";
        }
        else if(currentToken.equals(">"))
        {
            eat(">");
            return ">";
        }
        else if(currentToken.equals("<="))
        {
            eat("<=");
            return "<=";
        }
        else
        {
            eat(">=");
            return ">=";
        }
    }

    /**
     * Parses and eats a list of ProcedureDeclaration and list of VAR names
     * given in the body of text after a "PROCEDURE" token and VAR token. Continues parsing
     * tokens as long as the current token is "PROCEDURE" or VAR. Returns
     * a Program object made with the list of VARs, ProcedureDeclarations, and
     * parsed statement.
     * 
     * @return the created Program object
     */
    public Program parseProgram() throws ScanErrorException
    {
        ArrayList<String> vars = new ArrayList<String>();
        while(currentToken.equals("VAR"))
        {
            eat("VAR");
            while (!currentToken.equals(";"))
            {
                vars.add(currentToken);
                eat(currentToken);
                if(currentToken.equals(","))
                {
                    eat(",");
                }
            }
            eat(";");
        }
        List<ProcedureDeclaration> pd = new ArrayList<ProcedureDeclaration>();
        while(currentToken.equals("PROCEDURE"))
        {
            eat("PROCEDURE");
            String name = currentToken;
            eat(name);
            eat("(");
            ArrayList<String> params = new ArrayList();
            while(!currentToken.equals(")"))
            {
                String newparam = currentToken;
                eat(newparam);
                params.add(newparam);
                if(currentToken.equals(","))
                {
                    eat(",");
                }
            }
            eat(")");
            eat(";");
            pd.add(new ProcedureDeclaration(name, parseStatement(), params));
        }
        Statement stmt = parseStatement();
        return new Program(vars, pd, stmt);
    }
}

