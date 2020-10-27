package edu.miu.groupx.bankservice.service.impl;

import edu.miu.groupx.bankservice.exeception.AccountException;
import edu.miu.groupx.bankservice.model.AccountEntries;
import edu.miu.groupx.bankservice.model.Card;
import edu.miu.groupx.bankservice.model.CheckingAccount;
import edu.miu.groupx.bankservice.model.TransactionType;
import edu.miu.groupx.bankservice.repository.CheckingAccountRepository;
import edu.miu.groupx.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class AccountServiceImplementation implements AccountService {
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Value("${card-service.url}")
    private String cardServiceUrl;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CheckingAccount createCheckingAccount(String holderName, String cardType) {
        //call the card API here (either master-card  or visa-card)
        HttpHeaders headers = new HttpHeaders();
        //set headers and tokens here
        HttpEntity<String> cardHttpEntity = new HttpEntity<>(holderName, headers);
        System.out.println("calling card service.............." + cardType);
        // send request to the card service and ask it to generate a new card number for the new customer
        ResponseEntity<Card> newCard = restTemplate.postForEntity(cardServiceUrl + "/" + cardType, cardHttpEntity, Card.class);
        //set the details of the card individually. this is important for the fact that the card id will not be set to the one gotten from the remote
        CheckingAccount newCheckingAccount = new CheckingAccount();
        newCheckingAccount.setAccountNumber(this.generateAccountNumber());

        if (cardType.equals("master-card")) {
            Card newMasterCard = new Card();
            newMasterCard.setHolderName(newCard.getBody().getHolderName());
            newMasterCard.setCardNumber(newCard.getBody().getCardNumber());
            newMasterCard.setCCV(newCard.getBody().getCCV());
            newMasterCard.setExpirationDate(newCard.getBody().getExpirationDate());
            newMasterCard.setStatus(newCard.getBody().getStatus());
            newCheckingAccount.setCard(newMasterCard);

        } else {
            Card newVisaCard = new Card();
            newVisaCard.setHolderName(newCard.getBody().getHolderName());
            newVisaCard.setCardNumber(newCard.getBody().getCardNumber());
            newVisaCard.setCCV(newCard.getBody().getCCV());
            newVisaCard.setExpirationDate(newCard.getBody().getExpirationDate());
            newVisaCard.setStatus(newCard.getBody().getStatus());
            newCheckingAccount.setCard(newVisaCard);
        }


        return newCheckingAccount;
    }

    @Override
    public CheckingAccount updateCheckingAccount(CheckingAccount account) {

        return checkingAccountRepository.save(account);
    }

    @Override
    public void deleteCheckingAccount(CheckingAccount account) {

        checkingAccountRepository.delete(account);

    }

    @Override
    public CheckingAccount findCheckingAccountById(Long id) {
        return checkingAccountRepository.findById(id).orElseThrow(() -> new AccountException("Could not find account"));
    }

    @Override
    public List<CheckingAccount> getAllCheckingAccount() {

        return checkingAccountRepository.findAll();
    }

    @Override
    public CheckingAccount deposit(BigDecimal amount, String accountNumber) {
        CheckingAccount account = this.findAccountByAccountNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        AccountEntries accountEntry = new AccountEntries();
        accountEntry.setTransactionDate(LocalDate.now());
        accountEntry.setAmount(amount);
        accountEntry.setSubjectAccountNumber(accountNumber);
        accountEntry.setTransactionType(TransactionType.DEPOSIT);
        account.getAccountEntries().add(accountEntry);
        checkingAccountRepository.save(account);
        return checkingAccountRepository.save(account);
    }

    @Override
    public CheckingAccount withdraw(BigDecimal amount, String accountNumber) {

        CheckingAccount account = this.findAccountByAccountNumber(accountNumber);
        if (this.hasEnoughBalance(amount, accountNumber)) {
            account.setBalance(account.getBalance().subtract(amount));
            AccountEntries accountEntry = new AccountEntries();
            accountEntry.setTransactionDate(LocalDate.now());
            accountEntry.setAmount(amount);
            accountEntry.setSubjectAccountNumber(accountNumber);
            accountEntry.setTransactionType(TransactionType.WITHDRAWAL);
            account.getAccountEntries().add(accountEntry);
            checkingAccountRepository.save(account);
        }
        return account;
    }

    @Override
    public CheckingAccount findAccountByAccountNumber(String accountNumber) {

        return checkingAccountRepository.findCheckingAccountByAccountNumber(accountNumber);
    }

    @Override
    public boolean hasEnoughBalance(BigDecimal amount, String accountNumber) {
        CheckingAccount account = this.findAccountByAccountNumber(accountNumber);
        BigDecimal currentBalance = account.getBalance();
        BigDecimal difference = currentBalance.subtract(amount);
        return (difference.compareTo(BigDecimal.ZERO) != -1);
    }


    private Boolean transferFund(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) {
        Boolean transferStatus = false;
        if (hasEnoughBalance(amount, sourceAccountNumber)) {
//            withdraw(amount, sourceAccountNumber);
//            deposit(amount, destinationAccountNumber);
            CheckingAccount sourceAccount = this.findAccountByAccountNumber(sourceAccountNumber);
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            CheckingAccount destinationAccount = this.findAccountByAccountNumber(destinationAccountNumber);
            destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
            LocalDate transactionDate = LocalDate.now();

            AccountEntries sourceEntry = new AccountEntries();
            sourceEntry.setTransactionDate(transactionDate);
            sourceEntry.setAmount(amount);
            sourceEntry.setSubjectAccountNumber(destinationAccountNumber);
            sourceEntry.setTransactionType(TransactionType.WITHDRAWAL);
            sourceAccount.getAccountEntries().add(sourceEntry);
            checkingAccountRepository.save(sourceAccount);

            AccountEntries destinationEntry = new AccountEntries();
            destinationEntry.setTransactionDate(transactionDate);
            destinationEntry.setAmount(amount);
            destinationEntry.setSubjectAccountNumber(sourceAccountNumber);
            destinationEntry.setTransactionType(TransactionType.DEPOSIT);
            destinationAccount.getAccountEntries().add(destinationEntry);
            checkingAccountRepository.save(destinationAccount);
            transferStatus = true;

        }
        return transferStatus;
    }

    @Override
    public Boolean makePayment(String sourceAccount, String destinationAccount, BigDecimal amount) {
        return this.transferFund(sourceAccount, destinationAccount, amount);
    }

    /**
     * Account Utility methods
     */
    public String generateAccountNumber() {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder("2");
        for (int i = 0; i < 9; i++) {
            int rand = random.nextInt(10);
            stringBuilder.append(rand);
        }
        return stringBuilder.toString();
    }


}
