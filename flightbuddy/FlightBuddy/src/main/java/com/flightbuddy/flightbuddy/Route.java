package com.flightbuddy.flightbuddy;

import java.time.DayOfWeek;
import java.util.Set;

public class Route {

    private final Airport a;
    private final Airport b;

    private final Set<DayOfWeek> operatingDays;
    private final int flightsPerDay; // 1–3

    public Route(Airport a,
                 Airport b,
                 Set<DayOfWeek> operatingDays,
                 int flightsPerDay) {
        this.a = a;
        this.b = b;
        this.operatingDays = operatingDays;
        this.flightsPerDay = flightsPerDay;
    }

    public Airport getA() {
        return a;
    }

    public Airport getB() {
        return b;
    }

    public boolean connects(Airport from, Airport to) {
        return (a == from && b == to)
                || (a == to && b == from);
    }

    public boolean operatesOn(DayOfWeek day) {
        return operatingDays.contains(day);
    }

    public int getFlightsPerDay() {
        return flightsPerDay;
    }

    @Override
    public String toString() {
        return a + " <-> " + b;
    }
}
