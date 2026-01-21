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

public class BritainController {

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
                        getClass().getResourceAsStream("/Map_Britain.png")
                )
        );

        // === INFO ===
        Label title = new Label("Wielka Brytania");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label("""
Stolica: Londyn
""");
        description.setWrapText(true);

        Label airportsTitle = new Label("Lotniska:");
        airportsTitle.setStyle("-fx-font-weight: bold;");

        // === LOTNISKA (NA RAZIE BEZ AKCJI) ===
        Button lhr = new Button("Londyn (LHR)");
        Button man = new Button("Manchester (MAN)");
        Button edi = new Button("Edynburg (EDI)");

        lhr.setOnAction(e -> openFlightsView(Airport.LHR));
        man.setOnAction(e -> openFlightsView(Airport.MAN));
        edi.setOnAction(e -> openFlightsView(Airport.EDI));



        lhr.setMaxWidth(Double.MAX_VALUE);
        man.setMaxWidth(Double.MAX_VALUE);
        edi.setMaxWidth(Double.MAX_VALUE);

        // === WYPEŁNIENIE PANELU ===
        airportsView = new VBox(10);
        airportsView.getChildren().addAll(
                title,
                description,
                airportsTitle,
                lhr,
                man,
                edi
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
