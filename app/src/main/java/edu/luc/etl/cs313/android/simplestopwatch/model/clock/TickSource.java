package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

/**
 * A source of onTick events for the stopwatch.
 * This interface is typically implemented by the model.
 *
 * @author laufer
 */
public interface TickSource {

    /**
     * Registers a tick listener that receives tick events.
     *
     * @param listener the listener to register
     */
    void setTickListener(TickListener listener);
}
