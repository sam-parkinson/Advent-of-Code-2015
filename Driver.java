public class Driver {
    public static void main(String[] args) {
        FakeLisp fakeLisp = new FakeLisp("inputs/day1.txt");
        PaperCalculator paperCalc = new PaperCalculator("inputs/day2.txt");

        System.out.println("Problem 1.1: " + fakeLisp.getFloor());
        System.out.println("Problem 1.2: " + fakeLisp.getBasement());

        System.out.println();
        System.out.println("Problem 2.1: " + paperCalc.getFootage());
        System.out.println("Problem 2.2: " + paperCalc.getLength());
    }
}