package ast;
import java.io.*;

public class Emitter
{
    private PrintWriter out;
    private int counter;
    //creates an emitter for writing to a new file with given name
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
        counter = 0;
    }

    //prints one line of code to file (with non-labels indented)
    public void emit(String code)
    {
        if (!code.endsWith(":"))
            code = "\t" + code;
        out.println(code);
    }

    //closes the file.  should be called after all calls to emit.
    public void close()
    {
        out.close();
    }
    
    /**
     * Emits the MIPS code to push an element onto a stack
     * from the given register by moving the stack pointer 
     * 4 spaces.
     * 
     * @param reg the name of the register of the element being pushed onto the stack
     */
    public void emitPush(String reg)
    {
        emit("subu $sp $sp 4");
        emit("sw " + reg + " ($sp)");
    }
    
    /**
     * Emits the MIPS code to pop an element from a stack
     * from the given register by moving the stack pointer 
     * 4 spaces.
     * 
     * @param reg the name of the register of the element being popped from the stack
     */
    public void emitPop(String reg)
    {
        emit("lw " + reg + " ($sp)");
        emit("addu $sp $sp 4");
    }
    
    /**
     * Returns the next label ID accounted for by the accumulating
     * instance variable counter.
     * 
     * @return the next label ID
     */
    public int nextLabelID()
    {
        counter++;
        return counter;
    }
}