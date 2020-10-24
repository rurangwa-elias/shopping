package edu.miu.groupx.payment.paymentservice.exception;

public class PaymentException extends RuntimeException {
    public PaymentException(Exception e){
        super(e);
    }
    public PaymentException(String message){
        super(message);
    }
}
