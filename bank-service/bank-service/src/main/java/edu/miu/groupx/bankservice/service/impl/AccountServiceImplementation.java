package edu.miu.groupx.bankservice.service.impl;

import edu.miu.groupx.bankservice.exeception.AccountException;
import edu.miu.groupx.bankservice.model.Card;
import edu.miu.groupx.bankservice.model.CheckingAccount;
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
        Card newMasterCard = new Card();
        newMasterCard.setHolderName(newCard.getBody().getHolderName());
        newMasterCard.setCardNumber(newCard.getBody().getCardNumber());
        newMasterCard.setCCV(newCard.getBody().getCCV());
        newMasterCard.setExpirationDate(newCard.getBody().getExpirationDate());
        newMasterCard.setStatus(newCard.getBody().getStatus());
        newCheckingAccount.setCard(newMasterCard);
        return checkingAccountRepository.save(newCheckingAccount);
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
    public CheckingAccount getCheckingAccountById(Long id) {
        return checkingAccountRepository.findById(id).orElseThrow(() -> new AccountException("Could not find account"));
    }

    @Override
    public List<CheckingAccount> getAllCheckingAccount() {

        return checkingAccountRepository.findAll();
    }

    @Override
    public BigDecimal deposit(BigDecimal amount, String accountNumber) {
        CheckingAccount account = checkingAccountRepository.findCheckingAccountByCardCardNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        checkingAccountRepository.save(account);
        return account.getBalance();
    }

    @Override
    public BigDecimal withdraw(BigDecimal amount, String accountNumber) {
        CheckingAccount account = checkingAccountRepository.findCheckingAccountByCardCardNumber(accountNumber);
        BigDecimal currentBalance = account.getBalance();
        BigDecimal difference = currentBalance.subtract(amount);
        if (this.hasEnoughBalance(amount, accountNumber)) {
            currentBalance.subtract(amount);
            account.setBalance(currentBalance);
            checkingAccountRepository.save(account);
        }
        return account.getBalance();
    }

    @Override
    public CheckingAccount findAccountByCardNumber(String accountNumber) {
        return checkingAccountRepository.findCheckingAccountByCardCardNumber(accountNumber);
    }

    @Override
    public boolean hasEnoughBalance(BigDecimal amount, String accountNumber) {
        CheckingAccount account = checkingAccountRepository.findCheckingAccountByCardCardNumber(accountNumber);
        BigDecimal currentBalance = account.getBalance();
        BigDecimal difference = currentBalance.subtract(amount);
        return (difference.compareTo(BigDecimal.ZERO) != -1);
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
    /**
     * API caller
     */
//    private <T> T postApiCaller(String url){
//        HttpHeaders headers = new HttpHeaders();
//        Object type = null;
//        Class<T extends ? > className = type.getClass();
//        //set headers and tokens here
//       HttpEntity<String> cardHttpEntity = new HttpEntity<>(cardType, headers);
//        ResponseEntity<T> result = restTemplate.postForEntity(url, type.getClass());
//
//
//    }

}
