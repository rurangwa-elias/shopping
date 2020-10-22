package edu.miu.groupx.card.cardservice.repository;


import edu.miu.groupx.card.cardservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
