import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PaperCalculator {
    private int[][] packages;
    private int footage = 0;
    private int length = 0;

    public PaperCalculator(String address) {
        packages = makePackages(address);
        findFootage();
    }

    public int getFootage() {
        return this.footage;
    }

    public int getLength() {
        return this.length;
    }

    private int[][] makePackages(String address) {
        ArrayList<String> packageList = new ArrayList<String>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while(stdin.hasNext()) {
                packageList.add(stdin.nextLine());
            }
            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        int[][] packages = new int[packageList.size()][3];

        for (int i = 0; i < packages.length; i++) {
            String[] line = packageList.get(i).split("x");

            for (int j = 0; j < line.length; j++) {
                packages[i][j] = Integer.parseInt(line[j]);
            }

            Arrays.sort(packages[i]);
        }
        return packages;
    }

    private void findFootage() {
        for (int i = 0; i < packages.length; i++) {
            calculateArea(packages[i]);
        }
    }

    private void calculateArea(int[] box) {
        int[] sides = new int[3];

        sides[0] = box[0] * box[1];
        sides[1] = box[0] * box[2];
        sides[2] = box[1] * box[2];
        
        Arrays.sort(sides);
          
        footage += 3*sides[0] + 2*sides[1] + 2*sides[2];

        length += 2*box[0] + 2*box[1] + box[0]*box[1]*box[2];
    }

}