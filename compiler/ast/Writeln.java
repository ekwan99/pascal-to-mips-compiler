package ast;
import environment.Environment;

/**
 * The Writeln class represents the functionality of the Writeln function.
 * In the execution of this statement, the expression is evaluated then printed.
 *
 * @author Emily Kwan
 * @version 10/25/23
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructs objects of the Writeln class
     * @param exp the expression to be evaluated then printed
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * Evaluates then prints the given expression
     * @param env the environment in which the classes execute code
     */
    @Override
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }

    /**
     * Emits MIPS code that prints a given expression and 
     * then a new line.
     * 
     * @param e the emitter that emits the MIPS code
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("move $a0 $v0");
        e.emit("li $v0 1");
        e.emit("syscall");
        e.emit("la $a0, nL");
        e.emit("li $v0, 4");
        e.emit("syscall"); 
    }
}