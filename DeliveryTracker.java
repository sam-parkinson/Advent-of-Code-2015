import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class DeliveryTracker {
    private class Coords {
        private int x;
        private int y;

        private Coords(int[] xy) {
            this.x = xy[0];
            this.y = xy[1];
        }

        public boolean equals(Object obj) {
            if (obj == this) 
                return true;

            if (!(obj instanceof Coords))
                return false;

            Coords c = (Coords) obj;

            return this.x == c.x && this.y == c.y;
        }

        public String toString() {
            return "[" + this.x + ", " + this.y + "]";
        }
    }

    private char[] directions;
    private HashMap<String, Integer> coordinates;

    public DeliveryTracker(String address) {
        directions = makeDirections(address);
        coordinates = new HashMap<String, Integer>();
        followDirections();
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
        int[] coords = {0, 0};

        Coords c = new Coords(coords);
        String cs = c.toString();
        coordinates.put(cs, 1);

        for (int i = 0; i < directions.length; i++) {
            switch(directions[i]) {
                case '<':
                    coords[0] -= 1;
                    break;
                case '>':
                    coords[0] += 1;
                    break;
                case '^':
                    coords[1] -= 1;
                    break;
                case 'v':
                    coords[1] += 1;
                    break;
            }

            c = new Coords(coords);
            cs = c.toString();
            
            if (coordinates.containsKey(cs)) {
                coordinates.put(cs, coordinates.get(cs) + 1);
            } else {
                coordinates.put(cs, 1);
            }
        }
    }

    // part 2: use two coords arrays, one new hashmap
    // alternate taking turns using % 2
}