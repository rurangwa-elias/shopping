package edu.miu.groupx.card.cardservice.service.impl;

import edu.miu.groupx.card.cardservice.model.MasterCard;
import edu.miu.groupx.card.cardservice.service.VisaCardService;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class VisaVisaCardServiceImplementation implements VisaCardService {
    @Override
    public MasterCard findCardById(Long id) {
        return null;
    }

    @Override
    public void deleteCard(MasterCard card) {

    }

    @Override
    public MasterCard addCard(MasterCard card) {
        return null;
    }

    @Override
    public List<MasterCard> findAllCards() {
        return null;
    }
}
