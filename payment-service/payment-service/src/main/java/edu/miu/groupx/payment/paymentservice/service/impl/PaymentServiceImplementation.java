package edu.miu.groupx.payment.paymentservice.service.impl;

import edu.miu.groupx.payment.paymentservice.exception.PaymentException;
import edu.miu.groupx.payment.paymentservice.model.*;
import edu.miu.groupx.payment.paymentservice.repository.PaymentRepository;
import edu.miu.groupx.payment.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Transactional
@Component
public class PaymentServiceImplementation implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${card-service.url}")
    private String cardServiceUrl;
    @Value("${bank-service.url}")
    private String bankServiceUrl;


    @Override
    public Payment findPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentException("Payment not found!! "));
    }

    @Override
    public void deletePayment(Payment payment) {
        paymentRepository.delete(payment);
    }

    @Override
    public Receipt chargeOrder(Order newOrder) {
        Card paymentCard = newOrder.getCard();
        String cardStatus = this.getCardStatus(paymentCard);
        BigDecimal amount = newOrder.getAmount();

        if (cardStatus.equals(CardStatus.VALID)) {
            if (this.verifyCardBalance(paymentCard, amount).equals(BalanceStatus.ENOUGH)) {


                //CALL AN API TO DEDUCT THE AMOUNT FROM THE BALANCE AND CHARGE TO THE VENDOR
                //IF THE TRANSACTION IS SUCCESSFUL
                Payment newPayment = new Payment();
                newPayment.setAmount(amount);
                paymentRepository.save(newPayment);

                //generate a detailed receipt for the transaction
                Receipt receipt = new Receipt();

                return receipt;
            }
        }



        return null;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public String getCardStatus(Card card) {
        HttpHeaders headers = new HttpHeaders();
        // set headers here, for example extract the token and re-insert it
        HttpEntity<Card> cardStatusRequest = new HttpEntity<>(card, headers);
        ResponseEntity<String> cardResponse = restTemplate.postForEntity(cardServiceUrl + "master-card/verify", cardStatusRequest, String.class);
        return cardResponse.getBody();
    }

    @Override
    public BalanceStatus verifyCardBalance(Card card, BigDecimal amount) {
        //call a bank api and ask for the amount of available balance the user has

        //if the balance is sufficient then send Balance status message to the caller
        return null;
    }
}
