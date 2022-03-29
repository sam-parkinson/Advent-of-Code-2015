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
        smallestFiver = -1;
        smallestSixer = -1;
        findSmallestInt();
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

    private void findSmallestInt() {
        int i = 0;
        String[] arr;
        while (smallestFiver == -1 || smallestSixer == -1) {
            arr = findFirst5MD5(input + i);
            if (smallestFiver == -1 && arr[0].equals("00000")) {
                smallestFiver = i;
            }

            if (smallestSixer == -1 && arr[1].equals("000000")) {
                smallestSixer = i;
            }
            i++;
        }
    }

    // https://www.geeksforgeeks.org/md5-hash-in-java/

    private String[] findFirst5MD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return new String[] {hashtext.substring(0, 5), hashtext.substring(0, 6)};
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return null;
    }
}