package ast;
import environment.Environment;
/**
 * The Variable class is involved in the process of creating
 * and instantiating variables
 *
 * @author Emily Kwan
 * @version 10/10/23
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Creates objects of the Variable class
     * @param name the name of the variable
     */
    public Variable(String name)
    {
        this.name = name;
    }

    /**
     * Evaluates objects of the Variable class by returning
     * its value within the given environment
     * 
     * @param env the environment in which the classes evaluate code
     * @return the value of the variable

     */
    @Override
    public int eval(Environment env) 
    {
        return env.getVariable(name);
    }

    /**
     * Emits MIPS code that loads the value associated with the
     * variable's address into the $v0 register.
     * 
     * @param e the emitter that emits the MIPS code

     */
    public void compile(Emitter e)
    {
        e.emit("la $t1 var" + name);
        e.emit("lw $v0 ($t1)");
    }
}
