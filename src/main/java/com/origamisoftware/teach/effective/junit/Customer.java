package com.origamisoftware.teach.effective.junit;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple class that models a customer
 *
 * @author Rick Martel
 *
 */

public class Customer {
    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private List<Subscription> subscriptions;


    /**
     * Creates a new  Customer instance.
     *
     * @param customerName  the customer full name
     * @param customerStreetAddress The customer's street address
     * @param customerCity The customer's city
     * @param customerState The customer's state
     * @param customerZip The customer's zip code
     * @param subscriptionList List of customer's subscriptions
     */
    public Customer(int customerId, String customerName,String customerStreetAddress,
                    String customerCity,String customerState, String customerZip,
                    List <Subscription> subscriptionList) {
        this.id=customerId;
        this.name = customerName;
        this.address = customerStreetAddress;
        this.city=customerCity;
        this.state=customerState;
        this.zip=customerZip;
        this.subscriptions=subscriptionList;
    }

    /**
     * Returns the customer's name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer's address
     *
     * @return
     */
    public String getStreetAddress() {
        return address;
    }


    /**
     * Returns the customer's city
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the customer's state
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the customer's zip
     *
     * @return
     */
    public String getZip() {
        return zip;
    }
    /**
     * Returns the customer's subscriptions
     *
     * @return
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
    /**
     * Returns true if any subscription is active at the time provided.
     *
     * @param calendar is the customer active at this time?
     * @return
     */
    public boolean isActive(Calendar calendar) {
        if (subscriptions!=null) {
            for (Subscription sub : subscriptions) {
                if (sub.isActive(calendar)) {
                    //true if at least 1 subscription is active
                    return true;
                }

            }
        }
            //false if no subscriptions are active
            return false;
        }


    /**
     * Returns number of subscriptions.
     *
     * @return
     */
    public int getNumberSubscriptions() {
        int count = 0;
        if (subscriptions!=null) {
            for (Subscription sub : subscriptions) {
                count += 1;
            }
        }
        return count;
    }
    /**
     * Returns number of active subscriptions.
     *
     *  @param calendar Date to check for active subscription at
     * @return
     */

    public int getNumberActiveSubscriptions(Calendar calendar) {
        int count = 0;
        if (subscriptions!=null) {
            for (Subscription sub : subscriptions) {
                if (sub.isActive(calendar)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     * Returns number of active subscriptions.
     *
     *  @param calendar Date to get combined monthly rate for
     * @return
     */
    public BigDecimal getMonthlyRate(Calendar calendar) {
        BigDecimal rate = new BigDecimal(0);
        if (subscriptions!=null) {
            for (Subscription sub : subscriptions) {
                if (sub.isActive(calendar)) {
                    rate = rate.add(sub.getRate());
                }
            }
        }
        return rate;
    }

    }
