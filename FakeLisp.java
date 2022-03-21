import java.io.File;
import java.util.Scanner;

public class FakeLisp {
    private int floor;
    private int basement;
    private String parens;

    public FakeLisp(String address) {
        basement = -1;
        parens = makeParens(address);
        floor = findFloor();
    }

    public int getFloor() {
        return this.floor;
    }

    public int getBasement() {
        return this.basement;
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

        for (int i = 0; i < parens.length(); i++) {
            if (parens.charAt(i) == '(')
                fl++;
            else 
                fl--;

            if (basement == -1 && fl == -1) {
                basement = i + 1;
            }
        }

        return fl;
    }
}