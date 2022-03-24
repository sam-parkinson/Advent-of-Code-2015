import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AdventCoin {
    private String input;
    private int smallestFiver;
    private int smallestSixer;

    public AdventCoin(String address) {
        input = getInput(address);
        smallestFiver = findSmallestInt(5);
        smallestSixer = findSmallestInt(6);
    }

    public int getSmallestFiver() {
        return this.smallestFiver;
    }

    public int getSmallestSixer() {
        return this.smallestSixer;
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

    private int findSmallestInt(int x) {
        int i = 0;
        String str = "";
        while (i != -1) {
            str = findFirst5MD5(input + i, x);
            if (str.equals("0".repeat(x))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    // https://www.geeksforgeeks.org/md5-hash-in-java/

    private String findFirst5MD5(String input, int x) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext.substring(0, x);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return "";
    }
}