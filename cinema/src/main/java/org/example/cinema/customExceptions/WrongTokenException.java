package org.example.cinema.customExceptions;

public class WrongTokenException extends RuntimeException{
    public WrongTokenException(String message) {
        super(message);
    }

}
