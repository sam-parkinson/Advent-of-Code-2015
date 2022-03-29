import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StringChecker {
    private char[][] splitStrings;
    private int niceCount;
    private int newNiceCount;

    public StringChecker(String address) {
        makeStrings(address);
        countNiceStrings();
    }

    public int getNiceCount() {
        return this.niceCount;
    }

    public int getNewNiceCount() {
        return this.newNiceCount;
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
        int newNice = 0;

        for (int i = 0; i < splitStrings.length; i++) {
            if (checkStringNice(splitStrings[i])) {
                nice++;
            }

            if (checkStringNewNice(splitStrings[i])) {
                newNice++;
            }
        }

        niceCount = nice;
        newNiceCount = newNice;
    }

    private boolean checkStringNice(char[] str) {
        int vowelCounter = 0;
        boolean hasConsec = false;

        List<Character> badArr = Arrays.asList('a', 'c', 'p', 'x');
        List<Character> vowelArr = Arrays.asList('a', 'e', 'i', 'o', 'u');

        for (int i = 0; i < str.length; i++) {
            if (i < str.length - 1) {
                // check validity of substring
                if (badArr.contains(str[i])) {
                    if (str[i + 1] == (char)((int) str[i] + 1))
                        return false;
                }
                // check consecutive characters
                if (str[i] == str[i+1])
                    hasConsec = true;
            }
            // check char is vowel
            if (vowelArr.contains(str[i])) 
                vowelCounter++;
        }

        return hasConsec && vowelCounter >= 3;
    }

    private boolean checkStringNewNice(char[] str) {
        boolean hasTwoPair = false;
        boolean hasXYX = false;

        for (int i = 0; i < str.length - 2; i++) {
            if (i < str.length - 3) {
                for (int j = i + 2; j < str.length - 1; j++) {
                    if (str[i] == str[j] && str[i+1] == str[j+1])
                        hasTwoPair = true;
                } 
            }

            if (str[i] == str[i + 2])
                hasXYX = true;

            if (hasTwoPair && hasXYX)
                return true;
        }

        // one letter repeats with exactly one letter between them
        return false;
    }
}