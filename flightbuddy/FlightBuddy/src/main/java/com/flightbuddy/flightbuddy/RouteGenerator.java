package com.flightbuddy.flightbuddy;

import java.time.DayOfWeek;
import java.util.*;

public class RouteGenerator {

    private static final Random RANDOM = new Random(42); // STAŁY SEED
    private static final double CONNECTION_PROBABILITY = 1.0; // testowo 100%

    public List<Route> generateRoutes(List<Airport> airports) {

        List<Route> routes = new ArrayList<>();

        for (int i = 0; i < airports.size(); i++) {
            for (int j = i + 1; j < airports.size(); j++) {

                Airport a = airports.get(i);
                Airport b = airports.get(j);

                if (a == b) continue;

                if (!isConnectionAllowed(a, b)) continue;

                if (RANDOM.nextDouble() > CONNECTION_PROBABILITY) continue;

                Set<DayOfWeek> days = randomOperatingDays();
                int flightsPerDay = randomFlightsPerDay();

                routes.add(new Route(a, b, days, flightsPerDay));
            }
        }

        // DEBUG – możesz potem usunąć
        System.out.println("Generated routes: " + routes.size());

        return routes;
    }

    // ===================== REGUŁY POLITYCZNE =====================

    private boolean isConnectionAllowed(Airport a, Airport b) {
        boolean aRussia = "Rosja".equalsIgnoreCase(a.getCountry());
        boolean bRussia = "Rosja".equalsIgnoreCase(b.getCountry());

        // Rosja tylko wewnętrznie
        if (aRussia || bRussia) {
            return aRussia && bRussia;
        }
        return true;
    }

    // ===================== DNI OPEROWANIA =====================

    private Set<DayOfWeek> randomOperatingDays() {

        // KAŻDA trasa ma co najmniej 1 dzień
        int daysCount = 1 + RANDOM.nextInt(7);

        List<DayOfWeek> allDays =
                new ArrayList<>(List.of(DayOfWeek.values()));

        Collections.shuffle(allDays, RANDOM);

        return new HashSet<>(allDays.subList(0, daysCount));
    }

    // ===================== LICZBA LOTÓW =====================

    private int randomFlightsPerDay() {
        return weightedRandom(
                Map.of(
                        1, 50,
                        2, 35,
                        3, 15
                )
        );
    }

    // ===================== UTILITY =====================

    private int weightedRandom(Map<Integer, Integer> weights) {

        int total = weights.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int r = RANDOM.nextInt(total);

        int cumulative = 0;
        for (Map.Entry<Integer, Integer> entry : weights.entrySet()) {
            cumulative += entry.getValue();
            if (r < cumulative) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Weighted random failed");
    }
}
