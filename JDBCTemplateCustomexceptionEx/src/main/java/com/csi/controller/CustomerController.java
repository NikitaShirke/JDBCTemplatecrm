package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String>signUp(@RequestBody Customer customer)
    {
        customerServiceImpl.signUp(customer);
        return  ResponseEntity.ok("SignUp Done Successfully");
    }
    @GetMapping("/signin/{custEmail}/{custPassword}")
    public ResponseEntity<Boolean>signIn(@PathVariable String custEmail,@PathVariable String custPassword)
    {
        return  ResponseEntity.ok(customerServiceImpl.signIn(custEmail,custPassword));
    }
  @PostMapping("/saveall")
    public ResponseEntity<String>saveAll(@RequestBody List<Customer>customerList)
  {
     customerServiceImpl.saveAll(customerList);
     return ResponseEntity.ok("ALl Data Save Successfully");
  }
   @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Customer>findById(@PathVariable int custId)
   {
       return ResponseEntity.ok(customerServiceImpl.findById(custId));
   }
   @GetMapping("/findall")
    public ResponseEntity<List<Customer>>findAll()
   {
      return ResponseEntity.ok(customerServiceImpl.findAll());
   }
  @GetMapping("/findbyname/{custName}")
    public ResponseEntity<List<Customer>>findByName(@PathVariable String custName)
  {
      return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust->cust.getCustName().equals(custName)).toList());
  }
  @GetMapping("/findbycontactnumer/{custContactNumber}")
    public ResponseEntity<Customer>findByContactNumber(@PathVariable long custContactNumber)
  {
      return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust->cust.getCustContactNumber()==custContactNumber).toList().get(0));
  }
  @GetMapping("/findbyemail/{custEmail}")
  public ResponseEntity<Customer>findByEmail(@PathVariable String custEmail)
  {
      return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust->cust.getCustEmail().equals(custEmail)).toList().get(0));
  }
  @GetMapping("/sortbyid")
    public ResponseEntity<List<Customer>>sortById()
  {
      return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparing(Customer::getCustId)).toList());
  }
 @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer>>sortByName()
 {
     return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparing(Customer::getCustName)).toList());
 }
  @PutMapping("/update/{custId}")
    public ResponseEntity<String>update(@PathVariable int custId,@RequestBody Customer customer)
  {
      if(customerServiceImpl.findById(custId).getCustId()==custId)
      {
          customerServiceImpl.update(custId,customer);
      }
      return ResponseEntity.ok("Data Updated Successfully");
  }
  @PatchMapping("/changeaddress/{custId}/{custAddress}")
    public ResponseEntity<String>changeAddress(@PathVariable int custId,@PathVariable String custAddress)
  {
      if(customerServiceImpl.findById(custId).getCustId()==custId)
      {
          customerServiceImpl.changeAddress(custId,custAddress);
      }
      return ResponseEntity.ok("Change Address Successfully");
  }
  @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String>deleteById(@PathVariable int custId)
  {
      if(customerServiceImpl.findById(custId).getCustId()==custId) {
          customerServiceImpl.deleteById(custId);
      }
       return ResponseEntity.ok("Data Deleted Successfully");
  }
  @DeleteMapping("/deleteall")
    public ResponseEntity<String>deleteAll()
  {
      customerServiceImpl.deleteAll();
      return ResponseEntity.ok("Data Deleted Successfully");
  }
}
