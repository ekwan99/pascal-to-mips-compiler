package ast;
import environment.Environment;
import java.util.*;
/**
 * ProcedureDeclaration objects contain a name, Statement, parameters that are
 * the basis of the object, and corresponding get methods. The class also contains
 * a exec method that creates a key-value object in the Procedure HashMap in the given
 * environment.
 *
 * @author Emily Kwan
 * @version 11/8/23
 */
public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement stmt;
    private ArrayList<String> parameters;
    private ArrayList<String> localVars;
    /**
     * Constructor for objects of class ProcedureDeclaration
     * 
     * @param name the name of the ProcedureDeclaration
     * @param stmt the statment of the ProcedureDeclaration
     * @param parameters the parameters that are the basis of the ProcedureDeclaration
     */
    public ProcedureDeclaration(String name, Statement stmt, ArrayList<String> parameters)
    {
        this.name = name;
        this.stmt = stmt;
        this.parameters = parameters;
    }

    /**
     * Creates a key-value object in the Procedure HashMap in the given
     * environment.
     * 
     * @param env the given environment whose HashMap will be modified
     */
    public void exec(Environment env)
    {
        env.setProcedure(name, this);
    }

    /**
     * Returns the statement of the ProcedureDeclaration
     * 
     * @return the statement of the ProcedureDeclaration
     */
    public Statement getBody()
    {
        return stmt;
    }
    
    /**
     * Returns the parameters of the ProcedureDeclaration
     * @return the paramters of the ProcedureDeclaration
     */
    public ArrayList<String> getParameters()
    {
        return parameters;
    }
    /**
    public void compile(Emitter e)
    {
        e.emit("proc" + name + ":");
        for(String local: localVars)
        {
            e.emit("li $v0 0");
            e.emitPush("$v0");
        }
        e.emitPush("$ra");
        stmt.compile(e);
        for(String local: localVars)
        {
            e.emitPop("$v0");
        }
        e.emit("jr $ra");
    }
    */
}
