package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

/**
 * A listener for onTick events coming from the internal clock model.
 *
 * @author laufer
 */
public interface TickListener {
    /**
     * Called when a tick event is triggered by the clock.
     */
    void onTick();
}
