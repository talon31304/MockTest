package com.origamisoftware.teach.effective.junit;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
/**
 * A simple interface for a customer repository.
 *
 * @author Rick Martel
 *
 */
public interface CustomerRepository {
    void add(Customer customer);
    void remove(Customer customer);
    void update(Customer customer);
    Customer getById (int id);
    List <Customer> getAll();
}
