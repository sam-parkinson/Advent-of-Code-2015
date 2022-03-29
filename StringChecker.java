import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StringChecker {
    private char[][] splitStrings;
    private int niceCount;

    public StringChecker(String address) {
        makeStrings(address);
        countNiceStrings();
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
            if (checkStringNice(splitStrings[i])) {
                nice++;
            }
        }

        niceCount = nice;
    }

    private boolean checkStringNice(char[] str) {
        // conditions: 
        // contains 3 vowels (can be same vowel)
        // contains pair of consecutive letters
        // does not contain substrings ab, cd, pq, xy
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

        return hasConsec && vowelCounter <= 3;
    }
}