package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

/**
 * Represents the stopped state of the stopwatch.
 */

class StoppedState implements StopwatchState {

    /**
     * Initializes the stopped state with a reference to the state machine view.
     *
     * @param sm the state machine managing the stopwatch states
     */
    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    /**
     * Reference to the state machine view for performing actions and transitions.
     */
    private final StopwatchSMStateView sm;

    /**
     * Tracks how long the stopwatch has been in the stopped state.
     * Used to trigger automatic state transitions after a certain number of ticks.
     */
    private int stoppedTime = 0;

    /**
     * Handles the increment/reset event triggered by the user.
     */
    @Override
    public void onIncrementReset() {
        if (sm.getRuntime()==0){//If incremented past 0
            sm.actionStart();//Start tick events
        }
        stoppedTime = 0; //Reset stoppedTime on inc
        if (sm.getRuntime() < 99) {
            sm.actionInc();
            sm.actionUpdateView();
        }

    }

    /**
     * Handles periodic tick events from the clock.
     * If the stopwatch remains idle for more than 3 ticks or reaches the maximum
     * timer value of 99, the state transitions to the running state and triggers a beep.
     */
    @Override
    public void onTick() {
        stoppedTime++;// Count inactivity time
        if (stoppedTime > 3 || sm.getRuntime()== 99){
            sm.actionBeep(); // Play a beep sound
            sm.toRunningState(); // Transition to the running state
        }
    }

    /**
     * Updates the stopwatch UI to reflect the current runtime value.
     */
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    /**
     * Retrieves the unique state ID associated with the stopped state.
     *
     * @return the resource ID of the stopped state as defined in `R.string.STOPPED`
     */
    @Override
    public int getId() {
        return R.string.STOPPED;
    }
}
