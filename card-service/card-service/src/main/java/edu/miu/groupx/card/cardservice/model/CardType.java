package edu.miu.groupx.card.cardservice.model;

public enum CardType {
    MASTER_CARD_START_NUMBER(5),
    VISA_CARD_START_NUMBER(4),
    MASTER_CARD_LENGTH(16),
    VISA_CARD_LENGTH(15);
    private Integer firstDigit;

    public Integer getValue() {
        return this.firstDigit;
    }

    private CardType(Integer fistDigit) {
        this.firstDigit = fistDigit;
    }


}
