package com.flightbuddy.flightbuddy;

import java.time.LocalDate;
import java.util.List;

public class RoundTripResult {

    private final LocalDate departureDate;
    private final LocalDate returnDate;

    private final List<Flight> outboundFlights;
    private final List<Flight> inboundFlights;

    public RoundTripResult(
            LocalDate departureDate,
            LocalDate returnDate,
            List<Flight> outboundFlights,
            List<Flight> inboundFlights
    ) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.outboundFlights = outboundFlights;
        this.inboundFlights = inboundFlights;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public List<Flight> getOutboundFlights() {
        return outboundFlights;
    }

    public List<Flight> getInboundFlights() {
        return inboundFlights;
    }
}
