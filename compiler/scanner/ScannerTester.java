package scanner;
import java.io.*;
/**
 * Tests the scanner.Scanner class via test files
 * @author Emily Kwan
 * @version 08/31/2023
 */
public class ScannerTester
{
    /**
     * Tests the scanner.Scanner using "ScannerText.txt" file
     * @param str array of String objects
     * @throws FileNotFoundException if there is an I/O error
     * @throws ScanErrorException if an token is invalid
     */
    public static void main(String[] str) throws FileNotFoundException, 
                                                 ScanErrorException
    {
        InputStream inputstream = 
                                new FileInputStream("/"
                                +"Users/24emilyk"
                                +"/Desktop/Programming Projects"
                                + "/Compilers and Interpreters/scanner/ScannerTest (1).txt");
        Scanner scanner = new Scanner(inputstream);
        while (scanner.hasNext())
        {
            System.out.println(scanner.nextToken());
        }
        
    }
}
