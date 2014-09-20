package com.origamisoftware.teach.effective.junit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * JUnit tests for the <CODE></CODE>Customer</CODE> class
 *
 * @author Rick Martel
 */
public class CustomerTest {
    private Calendar yesterday,lastYear, futureYear,today,twoYearsAgo;
    private String name, street, city,state, zip;
    private BigDecimal rate,monthlyRate;
    private Subscription activeSubscription1,activeSubscription2,
            inactiveSubscription1,inactiveSubscription2;
    private SubscriptionPeriod activeSubscriptionPeriod1,activeSubscriptionPeriod2,
            inactiveSubscriptionPeriod1,inactiveSubscriptionPeriod2;


    @Before
    public void setup() {
        name="John Doe";
        street="12 Pine St";
        city="Cityville";
        state="MA";
        zip="01234";
        monthlyRate = new BigDecimal(20.00);

        //calendar variables
        today = Calendar.getInstance();
        yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        futureYear = Calendar.getInstance();
        futureYear.add(Calendar.YEAR, 1);
        lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);
        twoYearsAgo = Calendar.getInstance();
        twoYearsAgo.add(Calendar.YEAR, -2);


        rate = new BigDecimal(10.00);

        //subscription periods
        activeSubscriptionPeriod1 = new SubscriptionPeriod(yesterday.getTime(), futureYear.getTime());
        inactiveSubscriptionPeriod1 = new SubscriptionPeriod(lastYear.getTime(), yesterday.getTime());
        activeSubscriptionPeriod2 = new SubscriptionPeriod(lastYear.getTime(), futureYear.getTime());

        //subscriptions
        activeSubscription1 = new Subscription(1,rate, activeSubscriptionPeriod1);
        activeSubscription2 = new Subscription(2,rate, activeSubscriptionPeriod2);
        inactiveSubscription1 = new Subscription(3,rate, inactiveSubscriptionPeriod1);

    }

    /* Checking basic constructor function
         */
    @Test
    public void testCustomerConstruction() {
        Customer testCustomer = new Customer(1,name,street,city,state,zip,
                 Arrays.asList(activeSubscription1, activeSubscription2, inactiveSubscription1));
        assertEquals("The customer name is correct", testCustomer.getName(), name);
        assertEquals("The customer street address is correct", testCustomer.getStreetAddress(), street);
        assertEquals("The customer city is correct", testCustomer.getCity(), city);
        assertEquals("The customer state is correct", testCustomer.getState(), state);
        assertEquals("The customer zip is correct", testCustomer.getZip(), zip);
        assertEquals("The customer's subscription are correct", testCustomer.getSubscriptions(),
                Arrays.asList(activeSubscription1, activeSubscription2, inactiveSubscription1));

    }

    /* Checking IsActive() for active test customer
          */
    @Test
    public void testIsActivePositive() {
        Customer testCustomer = new Customer(1,name,street,city,state,zip,
                Arrays.asList(activeSubscription1, activeSubscription2, inactiveSubscription1));
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        assertTrue("The customer should be active tomorrow", testCustomer.isActive(tomorrow));
    }

    /* Checking IsActive() for inactive test customer
          */
    @Test
    public void testIsActiveNegative() {
        Customer testCustomer = new Customer(1,name,street,city,state,zip,
                Arrays.asList(activeSubscription1, activeSubscription2, inactiveSubscription1));
        assertFalse("The customer should not be active two years ago", testCustomer.isActive(twoYearsAgo));
    }
    /* check GetAmountDue() (validating Total Amount due for active subscriptions)
          */
    @Test
    public void testGetMonthlyRate() {
        Customer testCustomer = new Customer(1,name,street,city,state,zip,
                Arrays.asList(activeSubscription1, activeSubscription2, inactiveSubscription1));
        assertEquals("The monthly rate is correct", testCustomer.getMonthlyRate(today), monthlyRate);
    }

    /* check GetNumberActiveSubscriptions() (validating Total Number of active subscriptions)
          */
    @Test
    public void testGetNumberActiveSubscriptions() {
        Calendar now = Calendar.getInstance();
        Customer testCustomer = new Customer(1,name,street,city,state,zip,
                Arrays.asList(activeSubscription1, activeSubscription2, inactiveSubscription1));
        assertEquals("The number of active subscriptions for customer is correct", testCustomer.getNumberActiveSubscriptions(now),2);
    }

    /* check GetNumberSubscriptions() (validating Total Number of subscriptions)
          */
    @Test
    public void testNumberSubscriptions() {
        Customer testCustomer = new Customer(1,name,street,city,state,zip,
                Arrays.asList(activeSubscription1, activeSubscription2, inactiveSubscription1));
        assertEquals("The total number of subscriptions for customer is correct", testCustomer.getNumberSubscriptions(),3);
    }


}
