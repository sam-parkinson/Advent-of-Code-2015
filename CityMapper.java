import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CityMapper {
    private ArrayList<String> cityNames;
    private String[] edgeList;
    private int[][] adjacencyMatrix;

    public CityMapper(String address) {
        parseInput(address);
        // after parsing input generate adj matrix
        // then call graph algorithms
    }

    private void parseInput(String address) {
        ArrayList<String> edgeArrayList = new ArrayList<String>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                edgeArrayList.add(stdin.nextLine());
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        

        // populate edgeList with formatted edges, while adding city names as new ones are encountered
   
    }
}
/*
 * Part one of this essentially asks for the shortest Hamilton path through the graph
 * 
 * This is a path that touches each vertex exactly once. We don't care where we start and end
 * 
 * Simplest way to do this: find the shortest Hamilton path for each starting point
 * 
 * Then find the shortest of those
 * 
 * Track order of cities, it'll be important for the second part I think
 * 
 * We're going to be using an adjacency matrix for this -- every vertex is adjacent to every other vertex
 */

/*
 * Cities and indices -- my input
 * 0: Faerun
 * 1: Tristram
 * 2: Tambi
 * 3: Norrath
 * 4: Snowdin
 * 5: Straylight
 * 6: AlphaCentauri
 * 7: Arbre
 * 
 * This may be dynamic, so we're first going to create a set of string names
 */