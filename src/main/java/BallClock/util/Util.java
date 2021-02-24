package BallClock.util;

import static BallClock.util.Constants.INVALID_BALL_COUNT;
import static BallClock.util.Constants.MAX_BALL_COUNT;
import static BallClock.util.Constants.MIN_BALL_COUNT;

public class Util {

    /**
     * @param input - String to convert to Integer
     * @return -1 if invalid, otherwise valid
     */
    public static int isValidIntegerInput(String input) {
        int valueAsInt;

        try {
            valueAsInt = Integer.parseInt(input);
            if (!isValidBallCountRange(valueAsInt)){
                return INVALID_BALL_COUNT;
            }
        } catch (NumberFormatException ex) {
            return INVALID_BALL_COUNT;
        }
        return valueAsInt;
    }

    private static boolean isValidBallCountRange(int ballCount) {
        if ((ballCount < MIN_BALL_COUNT) || (ballCount > MAX_BALL_COUNT)){
            return false;
        }
        return true;
    }
}
