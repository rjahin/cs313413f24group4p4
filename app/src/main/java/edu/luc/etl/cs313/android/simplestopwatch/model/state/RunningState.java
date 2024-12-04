package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class RunningState implements StopwatchState {

    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    public void onIncrementReset() {
        sm.actionStop(); //stops timer (prevents crash when timer reset)
        sm.actionInit(); //resets timer and goes to stopped state

    }

    @Override
    public void onTick() {
        sm.actionInc(); // Increment the runtime
        int time = sm.updateUIRuntime(); // Get the current runtime
        if (time == 0) {
            sm.actionStop();            // Stop the timer
            sm.toAlarmSoundingState();  // Transition to the AlarmSoundingState
        } else {
            sm.toRunningState(); // Stay in the RunningState
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }
}
