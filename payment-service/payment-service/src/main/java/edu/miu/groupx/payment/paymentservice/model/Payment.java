package edu.miu.groupx.payment.paymentservice.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String paymentNumber;
    String customerName;
    String orderNumber;
    String cardInformation;
    LocalDate paymentDate;
    BigDecimal amount;
    PaymentStatus status;

}
