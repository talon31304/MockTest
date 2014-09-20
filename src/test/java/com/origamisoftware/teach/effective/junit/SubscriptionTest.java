package com.origamisoftware.teach.effective.junit;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for the <CODE></CODE>Subscription</CODE> class
 *
 * @author Spencer A Marks
 */
public class SubscriptionTest {

    private Date now;
    private Calendar endOfSubscription;
    private BigDecimal rate;
    private SubscriptionPeriod subscriptionPeriod;
    private int SubscriptionId;

    @Before
    public void setup() {
        now = Calendar.getInstance().getTime();
        endOfSubscription = Calendar.getInstance();
        endOfSubscription.add(Calendar.YEAR, 1);
        rate = new BigDecimal(10.00);
        subscriptionPeriod = new SubscriptionPeriod(now, endOfSubscription.getTime());
        SubscriptionId=12;
    }

    @Test
    public void testSubscriptionConstruction() {
        Subscription subscription = new Subscription(SubscriptionId,rate, subscriptionPeriod);

        /* NOTICE: I always put a descriptive string in front of my assert method.
         * You don't have to there is a version of assertEquals that does not take the String argument.
         * However, these descriptive strings are really helpful for debugging failing tests.
         * Get in the habit of always using them.
         */

    }

    // NOTICE: the name of the test method matches the name of method I am testing.

    @Test
    public void testIsActivePositive() {
        Subscription subscription = new Subscription(SubscriptionId,rate, subscriptionPeriod);
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        assertTrue("The subscription should be active tomorrow", subscription.isActive(tomorrow));
    }

    // NOTICE: it is a good idea to write tests that verify expected behavior in both positive and negative ways.
    // ALSO NOTICE: I have separate tests for each specific test. Don't bunch up a tests into one test method.
    @Test
    public void testIsActiveNegative() {
        Subscription subscription = new Subscription(SubscriptionId,rate, subscriptionPeriod);
        Calendar twoYearsAgo = Calendar.getInstance();
        twoYearsAgo.add(Calendar.YEAR, -2);
        assertFalse("The subscription should not be active two years ago", subscription.isActive(twoYearsAgo));
    }
}
