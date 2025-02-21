package ast;
import environment.Environment;
/**
 * The Conditional class contains instructions on how to evaluate expressions
 * that contain a conditional operand between a left and right expression.
 * This class returns a 1 for true and 0 for false once the exp. is evaluated.
 * @author Emily Kwan
 * @version 10/25/23
 */
public class Conditional extends Expression
{
    private String op;
    private Expression left;
    private Expression right;

    /**
     * Constructs objects of the Conditional class
     * @param operator the operator the expressions will be evaluated with
     * @param left1 the expression left of the operator
     * @param right1 the expression right of the operator
     */
    public Conditional(String operator, Expression left1, Expression right1)
    {
        op = operator;
        left = left1;
        right = right1;
    }

    /**
     * Evaluates expressions containing relative operators using boolean logic
     * @param env the environment in which the classes evaluate code
     * @return 0 or 1; 0 when expression is evaluated to be false, and 1 when evaluated to be true
     */
    @Override
    public int eval(Environment env)
    {
        int val1 = left.eval(env);
        int val2 = right.eval(env);
        int var = 0;
        if (op.equals("=="))
        {
            if (val1 == val2)
            {
                var = 1;
            }
        }
        else if (op.equals("<>"))
        {
            if (val1 != val2)
            {
                var = 1;
            }
        }
        else if (op.equals(">"))
        {
            if (val1 > val2)
            {
                var = 1;
            }
        }
        else if (op.equals("<"))
        {
            if (val1 < val2)
            {
                var = 1;
            }
        }
        else if (op.equals("<="))
        {
            if (val1 <= val2)
            {
                var = 1;
            }
        }
        else if (op.equals(">="))
        {
            if (val1 >= val2)
            {
                var = 1;
            }
        }
        return var;
    }

    /**
     * Emits code to evaluate a Conditional by storing the values of the left and right
     * expressions in a stack and different registers. Then emits code to evaluate the conditional
     * based on the relative operand.
     * 
     * @param e the emitter that emits the MIPS code
     * @param target the target label that the code branches to once the conditional is evaluated
     */
    public void compile(Emitter e, String target)
    {
        left.compile(e);
        e.emitPush("$v0");
        right.compile(e);
        e.emitPop("$t1");
        if (op.equals("="))
        {
            e.emit("bne $t1, $v0, " + target);
        }
        else if (op.equals("<>"))
        {
            e.emit("beq $t1, $v0, " + target);
        }
        else if (op.equals("<"))
        {
            e.emit("bge $t1, $v0, " + target);
        }
        else if (op.equals(">"))
        {
            e.emit("ble $t1, $v0, " + target);
        }
        else if (op.equals("<="))
        {
            e.emit("bgt $t1, $v0, " + target);
        }
        else if (op.equals(">="))
        {
            e.emit("blt $t1, $v0, " + target);
        }

    }
}