public class Driver {
    public static void main(String[] args) {
        FakeLisp fakeLisp = new FakeLisp("inputs/day1.txt");
        PaperCalculator paperCalc = new PaperCalculator("inputs/day2.txt");
        DeliveryTracker tracker = new DeliveryTracker("inputs/day3.txt");
        AdventCoin coin = new AdventCoin("inputs/day4.txt");

        System.out.println("Problem 1.1: " + fakeLisp.getFloor());
        System.out.println("Problem 1.2: " + fakeLisp.getBasement());

        System.out.println();
        System.out.println("Problem 2.1: " + paperCalc.getFootage());
        System.out.println("Problem 2.2: " + paperCalc.getLength());

        System.out.println();
        System.out.println("Problem 3.1: " + tracker.getTotalHouses());
        System.out.println("Problem 3.2: " + tracker.getTotalRoboHouses());

        System.out.println();
        System.out.println("Problem 4.1: " + coin.getSmallestFiver());
        System.out.println("Problem 4.2: " + coin.getSmallestSixer());
    }
}