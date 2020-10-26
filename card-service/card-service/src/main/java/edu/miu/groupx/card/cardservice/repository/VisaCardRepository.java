package edu.miu.groupx.card.cardservice.repository;

import edu.miu.groupx.card.cardservice.model.VisaCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaCardRepository extends JpaRepository<VisaCard, Long> {
    public VisaCard findMasterCardByCardNumberAndCCV(String cardNumber, String CCV);
}
