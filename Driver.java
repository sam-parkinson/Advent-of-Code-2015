public class Driver {
    public static void main(String[] args) {
        FakeLisp fakeLisp = new FakeLisp("inputs/day1.txt");
        PaperCalculator paperCalc = new PaperCalculator("inputs/day2.txt");
        DeliveryTracker tracker = new DeliveryTracker("inputs/day3.txt");
        // AdventCoin coin = new AdventCoin("inputs/day4.txt");
        StringChecker niceList = new StringChecker("inputs/day5.txt");
        LightGrid lightGrid = new LightGrid("inputs/day6.txt");
        LogicCircuit logicCircuit = new LogicCircuit("inputs/day7.txt");
        Matchsticks matchstick = new Matchsticks("inputs/day8.txt");

        System.out.println("Problem 1.1: " + fakeLisp.getFloor());
        System.out.println("Problem 1.2: " + fakeLisp.getBasement());

        System.out.println();
        System.out.println("Problem 2.1: " + paperCalc.getFootage());
        System.out.println("Problem 2.2: " + paperCalc.getLength());

        System.out.println();
        System.out.println("Problem 3.1: " + tracker.getTotalHouses());
        System.out.println("Problem 3.2: " + tracker.getTotalRoboHouses());

        /*
        System.out.println();
        System.out.println("Problem 4.1: " + coin.getSmallestFiver());
        System.out.println("Problem 4.2: " + coin.getSmallestSixer()); */

        System.out.println();
        System.out.println("Problem 5.1: " + niceList.getNiceCount());
        System.out.println("Problem 5.2: " + niceList.getNewNiceCount());

        System.out.println();
        System.out.println("Problem 6.1: " + lightGrid.getLit());
        System.out.println("Problem 6.2: " + lightGrid.getBrightness());

        System.out.println();
        System.out.println("Problem 7.1: " + logicCircuit.getAFirst());
        System.out.println("Problem 7.2: " + logicCircuit.getASecond());

        System.out.println();
        System.out.println("Problem 8.1: " + matchstick.getDifference());
    }
}