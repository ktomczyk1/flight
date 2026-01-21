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

public class BulgariaController {

    @FXML
    private ImageView mapImage;

    @FXML
    private VBox infoPane;

    @FXML
    private VBox airportsView;

    private FlightService flightService;

    @FXML
    public void initialize() {

        // === MAPA BUŁGARII ===
        mapImage.setImage(
                new Image(getClass().getResourceAsStream("/Map_Bulgaria.png"))
        );

        // === INFO ===
        Label title = new Label("Bułgaria");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label("""
Stolica: Sofia
""");
        description.setWrapText(true);

        Label airportsTitle = new Label("Lotniska:");
        airportsTitle.setStyle("-fx-font-weight: bold;");

        // === LOTNISKA ===
        Button sof = new Button("Sofia (SOF)");
        Button boj = new Button("Burgas (BOJ)");

        sof.setMaxWidth(Double.MAX_VALUE);
        boj.setMaxWidth(Double.MAX_VALUE);

        //
        sof.setOnAction(e -> openFlightsView(Airport.SOF));
        boj.setOnAction(e -> openFlightsView(Airport.BOJ));

        airportsView = new VBox(10);
        airportsView.getChildren().addAll(
                title,
                description,
                airportsTitle,
                sof,
                boj
        );

        infoPane.getChildren().setAll(airportsView);
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

            // 🔑 przekazujemy SERWIS + lotnisko startowe
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
