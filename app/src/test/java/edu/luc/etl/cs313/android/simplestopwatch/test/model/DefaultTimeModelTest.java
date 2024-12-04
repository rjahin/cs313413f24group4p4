package edu.luc.etl.cs313.android.simplestopwatch.test.model;

import org.junit.After;
import org.junit.Before;

import edu.luc.etl.cs313.android.simplestopwatch.model.time.DefaultTimeModel;

/**
 * Concrete testcase subclass for the default time model implementation.
 *
 * @author laufer
 * @see <a href="http://xunitpatterns.com/Testcase%20Superclass.html">Testcase Superclass Pattern</a>
 */
public class DefaultTimeModelTest extends AbstractTimeModelTest {

    @Before
    public void setUp() throws Exception {
        setModel(new DefaultTimeModel());
    }

    @After
    public void tearDown() throws Exception {
        setModel(null);
    }
}
