package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.TickListener;

/**
 * A state in a state machine. This interface is part of the State pattern.
 *
 * @author laufer
 */
interface StopwatchState extends StopwatchUIListener, TickListener {

    /**
     * Updates the UI to reflect the current state.
     */
    void updateView();

    /**
     * Retrieves the unique ID representing this state.
     *
     * @return the state ID as a resource integer
     */
    int getId();
}
