package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Code's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions
    void toRunningState();
    void toStoppedState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionInc();
    void actionDec();
    void actionUpdateView();
    void actionStartAlarm(); // Start the alarm
    void actionStopAlarm();  // Stop the alarm


    // state-dependent UI updates
    int updateUIRuntime();

    // Provides access to the current runtime value for state classes via the state machine.
    int getRuntime();
}
