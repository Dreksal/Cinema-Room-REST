package org.example.cinema;

import org.example.cinema.customExceptions.BadPasswordException;
import org.example.cinema.customExceptions.SeatAlreadyPurchasedException;
import org.example.cinema.customExceptions.WrongNumberOfRowOrColumnException;
import org.example.cinema.customExceptions.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllersExceptionHandler {

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<?> handleWrongToken(WrongTokenException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(WrongNumberOfRowOrColumnException.class)
    public ResponseEntity<?> handleWrongToken(WrongNumberOfRowOrColumnException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatAlreadyPurchasedException.class)
    public ResponseEntity<?> handleWrongToken(SeatAlreadyPurchasedException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadPasswordException.class)
    public ResponseEntity<?> handleBadPassword(BadPasswordException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
