package edu.miu.groupx.bankservice.controller;


import edu.miu.groupx.bankservice.model.Address;
import edu.miu.groupx.bankservice.model.CheckingAccount;
import edu.miu.groupx.bankservice.model.Customer;
import edu.miu.groupx.bankservice.service.AccountService;
import edu.miu.groupx.bankservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("bank")
public class BankServiceController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    /**
     * ************************************USER RELATED APIS ***********************************************************
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getAllUsers() {

        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.CREATED);
    }

    @PostMapping("/users/{cardType}")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer newCustomer, @PathVariable String cardType) {
        System.out.println("call captured...............");

        return new ResponseEntity<>(customerService.createNewCustomer(newCustomer, cardType), HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer updatedCustomer) {
        return new ResponseEntity<>(customerService.updateCustomerDetail(updatedCustomer), HttpStatus.CREATED);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.CREATED);
    }
    /**
     * ********************************ACCOUNT RELATED APIS******************************************
     */
}
