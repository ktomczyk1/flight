package com.flightbuddy.flightbuddy;


import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.List;

public class AutoCompleteSupport {

    private final TextField textField;
    private final ListView<String> listView;
    private final List<String> allItems;

    public AutoCompleteSupport(TextField textField,
                               ListView<String> listView,
                               List<String> allItems) {

        this.textField = textField;
        this.listView = listView;
        this.allItems = allItems;

        configure();
    }

    private void configure() {

        // Filtrowanie po wpisywanym tekście
        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText == null || newText.isBlank()) {
                hide();
                return;
            }

            List<String> matches = allItems.stream()
                    .filter(item -> item.toLowerCase().startsWith(newText.toLowerCase()))
                    .toList();

            if (matches.isEmpty()) {
                hide();
            } else {
                listView.setItems(FXCollections.observableArrayList(matches));
                show();
            }
        });

        // Strzałka w dół z TextField
        textField.setOnKeyPressed(e -> {
            if (!listView.isVisible()) return;

            if (e.getCode() == KeyCode.DOWN) {
                listView.getSelectionModel().selectFirst();
                listView.requestFocus();
            }
        });

        // Obsługa klawiatury w liście
        listView.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                acceptSelection();
            } else if (e.getCode() == KeyCode.ESCAPE) {
                hide();
                textField.requestFocus();
            }
        });

        // Klik myszką
        listView.setOnMouseClicked(e -> acceptSelection());

        // Podświetlenie dopasowania
        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    String input = textField.getText();
                    if (input != null && item.toLowerCase().startsWith(input.toLowerCase())) {
                        setStyle("-fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });
    }

    private void acceptSelection() {
        String selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            textField.setText(selected);
            hide();
            textField.requestFocus();
            textField.positionCaret(selected.length());
        }
    }

    private void show() {
        listView.setVisible(true);
        listView.setManaged(true);
    }

    private void hide() {
        listView.setVisible(false);
        listView.setManaged(false);
    }
}
