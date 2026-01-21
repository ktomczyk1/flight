package com.flightbuddy.flightbuddy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.*;

public class FlightService {




    private final List<Route> routes;

    public FlightService(List<Route> routes) {
        this.routes = routes;
    }

    // --- GŁÓWNA METODA ---
    public List<Flight> searchFlights(
            Airport from,
            Airport to,
            LocalDate fromDate,
            LocalDate toDate
    ) {
        List<Flight> result = new ArrayList<>();

        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {

            DayOfWeek day = date.getDayOfWeek();

            for (Route route : routes) {

                if (!route.connects(from, to)) continue;
                if (!route.operatesOn(day)) continue;

                result.addAll(generateFlightsForDay(route, from, to, date));
            }
        }
        return result;
    }


    // loty wte i we wte
    public RoundTripResult searchRoundTripOnExactDates(
            Airport from,
            Airport to,
            LocalDate departureDate,
            LocalDate returnDate
    ) {
        List<Flight> outbound = searchFlights(from, to, departureDate, departureDate);
        List<Flight> inbound  = searchFlights(to, from, returnDate, returnDate);

        outbound.sort(Comparator.comparing(Flight::getTime));
        inbound.sort(Comparator.comparing(Flight::getTime));

        System.out.println("OUTBOUND " + departureDate + " = " + outbound.size());
        System.out.println("INBOUND  " + returnDate + " = " + inbound.size());


        return new RoundTripResult(departureDate, returnDate, outbound, inbound);
    }


    // --- LOTY NA JEDEN DZIEŃ ---
    private List<Flight> generateFlightsForDay(
            Route route,
            Airport from,
            Airport to,
            LocalDate date
    ) {
        List<Flight> flights = new ArrayList<>();

        for (int i = 0; i < route.getFlightsPerDay(); i++) {

            long seed = Objects.hash(
                    route.getA().name(),
                    route.getB().name(),
                    date,
                    i
            );

            Random r = new Random(seed);

            int hour = 6 + r.nextInt(17);      // 6–22
            int minute = r.nextInt(12) * 5;

            LocalTime time = LocalTime.of(hour, minute);


            int price = deterministicPrice(from, to, date, time);

            flights.add(new Flight(
                    from,
                    to,
                    date,
                    time,
                    price
            ));

        }

        return flights;
    }

    private int deterministicPrice(Airport from, Airport to, LocalDate date, LocalTime time) {

        // seed zależy od trasy + dnia + godziny (zawsze ten sam wynik dla tego lotu)
        long seed = Objects.hash(from.name(), to.name(), date, time.getHour(), time.getMinute(), "PRICE");

        Random r = new Random(seed);

        // 70..600 włącznie
        return 70 + r.nextInt(531);
    }



}
