package edu.luc.etl.cs313.android.simplestopwatch.common;

/**
 * A listener for UI update events.
 * This interface is typically implemented by the adapter, with the
 * events coming from the model.
 *
 * @author laufer
 */
public interface StopwatchModelListener {

    /**
     * Called when the timer value is updated.
     *
     * @param timeValue the updated time in seconds
     */
    void onTimeUpdate(int timeValue);

    /**
     * Called when the stopwatch state changes.
     *
     * @param stateId the updated state ID
     */
    void onStateUpdate(int stateId);

    /**
     * Called when the alarm should be triggered.
     */
    void onAlarm();

    /**
     * Called when a beep sound should be played.
     */
    void onBeep();
}
