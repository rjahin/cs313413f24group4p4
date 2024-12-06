package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class StoppedState implements StopwatchState {

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    private int stoppedTime = 0;

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

    @Override
    public void onTick() {
        stoppedTime++;
        if (stoppedTime > 3 || sm.getRuntime()== 99){
            sm.actionBeep();
            sm.toRunningState();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }
}
