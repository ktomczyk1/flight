package com.flightbuddy.flightbuddy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SpainController {

    @FXML
    private ImageView mapImage;

    @FXML
    private VBox infoPane;


    @FXML
    private VBox airportsView;

    @FXML
    public void initialize() {

        // === MAPA POLSKI ===
        mapImage.setImage(
                new Image(
                        getClass().getResourceAsStream("/Map_Spain.png")
                )
        );

        // === INFO ===
        Label title = new Label("Hiszpania");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label("""
Stolica: Madryt
""");
        description.setWrapText(true);

        Label airportsTitle = new Label("Lotniska:");
        airportsTitle.setStyle("-fx-font-weight: bold;");

        // === LOTNISKA (NA RAZIE BEZ AKCJI) ===
        Button mad = new Button("Madryt (MAD)");
        Button agp = new Button("Malaga (AGP)");
        Button bcn = new Button("Barcelona (BCN)");
        Button pmi = new Button("Palma de Mallorca (PMI)");

        mad.setOnAction(e -> showTestFlights("Madryt", "MAD"));
        agp.setOnAction(e -> showTestFlights("Malaga", "AGP"));
        bcn.setOnAction(e -> showTestFlights("Barcelona", "BCN"));
        pmi.setOnAction(e -> showTestFlights("Palma de Mallorca", "PMI"));



        mad.setMaxWidth(Double.MAX_VALUE);
        agp.setMaxWidth(Double.MAX_VALUE);
        bcn.setMaxWidth(Double.MAX_VALUE);
        pmi.setMaxWidth(Double.MAX_VALUE);

        // === WYPEŁNIENIE PANELU ===
        airportsView = new VBox(10);
        airportsView.getChildren().addAll(
                title,
                description,
                airportsTitle,
                mad,
                agp,
                bcn,
                pmi
        );

        infoPane.getChildren().clear();
        infoPane.getChildren().add(airportsView);

    }

    private void openFlightsView(Airport airport) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/flights-view.fxml")
            );

            Scene scene = new Scene(loader.load());
            FlightsController controller = loader.getController();
            controller.setAirport(airport);

            Stage stage = new Stage();
            stage.setTitle("Loty");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showTestFlights(String airportName, String code) {

        Button back = new Button("← Powrót do listy lotnisk");
        back.setOnAction(e -> {
            infoPane.getChildren().clear();
            infoPane.getChildren().add(airportsView);
        });

        Label title = new Label("Lotnisko: " + airportName + " (" + code + ")");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label flightsTitle = new Label("Dzisiejsze loty (TEST):");
        flightsTitle.setStyle("-fx-font-weight: bold;");

        Label flight = new Label("""
✈ %s → BER
Godzina: 14:30
Linia: FlightBuddy Airlines
Status: Planowany
""".formatted(code));
        flight.setStyle("""
        -fx-border-color: lightgray;
        -fx-padding: 10;
        -fx-background-color: #f9f9f9;
    """);

        infoPane.getChildren().clear();
        infoPane.getChildren().addAll(
                back,
                title,
                flightsTitle,
                flight
        );
    }




}
