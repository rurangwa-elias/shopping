package edu.miu.groupx.card.cardservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class MasterCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String holderName;
    private String cardNumber;
    private String CCV;
    private String expirationMonth;
    private String expirationYear;
    private CardStatus status;

    public MasterCard(String holderName, String cardNumber, String CCV, String expirationMonth, String expirationYear) {
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.CCV = CCV;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }
}
