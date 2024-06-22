package com.csi.dao;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    String INSERT_SQL = "insert into customer(custId,custName,custAddress,custContactNumber,custDOB,custEmail,custPassword)values(?,?,?,?,?,?,?)";
    String ALL_SQL = "select * from customer";
    String ALL_SQL_BY_ID = "select * from customer where custId=?";
    String UPDATE_SQL = "update customer set custName=?,custAddress=?,custContactNumber=?,custDOB=?,custEmail=?,custPassword=? where custId=?";
    String DELETE_SQL_BY_ID = "delete from customer where custId=?";
    String DELETE_ALL_SQL = "truncate table customer";
    String PATCH_SQL="update customer set custAddress=?where custId=?";

    private Customer customer(ResultSet resultSet, int numRow) throws SQLException {
        return Customer.builder().custId(resultSet.getInt(1)).custName(resultSet.getString(2)).custAddress(resultSet.getString(3)).custContactNumber(resultSet.getLong(4)).custDOB(resultSet.getDate(5)).custEmail(resultSet.getString(6)).custPassword(resultSet.getString(7)).build();
    }

    @Override
    public void signUp(Customer customer) {
        jdbcTemplate.update(INSERT_SQL,customer.getCustId(),customer.getCustName(),customer.getCustAddress(),customer.getCustContactNumber(),customer.getCustDOB(),customer.getCustEmail(),customer.getCustPassword());

    }

    @Override
    public void saveAll(List<Customer> customerList) {

        for(Customer customer : customerList)
        {
            jdbcTemplate.update(INSERT_SQL,customer.getCustId(),customer.getCustName(),customer.getCustAddress(),customer.getCustContactNumber(),customer.getCustDOB(),customer.getCustEmail(),customer.getCustPassword());
        }
    }

    @Override
    public boolean signIn(String custEmail, String custPassword) {
        boolean flag=false;
        for(Customer customer : findAll())
        {
            if(customer.getCustEmail().equals(custEmail) && customer.getCustPassword().equals(custPassword))
            {
                flag=true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Customer finById(int custId) {
        try
        {
            return jdbcTemplate.query(ALL_SQL_BY_ID,this::customer,custId).get(0);

        }catch (Exception exception)
        {
           throw new RecordNotFoundException("Customer ID Does Not Exit");
        }
        }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query(ALL_SQL,this::customer);
    }

    @Override
    public void update(int custId, Customer customer) {

        jdbcTemplate.update(UPDATE_SQL,customer.getCustName(),customer.getCustAddress(),customer.getCustContactNumber(),customer.getCustDOB(),customer.getCustEmail(),customer.getCustPassword(),custId);
    }

    @Override
    public void changeAddress(int custId, String custAddress) {
      jdbcTemplate.update(PATCH_SQL,custAddress,custId);
    }

    @Override
    public void deleteById(int custId) {
      jdbcTemplate.update(DELETE_SQL_BY_ID,custId);
    }

    @Override
    public void deleteAll() {
    jdbcTemplate.update(DELETE_ALL_SQL);
    }
}
