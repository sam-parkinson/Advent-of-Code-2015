public class Driver {
    public static void main(String[] args) {
        FakeLisp fakeLisp = new FakeLisp("inputs/day1.txt");

        System.out.println("Problem 1.1: " + fakeLisp.getFloor());
        System.out.println("Problem 1.2: " + fakeLisp.getBasement());
    }
}