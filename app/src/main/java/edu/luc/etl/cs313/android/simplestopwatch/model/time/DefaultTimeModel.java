package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

    private int runningTime = 0;


    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    @Override
    public void incRuntime() {
        if (runningTime < 99) {
            runningTime++; // Increment time up to 99
        }
    }

    @Override
    public void decRuntime() {
        if (runningTime > 0) {
            runningTime--;
        }
    }

    @Override
    public int getRuntime() {
        return runningTime;
    }
}