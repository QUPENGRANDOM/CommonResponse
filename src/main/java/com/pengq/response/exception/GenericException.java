package com.pengq.response.exception;

import com.pengq.response.Message;

public abstract class GenericException extends Exception{
    private String responseCode;

    public GenericException(String responseCode,String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public <T extends Message> GenericException(T entity) {
        super(entity.getMessage());
        this.responseCode = entity.getCode();
    }

    public String getResponseCode(){
        return responseCode;
    }
}
