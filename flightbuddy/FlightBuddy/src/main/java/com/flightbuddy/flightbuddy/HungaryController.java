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

public class HungaryController {

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
                        getClass().getResourceAsStream("/Map_Hungary.png")
                )
        );

        // === INFO ===
        Label title = new Label("Węgry");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label("""
Stolica: Budapeszt
""");
        description.setWrapText(true);

        Label airportsTitle = new Label("Lotniska:");
        airportsTitle.setStyle("-fx-font-weight: bold;");

        // === LOTNISKA (NA RAZIE BEZ AKCJI) ===
        Button bud = new Button("Budapeszt (BUD)");

        bud.setOnAction(e -> showTestFlights("Budapeszt", "BUD"));



        bud.setMaxWidth(Double.MAX_VALUE);

        // === WYPEŁNIENIE PANELU ===
        airportsView = new VBox(10);
        airportsView.getChildren().addAll(
                title,
                description,
                airportsTitle,
                bud
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
