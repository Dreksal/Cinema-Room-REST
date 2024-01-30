package org.example.cinema;

import java.util.Objects;

public class Seats {
    private int row;
    private int column;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seats seats = (Seats) o;
        return row == seats.row && column == seats.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }


    //constructors
    public Seats() {}

    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Seats(int row, int column, int price) {
        this(row, column);
        this.price = price;
    }

    //getters and setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
