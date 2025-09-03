package org.example.backend.Exceptions;

public class NotAvailableException extends RuntimeException{
    public NotAvailableException(String message) {
        super(message);
    }
}
