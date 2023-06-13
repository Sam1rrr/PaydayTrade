package com.gradiant.paydaytrade.exception;

public class AuthenticationFailException  extends IllegalArgumentException {
    public AuthenticationFailException(String msg){
        super(msg);
    }
}
