package edu.miu.groupx.card.cardservice.service;

import edu.miu.groupx.card.cardservice.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CardService {
    public Card findCardById(Long id);
    public void deleteCard( Card card);
    public  Card addCard(Card card);
    public List<Card>findAllCards();
    public String generateCreditCardNumber(String bin, int length);
    boolean isValidCard(String cardNumber);


}
