import java.io.PrintStream;
import java.util.Scanner;

public class Rycox {

    private static PrintStream p = System.out;

    public static void main(String[] args) {
        TVSystem ts = new TVSystem();

        Scanner cs = new Scanner(System.in);
        ts.loginMenu();

    }
}
