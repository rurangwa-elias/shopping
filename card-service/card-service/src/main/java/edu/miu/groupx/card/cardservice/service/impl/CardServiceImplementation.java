package edu.miu.groupx.card.cardservice.service.impl;

import edu.miu.groupx.card.cardservice.model.Card;
import edu.miu.groupx.card.cardservice.repository.CardRepository;
import edu.miu.groupx.card.cardservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CardServiceImplementation implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card findCardById(Long id) {
        return null;
    }

    @Override
    public void deleteCard(Card card) {

    }

    @Override
    public Card addCard(Card card) {
        return null;
    }

    @Override
    public List<Card> findAllCards() {
        return null;
    }

    @Override
    public String generateCreditCardNumber(String bin, int length) {
        return null;
    }

    @Override
    public boolean isValidCard(String cardNumber) {
        return false;
    }
}
