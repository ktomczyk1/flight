package com.flightbuddy.flightbuddy;

public class Booking {
    private Flight outbound;
    private Flight inbound;

    public Booking(Flight outbound, Flight inbound) {
        this.outbound = outbound;
        this.inbound = inbound;
    }

    public Flight getOutbound() { return outbound; }
    public Flight getInbound() { return inbound; }
}
