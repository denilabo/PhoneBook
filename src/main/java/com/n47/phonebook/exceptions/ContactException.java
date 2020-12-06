package com.n47.phonebook.exceptions;

public class ContactException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;
    ContactException() {
        super();
    }
 
    public ContactException(String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
 
    public String geMessage() {
        return message;
    }
 
}