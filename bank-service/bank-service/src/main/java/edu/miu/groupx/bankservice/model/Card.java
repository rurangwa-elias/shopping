package edu.miu.groupx.bankservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String holderName;
    private String cardNumber;
    private String CCV;
    private String expirationDate;
    private CardStatus status;


}
