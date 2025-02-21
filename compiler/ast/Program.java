package ast;
import java.util.*;
import environment.Environment;
/**
 * The Program class serves as the root of the AST. Contains a list of
 * ProcedureDeclarations, a Statement, corresponding get methods, and an exec method.
 *
 * @author Emily Kwan
 * @version 11/8/23
 */
public class Program extends Statement
{
    private List<String> varNames;
    private List<ProcedureDeclaration> procs;
    private Statement stmt;

    /**
     * Constructor for objects of class Program
     * 
     * @param procs the list of ProcedureDeclaration in the Program
     * @param stmt the Statement in the Program
     * @param varNames the name of the Variables assigned in the Program
     */
    public Program(List<String> varNames, List<ProcedureDeclaration> procs, Statement stmt)
    {
        this.procs = procs;
        this.stmt = stmt;
        this.varNames = varNames;
    }

    /**
     * Returns the list of ProcedureDeclarations contained in the Program
     * 
     * @return the list of ProcedureDeclarations
     */
    public List<ProcedureDeclaration> getProcedures()
    {
        return procs;
    }

    /**
     * Returns the Statement contained in the Program
     * 
     * @return the Statement
     */
    public Statement getStatement()
    {
        return stmt;
    }

    /**
     * Executes the specified list of ProcedureDeclarations one by one in the
     * given environment. Then, the specified statement is executed in the given
     * environment and 0 is returned.
     * 
     * @param env the given environment where code is executed
     */
    public void exec(Environment env)
    {
        for(ProcedureDeclaration p : procs)
        {
            p.exec(env);
        }
        stmt.exec(env);
    }

    /**
     * Creates an emitter to compile a program of the given file name.
     * 
     * @param fileName the name of the file being compiled
     */
    public void compile(String fileName)
    {
        Emitter e = new Emitter(fileName);
    }

    /**
     * Emits MIPS code to compile a program. Writes the following
     * template that structures the code in the output file.
     * 
     * @param e the emitter that emits the MIPS code
     */
    public void compile(Emitter e)
    {
        e.emit(".data");
        e.emit("nL:    .asciiz    \"\\n\"");
        for(String v: varNames)
        {
            e.emit("var" + v + ":\t .word \t 0");
        }
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main: ");
        stmt.compile(e);
        e.emit("li $v0 10");
        e.emit("syscall");
        e.close();
    }
}
