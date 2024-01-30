package org.example.cinema;

import org.example.cinema.customExceptions.WrongTokenException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Cinema {
    private static volatile Cinema instance;

    private final int rows = 9;
    private final int columns = 9;
    private CopyOnWriteArrayList<Seats> seats;                  //thread safety collections


    @JsonIgnore
    private long income = 0;
    @JsonIgnore
    private final ConcurrentMap<Ticket, Seats> boughtSeats;

    private Cinema() {
        seats = new CopyOnWriteArrayList<>();
        boughtSeats = new ConcurrentHashMap<>();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if( i < 4) {
                    seats.add(new Seats(i + 1, j + 1, 10));
                } else {
                    seats.add(new Seats(i + 1, j + 1, 8));
                }
            }
        }
    }

    public static Cinema getInstance() {
        Cinema result = instance;                   //double-checked locking (DCL)
        if (result != null) {
            return result;
        }
        synchronized (Cinema.class) {
            if(instance == null) {
                instance = new Cinema();
            }
            return instance;
        }
    }

    public int purchase(@NonNull Seats seatToBuy) {             //if seat is taken, then method returns 0, if there is wrong numb of row or column, then method return -1, otherwise, method return 1
        if(seatToBuy.getColumn() > columns || seatToBuy.getColumn() < 1) {
            return -1;
        }
        if(seatToBuy.getRow() > rows || seatToBuy.getRow() < 1) {
            return -1;
        }

        for(Seats seat : seats) {
            if(seatToBuy.equals(seat)) {
                seats.remove(seat);
                boughtSeats.put(new Ticket(), seat);
                income += seat.getPrice();
                return 1;
            }
        }
        return 0;
    }


    //getters and setters
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public CopyOnWriteArrayList<Seats> getSeats() {
        return seats;
    }

    public Seats getBoughtSeat(Seats seatToCheck) {
        for(Seats seat : boughtSeats.values()) {
            if(seatToCheck.equals(seat)) {
                return seat;
            }
        }
        return null;
    }
    public Ticket getBoughtTicket(Seats seatToCheck) {
        for(Map.Entry<Ticket, Seats> entry : boughtSeats.entrySet()) {
            if(entry.getValue().equals(seatToCheck)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Seats refundMoney(Ticket ticketToCheck) {
        for(Ticket ticket : boughtSeats.keySet()) {
            if(ticket.equals(ticketToCheck)) {
                Seats seat = boughtSeats.remove(ticket);
                seats.add(seat);
                income -= seat.getPrice();
                return seat;
            }
        }
        throw new WrongTokenException("Wrong token!");
    }

    public void setSeats(CopyOnWriteArrayList<Seats> seats) {
        this.seats = seats;
    }

    public long getIncome() {
        return income;
    }

    public ConcurrentMap<Ticket, Seats> getBoughtSeats() {
        return boughtSeats;
    }
}
