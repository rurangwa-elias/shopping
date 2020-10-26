package edu.miu.groupx.card.cardservice.service;


import org.springframework.stereotype.Service;

@Service
public interface CardUtilService {
    public String generateCreditCardNumber(String bin, int length);
    public boolean isValidCard(String cardNumber);
    public int checkDigit(String number);
    public String generateExpirationDate();
    public String generateCCV();



}
