package edu.luc.etl.cs313.android.simplestopwatch.common;

/**
 * A listener for stopwatch events coming from the UI.
 *
 * @author laufer
 */
public interface StopwatchUIListener {

    /**
     * Called when the user triggers the increment or reset action from the UI.
     * Typically triggered by a button press event
     */
    void onIncrementReset();
}
