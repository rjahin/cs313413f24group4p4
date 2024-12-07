package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

/**
 * Represents the running state of the stopwatch.
 * Handles timer decrement and alarm triggering.
 */
class RunningState implements StopwatchState {

    /**
     * Initializes the running state with the provided state machine.
     *
     * @param sm the state machine managing the stopwatch
     */
    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    /**
     * The state machine view for executing actions and transitions.
     */
    private final StopwatchSMStateView sm;

    public void onIncrementReset() {
        sm.actionStop(); // Stop the stopwatch
        sm.actionReset(); // Reset the timer
        sm.toStoppedState(); // Transition to Stopped state
    }

    @Override
    public void onTick() {
        if (sm.getRuntime() == 0) {
            sm.actionAlarm(); // Start the alarm when time reaches zero
        }
        else{
            sm.actionDec(); // Decrement time
        }
    }


    @Override
    public void updateView() {
        sm.updateUIRuntime();
    } // Update the display

    @Override
    public int getId() {
        return R.string.RUNNING;
    } //Unique ID for running state
}
