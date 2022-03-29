import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StringChecker {
    private char[][] splitStrings;
    private int niceCount;

    public StringChecker(String address) {
        makeStrings(address);
    }

    public int getNiceCount() {
        return this.niceCount;
    }

    private void makeStrings(String address) {
        ArrayList<String> strings = new ArrayList<String>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                strings.add(stdin.nextLine());
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        splitStrings = new char[strings.size()][strings.get(0).length()];

        for (int i = 0; i < splitStrings.length; i++) {
           splitStrings[i] = strings.get(i).toCharArray(); 
        }
    }

    private void countNiceStrings() {
        int nice = 0;

        for (int i = 0; i < splitStrings.length; i++) {
            
        }

        niceCount = nice;
    }

    private boolean checkStringNice(char[] str) {

        return false;
    }
}