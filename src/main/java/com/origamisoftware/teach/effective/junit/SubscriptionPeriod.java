package com.origamisoftware.teach.effective.junit;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple Date Range Class
 *
 * @author Spencer A Marks
 * @author Rick Martel
 */
public class SubscriptionPeriod {

    private Date startDate;
    private Date endDate;

    /**
     * Creates a  SubscriptionPeriod instance
     *
     * @param startDate
     * @param endDate
     */
    public SubscriptionPeriod(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the the start date of the subscription
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Returns the the end date of the subscription
     *
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }



    /**
     * Returns the total Days in the subscription
     *
     * @return
     */
    public int getTotalDays() {
        return differenceInDays(startDate,endDate);
    }

    /**
     * Returns the total months on the subscription.
     *
     * @return
     */
    public int getTotalMonths() {

        return differenceInMonths(startDate,endDate);
    }

    /*  TODO add new functionality to the SubscriptionPeriod class here and write a test for it in the test class.
     *  This functionality can be as simple as you want. The goal is to give you practice writing a test and some functionality
     */

    /**
     * Returns the number of days past since subscription started until today
     *
     * @return
     */
    public int getDaysElapsed() {

        return differenceInDays(startDate,new Date());
    }


    /**
     * This helper method returns the number of months in the range.
     *
     * @param begin
     * @param end
     * @return
     */
    private int differenceInMonths(Date begin, Date end) {
        Calendar start = Calendar.getInstance();
        start.setTime(begin);
        Calendar stop = Calendar.getInstance();
        stop.setTime(end);

        int stopYear = stop.get(Calendar.YEAR);
        int startYear = start.get(Calendar.YEAR);
        int stopMonth = stop.get(Calendar.MONTH);
        int startMonth = start.get(Calendar.MONTH);
        return (stopYear - startYear) * 12 + (stopMonth - startMonth);

    }
    /**
     * This helper method returns the number of days in the range.
     *
     * @param begin
     * @param end
     * @return
     */
    private int differenceInDays(Date begin, Date end) {
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(begin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(end);

        return (int)((calEnd.getTimeInMillis()-calStart.getTimeInMillis())/(1000*60*60*24));
    }


}
