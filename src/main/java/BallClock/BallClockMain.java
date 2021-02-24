package BallClock;

import java.util.Scanner;

import static BallClock.util.Constants.INTRO_MSG;
import static BallClock.util.Constants.INVALID_INPUT_MSG;
import static BallClock.util.Constants.INVALID_BALL_COUNT;
import static BallClock.util.Util.isValidIntegerInput;

public class BallClockMain {
    /* Command Line variables */
    private static Scanner inputScan = new Scanner(System.in);
    private static String inputString;
    private static int inputInt;

    /* Calculation variables */
    private static BallClockOperation ballClock;
    private static int days;
    private static long startTime;
    private static long endTime;
    private static long duration;

    public static void main(String[] args) {
        System.out.println(INTRO_MSG);
        while (!(inputString = inputScan.nextLine()).equals("exit")) {
            if ((inputInt = isValidIntegerInput(inputString)) != INVALID_BALL_COUNT){
                ballClock = new BallClockOperation(inputInt);

                startTime = System.currentTimeMillis();
                days = ballClock.calculateDaysToCycle();
                endTime = System.currentTimeMillis();
                duration = endTime - startTime;

                System.out.println(inputInt + " balls take " + days + " days. Took " + duration + " milliseconds to calculate");
            } else{
                System.out.println(INVALID_INPUT_MSG);
            }
            System.out.println(INTRO_MSG);
        }
    }
}
