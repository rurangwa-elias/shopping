package edu.miu.groupx.card.cardservice.service.impl;

import edu.miu.groupx.card.cardservice.exception.CardException;
import edu.miu.groupx.card.cardservice.model.MasterCard;
import edu.miu.groupx.card.cardservice.repository.MasterCardRepository;
import edu.miu.groupx.card.cardservice.service.MasterCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MasterCardServiceImplementation implements MasterCardService {
    @Autowired
    private MasterCardRepository masterCardRepository;


    @Override
    public MasterCard findCardById(Long id) {

        return masterCardRepository.findById(id).orElseThrow(() ->
                new CardException("Card not found"));
    }

    @Override
    public void deleteCard(MasterCard card) {

        masterCardRepository.delete(card);

    }

    @Override
    public MasterCard addCard(MasterCard card) {

        return masterCardRepository.save(card);
    }

    @Override
    public List<MasterCard> findAllCards() {

        return masterCardRepository.findAll();
    }

    @Override
    public String getMasterCardStatus(String cardNumber, String CCV) {
        MasterCard card = masterCardRepository.findMasterCardByCardNumberAndCCV(cardNumber, CCV);
        String STATUS ="INVALID";
        return STATUS;
    }


}


