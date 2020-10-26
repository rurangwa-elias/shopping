package edu.miu.groupx.bankservice.exeception;

public class CustomerException extends RuntimeException{
    public CustomerException(Exception e){
        super(e);
    }
    public CustomerException(String message){
        super(message);
    }
}
