package com.flightbuddy.flightbuddy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Flight {

    private final UUID id;
    private final Airport from;
    private final Airport to;
    private final LocalDate date;
    private final LocalTime time;
    private final int price;
    private FlightStatus status;

    public Flight(Airport from, Airport to,
            LocalDate date, LocalTime time, int price
    ) {
        this.id = UUID.randomUUID();
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.price = price;
        this.status = FlightStatus.ACTIVE;
    }

    public UUID getId() { return id; }
    public Airport getFrom() { return from; }
    public Airport getTo() { return to; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public int getPrice() { return price; }
    public FlightStatus getStatus() {return status;}
    public boolean isActive() {return status == FlightStatus.ACTIVE;}
    public void cancel() {this.status = FlightStatus.CANCELED;} // Anuluje lot
    public void restore() {this.status = FlightStatus.ACTIVE;}  // Przywraca anulowany lot
}
