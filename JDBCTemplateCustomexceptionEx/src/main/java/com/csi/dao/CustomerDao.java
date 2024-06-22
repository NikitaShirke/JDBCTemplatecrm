package com.csi.dao;
import com.csi.model.Customer;
import java.util.List;
public interface CustomerDao {
    void signUp(Customer customer);
    void saveAll(List<Customer>customerList);
    boolean signIn(String custEmail,String custPassword);
    Customer finById(int custId);
    List<Customer>findAll();
    void update(int custId,Customer customer);
    void changeAddress(int custId,String custAddress);
    void deleteById(int custId);
    void deleteAll();
}
