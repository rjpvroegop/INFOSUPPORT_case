package controller;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rjpvr on 12-10-2016.
 */
public class DateControllerTest {

    DateController dc;

    @Before
    public void init(){
        dc = new DateController();
    }

    @Test
    public void testWeekToDate(){
        assertThat(dc.getWeekOfYearDate(42, 2013).getYear(), is(2013));
        assertThat(dc.getWeekOfYearDate(42, 2013).getMonthValue(), is(10));
        assertThat(dc.getWeekOfYearDate(42, 2013).getDayOfMonth(), is(13));
    }

}
