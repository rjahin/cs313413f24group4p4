package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An implementation of the internal clock.
 *
 * @author laufer
 */
public class DefaultClockModel implements ClockModel {

    // TODO make accurate by keeping track of partial seconds when canceled etc.

    /**
     * Timer instance for scheduling tick events.
     */
    private Timer timer;

    /**
     * Listener for receiving tick events from the clock model.
     */
    private TickListener listener;

    private int time; //to decrement time

    @Override
    public void setTickListener(final TickListener listener) {
        this.listener = listener;
    }

    /**
     * Starts the clock, generating tick events every second.
     * Ticks are scheduled with an initial delay of 1 second.
     */
    @Override
    public void start() {
        timer = new Timer();

        // The clock model runs onTick every 1000 milliseconds
        timer.schedule(new TimerTask() {
            @Override public void run() {
                // fire event
                //if (time > 0) {
                //time--;
                listener.onTick(); // Notify listener on each tick
                //}
            }
        }, /*initial delay*/ 1000, /*periodic delay*/ 1000);
    }

    /**
     * Stops the clock, canceling any scheduled ticks.
     */
    @Override
    public void stop() {
        timer.cancel();
    }
}