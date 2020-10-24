package edu.miu.groupx.card.cardservice.service.impl;

import edu.miu.groupx.card.cardservice.exception.CardException;
import edu.miu.groupx.card.cardservice.model.VisaCard;
import edu.miu.groupx.card.cardservice.repository.VisaCardRepository;
import edu.miu.groupx.card.cardservice.service.VisaCardService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class VisaCardServiceImplementation implements VisaCardService {

    @Autowired
    private VisaCardRepository visaCardRepository;

    @Override
    public VisaCard findCardById(Long id) {

        return this.visaCardRepository.findById(id).orElseThrow(() -> new CardException("Card not found"));
    }

    @Override
    public void deleteCard(VisaCard card) {
        this.visaCardRepository.delete(card);
    }

    @Override
    public VisaCard addCard(VisaCard card) {

        return this.visaCardRepository.save(card);
    }

    @Override
    public List<VisaCard> findAllCards() {

        return this.visaCardRepository.findAll();
    }
}
