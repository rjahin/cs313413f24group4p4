package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions
    void toRunningState();
    void toStoppedState();
    //void toLapRunningState();
   // void toLapStoppedState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionLap();
    void actionInc();
    void actionUpdateView();

    // state-dependent UI updates
    int updateUIRuntime();
    void updateUILaptime();

    // Provides access to the current runtime value for state classes via the state machine.
    int getRuntime();
}
