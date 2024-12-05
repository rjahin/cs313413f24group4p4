package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class RunningState implements StopwatchState {

    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    public void onIncrementReset() {
        sm.actionStop();
        sm.actionReset();
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        sm.actionDec();
        int time = sm.getRuntime();
        if (time == 0) {
            sm.actionStop();
            sm.toStoppedState();
            sm.actionAlarm(); // Start the alarm when time reaches zero
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
