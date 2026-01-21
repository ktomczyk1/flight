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

public class RomaniaController {

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
                        getClass().getResourceAsStream("/Map_Romania.png")
                )
        );

        // === INFO ===
        Label title = new Label("Rumunia");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label("""
Stolica: Bukareszt
""");
        description.setWrapText(true);

        Label airportsTitle = new Label("Lotniska:");
        airportsTitle.setStyle("-fx-font-weight: bold;");

        // === LOTNISKA (NA RAZIE BEZ AKCJI) ===
        Button otp = new Button("Bukareszt (OTP)");
        Button sbz = new Button("Sybin (SBZ)");
        Button ias = new Button("Jassy (IAS)");

        otp.setOnAction(e -> openFlightsView(Airport.OTP));
        sbz.setOnAction(e -> openFlightsView(Airport.SBZ));
        ias.setOnAction(e -> openFlightsView(Airport.IAS));



        otp.setMaxWidth(Double.MAX_VALUE);
        sbz.setMaxWidth(Double.MAX_VALUE);
        ias.setMaxWidth(Double.MAX_VALUE);

        // === WYPEŁNIENIE PANELU ===
        airportsView = new VBox(10);
        airportsView.getChildren().addAll(
                title,
                description,
                airportsTitle,
                otp,
                sbz,
                ias
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
