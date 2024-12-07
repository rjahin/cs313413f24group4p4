package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchModelListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 * This class manages transitions between states and triggers actions.
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    /**
     * Initializes the stopwatch state machine with the provided models.
     *
     * @param timeModel the time model managing the stopwatch time
     * @param clockModel the clock model generating periodic tick events
     */
    public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    /**
     * The internal time model holding the current runtime.
     */
    private final TimeModel timeModel;

    /**
     * The internal clock model responsible for triggering ticks.
     */
    private final ClockModel clockModel;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private StopwatchState state;

    //new
    public int getRuntime() {
        return timeModel.getRuntime();
    }

    protected void setState(final StopwatchState state) {
        this.state = state;
        listener.onStateUpdate(state.getId());
    }

    @Override
    public synchronized void onStartStop() {
        state.onIncrementReset();
    }

    private StopwatchModelListener listener;

    @Override
    public void setModelListener(final StopwatchModelListener listener) {
        this.listener = listener;
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread
    @Override public synchronized void onIncrementReset()  { state.onIncrementReset(); }
    @Override public synchronized void onTick()      { state.onTick(); }

    public int updateUIRuntime() {
        int time = timeModel.getRuntime(); // Fetch the current runtime
        listener.onTimeUpdate(time);       // Notify the UI listener
        return time;                       // Return the runtime value
    }

    // known states
    private final StopwatchState STOPPED     = new StoppedState(this);
    private final StopwatchState RUNNING     = new RunningState(this);

    // transitions
    @Override public void toRunningState()    { setState(RUNNING); } // Move to Stopped
    @Override public void toStoppedState()    { setState(STOPPED); } // Move to Running
    // actions
    @Override public void actionInit()       { toStoppedState(); actionReset(); } // Initialize to stopped state and Reset the stopwatch
    @Override public void actionReset()      { timeModel.resetRuntime(); actionUpdateView(); } // Reset time and Update UI
    @Override public void actionStart()      {  clockModel.start(); } // Start the clock
    @Override public void actionStop()       { clockModel.stop(); } // Stop the clock
    @Override public void actionInc()        { timeModel.incRuntime(); actionUpdateView(); } // Increment time and Update UI
    @Override public void actionDec()        { timeModel.decRuntime(); actionUpdateView(); } //to decrement timer
    @Override public void actionUpdateView() { state.updateView(); } // Refresh UI based on state
    @Override public void actionAlarm()      {listener.onAlarm();} // Trigger alarm sound
    @Override public void actionBeep()       {listener.onBeep();} // Trigger beep sound
    }
