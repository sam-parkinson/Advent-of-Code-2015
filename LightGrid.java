import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LightGrid {
    private class Instruction {
        int x1, y1, x2, y2;
        int inst;

        private Instruction(int inst, int x1, int y1, int x2, int y2) {
            this.inst = inst;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    boolean[][] grid;
    int[][] brightGrid;
    Instruction[] instructions;
    int lit;
    int brightness;
    
    public LightGrid(String address) {
        parseInput(address);
        grid = new boolean[1000][1000];
        brightGrid = new int[1000][1000];
        parseInstructions();
        lit = countLit();
        brightness = countBrightness();
    }

    public int getLit() {
        return this.lit;
    }

    public int getBrightness() {
        return this.brightness;
    }

    private void parseInput(String address) {
        ArrayList<String[]> lineArr = new ArrayList<String[]>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                lineArr.add(stdin.nextLine().split(" "));
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        instructions = new Instruction[lineArr.size()];

        for (int i = 0; i < lineArr.size(); i++) {
            String[] arr = lineArr.get(i);
            int x = arr[0].equals("turn") ? 1 : 0;
            
            instructions[i] = makeInstruction(arr, x);
        }
    }

    private Instruction makeInstruction(String[] arr, int x) {
        int inst, x1, y1, x2, y2;

        String instruction = arr[x];

        switch (instruction.charAt(1)) {
            case 'n':
                inst = 1;
                break;
            case 'f':
                inst = -1;
                break;
            case 'o':
            default:
                inst = 0;
                break;
        }

        String[] first = arr[x+1].split(",");
        String[] second = arr[x+3].split(",");

        x1 = Integer.parseInt(first[0]);
        y1 = Integer.parseInt(first[1]);
        x2 = Integer.parseInt(second[0]);
        y2 = Integer.parseInt(second[1]);

        return new Instruction(inst, x1, y1, x2, y2);
    }

    private void parseInstructions() {
        Instruction curr;
        for (int i = 0; i < instructions.length; i++) {
            // three function choices -- use switch statement based on instruction
            // either turn off, toggle, or turn on
            curr = instructions[i];
            switch(curr.inst) {
                case 1:
                    turnOn(curr.x1, curr.y1, curr.x2, curr.y2);
                    break;
                case -1:
                    turnOff(curr.x1, curr.y1, curr.x2, curr.y2);
                    break;
                case 0:
                default:
                    toggle(curr.x1, curr.y1, curr.x2, curr.y2);
                    break;

            }
        }
    }

    // go through array, either turning values to true, turning to false, or flipping
    private void turnOn(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (grid[i][j] != true)
                    grid[i][j] = true;

                brightGrid[i][j]++;
            }
        }
    }

    private void turnOff(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (grid[i][j] != false)
                    grid[i][j] = false;

                if (brightGrid[i][j] > 0)
                    brightGrid[i][j]--;
            }
        }
    }

    private void toggle(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                grid[i][j] = !grid[i][j];
                brightGrid[i][j] += 2;
            }
        }
    }

    private int countLit() {
        int lit = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == true)
                    lit++;
            }
        }

        return lit;
    }

    private int countBrightness() {
        int brightness = 0;

        for (int i = 0; i < brightGrid.length; i++) {
            for (int j = 0; j < brightGrid[i].length; j++) {
                brightness += brightGrid[i][j];
            }
        }

        return brightness;
    }
}