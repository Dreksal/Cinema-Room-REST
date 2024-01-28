package org.example.cinema;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCustomMessage {
    String token;

    @JsonProperty("ticket")
    Seats seat;




    public ResponseCustomMessage(Seats seat) {
        this.seat = seat;
    }

    public ResponseCustomMessage(Seats seat, Ticket token) {
        this(seat);
        this.token = token.getToken();
    }

    public String getToken() {
        return token;
    }

    public void setToken(Ticket token) {
        this.token = token.getToken();
    }

    public Seats getSeat() {
        return seat;
    }

    public void setSeat(Seats seat) {
        this.seat = seat;
    }
}
