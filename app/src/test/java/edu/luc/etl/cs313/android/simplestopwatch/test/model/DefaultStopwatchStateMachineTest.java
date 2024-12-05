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

    @Test
    public void testAlarmSoundingAndReset() {
        // Start the stopwatch
        model.onStartStop();
        assertEquals(R.string.RUNNING, dependency.getState());

        // Simulate ticking until time reaches zero
        onTickRepeat(15);
        assertEquals(-1, dependency.getTime()); // Verify alarm started
        assertTrue(dependency.isStarted()); // Ensure timer is not running anymore

        // Press the button to stop the alarm
        model.onStartStop();
        assertEquals(R.string.STOPPED, dependency.getState()); // Verify stopped state
        assertEquals(0, dependency.getTime()); // Ensure timer reset to zero
    }

}
