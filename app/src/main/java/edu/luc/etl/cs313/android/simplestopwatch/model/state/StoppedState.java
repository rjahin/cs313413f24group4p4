package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class StoppedState implements StopwatchState {

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onIncrementReset() {
        if (sm.getRuntime() < 99) {
            sm.actionInc();
            int time = sm.getRuntime();
            if (time == 15) {
                sm.actionStart();
                sm.toRunningState();
            }
        }

    }

//        if (sm.getRuntime() < 99) {
//            sm.actionInc(); // Increment time by 1
//        } else {
//            sm.actionStart(); // Transition to running state
//            sm.toRunningState();
//        }

        /*if (sm.getRuntime() < 99) {
            sm.actionInc(); // Increment time by 1
            sm.actionUpdateView(); // Update the UI to show the new time

            // If time hits 15, start the timer and transition to Running state
            if (sm.getRuntime() == 15) {
                sm.toRunningState();// Transition to Running state
                sm.actionStart(); // Start the timer

            }
        } else {
            // If the time hits 99, transition to Running state
            sm.actionStart();
            sm.toRunningState();
        }*/


    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
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
