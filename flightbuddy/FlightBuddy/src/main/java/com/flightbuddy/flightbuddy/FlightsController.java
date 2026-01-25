package com.flightbuddy.flightbuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FlightsController {

    @FXML private Label titleLabel;
    @FXML private TextField searchField;
    @FXML private VBox flightsBox;
    @FXML private DatePicker datePicker;
    @FXML private Button refreshButton;

    private FlightService flightService;
    private Airport fromAirport;
    private List<Flight> allFlights = new ArrayList<>(); // Loty do filtrowania
    private LocalDate selectedDate;

    // INIT
    @FXML public void initialize() {
        selectedDate = LocalDate.now();
        datePicker.setValue(selectedDate);

        datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                selectedDate = newDate;
                loadFlightsForSelectedDate();
            }
        });

        refreshButton.setOnAction(e -> loadFlightsForSelectedDate());

        searchField.textProperty().addListener(
                (obs, oldVal, newVal) -> filterFlights(newVal)
        );
    }


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
        loadFlightsForSelectedDate();
    }


    // Wyświetla loty dla wybranej daty na mapie
    private void loadFlightsForSelectedDate() {
        allFlights.clear();

        for (Airport to : Airport.values()) {
            if (to == fromAirport) continue;
            allFlights.addAll(
                    flightService.searchFlights(fromAirport, to, selectedDate, selectedDate)
            );
        }

        allFlights.sort(Comparator.comparing(Flight::getTime));

        filterFlights(searchField.getText());
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
            if (status == FlightStatus.CANCELLED) {text += " (ANULOWANY)";}
            Label flightLabel = new Label(text);

            if (status == FlightStatus.CANCELLED) {
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
