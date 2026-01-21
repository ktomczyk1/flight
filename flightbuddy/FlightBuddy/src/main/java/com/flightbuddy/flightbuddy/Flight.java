package com.flightbuddy.flightbuddy;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {

    private final Airport from;
    private final Airport to;
    private final LocalDate date;
    private final LocalTime time;
    private final int price; // PLN

    public Flight(Airport from, Airport to, LocalDate date, LocalTime time, int price) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getPrice() { return price; }
}
