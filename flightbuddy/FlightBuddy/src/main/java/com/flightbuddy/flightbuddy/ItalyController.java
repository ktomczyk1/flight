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

public class ItalyController {

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
                        getClass().getResourceAsStream("/Map_Italy.png")
                )
        );

        // === INFO ===
        Label title = new Label("Włochy");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label("""
Stolica: Rzym
""");
        description.setWrapText(true);

        Label airportsTitle = new Label("Lotniska:");
        airportsTitle.setStyle("-fx-font-weight: bold;");

        // === LOTNISKA (NA RAZIE BEZ AKCJI) ===
        Button mxp = new Button("Mediolan (MXP)");
        Button fco = new Button("Rzym (FCO)");
        Button vce = new Button("Wenecja (VCE)");
        Button blq = new Button("Bolonia (BLQ)");

        mxp.setOnAction(e -> showTestFlights("Mediolan", "MXP"));
        fco.setOnAction(e -> showTestFlights("Rzym", "FCO"));
        vce.setOnAction(e -> showTestFlights("Wenecja", "VCE"));
        blq.setOnAction(e -> showTestFlights("Bolonia", "BLQ"));



        mxp.setMaxWidth(Double.MAX_VALUE);
        fco.setMaxWidth(Double.MAX_VALUE);
        vce.setMaxWidth(Double.MAX_VALUE);
        blq.setMaxWidth(Double.MAX_VALUE);

        // === WYPEŁNIENIE PANELU ===
        airportsView = new VBox(10);
        airportsView.getChildren().addAll(
                title,
                description,
                airportsTitle,
                mxp,
                fco,
                vce,
                blq
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
