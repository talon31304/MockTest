package com.origamisoftware.teach.effective.junit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * A simple class that models a few simple billing concepts.
 *
 * @author Rick Martel
 *
 */
public class BillingCycle {
    private Calendar BillingDate;
    private BigDecimal PostageRate;
    private BigDecimal SalesTax;
    private CustomerRepository Repository;
    private int NumberOfCustomersToBill;
    private List<Customer> BillableCustomers;

    /**
     * Creates a new  BillingCycle instance.
     *
     * @param customerDataRepository     a customer repository that contains customer data
     * @param postage The postage cost per bill <CODE> </CODE>BigDecimal </CODE>
     * @param tax the tax percent added to any subscription <CODE> </CODE>BigDecimal </CODE>
     * @param billDate the date bills are being accessed for
     */
    public BillingCycle(CustomerRepository customerDataRepository, BigDecimal postage, BigDecimal tax, Calendar billDate) {
       this.Repository=customerDataRepository;
        this.BillingDate=billDate;
        this.PostageRate = postage;
        this.SalesTax = tax;
        setBillableCustomers();
        setNumberOfCustomersToBill();
    }

    /**
     * Returns the customer repository being used.
     *
     * @return
     */
     public CustomerRepository getRepository() {return Repository;}

    /**
     * Returns the billing date.
     *
     * @return
     */
    public Calendar getBillingDate() {return BillingDate;}

    /**
     * Returns the postage rate.
     *
     * @return
     */
    public BigDecimal getPostageRate() {return PostageRate;}


    /**
     * Returns the sales tax.
     *
     * @return
     */
    public BigDecimal getSalesTax() {return SalesTax;}


    /**
     * Returns the number of customers active at billing date.
     *
     * @return
     */
    public int getNumberOfCustomersToBill() {return NumberOfCustomersToBill;}



    /**
     * Returns a List of customers active at billing date.
     *
     * @return
     */
    public List<Customer> getBillableCustomers() {return BillableCustomers;}


    /**
     * Creates and set list of billable customers based on billing date and other fields.
     */
    private void  setBillableCustomers() {
        List<Customer> all=Repository.getAll();
        List<Customer> active= new ArrayList<Customer>();
        for (Customer customer : all) {
            if (customer.isActive(BillingDate)) {
                active.add(customer);
            }
        }
        BillableCustomers= active;
    }


    /**
     * Determines and sets teh number of billable customers based on billing date and other fields.
     */
    private void  setNumberOfCustomersToBill() {
       int count=0;
        for (Customer customer : BillableCustomers) {
           count+=1;
        }
        NumberOfCustomersToBill= count;
    }

    /**
     * Returns total Postage Cost for all bills generated for billing date.
     *
     * @return
     */
    public BigDecimal getTotalPostageCost() {
        return PostageRate.multiply(new BigDecimal(NumberOfCustomersToBill));
    }

    /**
     * Returns total billed amount for all bills generated for billing date.
     *
     * @return
     */
    public BigDecimal getTotalAmountBilled() {
        BigDecimal runningTotal= new BigDecimal(0);
        for (Customer customer : BillableCustomers) {
            BigDecimal mRate=customer.getMonthlyRate(BillingDate);
            runningTotal=runningTotal.add(mRate);
        }
        return runningTotal;
    }


    /**
     * Returns total billed amount for one customer on billing date.
     *
     * @return
     */

    public BigDecimal getCustomerAmountBilled(int customerId) {
        return Repository.getById(customerId).getMonthlyRate(BillingDate);
    }

}
