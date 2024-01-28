package org.example.cinema.customExceptions;

public class WrongNumberOfRowOrColumnException extends RuntimeException{

    public WrongNumberOfRowOrColumnException(String message) {
        super(message);
    }
}
