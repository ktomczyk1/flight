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

    private FlightService flightService;

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

        // === LOTNISKA
        Button mxp = new Button("Mediolan (MXP)");
        Button fco = new Button("Rzym (FCO)");
        Button vce = new Button("Wenecja (VCE)");
        Button blq = new Button("Bolonia (BLQ)");

        mxp.setOnAction(e -> openFlightsView(Airport.MXP));
        fco.setOnAction(e -> openFlightsView(Airport.FCO));
        vce.setOnAction(e -> openFlightsView(Airport.VCE));
        blq.setOnAction(e -> openFlightsView(Airport.BLQ));



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
