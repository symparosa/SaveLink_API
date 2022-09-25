package com.elisangela.savelink.dtos;

import com.elisangela.savelink.enums.responseType;

public class response {
    
    private String message;

    private responseType responseType;

    private Object object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public responseType getResponseType() {
        return responseType;
    }

    public void setResponseType(responseType responseType) {
        this.responseType = responseType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
