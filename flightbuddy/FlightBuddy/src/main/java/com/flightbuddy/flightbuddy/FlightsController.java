package com.flightbuddy.flightbuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FlightsController {

    @FXML
    private Label titleLabel;

    public void setAirport(Airport airport) {
        titleLabel.setText("Dzisiejsze loty – " + airport.getDisplayName());
    }
}
