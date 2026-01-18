package com.flightbuddy.flightbuddy;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StartController {

    // --- GÓRA: przyciski logowania/rejestracji , zalogowany użytkownik ---
    @FXML private Button showLoginButton;
    @FXML private Button showRegisterButton;
    @FXML private Label loggedInLabel;

    // --- FORMULARZE ---
    @FXML private VBox loginPane;
    @FXML private VBox registerPane;

    @FXML private Button showMapButton;

    @FXML private Button closeLoginButton;
    @FXML private Button closeRegisterButton;

    // --- LOGOWANIE ---
    @FXML private TextField loginEmailField;
    @FXML private PasswordField loginPasswordField;
    @FXML private Button loginButton;
    @FXML private Label loginMessageLabel;

    // --- REJESTRACJA ---
    @FXML private TextField regFirstNameField;
    @FXML private TextField regLastNameField;
    @FXML private TextField regEmailField;
    @FXML private TextField regPhoneField;
    @FXML private PasswordField regPasswordField;
    @FXML private Button registerButton;
    @FXML private Label registerMessageLabel;

    // --- WYSZUKIWARKA LOTÓW ---
    @FXML private TextField fromField;
    @FXML private TextField toField;
    @FXML private DatePicker fromDatePicker;
    @FXML private DatePicker toDatePicker;
    @FXML private Button searchButton;
    @FXML private GridPane searchResultsPane;

    // Lista użytkowników w pamięci
    private final List<User> users = new ArrayList<>();
    private User loggedInUser = null; // przechowuje zalogowanego użytkownika

    @FXML
    private void initialize() {
        // --- POKAZ/UKRYJ FORMULARZE ---
        showLoginButton.setOnAction(e -> {
            loginPane.setVisible(true);
            registerPane.setVisible(false);
        });

        showRegisterButton.setOnAction(e -> {
            registerPane.setVisible(true);
            loginPane.setVisible(false);
        });

        closeLoginButton.setOnAction(e -> loginPane.setVisible(false));
        closeRegisterButton.setOnAction(e -> registerPane.setVisible(false));

        // --- LOGOWANIE ---
        loginButton.setOnAction(e -> handleLogin());

        // --- REJESTRACJA ---
        registerButton.setOnAction(e -> handleRegister());

        // --- WYSZUKIWARKA LOTÓW ---
        searchButton.setOnAction(e -> {handleSearch();});

        // --- POKAZANIE MAPY ---
        showMapButton.setOnAction(e -> openMapWindow());
    }

    // Otwarcie mapy
    private void openMapWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 600);
            Stage stage = new Stage();
            stage.setTitle("FlightBuddy – Mapa");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // --- LOGOWANIE ---
    private void handleLogin() {
        String email = loginEmailField.getText().trim();
        String password = loginPasswordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            loginMessageLabel.setText("Wypełnij wszystkie pola!");
            return;
        }

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loggedInUser = user;

                // Pokazujemy imię i nazwisko w górnym pasku
                loggedInLabel.setText("Zalogowano jako: " + user.getFirstName() + " " + user.getLastName());

                // Czyścimy formularz logowania
                loginEmailField.clear();
                loginPasswordField.clear();
                loginMessageLabel.setText("");

                // Zamykamy okno logowania, oraz przycisk Zaloguj -> Wyloguj
                loginPane.setVisible(false);
                showLoginButton.setText("Wyloguj");
                showLoginButton.setOnAction(e -> handleLogout());
                return;
            }
        }

        loginMessageLabel.setStyle("-fx-text-fill: red;");
        loginMessageLabel.setText("Nieprawidłowy email lub hasło!");
    }



    // --- REJESTRACJA ---
    private void handleRegister() {
        String firstName = regFirstNameField.getText().trim();
        String lastName = regLastNameField.getText().trim();
        String email = regEmailField.getText().trim();
        String phone = regPhoneField.getText().trim();
        String password = regPasswordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                phone.isEmpty() || password.isEmpty()) {
            registerMessageLabel.setText("Wypełnij wszystkie pola!");
            registerMessageLabel.setTextFill(Color.RED);
            return;
        }

        if (!Pattern.matches("\\d{9}", phone)) {
            registerMessageLabel.setText("Telefon musi mieć 9 cyfr!");
            registerMessageLabel.setTextFill(Color.RED);
            return;
        }

        if (password.length() < 4) {
            registerMessageLabel.setText("Hasło musi mieć co najmniej 4 znaki!");
            registerMessageLabel.setTextFill(Color.RED);
            return;
        }

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                registerMessageLabel.setText("Użytkownik z tym emailem już istnieje!");
                registerMessageLabel.setTextFill(Color.RED);
                return;
            }
        }

        users.add(new User(firstName, lastName, email, phone, password));
        registerPane.setVisible(false);
        showAlert(Alert.AlertType.INFORMATION, "Rejestracja", "Konto utworzone poprawnie!");
        clearRegistrationFields();
        registerMessageLabel.setTextFill(Color.GREEN);
        clearRegistrationFields();
    }

    private void clearRegistrationFields() {
        regFirstNameField.clear();
        regLastNameField.clear();
        regEmailField.clear();
        regPhoneField.clear();
        regPasswordField.clear();
    }

    // --- WYSZUKIWARKA LOTÓW ---
    private void handleSearch() {
        // Sprawdzenie czy użytkownik jest zalogowany
        if (loggedInUser == null) {
            showAlert(AlertType.WARNING, "Brak dostępu", "Musisz być zalogowany, aby wyszukiwać loty!");
            return;
        }

        String from = fromField.getText().trim();
        String to = toField.getText().trim();
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (from.equals(to)) {
            showAlert(AlertType.ERROR, "Błąd", "Pole 'Dokąd' nie może być takie samo jak pole 'Skąd'!");
            return;
        }

        if (from.isEmpty() || to.isEmpty() || fromDate == null || toDate == null) {
            showAlert(AlertType.ERROR, "Błąd", "Wypełnij wszystkie pola wyszukiwania!");
            return;
        }

        if (fromDate.isAfter(toDate)) {
            showAlert(AlertType.ERROR, "Błąd", "Data 'Od' nie może być późniejsza niż 'Do'");
            return;
        }

        if (fromDate.isAfter(toDate)) {
            showAlert(AlertType.ERROR, "Błąd", "Data 'Od' nie może być późniejsza niż 'Do'");
            return;
        }

        showFlights(from, to, fromDate, toDate);
    }


    private void showFlights(String from, String to, LocalDate fromDate, LocalDate toDate) {
        searchResultsPane.getChildren().clear(); // czyścimy poprzednie wyniki

        // Przykładowe dane – dla każdej daty w przedziale generujemy przykładowe loty
        long days = ChronoUnit.DAYS.between(fromDate, toDate) + 1;
        int col = 0;
        for (int i = 0; i < days; i++) {
            LocalDate date = fromDate.plusDays(i);
            VBox flightBox = new VBox(5);
            flightBox.setStyle("-fx-border-color: black; -fx-padding: 10;");
            flightBox.getChildren().addAll(
                    new Label(from + " -> " + to),
                    new Label("Data: " + date.toString()),
                    new Label("Linia: FlightBuddy"),
                    new Label("Airlines Status: Planowany")
            );
            searchResultsPane.add(flightBox, col++, 0);
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    //
    private void handleLogout() {
        loggedInUser = null;
        loggedInLabel.setText("");

        // Zmiana przycisku Wyloguj -> Zaloguj
        showLoginButton.setText("Zaloguj");
        showLoginButton.setOnAction(e -> {
            loginPane.setVisible(true);
            registerPane.setVisible(false);
        });
    }

    // --- KLASA POMOCNICZA USER ---
    private static class User {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String phone;
        private final String password;

        public User(String firstName, String lastName, String email, String phone, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.password = password;
        }

        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
    }

}
