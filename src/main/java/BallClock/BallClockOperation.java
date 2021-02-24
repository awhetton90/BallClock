package BallClock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static BallClock.util.Constants.BALL_DEFAULT_TAG;
import static BallClock.util.Constants.MAX_FIVE_MINUTE_TRACK_COUNT;
import static BallClock.util.Constants.MAX_HOUR_TRACK_COUNT;
import static BallClock.util.Constants.MAX_MINUTE_TRACK_COUNT;
import static BallClock.util.Constants.MINUTES_TO_DAYS_RATIO;

public class BallClockOperation {

    private Queue<String> bottomBallQueue;
    private Queue<String> originalQueueOrder;
    private Stack<String> minuteTrack;
    private Stack<String> fiveMinuteTrack;
    private Stack<String> hourTrack;
    private int totalMinutesToCycle;

    public BallClockOperation(int ballCount) {
        bottomBallQueue = new LinkedList<>();
        for (int i = 1; i < ballCount+1; i++){
            /* Give balls generic names associated with position in queue/stacks */
            bottomBallQueue.add(BALL_DEFAULT_TAG+i);
        }
        originalQueueOrder = new LinkedList<>(bottomBallQueue);
        minuteTrack = new Stack<>();
        fiveMinuteTrack = new Stack<>();
        hourTrack = new Stack<>();
        totalMinutesToCycle = 0;
    }


    /**
     * @return Days to circulate balls back to original positions
     */
    public int calculateDaysToCycle(){
        /* Start with first ball */
        minuteTrack.add(bottomBallQueue.remove());
        /* Keep track of total minutes */
        totalMinutesToCycle++;
        /* At each iteration, check if the bottom ball queue
         * matches the original queue.
         */
        while (!bottomBallQueue.equals(originalQueueOrder)) {
            fillMinuteTrack();
            checkAndAdjustFiveMinuteTrack();
            checkAndAdjustHourTrack();
        }

        /* convert minutes to days */
        return totalMinutesToCycle / MINUTES_TO_DAYS_RATIO;
    }

    private void fillMinuteTrack() {
        /* Fill minute track */
        while (minuteTrack.size() <= MAX_MINUTE_TRACK_COUNT) {
            minuteTrack.add(bottomBallQueue.remove());
            totalMinutesToCycle++;
        }

        /* Add fifth ball to five minute track, and send rest back to queue */
        fiveMinuteTrack.add(minuteTrack.pop());
        for (int i = 0; i < MAX_MINUTE_TRACK_COUNT; i++){
            bottomBallQueue.add(minuteTrack.pop());
        }
    }

    private void checkAndAdjustFiveMinuteTrack() {
        /* When five minute track is full, send twelfth to hour track and rest back to queue */
        if (fiveMinuteTrack.size() > MAX_FIVE_MINUTE_TRACK_COUNT) {
            hourTrack.add(fiveMinuteTrack.pop());
            for (int i = 0; i < MAX_FIVE_MINUTE_TRACK_COUNT; i++){
                bottomBallQueue.add(fiveMinuteTrack.pop());
            }
        }
    }

    private void checkAndAdjustHourTrack() {
        /* When hour track is full, send first eleven back to queue, then the twelfth */
        if (hourTrack.size() > MAX_HOUR_TRACK_COUNT) {
            String twelfthBall = hourTrack.pop();
            for (int i = 0; i < MAX_HOUR_TRACK_COUNT; i++){
                bottomBallQueue.add(hourTrack.pop());
            }
            bottomBallQueue.add(twelfthBall);
        }
    }


}
