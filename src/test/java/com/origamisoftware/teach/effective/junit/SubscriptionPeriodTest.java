package com.origamisoftware.teach.effective.junit;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * @author Spencer A Marks
 * @author Rick Martel
 */
public class SubscriptionPeriodTest {

    private Calendar now;
    private Calendar sixthMonthsFromNow;
    private Calendar threeMonthsAgo;

    /**
     * This code is used to setup a known state or baseline
     * It is executed before every test
     */
    @Before
    public void setup() {
        // create a known state (also known as a baseline)


        now = Calendar.getInstance();
        sixthMonthsFromNow = Calendar.getInstance();
        sixthMonthsFromNow.add(Calendar.MONTH, 6);
        threeMonthsAgo= Calendar.getInstance();
        threeMonthsAgo.add(Calendar.MONTH, -3);

    }

    @Test
    public void testConstruction() {

        // The basic anatomy of a test is this:

        // create a known state - this is done for us already in the setup method

        // change the state, in this case create a new object
        SubscriptionPeriod subscriptionPeriod = new SubscriptionPeriod(now.getTime(), sixthMonthsFromNow.getTime());

        // verify (assert) the change did what we expect
        assertEquals("start date", now.getTime(), subscriptionPeriod.getStartDate());
        assertEquals("end date", sixthMonthsFromNow.getTime(), subscriptionPeriod.getEndDate());

    }


    /**
     * TODO Currently, this test fails, it is your job to make it pass.
     */
    @Test
    public void testTotalDays() {
        SubscriptionPeriod subscriptionPeriod = new SubscriptionPeriod(now.getTime(), sixthMonthsFromNow.getTime());
        int totalDays = subscriptionPeriod.getTotalDays();
        long differenceInDays = (sixthMonthsFromNow.getTime().getTime() - now.getTime().getTime()) / (1000 * 60 * 60 * 24);
        assertEquals(totalDays, differenceInDays);
    }


    /**
     * TODO Currently, this test fails, it is your job to make it pass.
     */
    @Test
    public void testTotalMonths() {
        SubscriptionPeriod subscriptionPeriod = new SubscriptionPeriod(now.getTime(), sixthMonthsFromNow.getTime());
        int totalMonths = subscriptionPeriod.getTotalMonths();
        long differenceInMonth = differenceInMonths(now, sixthMonthsFromNow);
        assertEquals(totalMonths, differenceInMonth);
    }


    // TODO add a feature you would like to see in the subscriptionPeriod class and write a test for it here.
    @Test
    public void testDaysElapsed() {
        SubscriptionPeriod subscriptionPeriod = new SubscriptionPeriod(threeMonthsAgo.getTime(), sixthMonthsFromNow.getTime());
        int daysElapsed = subscriptionPeriod.getDaysElapsed();
        long differenceInDays = (now.getTime().getTime()-threeMonthsAgo.getTime().getTime()) / (1000 * 60 * 60 * 24);
        assertEquals(daysElapsed, differenceInDays);
    }


    // it is perfectly fine to have helper methods in test code.

    /**
     * This helper method returns the number of months in the range.
     *
     * @param start
     * @param stop
     * @return
     */
    private int differenceInMonths(Calendar start, Calendar stop) {
        int stopYear = stop.get(Calendar.YEAR);
        int startYear = start.get(Calendar.YEAR);
        int stopMonth = stop.get(Calendar.MONTH);
        int startMonth = start.get(Calendar.MONTH);
        return (stopYear - startYear) * 12 + (stopMonth - startMonth);

    }


    @Test
    public void fixedTestCool() {
        if (1!=1) {
            fail("This is still broken.");
        }



    }

}
