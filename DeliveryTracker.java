import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class DeliveryTracker {
    private char[] directions;
    private HashMap<Integer[], Integer> coordinates;

    public DeliveryTracker(String address) {
        directions = makeDirections(address);
    }

    public int getTotalHouses() {
        return coordinates.size();
    }

    private char[] makeDirections(String address) {
        String input = "";

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            input = stdin.nextLine();

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        char[] directions = new char[input.length()];

        for (int i = 0; i < directions.length; i++) {
            directions[i] = input.charAt(i);
        }

        return directions;
    }

    private void followDirections() {
        // {x, y}
        // > right, < left
        // 
        Integer[] coords = {0, 0};

        coordinates.put(coords, 1);

        // start at 0, 0

        // go through directions
        // move
        // check to see if coords exist
        // if not, add
        // if so, increment
    }
}