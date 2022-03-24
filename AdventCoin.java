import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AdventCoin {
    private String input;
    private int smallestInt;

    public AdventCoin(String address) {
        input = getInput(address);

    }

    private String getInput(String address) {
        String input = "";

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            input = stdin.nextLine();

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return input;
    }

    private int findSmallestInt() {

        return -1;
    }

    // https://www.geeksforgeeks.org/md5-hash-in-java/

    private String findMD5(String input) {
        return "";
    }
}