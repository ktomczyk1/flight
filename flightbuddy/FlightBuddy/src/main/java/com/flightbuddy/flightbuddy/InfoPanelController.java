package com.flightbuddy.flightbuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class InfoPanelController {

    @FXML
    private VBox infoPane;

    @FXML
    private Label titleLabel;

    @FXML
    private TextArea descriptionArea;

    public void showCountry(CountryInfo info) {
        infoPane.setManaged(true);
        infoPane.setVisible(true);

        titleLabel.setText(info.name());
        descriptionArea.setText(info.description());
    }

    public void hide() {
        infoPane.setVisible(false);
        infoPane.setManaged(false);
    }
}
