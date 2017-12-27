import java.io.*;
import java.util.Scanner;

public class CharacterFrequency
{
    public static void main(String[]args) throws IOException
    {
        try
        {
            boolean end = false;
            Scanner s = new Scanner(new BufferedReader(new FileReader("letter1.txt")));
            FrequencyBag characters = new FrequencyBag();
            char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            while(s.hasNext())
            {
                String token = s.next();
                token = token.toLowerCase();
                for(int i = 0; i<token.length();i++)
                {
                    characters.add(token.charAt(i));
                }
            }
            for(int j = 0; j<26;j++)
            {
                int frequency = characters.getFrequencyOf(alphabet[j]);
                System.out.println(alphabet[j] + ": " + frequency);
            }
        }
        catch (IOException ex)
        {
            System.out.print(ex);
        }
    }       
}