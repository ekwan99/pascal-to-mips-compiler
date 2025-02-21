package ast;
import environment.Environment;


/**
 * Expression class is the abstract class representing
 * all expressions that are used in executing code (arithmetic
 * and other mathematical operations)
 *
 * @author Emily Kwan
 * @version 10/10/23
 */
public abstract class Expression
{
    /**
     * Provides subclasses with a type of template 
     * on how they will evaluate themselves
     * 
     * @param env the environment in which the classes evaluate code
     * @return the value of the given object of the particular subclass
     */
    public abstract int eval(Environment env);
    
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

