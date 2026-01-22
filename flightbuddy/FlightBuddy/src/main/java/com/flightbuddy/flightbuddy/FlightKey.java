package com.flightbuddy.flightbuddy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class FlightKey {

    private final Airport from;
    private final Airport to;
    private final LocalDate date;
    private final LocalTime time;

    public FlightKey(Airport from, Airport to, LocalDate date, LocalTime time) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
    }

    public static FlightKey fromFlight(Flight f) {
        return new FlightKey(
                f.getFrom(),
                f.getTo(),
                f.getDate(),
                f.getTime()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightKey other)) return false;
        return from == other.from
                && to == other.to
                && Objects.equals(date, other.date)
                && Objects.equals(time, other.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, date, time);
    }

}
