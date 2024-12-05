package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import java.util.Timer;
import java.util.TimerTask;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

    private int runningTime = 0;
    private boolean isDecrementing = false; // Flag to track if we're in decrement mode
    private Timer decrementTimer;


    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    @Override
    public void incRuntime() {
        if (!isDecrementing && runningTime < 99) {
            runningTime++; // Increment time up to 99

            if (runningTime == 15) {
                decRuntime(); // Switch to decrement mode when reaching 99
            }
        }
    }

    @Override
    public void decRuntime() {
        if (!isDecrementing) {
            isDecrementing = true;

            decrementTimer = new Timer();
            decrementTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (runningTime > 0) {
                        runningTime--; // Decrement time down to 0
                    } else {
                        stopDecrementing(); // Stop when time reaches 0
                    }
                }
            }, 1000, 1000); // Decrement time every 1000ms
        }
    }

    private void stopDecrementing() {
        isDecrementing = false;
        if (decrementTimer != null) {
            decrementTimer.cancel(); // Stop the decrement timer
            decrementTimer = null;
        }
        runningTime = 0; // Ensure time stays at 0
    }

    @Override
    public int getRuntime() {
        return runningTime;
    }
}