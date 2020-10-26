package edu.miu.groupx.bankservice.service.impl;

import edu.miu.groupx.bankservice.exeception.CustomerException;
import edu.miu.groupx.bankservice.model.CheckingAccount;
import edu.miu.groupx.bankservice.model.Customer;
import edu.miu.groupx.bankservice.repository.CustomerRepository;
import edu.miu.groupx.bankservice.service.AccountService;
import edu.miu.groupx.bankservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImplementation implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public Customer createNewCustomer(Customer newCustomer, String cardType) {

        String holderName = newCustomer.getFirstName() + " " + newCustomer.getLastName();
        CheckingAccount account = accountService.createCheckingAccount(holderName, cardType);
        newCustomer.setAccount(account);

        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerException("Customer by this id does not exist"));
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Customer updateCustomerDetail(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
