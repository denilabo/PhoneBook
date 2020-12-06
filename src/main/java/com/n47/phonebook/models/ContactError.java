package com.n47.phonebook.models;

public class ContactError {
    private String code;

    private String message;

    public String getCode() {
        return code;
    }
    
    public ContactError (){
        
    }
    
    public ContactError (String message){
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}