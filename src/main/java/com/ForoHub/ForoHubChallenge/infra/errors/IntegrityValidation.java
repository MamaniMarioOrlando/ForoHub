package com.ForoHub.ForoHubChallenge.infra.errors;

public class IntegrityValidation extends RuntimeException{
    public IntegrityValidation(String s){
        super(s);
    }
}
