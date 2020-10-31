package edu.miu.groupx.payment.paymentservice.model.enums;

public enum TransactionStatus {
    CARD_NOT_SUPPORTED("CARD_NOT_SUPPORTED"),
    INSUFFICIENT_FUND("INSUFFICIENT_FUND"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    CANNOT_VERIFY_RECIPIENT_ACCOUNT("CANNOT_VERIFY_RECIPIENT'S_ACCOUNT "),
    CANNOT_VERIFY_PAYER_ACCOUNT("SOURCE_ACCOUNT_CANNOT_BE_VERIFIED"),
    NOT_ENOUGH_BALANCE("NOT_ENOUGH_BALANCE"),
    ACCOUNT_CANNOT_MAKE_PAYMENTS("ACCOUNT_IS_DISABLED_FOR_MAKING_PAYMENTS"),
    PAYMENT_COMPLETED_SUCCESSFULLY("TRANSACTION_HAS_COMPLETED_SUCCESSFULLY"),
    DEPOSIT_SUCCESSFUL("DEPOSIT_SUCCESSFUL"),
    DEPOSIT_UNSUCCESSFUL("CANNOT_COMPLETE_DEPOSIT"),
    WITHDRAWAL_UNSUCCESSFUL("CANNOT_COMPLETE_WITHDRAWAL "),
    WITHDRAWAL_SUCCESSFUL("WITHDRAWAL_SUCCESSFUL"),
    CARD_ERROR("CARD_ERROR");
    private String transactionStatus;
    public String getTransactionStatus(){
        return this.transactionStatus;
    }
    private TransactionStatus(String transactionStatus){
        this.transactionStatus = transactionStatus;
    }
}