package edu.miu.groupx.payment.paymentservice.service;

import edu.miu.groupx.payment.paymentservice.model.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface PaymentService {
    public Payment findPaymentById(Long id);
    public void deletePayment(Payment payment);
    public Receipt chargeOrder(Order order);
    public Payment updatePayment(Payment payment);
    public List<Payment>findAllPayments();
    public String getCardStatus(Card card);
    public BalanceStatus verifyCardBalance(Card card, BigDecimal amount);

}
