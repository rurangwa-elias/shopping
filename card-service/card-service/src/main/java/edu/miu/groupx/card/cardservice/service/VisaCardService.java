package edu.miu.groupx.card.cardservice.service;

import edu.miu.groupx.card.cardservice.model.VisaCard;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VisaCardService {
    public VisaCard findCardById(Long id);
    public void deleteCard( VisaCard card);
    public VisaCard addCard(VisaCard card);
    public List<VisaCard>findAllCards();



}
