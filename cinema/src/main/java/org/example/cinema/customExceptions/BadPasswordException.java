package org.example.cinema.customExceptions;

public class BadPasswordException extends RuntimeException{
    public BadPasswordException(String message) {
        super(message);
    }
}
