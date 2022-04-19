import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LogicCircuit {
    private class LogicGate {
        String[] input;
        String output;
        String operation; // maybe make this an enum type?
        
        private LogicGate(String[] input, String output, String operation) {
            input = this.input;
            output = this.output;
            operation = this.operation;
        }
    }

    private class Wire {
        ArrayList<Integer> inputIndices;
        int outputIndex, value;

        private Wire() {
            this.inputIndices = new ArrayList<Integer>();
            this.outputIndex = -1;
            this.value = -1;    // setting value to -1 gives us a means to check which wires do not have values calculated yet
        }

        private int getValue() {
            return this.value;
        }
    }

    private LogicGate[] gateArr;
    private HashMap<String, Wire> wireMap;  

    public LogicCircuit(String address) {
        makeGates(address);
        buildWires();
        // process wires
        // call function to start processing logic circuit
    }

    // getter for wire value

    private void makeGates(String address) {
        ArrayList<String> lineArr = new ArrayList<String>();

        try {
            File file = new File(address);

            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                lineArr.add(stdin.nextLine());
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        gateArr = new LogicGate[lineArr.size()];

        for (int i = 0; i < gateArr.length; i++) {
            gateArr[i] = parseGateStr(lineArr.get(i));
        }
    }

    private LogicGate parseGateStr(String str) {

        String[] arr = str.split(" ");
        int i = 0;

        String output = arr[arr.length - 1];
        String operation = "";

        ArrayList<String> inputList = new ArrayList<String>();

        while (!arr[i].equals("->")) {
            if (Character.isUpperCase(arr[i].charAt(0))) {
                operation = arr[i];
            } else {
                inputList.add(arr[i]);
            }

            i++;
        }

        String[] input = new String[inputList.size()];
        input = inputList.toArray(input);

        
        return new LogicGate(input, operation, output);
    }

    private void buildWires() {
        for (int i = 0; i < gateArr.length; i++) {
            // check inputs

            if (Character.isLowerCase(gateArr[i].input[0].charAt(0))) {
                // create wire if not existing
                // add gate index to input
            }

            if (gateArr[i].input.length == 2 && Character.isLowerCase(gateArr[i].input[1].charAt(0))) {
                // create wire if not existing
                // add gate index to input
            }

            // output is always a string
            // create wire if not existing, set output index
        }
    }

    private void processGates() {
        // go through gate arr
        // check to see if we know output value, if not:
            // run input values through operation, assign to output wire's value
            // if input values unknown apply recursion
    }
}

/*
    Each logic gate is made up of:
    1. Input
    2. Operation
    3. Output

    Output is wire
    Input can be either wire or number

    Can also be wire and wire, wire and number, or number and number

    Operations are bitwise arithmetic

    Wires exist if they show up in either the input or output of a logic gate
    When setting up circuit, check if input is number or letter
*/

/*
    Data structure note things

    Hashmap?

    Wires and gates

    What we want is values on wires

    Wire, input, output, number value, operation

    Recursion
        Base case: number
        Exit case: no output

    Start with wire we know has no further output (i.e., appears in output table but not in input table) -- can be more than one such wire
    Look at the input wires for that, get values of those wires
    Keep going until base case(s) -- this is going to be filling out a tree?

    Step one: parse input into logic gates

    Step two: go through logic gates, find out which wires exist

    Step three: map wires to connections and vice-versa

    Each gate knows what wires it inputs to and hashes to them easily
    Each wire knows index of output and input gates
    
*/