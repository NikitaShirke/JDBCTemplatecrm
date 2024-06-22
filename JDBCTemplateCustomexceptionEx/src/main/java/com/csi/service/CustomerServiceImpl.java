package com.csi.service;

import com.csi.dao.CustomerDao;
import com.csi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDaoImpl;

    @Override
    public void signUp(Customer customer) {
        customerDaoImpl.signUp(customer);
    }

    @Override
    public void saveAll(List<Customer> customerList) {
        customerDaoImpl.saveAll(customerList);
    }

    @Override
    public Boolean signIn(String custEmail, String custPassword) {
        return customerDaoImpl.signIn(custEmail, custPassword);
    }

    @Override
    public Customer findById(int custId) {
        return customerDaoImpl.finById(custId);
    }

    @Override
    public List<Customer> findAll() {
        return customerDaoImpl.findAll();
    }

    @Override
    public void update(int custId, Customer customer) {
        customerDaoImpl.update(custId, customer);
    }

    @Override
    public void changeAddress(int custId, String custAddress) {
        customerDaoImpl.changeAddress(custId, custAddress);
    }

    @Override
    public void deleteById(int custId) {
        customerDaoImpl.deleteById(custId);
    }

    @Override
    public void deleteAll() {
        customerDaoImpl.deleteAll();
    }
}
