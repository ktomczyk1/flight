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

public class AlbaniaController {

    @FXML
    private ImageView mapImage;

    @FXML
    private VBox infoPane;

    @FXML
    private VBox airportsView;


    private FlightService flightService;


    @FXML
    public void initialize() {


        mapImage.setImage(
                new Image(getClass().getResourceAsStream("/Map_Albania.png"))
        );


        Label title = new Label("Albania");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label("""
Stolica: Tirana
""");
        description.setWrapText(true);

        Label airportsTitle = new Label("Lotniska:");
        airportsTitle.setStyle("-fx-font-weight: bold;");


        Button tia = new Button("Tirana (TIA)");
        tia.setMaxWidth(Double.MAX_VALUE);


        tia.setOnAction(e -> openFlightsView(Airport.TIA));


        airportsView = new VBox(10);
        airportsView.getChildren().addAll(
                title,
                description,
                airportsTitle,
                tia
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

            System.out.println("COUNTRY flightService hash = " + System.identityHashCode(flightService));

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
