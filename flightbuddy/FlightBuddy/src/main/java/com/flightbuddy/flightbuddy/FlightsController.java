package com.flightbuddy.flightbuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FlightsController {

    @FXML private Label titleLabel;
    @FXML private TextField searchField;
    @FXML private VBox flightsBox;

    private FlightService flightService;
    private Airport fromAirport;
    private List<Flight> allFlights = new ArrayList<>(); // Loty do filtrowania

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
        allFlights.clear();

        for (Airport to : Airport.values()) {
            if (to == fromAirport) continue;
            allFlights.addAll(flightService.searchFlights(fromAirport, to, today, today));
        }

        allFlights.sort(Comparator.comparing(Flight::getTime));

        // ustawiamy listener na TextField
        searchField.textProperty().addListener((obs, oldVal, newVal) -> filterFlights(newVal));

        // początkowe wyświetlenie wszystkich lotów
        filterFlights("");
    }

    private void filterFlights(String filter) {
        flightsBox.getChildren().clear();

        String search = filter.toLowerCase();

        List<Flight> filtered = allFlights.stream()
                .filter(f -> f.getTo().getDisplayName().toLowerCase().contains(search))
                .toList();

        if (filtered.isEmpty()) {
            flightsBox.getChildren().add(new Label("Brak lotów spełniających kryteria"));
            return;
        }

        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

        for (Flight f : filtered) {
            FlightStatus status = flightService.getStatus(f);

            // Informacje o locie i dopisek, jeśli anulowany
            String text = "✈ " + f.getTime().format(timeFmt)
                    + " → " + f.getTo().getDisplayName();
            if (status == FlightStatus.CANCELED) {text += " (ANULOWANY)";}
            Label flightLabel = new Label(text);

            if (status == FlightStatus.CANCELED) {
                flightLabel.setStyle("""
            -fx-padding: 8;
            -fx-border-color: red;
            -fx-background-color: #ffe5e5;
            -fx-text-fill: #a00000;
        """);
            } else {
                flightLabel.setStyle("""
            -fx-padding: 8;
            -fx-border-color: lightgray;
            -fx-background-color: #f9f9f9;
        """);
            }

            flightsBox.getChildren().add(flightLabel);
        }
    }
}
