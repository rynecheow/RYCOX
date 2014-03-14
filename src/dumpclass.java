import java.io.PrintStream;


public class dumpclass {

    private static PrintStream p = System.out;

    public dumpclass() {
    }

    public void a() {
        p.print("Menu1");
        b();
    }

    public void b() {
        p.print("\nMenu2");
    }
}
