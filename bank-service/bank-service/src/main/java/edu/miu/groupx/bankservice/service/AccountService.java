package edu.miu.groupx.bankservice.service;

import edu.miu.groupx.bankservice.model.Card;
import edu.miu.groupx.bankservice.model.CheckingAccount;
import edu.miu.groupx.bankservice.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
 @Service
public interface AccountService {
    public CheckingAccount createCheckingAccount(String holderName, String cardType);

    public CheckingAccount updateCheckingAccount(CheckingAccount account);

    public void deleteCheckingAccount(CheckingAccount account);

    public CheckingAccount getCheckingAccountById(Long id);

    public List<CheckingAccount> getAllCheckingAccount();

    public BigDecimal deposit(BigDecimal amount, String accountNumber);

    public BigDecimal withdraw(BigDecimal amount, String accountNumber);

    public CheckingAccount findAccountByCardNumber(String accountNumber);

    public boolean hasEnoughBalance(BigDecimal amount, String accountNumber);


}
