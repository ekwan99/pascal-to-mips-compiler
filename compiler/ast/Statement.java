package ast;
import environment.Environment;


/**
 * Statement class is the abstract class representing
 * all statements that are used in executing code
 *
 * @author Emily Kwan
 * @version 10/10/23
 */
public abstract class Statement
{   
    /**
     * Provides subclasses with a type of template 
     * on how they will instruct themselves
     * 
     * @param env the environment in which the classes execute code
     */
    
    public abstract void exec(Environment env);
    
    /**
     * Provides subclasses with a type of template on how they
     * will emit MIPS code based on what AST component it is.
     * 
     * @param e the emitter that emits the MIPS code
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Implement me!!!!!");
    }
}

