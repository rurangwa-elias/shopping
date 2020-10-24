package edu.miu.groupx.payment.paymentservice.model;

import lombok.Data;

@Data
public class Receipt {
    private Long receiptNumber;
    private String orderNumber;
    private String description;
    private String customerName;


}
