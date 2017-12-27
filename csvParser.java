import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class csvParser
{
    // instance variables - replace the example below with your own
    private int x;
    public static void main(String[]args) throws FileNotFoundException
    {
    Scanner scan = new Scanner(new BufferedReader(new FileReader("C:/Users/Sanketh Kolli/Desktop/encrypted/DNRformover18")));
    scan.useDelimiter(",");

    //3rd field is HospID
    parseLine(scan);
    }
    public static void parseLine(Scanner scan)
    {
        int count = 36;
        while(count<10000)
        {
            if(count%36==0)
            System.out.print(scan.next());
            else
            scan.next();
            count++;
        }
    }
}
