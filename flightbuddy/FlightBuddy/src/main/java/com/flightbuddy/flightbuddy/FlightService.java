package com.flightbuddy.flightbuddy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class FlightService {

    private final Map<FlightKey, FlightStatus> flightStatuses = new HashMap<>();
    private final List<Flight> manualFlights = new ArrayList<>();
    private final List<Flight> generatedFlights = new ArrayList<>();
    private final List<Route> routes;

    public FlightService(List<Route> routes) {
        this.routes = routes;

        // wygenerowanie wszystkich lotów z tras na najbliższe dni (np. 30 dni)
        LocalDate today = LocalDate.now();
        LocalDate end = today.plusDays(30);

        for (LocalDate date = today; !date.isAfter(end); date = date.plusDays(1)) {
            for (Route route : routes) {
                if (route.operatesOn(date.getDayOfWeek())) {
                    generatedFlights.addAll(generateFlightsForDay(route, route.getA(), route.getB(), date));
                }
            }
        }
    }

    // --- LOTY NA OKREŚLONY PRZEDZIAŁ DAT ---
    public List<Flight> searchFlights(
            Airport from,
            Airport to,
            LocalDate fromDate,
            LocalDate toDate
    ) {
        List<Flight> result = new ArrayList<>();

        for (Flight f : manualFlights) {
            if (f.getFrom() == from && f.getTo() == to &&
                    !f.getDate().isBefore(fromDate) && !f.getDate().isAfter(toDate)) {
                result.add(f); // dokładnie ten sam obiekt
            }
        }

        for (Flight f : generatedFlights) {
            if (f.getFrom() == from && f.getTo() == to &&
                    !f.getDate().isBefore(fromDate) && !f.getDate().isAfter(toDate)) {
                result.add(f); // dokładnie ten sam obiekt
            }
        }

        result.sort(Comparator.comparing(Flight::getTime));
        return result;
    }

    // loty wte i we wte
    public RoundTripResult searchRoundTripOnExactDates(
            Airport from,
            Airport to,
            LocalDate departureDate,
            LocalDate returnDate
    ) {
        // 🔑 Zwracamy te same obiekty lotów z list
        List<Flight> outbound = searchFlights(from, to, departureDate, departureDate);
        List<Flight> inbound  = searchFlights(to, from, returnDate, returnDate);

        outbound.sort(Comparator.comparing(Flight::getTime));
        inbound.sort(Comparator.comparing(Flight::getTime));

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

            Flight f = new Flight(from, to, date, time, price);

            // ustawienie statusu z mapy (jeśli już anulowany)
            FlightStatus status = flightStatuses.getOrDefault(FlightKey.fromFlight(f), FlightStatus.AVAILABLE);
            if (status == FlightStatus.CANCELLED) f.cancel();

            flights.add(f);
        }

        return flights;
    }

    private int deterministicPrice(Airport from, Airport to, LocalDate date, LocalTime time) {
        long seed = Objects.hash(from.name(), to.name(), date, time.getHour(), time.getMinute(), "PRICE");
        Random r = new Random(seed);
        return 70 + r.nextInt(531);
    }

    public List<Flight> getAllFlights() {
        List<Flight> all = new ArrayList<>();
        all.addAll(generatedFlights);
        all.addAll(manualFlights);
        return all;
    }

    // --- ANULOWANIE LOTÓW ---
    public void cancelFlight(Flight f) {
        f.cancel(); // oznaczamy sam obiekt
        flightStatuses.put(FlightKey.fromFlight(f), FlightStatus.CANCELLED);
    }

    public void restoreFlight(Flight f) {
        f.restore();
        flightStatuses.remove(FlightKey.fromFlight(f));
    }

    public FlightStatus getStatus(Flight f) {
        return flightStatuses.getOrDefault(FlightKey.fromFlight(f), FlightStatus.AVAILABLE);
    }

    public void addFlight(Flight f) {
        manualFlights.add(f);
    }
}
