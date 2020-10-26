package edu.miu.groupx.bankservice.controller;


import edu.miu.groupx.bankservice.model.Customer;
import edu.miu.groupx.bankservice.model.wrappermodel.Payment;
import edu.miu.groupx.bankservice.model.wrappermodel.User;
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
     *
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getAllUsers() {

        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.CREATED);
    }

    @PostMapping("/users/{cardType}")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody User newUser, @PathVariable String cardType) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(newUser.getFirstName());
        newCustomer.setLastName(newUser.getLastName());
        newCustomer.setDob_date(newUser.getDob_date());
        newCustomer.setDob_month(newUser.getDob_month());
        newCustomer.setDob_year(newUser.getDob_year());
        newCustomer.setSSN(newUser.getSSN());

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
    @PostMapping("/make-payment")
    public ResponseEntity<Boolean> makePayment(@RequestBody Payment payment) {
        return new ResponseEntity<Boolean>(accountService.makePayment(
                payment.getSourceAccountNumber(),
                payment.getDestinationAccountNumber(),
                payment.getAmount()),HttpStatus.CREATED
                );
    }

}
