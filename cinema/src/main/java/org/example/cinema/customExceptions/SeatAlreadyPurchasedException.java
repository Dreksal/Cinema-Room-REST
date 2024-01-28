package org.example.cinema.customExceptions;

public class SeatAlreadyPurchasedException extends RuntimeException{
    public SeatAlreadyPurchasedException(String message) {
        super(message);
    }
}
