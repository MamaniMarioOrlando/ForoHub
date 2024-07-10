package com.ForoHub.ForoHubChallenge.infra.errors;

public class ResourcedNotFoundException extends IllegalArgumentException{
    private String resource;
    private String fieldName;
    private Long value;
    public ResourcedNotFoundException(String resource, String fieldName, Long value){
        super(String.format("%s not found: %s, %s", resource, fieldName, value));
        this.resource =  resource;
        this.fieldName = fieldName;
        this.value = value;
    }
}
