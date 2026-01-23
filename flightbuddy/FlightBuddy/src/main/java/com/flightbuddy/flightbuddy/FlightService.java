package com.flightbuddy.flightbuddy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
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
        LocalDate end = today.plusDays(30); // generujemy 30 dni lotów

        for (LocalDate date = today; !date.isAfter(end); date = date.plusDays(1)) {
            for (Route route : routes) {
                if (route.operatesOn(date.getDayOfWeek())) {
                    generatedFlights.addAll(generateFlightsForDay(route, route.getA(), route.getB(), date));
                }
            }
        }
    }


    // --- GŁÓWNA METODA ---
// --- LOTY NA OKREŚLONY PRZEDZIAŁ DAT ---
    public List<Flight> searchFlights(
            Airport from,
            Airport to,
            LocalDate fromDate,
            LocalDate toDate
    ) {
        List<Flight> result = new ArrayList<>();

        // 1️⃣ Dodaj ręcznie wprowadzone loty, które pasują do kryteriów
        for (Flight f : manualFlights) {
            if (f.getFrom() == from && f.getTo() == to &&
                    !f.getDate().isBefore(fromDate) && !f.getDate().isAfter(toDate)) {
                result.add(f);
            }
        }

        // 2️⃣ Generowanie seedowych lotów tylko jeśli nie mamy już ręcznych dla tej trasy i dnia
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            final LocalDate currentDate = date;  // <-- final
            DayOfWeek day = date.getDayOfWeek();

            for (Route route : routes) {
                if (!route.connects(from, to)) continue;
                if (!route.operatesOn(day)) continue;

                final Airport fromAirport = from;  // <-- final
                final Airport toAirport = to;      // <-- final

                boolean hasManualFlight = manualFlights.stream().anyMatch(f ->
                        f.getFrom() == fromAirport &&
                                f.getTo() == toAirport &&
                                f.getDate().equals(currentDate)
                );

                if (!hasManualFlight) {
                    result.addAll(generateFlightsForDay(route, from, to, date));
                }
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

    public List<Flight> getAllFlights() {
        List<Flight> all = new ArrayList<>();
        all.addAll(generatedFlights);
        all.addAll(manualFlights);
        return all;
    }

    /*
    public void initFlights() {
        // opcjonalnie: wypełnienie listy początkowymi lotami
        for (Route r : routes) {
            generatedFlights.addAll(generateFlightsForDay(r, r.getA(), r.getB(), LocalDate.now()));
        }
    }
    */

    // Zwraca status lotu (AVAILABLE / CANCELLED)
    public FlightStatus getStatus(Flight f) {
        return flightStatuses.getOrDefault(FlightKey.fromFlight(f), FlightStatus.AVAILABLE);}

    // Dodaje lot
    public void addFlight(Flight f) {manualFlights.add(f);}

    // Anuluje lot
    public void cancelFlight(Flight f) {
        flightStatuses.put(FlightKey.fromFlight(f), FlightStatus.CANCELLED);}

    // Przywraca anulowany lot
    public void restoreFlight(Flight f) {
        flightStatuses.remove(FlightKey.fromFlight(f));}

}
