import java.util.Scanner;
import java.util.Random;
public class general
{
    public static void print (String txt)
    {
        System.out.println(txt);
        return;
    }
    public static String getstring (String x)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(x);
        String response = scanner.nextLine();
        return response;
    }
    public static int getint (String x)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(x);
        int response = scanner.nextInt();
        return response;
    }
    public static int getRand (int limit)
    {
        Random rand = new Random();
        int random = rand.nextInt(limit) + 1;
        return random;
    }
}
