package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

    /**
     * The current runtime value of the stopwatch in seconds.
     */
    private int runningTime = 0;

    /**
     * Resets the runtime value to zero.
     */
    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    /**
     * Increments the runtime value by one second.
     */
    @Override
    public void incRuntime() {
        if (runningTime < 99) {
            runningTime++; // Increment time up to 99
        }
    }

    /**
     * Decrements the runtime value by one second.
     */
    @Override
    public void decRuntime() {
        if (runningTime > 0) {
            runningTime--;
        }
    }

    /**
     * Retrieves the current runtime value of the stopwatch.
     *
     * @return the current runtime in seconds
     */
    @Override
    public int getRuntime() {
        return runningTime;
    }
}