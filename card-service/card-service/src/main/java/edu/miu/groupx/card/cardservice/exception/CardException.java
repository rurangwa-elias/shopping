package edu.miu.groupx.card.cardservice.exception;

public class CardException extends RuntimeException{
    public CardException(Exception e){
        super(e);
    }
    public CardException(String message){
        super(message);
    }
}
