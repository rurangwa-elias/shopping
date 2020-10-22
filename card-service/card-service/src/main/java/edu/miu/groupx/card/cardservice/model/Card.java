package edu.miu.groupx.card.cardservice.model;


import lombok.Data;
import javax.persistence.*;
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String holderName ;
    private String cardNumber;
    private String CCV ;
    private String expirationDate;

}
