package edu.miu.groupx.card.cardservice.service;

import edu.miu.groupx.card.cardservice.model.MasterCard;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CardService {
    public MasterCard findCardById(Long id);
    public void deleteCard( MasterCard card);
    public MasterCard addCard(MasterCard card);
    public List<MasterCard>findAllCards();



}
