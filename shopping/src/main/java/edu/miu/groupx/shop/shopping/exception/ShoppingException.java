package edu.miu.groupx.shop.shopping.exception;

public class ShoppingException  extends RuntimeException{

    public ShoppingException(Exception ex){
        super(ex);
    }
    public  ShoppingException(String message){
        super(message);
    }
}
