package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class AlarmSoundingState implements StopwatchState {

    private final StopwatchSMStateView sm;

    public AlarmSoundingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    @Override
    public void onIncrementReset() {
        sm.actionStop();       // Stop the alarm
        sm.actionReset();      // Reset the time to zero
        sm.toStoppedState();   // Transition to the stopped state
    }


    @Override
    public void onTick() {
        // The alarm continues to sound; no action on tick
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.ALARM_SOUNDING;
    }


}