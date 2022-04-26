import java.io.File;
import java.util.Scanner;

public class CityMapper {

    public CityMapper(String address) {

    }

    private void parseInput(String address) {

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}