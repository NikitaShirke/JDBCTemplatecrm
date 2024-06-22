package com.csi.service;

import com.csi.model.Customer;

import java.util.List;

public interface CustomerService {

    void signUp(Customer customer);
    void saveAll(List<Customer>customerList);
    Boolean signIn(String custEmail,String custPassword);
    Customer findById(int custId);
    List<Customer>findAll();
    void update(int custId,Customer customer);
    void changeAddress(int custId,String custAddress);
    void deleteById(int custId);
    void deleteAll();
}
