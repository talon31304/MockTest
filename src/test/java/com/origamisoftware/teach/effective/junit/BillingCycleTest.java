package com.origamisoftware.teach.effective.junit;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *  JUnit tests for the <CODE></CODE>BillingCycle</CODE> class
 *
 * @author Rick Martel
 */
public class BillingCycleTest {
    private Calendar yesterday, futureYear,today,twoYearsAgo;
    private BigDecimal postage,tax,rate;
    private SubscriptionPeriod activeSubscriptionPeriod1,activeSubscriptionPeriod2,
    inactiveSubscriptionPeriod1;
    private Subscription activeSubscription1,activeSubscription2,
        inactiveSubscription1;
    private int TotalActive,ActiveCustomerId1,ActiveCustomerId2,InactiveCustomerId1;
    private CustomerRepository MockedCustomerRep;
    private Customer ActiveCustomer1,ActiveCustomer2,InactiveCustomer1,InactiveCustomer2;
    private  List <Subscription> activeSubscriptions, inactiveSubscriptions;

    @Before
    public void setup() {
        MockedCustomerRep = mock(CustomerRepository.class);
        TotalActive=2;
        //Calendar Variable setup
        today = Calendar.getInstance();
        yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        futureYear = Calendar.getInstance();
        futureYear.add(Calendar.YEAR, 1);
        twoYearsAgo = Calendar.getInstance();
        twoYearsAgo.add(Calendar.YEAR, -2);


        //Rates,Taxes, ETC for test
        postage = new BigDecimal(.70);
        tax = new BigDecimal(.0625);
        rate = new BigDecimal(10.00);

        //subscription periods
        activeSubscriptionPeriod1 = new SubscriptionPeriod(yesterday.getTime(), futureYear.getTime());
        inactiveSubscriptionPeriod1 = new SubscriptionPeriod(twoYearsAgo.getTime(), yesterday.getTime());
        activeSubscriptionPeriod2 = new SubscriptionPeriod(twoYearsAgo.getTime(), futureYear.getTime());

        //subscriptions
        activeSubscription1 = new Subscription(1,rate, activeSubscriptionPeriod1);
        activeSubscription2 = new Subscription(2,rate, activeSubscriptionPeriod2);
        inactiveSubscription1 = new Subscription(3,rate, inactiveSubscriptionPeriod1);

        // subscription lists
       activeSubscriptions=
           Arrays.asList(activeSubscription1,activeSubscription2, inactiveSubscription1);
       inactiveSubscriptions=
           Arrays.asList(inactiveSubscription1, inactiveSubscription1);

        //customerId's
        ActiveCustomerId1=1;
        ActiveCustomerId2=2;
        InactiveCustomerId1=3;

        //Customers
        ActiveCustomer1= new Customer( ActiveCustomerId1,"John Doe","12 Elm St","SmallTown","MA","01234",activeSubscriptions);
        ActiveCustomer2= new Customer( ActiveCustomerId2,"Jane Deer","12 3rd St Unit 43","BigTown","MA","01234",activeSubscriptions);
        InactiveCustomer1= new Customer(InactiveCustomerId1,"Frank Doe","12 Center St","SmallCity","MA","01234",inactiveSubscriptions);
        InactiveCustomer2= new Customer(InactiveCustomerId1,"John Doe","12 5th Ave Apt 12","BigCity","MA","01234",inactiveSubscriptions);

        //need to mock method calls.
        when(MockedCustomerRep.getAll()).thenReturn(Arrays.asList(ActiveCustomer1, ActiveCustomer2,InactiveCustomer1,InactiveCustomer2));
        when(MockedCustomerRep.getById(ActiveCustomerId1)).thenReturn(ActiveCustomer1);
        when(MockedCustomerRep.getById(ActiveCustomerId2)).thenReturn(ActiveCustomer2);
    }

      /* Checking basic constructor functionality
          */

    @Test
    public void testBillingCycleConstruction() {
    BillingCycle billing= new BillingCycle(MockedCustomerRep,postage,tax,today);

        assertEquals("Billing Date", today, billing.getBillingDate());
        assertEquals("Number of Active Customers to Bill", TotalActive, billing.getNumberOfCustomersToBill());
        assertEquals("Postage Rate", postage, billing.getPostageRate());
        assertEquals("Sales Tax", tax, billing.getSalesTax());
        assertEquals(MockedCustomerRep,billing.getRepository());
    }





    /* Checking GetTotalPostageCost()
        */
    @Test
    public void testGetTotalPostageCost() {
        BillingCycle billing= new BillingCycle(MockedCustomerRep,postage,tax,today);
        assertEquals("Total Postage",
                postage.multiply(new BigDecimal(TotalActive)),
                billing.getTotalPostageCost());
    }

    /* Checking GetTotalAmountBilled() - amount billed for all customers for date in question
         */
    @Test
    public void testGetTotalAmountBilled() {
        BillingCycle billing= new BillingCycle(MockedCustomerRep,postage,tax,today);
        assertEquals("Total Amount Billed",new BigDecimal(40), billing.getTotalAmountBilled());

    }

    /* Checking GetCustomerAmountBilled()
         */
    @Test
    public void testGetCustomerAmountBilled() {
        BillingCycle billing= new BillingCycle(MockedCustomerRep,postage,tax,today);

        assertEquals("Total Amount Customer 1 Billed", new BigDecimal(20), billing.getCustomerAmountBilled(ActiveCustomerId1));
    }
}
