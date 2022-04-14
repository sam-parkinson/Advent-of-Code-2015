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
        ArrayList<Integer> outputIndices;
        int value;

        private int getValue() {
            return this.value;
        }
    }

    private LogicGate[] gateArr;
    private HashMap<String, Wire> wireMap;

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

    public LogicCircuit(String address) {
        makeGates(address);
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
        // split string into component parts
        // go through array

        // while the string is not ->

        // if lowercase or number, add to input array
        // if uppercase, add to operation
        
        // add last item to output
        return null;
    }
}