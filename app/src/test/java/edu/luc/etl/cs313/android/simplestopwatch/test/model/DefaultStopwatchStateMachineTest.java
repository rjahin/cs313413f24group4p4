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
}