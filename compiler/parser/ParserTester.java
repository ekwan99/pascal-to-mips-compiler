package parser;
import scanner.Scanner;
import scanner.ScannerTester;
import scanner.ScanErrorException;
import java.io.*;
import environment.Environment;
import ast.Emitter;

/**
 * Tests the parser.Parser class via test files
 * @author Emily Kwan
 * @version 10/04/2023
 */
public class ParserTester
{
    /**
     * Tests the parser using file
     * @param str array of String objects
     * @throws FileNotFoundException if there is an I/O error
     * @throws ScanErrorException if an token is invalid
     */
    public static void main(String[] str) throws FileNotFoundException, 
                                                 ScanErrorException
    {
        InputStream inputstream = new FileInputStream("parserTest0.txt");
        Scanner scanner = new Scanner(inputstream);
        Parser parser = new Parser(scanner);
        Environment environment = new Environment();
        Emitter e = new Emitter("output5.asm");
        while (scanner.hasNext())
        {
            parser.parseProgram().compile(e);
        }
        
    }
}
