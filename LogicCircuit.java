import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class LogicCircuit {
    private class LogicGate {
        String input;
        String output;
        // output and input strings used to hash values
        String operation; // maybe make this an enum type?
        
    }

    private class Wire {
        // input gate indices
        // output gate indices (?)
        // value
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

        Recursion?

        Base case: number

        Exit case: no output

        Start with wire we know has no further output (i.e., appears in output table but not in input table) -- can be more than one such wire
        Look at the input wires for that, get values of those wires
        Keep going until base case(s) -- this is going to be filling out a tree?

        Not really a tree

        Okay, so, step one: parse input into logic gates

        Step two: go through logic gates, find out which wires exist

        We know which wires exist and which connections exist
        Map wires to connections and vice-versa

        Connections to wires is easy

        Array of gates
        HashMap of wires
        Each gate knows what wires it inputs to and hashes to them easily
        Each wire knows index of output and input gates
        
    */

    public LogicCircuit(String address) {

    }
}