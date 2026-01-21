package com.flightbuddy.flightbuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FlightsController {

    @FXML
    private Label titleLabel;

    @FXML
    private VBox flightsBox;

    private FlightService flightService;
    private Airport fromAirport;

    // ===================== SETTERY =====================

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
        System.out.println("FLIGHTS flightService hash = " + System.identityHashCode(flightService));
        tryLoadFlights();
    }

    public void setFromAirport(Airport from) {
        this.fromAirport = from;
        titleLabel.setText("Dzisiejsze loty – " + from.getDisplayName());
        tryLoadFlights();
    }

    // ===================== KLUCZ =====================

    private void tryLoadFlights() {
        if (flightService == null || fromAirport == null) {
            return; // czekamy aż oba będą ustawione
        }
        loadTodayFlights();
    }

    // ===================== RYSOWANIE =====================

    private void loadTodayFlights() {

        LocalDate today = LocalDate.now();
        List<Flight> allFlights = new ArrayList<>();

        for (Airport to : Airport.values()) {
            if (to == fromAirport) continue;

            allFlights.addAll(
                    flightService.searchFlights(fromAirport, to, today, today)
            );
        }

        allFlights.sort(Comparator.comparing(Flight::getTime));

        flightsBox.getChildren().clear();

        if (allFlights.isEmpty()) {
            flightsBox.getChildren().add(
                    new Label("Brak lotów na dziś")
            );
            return;
        }

        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

        for (Flight f : allFlights) {
            Label flightLabel = new Label(
                    "✈ " + f.getTime().format(timeFmt)
                            + " → " + f.getTo().getDisplayName()
            );
            flightLabel.setStyle("""
                -fx-padding: 8;
                -fx-border-color: lightgray;
                -fx-background-color: #f9f9f9;
            """);

            flightsBox.getChildren().add(flightLabel);
        }
    }
}
