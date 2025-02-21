package ast;
import environment.Environment;
/**
 * The While class contains instructions on how the compiler should
 * handle "while" loop statements
 * 
 * @author Emily Kwan
 * @version 10/25/23
 */
public class While extends Statement
{
    private Conditional conditional;
    private Statement stat1;

    /**
     * Constructs objects of the While class when 
     * given 1 condition and 1 statement
     * @param c the conditional expression to be executed
     * @param s1 the first statement to be executed if conditional is true
     */
    public While(Conditional c, Statement s1)
    {
        conditional = c;
        stat1 = s1;
    }

    /**
     * Executes the while loop
     * Checks if the condition is true, then
     * executes the statement until the condition
     * is no longer met
     * 
     * @param env the environment in which the classes execute code

     */
    @Override
    public void exec(Environment env) 
    {
        int ret = conditional.eval(env);
        while (ret == 1)
        {
            if (stat1 != null)
            {
                stat1.exec(env);
            }
            ret = conditional.eval(env);
        }
    }
    
    /**
     * Emits code that evaluates a conditional and runs a 
     * while loop accordingly. Re-compiles the statement everytime
     * and jumps back or ends the while loop based on the evaluation.
     * 
     * @param e the emitter that emits the MIPS code
     */
    public void compile(Emitter e)
    {
        int label = e.nextLabelID();
        e.emit("while" + label+ ":");
        conditional.compile(e, "endwhile" + label);
        stat1.compile(e);
        e.emit("j while" + label);
        e.emit("endwhile" + label+ ":");
    }
}