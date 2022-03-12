import java.io.File;
import java.util.Scanner;

public class FakeLisp {
    private int floor;
    private String parens;

    public FakeLisp(String address) {
        parens = makeParens(address);
        floor = findFloor();
    }

    public int getFloor() {
        return this.floor;
    }

    private String makeParens(String address) {
        String p = "";
        
        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            p = stdin.nextLine();

            stdin.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        
        return p;
    }

    private int findFloor() {
        int fl = 0;
        return fl;
    }
}