package com.ForoHub.ForoHubChallenge.infra.errors;

import lombok.Getter;

@Getter
public class ResourcedNotFoundException extends IllegalArgumentException{
    private final String resource;
    private final String fieldName;
    private final Long value;
    public ResourcedNotFoundException(String resource, String fieldName, Long value){
        super(String.format("%s not found: %s, %s", resource, fieldName, value));
        this.resource =  resource;
        this.fieldName = fieldName;
        this.value = value;
    }
}
