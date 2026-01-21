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

public class TurkeyController {

    @FXML
    private ImageView mapImage;

    @FXML
    private VBox infoPane;

    private FlightService flightService;

    @FXML
    private VBox airportsView;

    @FXML
    public void initialize() {

        // === MAPA POLSKI ===
        mapImage.setImage(
                new Image(
                        getClass().getResourceAsStream("/Map_Turkey.png")
                )
        );

        // === INFO ===
        Label title = new Label("Turcja");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label("""
Stolica: Ankara
""");
        description.setWrapText(true);

        Label airportsTitle = new Label("Lotniska:");
        airportsTitle.setStyle("-fx-font-weight: bold;");

        // === LOTNISKA (NA RAZIE BEZ AKCJI) ===
        Button ist = new Button("Stambuł (IST)");
        Button ayt = new Button("Antalya (AYT)");
        Button esb = new Button("Ankara (ESB)");
        Button adb = new Button("Izmir (ADB)");

        ist.setOnAction(e -> openFlightsView(Airport.IST));
        ayt.setOnAction(e -> openFlightsView(Airport.AYT));
        esb.setOnAction(e -> openFlightsView(Airport.ESB));
        adb.setOnAction(e -> openFlightsView(Airport.ADB));



        ist.setMaxWidth(Double.MAX_VALUE);
        ayt.setMaxWidth(Double.MAX_VALUE);
        esb.setMaxWidth(Double.MAX_VALUE);
        adb.setMaxWidth(Double.MAX_VALUE);

        // === WYPEŁNIENIE PANELU ===
        airportsView = new VBox(10);
        airportsView.getChildren().addAll(
                title,
                description,
                airportsTitle,
                ist,
                ayt,
                esb,
                adb
        );

        infoPane.getChildren().clear();
        infoPane.getChildren().add(airportsView);

    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }


    private void openFlightsView(Airport airport) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/flights-view.fxml")
            );

            Scene scene = new Scene(loader.load());
            FlightsController controller = loader.getController();


            controller.setFlightService(flightService);
            controller.setFromAirport(airport);

            Stage stage = new Stage();
            stage.setTitle("Loty – " + airport.getDisplayName());
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
