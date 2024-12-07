package edu.luc.etl.cs313.android.simplestopwatch.model;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchModelListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.DefaultClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.DefaultStopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.StopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.DefaultTimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * A concrete implementation of the stopwatch model facade that connects the stopwatch's
 * clock, time management, and state machine components. This class follows the Facade design
 * pattern, providing a simplified interface for integrating the model into the UI layer.
 */
public class ConcreteStopwatchModelFacade implements StopwatchModelFacade {

    /**
     * Manages the stopwatch's state and state transitions.
     */
    private final StopwatchStateMachine stateMachine;

    /**
     * Manages the stopwatch's ticking events and time tracking.
     */
    private final ClockModel clockModel;

    /**
     * Manages the stopwatch's runtime value and time calculations.
     */
    private final TimeModel timeModel;

    /**
     * Initializes the stopwatch model, connecting its components and establishing
     * communication channels between the clock, time, and state models.
     */
    public ConcreteStopwatchModelFacade() {
        timeModel = new DefaultTimeModel();
        clockModel = new DefaultClockModel();
        stateMachine = new DefaultStopwatchStateMachine(timeModel, clockModel);
        clockModel.setTickListener(stateMachine);
    }

    /**
     * Starts the stopwatch model, initializing its state and resetting the timer.
     */
    @Override
    public void start() {
        stateMachine.actionInit();
    }

    /**
     * Registers a model listener that will receive UI updates from the model.
     *
     * @param listener the UI listener implementation to register
     */
    @Override
    public void setModelListener(final StopwatchModelListener listener) {
        stateMachine.setModelListener(listener);
    }

    /**
     * Handles the increment/reset event triggered by the user.
     */
    @Override
    public void onIncrementReset() {
        stateMachine.onIncrementReset();
    }
}
