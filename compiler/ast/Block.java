package ast;
import java.util.ArrayList;
import environment.Environment;

/**
 * The Block class groups multiple statements together so that they
 * can be executed together
 *
 * @author Emily Kwan
 * @version 10/25/23
 */
public class Block extends Statement
{
    private ArrayList<Statement> statements;

    /**
     * Constructs Block objects
     * @param statements the list of statements within the block
     */
    public Block(ArrayList<Statement> statements)
    {
        this.statements = statements;
    }
    
    /**
     * Executes the whole block by going through
     *  each statement in the list (block)
     * 
     * @param env the environment in which the classes execute code

     */
    @Override
    public void exec(Environment env)
    {
        for (int i = 0; i < statements.size(); i++)
        {
            Statement stat = statements.get(i);
            stat.exec(env);
        }
    }
    
    /**
     * Emits MIPS code that evaluates a block of statements
     * one by one.
     * 
     * @param e the emitter that emits the MIPS code
     */
    public void compile(Emitter e)
    {
        for(Statement stmt : statements)
        {
            stmt.compile(e);
        }
    }
}
