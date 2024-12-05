package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchModelListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;

    private final ClockModel clockModel;

    private boolean alarmSounding = false;

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
        if (alarmSounding) {
            alarmSounding = false;
            actionStopAlarm();
            toStoppedState();
        } else {
            state.onIncrementReset();
        }
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
    @Override public void toRunningState()    { setState(RUNNING); }
    @Override public void toStoppedState()    { setState(STOPPED); }
    // actions
    @Override public void actionInit()       { toStoppedState(); actionReset(); }
    @Override public void actionReset()      { timeModel.resetRuntime(); actionUpdateView(); }
    @Override public void actionStart()      {  clockModel.start(); }
    @Override public void actionStop()       { clockModel.stop(); }
    @Override public void actionInc()        { timeModel.incRuntime(); actionUpdateView(); }
    @Override public void actionDec()        { timeModel.decRuntime(); actionUpdateView(); } //to decrement timer
    @Override public void actionUpdateView() { state.updateView(); }
    public void actionStartAlarm() {
        alarmSounding = true;
        listener.onTimeUpdate(-1); // Signal the alarm
    }

    @Override
    public void actionStopAlarm() {
        listener.onTimeUpdate(0); // Stop the alarm
    }  //  alarm stop logic
}
