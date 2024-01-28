package org.example.cinema;

import java.util.Objects;
import java.util.UUID;

public class Ticket {


    private final UUID token;

    public Ticket() {
        this.token = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Ticket object = (Ticket) obj;
        return Objects.equals(token, object.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    public String getToken() {
        return token.toString();
    }
}
