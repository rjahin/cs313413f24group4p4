package edu.luc.etl.cs313.android.simplestopwatch.test.model;

import edu.luc.etl.cs313.android.simplestopwatch.R;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.etl.cs313.android.simplestopwatch.model.state.DefaultStopwatchStateMachine;

/**
 * Concrete testcase subclass for the default stopwatch state machine
 * implementation.
 *
 * @author laufer
 * @see <a href="http://xunitpatterns.com/Testcase%20Superclass.html">Testcase Superclass Pattern</a>
 */
public class DefaultStopwatchStateMachineTest extends AbstractStopwatchStateMachineTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setModel(new DefaultStopwatchStateMachine(getDependency(), getDependency()));
    }

    @After
    public void tearDown() {
        setModel(null);
        super.tearDown();
    }

    public void testAlarmSoundingToStopped() {
        // 1. Start the stopwatch to transition to the RunningState
        model.onStartStop(); // Press the start button
        assertEquals(R.string.RUNNING, dependency.getState()); // Verify RunningState

        // 2. Simulate ticking until the timer reaches zero
        onTickRepeat(15); // Assume 15 ticks make the time reach 0
        assertEquals(R.string.ALARM_SOUNDING, dependency.getState()); // Verify AlarmSoundingState

        // 3. Press the button to stop the alarm
        model.onStartStop(); // Press the button in AlarmSoundingState
        assertEquals(R.string.STOPPED, dependency.getState()); // Verify transition to StoppedState
        assertTimeEquals(0); // Verify the timer is reset to zero
    }
}
