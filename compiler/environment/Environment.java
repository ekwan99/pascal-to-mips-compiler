package environment;
import ast.*;
import java.util.*;
import java.util.Map;
import java.util.HashMap;

/**
 * The Environment class creates environments that contains instructions
 * for how code should be evaluated/executed
 *
 * @author Emily Kwan
 * @version 10/25/23
 */
public class Environment
{
    private HashMap<String, Integer> variables;
    private HashMap<String, ProcedureDeclaration> pd;
    private Environment parent;

    /**
     * Constructs objects of the Environment class
     */
    public Environment()
    {
        this.variables = new HashMap<String, Integer>();
        this.pd = new HashMap<String, ProcedureDeclaration>();
        this.parent = null;
    }

    /**
     * Constructs objects of the Environment class when given a parent Environment
     * 
     * @param parent the parent Environment of the Environment being instantiated
     */
    public Environment(Environment parent)
    {
        this.variables = new HashMap<String, Integer>();
        this.pd = new HashMap<String, ProcedureDeclaration>();
        this.parent = parent;
    }

    /**
     * Adds variable to the environment (map) if it isn't contained
     * already. If the variable is contained in the parent Environment,
     * its value is set in the parent Environment; Otherwise, it is put in
     * the current environment.
     * 
     * @param variable the variable to be stored
     * @param value the value to be stored by the variable
     */
    public void setVariable(String variable, int value)
    {
        if(variables.containsKey(variable))
        {
            variables.put(variable, value);
            return;
        }
        else 
        {
            if(parent != null)
            {
                if(parent.variables.containsKey(variable))
                {
                    parent.variables.put(variable, value);
                    return;
                }
            }
        }
        variables.put(variable, value);    
    }

    /**
     * Returns the value or the variable contained within the
     * environment/map. If the variable is contained in the parent Environment,
     * its value is returned from the parent Environment; Otherwise, it is returned
     * from the current environment.
     * 
     * @param variable the name of the variable that is searched for in the map
     * @return the value of the variable
     */
    public int getVariable(String variable)
    {
        if(variables.containsKey(variable))
        {
            return variables.get(variable);
        }
        else 
        {
            if(parent != null)
            {
                if(parent.variables.containsKey(variable))
                {
                    return parent.variables.get(variable);
                }
            }
        }
        return variables.get(variable);
    }

    /**
     * Puts the name and corresponding ProcedureDeclaration in the
     * HashMap.
     * 
     * @param name the name of the ProcedureDeclaration
     * @param proc the ProcedureDeclaration corresponding the name that is placed
     * in the HashMap
     */
    public void setProcedure(String name, ProcedureDeclaration proc)
    {
        pd.put(name, proc);
    }

    /**
     * Returns the ProcedureDeclaration corresponding the given name in the
     * HashMap. If the parent is not null, then the parent's procedure is returned.
     * 
     * @param name the name of the ProcedureDeclaration that is returned
     * @return the ProcedureDeclaration that corresponds to the given name
     */
    public ProcedureDeclaration getProcedure(String name)
    {
        if(parent != null)
        {
            return parent.getProcedure(name);
        }
        return pd.get(name);
    }

    /**
     * Puts a variable into the variable HashMap with the given variable and value.
     * 
     * @param variable the variable to be assigned in the HashMap
     * @param value the value to be assigned to the variable in the HashMap
     */
    public void declareVariable(String variable, int value)
    {
        variables.put(variable, value);
    } 

    /**
     * Returns the parent environment of the current Environment
     * 
     * @return the parent Environment
     */
    public Environment getParent()
    {
        return parent;
    }
}
