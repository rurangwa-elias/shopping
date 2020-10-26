package edu.miu.groupx.bankservice.exeception;

public class AccountException  extends RuntimeException{
    public AccountException(Exception e){
        super(e);
    }
    public AccountException(String message){
        super(message);
    }
}
