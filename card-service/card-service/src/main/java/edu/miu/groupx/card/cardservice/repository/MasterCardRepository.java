package edu.miu.groupx.card.cardservice.repository;


import edu.miu.groupx.card.cardservice.model.MasterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCardRepository extends JpaRepository<MasterCard, Long> {
    MasterCard findMasterCardByCardNumberAndCCV(String cardNumber, String CCV);
}
