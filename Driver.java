public class Driver {
    public static void main(String[] args) {
        FakeLisp fakeLisp = new FakeLisp("inputs/day1.txt");

        System.out.println("Day 1, Problem 1: " + fakeLisp.getFloor());
    }
}