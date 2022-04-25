import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Matchsticks {
    String[] rawArr, netArr;
    int rawCharCount, netCharCount, cookedCharCount;

    public Matchsticks(String address) {
        parseStrings(address);
        rawCharCount = 0;
        netCharCount = 0;
        cookedCharCount = 0;
        countCharacters();
    }

    public int getDifference() {
        return this.rawCharCount - this.netCharCount;
    }

    public int getCookedDiff() {
        return this.cookedCharCount - this.rawCharCount;
    }

    private void parseStrings(String address) {
        ArrayList<String> strList = new ArrayList<String>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                strList.add(stdin.nextLine());
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        rawArr = new String[strList.size()];
        rawArr = strList.toArray(rawArr);

        netArr = new String[rawArr.length];
        for (int i = 0; i < netArr.length; i++) {
            netArr[i] = reEscapeString(rawArr[i]);
        }
    }

    private void countCharacters() {
        for (int i = 0; i < rawArr.length; i++) {
            this.rawCharCount += rawArr[i].length();
            this.netCharCount += netArr[i].length();
            this.cookedCharCount += cookedCharCounter(rawArr[i]);
        }
    }

    private String reEscapeString(String str) {
        StringBuilder netStr = new StringBuilder();

        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == '\\') {
                switch (str.charAt(i + 1)) {
                    case 'x':
                        String hex = str.substring(i + 2, i + 4);
                        netStr.append((char) Integer.parseInt(hex, 16));
                        i += 3;
                        break;
                    case '\\':
                        netStr.append('\\');
                        i++;
                        break;
                    case '"':
                    default:
                        netStr.append('"');
                        i++;
                        break;
                }
            } else {
                netStr.append(str.charAt(i));
            }
        }

        return netStr.toString();
    }

    private int cookedCharCounter(String str) {
        int count = 2;

        for (int i = 0; i < str.length(); i++) {
            count += (str.charAt(i) == '\\' || str.charAt(i) == '"') ? 2 : 1;
        }
        
        return count;
    }
}