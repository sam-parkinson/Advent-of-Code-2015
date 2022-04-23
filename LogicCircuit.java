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
            this.input = input;
            this.output = output;
            this.operation = operation;
        }

        private LogicGate(LogicGate old) {
            String[] arr = new String[old.input.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = old.input[i];
            }

            this.input = arr;
            this.output = old.output;
            this.operation = old.operation;
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

        wireMap = new HashMap<String, Wire>();

        buildWires();
        processGates();
    }

    public int getWireValue(String wire) {
        return wireMap.get(wire).value;
    }

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
            gateArr[i] = new LogicGate(parseGateStr(lineArr.get(i)));
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

        return new LogicGate(input, output, operation);
    }

    private void buildWires() {
        for (int i = 0; i < gateArr.length; i++) {
            // check inputs
            String inp0 = gateArr[i].input[0];
            if (Character.isLowerCase(inp0.charAt(0))) {
                wireMap.putIfAbsent(inp0, new Wire());

                Wire w = wireMap.get(inp0);
                w.inputIndices.add(i);

                wireMap.put(inp0, w);
            }

            String inp1 = gateArr[i].input.length == 2 ? gateArr[i].input[1] : null;
            if (inp1 != null && Character.isLowerCase(inp1.charAt(0))) {
                wireMap.putIfAbsent(inp1, new Wire());

                Wire w = wireMap.get(inp1);
                w.inputIndices.add(i);

                wireMap.put(inp1, w);
            }

            String out = gateArr[i].output;
            wireMap.putIfAbsent(out, new Wire());

            Wire w = wireMap.get(out);
            w.outputIndex = i;

            wireMap.put(out, w);
        }
    }

    private void processGates() {
        for (int i = 0; i < gateArr.length; i++) {
            checkGateValues(i, gateArr[i]);            
        }
        // go through gate arr
        // check to see if we know output value, if not:
            // run input values through operation, assign to output wire's value
            // if input values unknown apply recursion
    }

    private int checkGateValues(int index, LogicGate gate) { // this is the recursive function

        int outValue;

        if (wireMap.get(gate.output).value == -1) {
            // switch statement based on operation
            String op = gate.operation;

            // call correct function
            switch (op) {
                case "AND":
                    outValue = andGate(gate.input);
                    break;
                case "LSHIFT":
                    outValue = lshiftGate(gate.input);
                    break;
                case "RSHIFT":
                    outValue = rshiftGate(gate.input);
                    break;
                case "OR":
                    outValue = orGate(gate.input);
                    break;
                case "NOT":
                    outValue = notGate(gate.input);
                    break;
                default:
                    outValue = passThroughGate(gate.input);
            }
            
            wireMap.get(gate.output).value = outValue;
        } else {
            outValue = wireMap.get(gate.output).value;
        }

        return outValue;
    }

    private int andGate(String[] input) {
        int inputA, inputB;

        if (Character.isLowerCase(input[0].charAt(0))) {
            int i = wireMap.get(input[0]).outputIndex;
            inputA = checkGateValues(i, gateArr[i]);
        } else {
            inputA = Integer.parseInt(input[0]);
        }

        if (Character.isLowerCase(input[1].charAt(0))) {
            int i = wireMap.get(input[1]).outputIndex;
            inputB = checkGateValues(i, gateArr[i]);
        } else {
            inputB = Integer.parseInt(input[1]);
        }

        short a = (short) inputA;
        short b = (short) inputB;

        return Short.toUnsignedInt((short) (a & b));
    }

    private int orGate(String[] input) {

        int inputA, inputB;

        if (Character.isLowerCase(input[0].charAt(0))) {
            int i = wireMap.get(input[0]).outputIndex;
            inputA = checkGateValues(i, gateArr[i]);
        } else {
            inputA = Integer.parseInt(input[0]);
        }

        if (Character.isLowerCase(input[1].charAt(0))) {
            int i = wireMap.get(input[1]).outputIndex;
            inputB = checkGateValues(i, gateArr[i]);
        } else {
            inputB = Integer.parseInt(input[1]);
        }

        short a = (short) inputA;
        short b = (short) inputB;;

        return Short.toUnsignedInt((short) (a ^ b));
    }

    private int notGate(String[] input) {
        int inputA; 

        if (Character.isLowerCase(input[0].charAt(0))) {
            int i = wireMap.get(input[0]).outputIndex;
            inputA = checkGateValues(i, gateArr[i]);
        } else {
            inputA = Integer.parseInt(input[0]);
        }

        short a = (short) inputA;

        return Short.toUnsignedInt((short) ~a);
    }

    private int lshiftGate(String[] input) {
        int inputA, shift; 

        if (Character.isLowerCase(input[0].charAt(0))) {
            int i = wireMap.get(input[0]).outputIndex;
            inputA = checkGateValues(i, gateArr[i]);
        } else {
            inputA = Integer.parseInt(input[0]);
        }

        shift = Integer.parseInt(input[1]);

        return Short.toUnsignedInt((short) (inputA << shift));
    }

    private int rshiftGate(String[] input) {

        int inputA, shift; 

        if (Character.isLowerCase(input[0].charAt(0))) {
            int i = wireMap.get(input[0]).outputIndex;
            inputA = checkGateValues(i, gateArr[i]);
        } else {
            inputA = Integer.parseInt(input[0]);
        }

        shift = Integer.parseInt(input[1]);

        return Short.toUnsignedInt((short) (inputA >>> shift));
    }

    private int passThroughGate(String[] input) {
        int inputA;

        if (Character.isLowerCase(input[0].charAt(0))) {
            int i = wireMap.get(input[0]).outputIndex;
            inputA = checkGateValues(i, gateArr[i]);
        } else {
            inputA = Integer.parseInt(input[0]);
        }

        return inputA;
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