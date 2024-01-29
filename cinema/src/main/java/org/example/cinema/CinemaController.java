package org.example.cinema;

import org.example.cinema.customExceptions.BadPasswordException;
import org.example.cinema.customExceptions.SeatAlreadyPurchasedException;
import org.example.cinema.customExceptions.WrongNumberOfRowOrColumnException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {

    @GetMapping("/seats")
    public Cinema getCinemaInfo() {
        return Cinema.getInstance();
    }


    @PostMapping("/purchase")
    public ResponseEntity<ResponseCustomMessage> tryPurchase(@RequestBody Seats seatToBuy) {
        int codeOfPurchase = Cinema.getInstance().purchase(seatToBuy);
        switch (codeOfPurchase) {
            case 0:
                throw new SeatAlreadyPurchasedException("The ticket has been already purchased!");
            case 1:
                return ResponseEntity.ok()
                        .body(new ResponseCustomMessage(Cinema.getInstance().getBoughtSeat(seatToBuy),
                                Cinema.getInstance().getBoughtTicket(seatToBuy)));
            case -1:
                throw new WrongNumberOfRowOrColumnException("The number of a row or a column is out of bounds!");
        }
        return null;
    }


    @PostMapping("/return")
    public ResponseEntity<ResponseCustomMessage> refundTicket(@RequestBody Ticket ticket) {
        Seats seat = Cinema.getInstance().refundMoney(ticket);
        return ResponseEntity.ok().body(new ResponseCustomMessage(seat));
    }

    @GetMapping("/stats")
    public ResponseEntity<?> showStats(@RequestParam(required = false) String password) {
        if(password != null && password.equals("super_secret")) {
            return  ResponseEntity.ok().body(Map.of(
                    "income", Cinema.getInstance().getIncome(),
                    "available", Cinema.getInstance().getSeats().size(),
                    "purchased", Cinema.getInstance().getBoughtSeats().size()
            ));
        }
        throw new BadPasswordException("The password is wrong!");
    }
}
