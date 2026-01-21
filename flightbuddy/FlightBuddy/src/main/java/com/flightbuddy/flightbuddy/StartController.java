package com.flightbuddy.flightbuddy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.flightbuddy.flightbuddy.Flight;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;



public class StartController {

    private Flight selectedOutbound = null;
    private Flight selectedInbound = null;

    private VBox selectedOutboundBox = null;
    private VBox selectedInboundBox = null;

    @FXML private Button bookButton; // dodamy w FXML
    @FXML private Button showBookingsButton;

    // ===================== GLOBAL =====================
    private FlightService flightService;

    // ===================== UI =====================
    @FXML private Button showLoginButton;
    @FXML private Button showRegisterButton;
    @FXML private Button showMapButton;
    @FXML private Label loggedInLabel;

    @FXML private Button showAdminToolsButton; // Narzędzia administratora

    @FXML private VBox loginPane;
    @FXML private VBox registerPane;

    @FXML private Button closeLoginButton;
    @FXML private Button closeRegisterButton;

    @FXML private TextField loginEmailField;
    @FXML private PasswordField loginPasswordField;
    @FXML private Button loginButton;
    @FXML private Label loginMessageLabel;

    @FXML private TextField regFirstNameField;
    @FXML private TextField regLastNameField;
    @FXML private TextField regEmailField;
    @FXML private TextField regPhoneField;
    @FXML private PasswordField regPasswordField;
    @FXML private Button registerButton;
    @FXML private Label registerMessageLabel;

    @FXML private TextField fromField;
    @FXML private TextField toField;
    @FXML private DatePicker fromDatePicker;
    @FXML private DatePicker toDatePicker;
    @FXML private Button searchButton;
    @FXML private GridPane searchResultsPane;

    @FXML private ListView<String> fromSuggestions;
    @FXML private ListView<String> toSuggestions;

    // ===================== DANE =====================
    private final UserService userService = new UserService();
    private User loggedInUser;

    private final List<String> cities = List.of(
            "Warszawa (WAW)",
            "Gdańsk (GDN)",
            "Kraków (KRK)",
            "Katowice (KTW)",
            "Frankfurt (FRA)",
            "Berlin (BER)",
            "Hamburg (HAM)",
            "Monachium (MUC)",
            "Kopenhaga (CPH)",
            "Wilno (VNO)",
            "Moskwa (SMO)",
            "Petersburg (LED)",
            "Władywostok (VVO)",
            "Mińsk (MSQ)",
            "Oulu (OUL)",
            "Helsinki (HEL)",
            "Malmo (MMX)",
            "Sztokholm (ARN)",
            "Praga (PRG)",
            "Bratysława (BTS)",
            "Innsbruck (INN)",
            "Wiedeń (VIE)",
            "Zurych (ZRH)",
            "Amsterdam (AMS)",
            "Bruksela (BRU)",
            "Luksemburg (LUX)",
            "Budapeszt (BUD)",
            "Lublana (LJU)",
            "Kiszyniów (RMO)",
            "Tallin (TTL)",
            "Ryga (RIX)",
            "Oslo (OSL)",
            "Bergen (BGO)",
            "Zagrzeb (ZAG)",
            "Split (SPU)",
            "Sarajewo (SJJ)",
            "Podgorica (TGD)",
            "Prisztina (PRN)",
            "Belgrad (BEG)",
            "Tirana (TIA)",
            "Skopje (SKP)",
            "Bukareszt (OTP",
            "Jassy (IAS)",
            "Sybin (SBZ)",
            "Ateny (ATH)",
            "Chania (CHQ)",
            "Saloniki (SKG)",
            "Burgas (BOJ)",
            "Sofia (SOF)",
            "Stambuł (IST)",
            "Antalya (AYT)",
            "Ankara (ESB)",
            "Izmir (ADB)",
            "Madryt (MAD)",
            "Malaga (AGP)",
            "Barcelona (BCN)",
            "Palma de Mallorca (PMI)",
            "Lizbona (LIS)",
            "Porto (OPO)",
            "Dublin (DUB)",
            "Mediolan (MXP)",
            "Rzym (FCO)",
            "Wenecja (VCE)",
            "Bolonia (BLQ)",
            "Paryż (CDG)",
            "Marsylia (MRS)",
            "Nicea (NCE)",
            "Londyn (LHR)",
            "Manchester (MAN)",
            "Edynburg (EDI)",
            "Reykjavik (RKV)"
    );

    // ===================== INIT =====================
    @FXML
    private void initialize() {
        bookButton.setOnAction(e -> handleBooking());
        showBookingsButton.setOnAction(e -> openBookingsWindow());
        showBookingsButton.setVisible(false);

        // 🔑 TWORZYMY ŚWIAT LOTÓW – RAZ
        RouteGenerator generator = new RouteGenerator();
        flightService = new FlightService(
                generator.generateRoutes(List.of(Airport.values()))
        );

        // autocomplete
        new AutoCompleteSupport(fromField, fromSuggestions, cities);
        new AutoCompleteSupport(toField, toSuggestions, cities);

        // ---------------- LOGIN / REGISTER ----------------
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

        // ---------------- LOGIN / REGISTER HANDLERS ----------------
        loginButton.setOnAction(e -> {
            handleLogin();

            // po zalogowaniu pokaż przycisk admina, jeśli to admin
            if (loggedInUser != null && loggedInUser.getRole() == User.Role.ADMIN) {
                showAdminToolsButton.setVisible(true);
            }

            showBookingsButton.setVisible(true);
        });

        registerButton.setOnAction(e -> handleRegister());
        searchButton.setOnAction(e -> handleSearch());
        showMapButton.setOnAction(e -> openMapWindow());
        showAdminToolsButton.setVisible(false);
        showAdminToolsButton.setOnAction(e -> openAdminToolsWindow());
    }


    // ===================== MAPA =====================
    private void openMapWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/hello-view.fxml")
            );
            Scene scene = new Scene(loader.load());

            // 🔑 TO JEST TWÓJ "MAP CONTROLLER"
            HelloController helloController = loader.getController();

            // 🔥 NAJWAŻNIEJSZA LINIA W PROJEKCIE
            helloController.setFlightService(flightService);

            Stage stage = new Stage();
            stage.setTitle("Mapa Europy");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    // ===================== LOGOWANIE =====================
    @FXML
    private void handleLogin() {

        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();

        User user = userService.login(email, password);

        if (user == null) {
            showAlert("Błąd", "Niepoprawny email lub hasło");
            return;
        }

        loggedInUser = user;
        loggedInLabel.setText("Zalogowano jako:\n " + user.getFirstName() + " " + user.getLastName());

        showLoginButton.setText("Wyloguj");
        showLoginButton.setOnAction(e -> handleLogout());
        showRegisterButton.setDisable(true);
        showBookingsButton.setVisible(true);

        resetLoginFields();
        goToStartPage();

        loginEmailField.clear();
        loginPasswordField.clear();
        loginPane.setVisible(false);

        if (user.getRole() == User.Role.ADMIN) {
            showAlert("Admin", "Zalogowano jako administrator");
        }
    }

    private void handleLogout() {
        loggedInUser = null;
        loggedInLabel.setText("Nie zalogowano");

        // Przywrócenie przycisków logowania/rejestracji
        showLoginButton.setDisable(false);
        showLoginButton.setText("Zaloguj");
        showLoginButton.setOnAction(e -> loginPane.setVisible(true));
        showRegisterButton.setDisable(false);

        // Wyczyszczenie pól logowania/rejestracji
        loginEmailField.clear();
        loginPasswordField.clear();
        loginMessageLabel.setText("");

        regFirstNameField.clear();
        regLastNameField.clear();
        regEmailField.clear();
        regPhoneField.clear();
        regPasswordField.clear();
        registerMessageLabel.setText("");

        // Wyczyszczenie wyszukiwarki
        fromField.clear();
        toField.clear();
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);

        // Ukrycie wyników wyszukiwania
        searchResultsPane.getChildren().clear();
        bookButton.setDisable(true);

        // Ukrycie zarezerwowanych lotów
        showBookingsButton.setVisible(false);
        // showBookingsButton.setOnAction(e -> openBookingsWindow());

        // Ukrycie narzędzi administratora
        showAdminToolsButton.setVisible(false);
    }




    @FXML
    private void handleRegister() {

        String firstName = regFirstNameField.getText();
        String lastName = regLastNameField.getText();
        String email = regEmailField.getText();
        String phone = regPhoneField.getText();
        String password = regPasswordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty()
                || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            showAlert("Błąd", "Wszystkie pola muszą być wypełnione");
            return;
        }

        if (!phone.matches("\\d{9}")) {
            showAlert("Błąd", "Numer telefonu musi mieć dokładnie 9 cyfr");
            return;
        }

        if (password.length() < 4) {
            showAlert("Błąd", "Hasło musi mieć co najmniej 4 znaki");
            return;
        }


        User user = new User(
                firstName,
                lastName,
                email,
                phone,
                password,
                User.Role.USER
        );

        if (!userService.register(user)) {
            showAlert("Błąd", "Konto z takim emailem lub numerem telefonu już istnieje");
            return;
        }

        showAlert("Sukces", "Konto zostało utworzone");

        resetRegisterFields();
        goToStartPage();
    }

    private void resetLoginFields() {
        loginEmailField.clear();
        loginPasswordField.clear();
        loginMessageLabel.setText("");
    }

    private void resetRegisterFields() {
        regFirstNameField.clear();
        regLastNameField.clear();
        regEmailField.clear();
        regPhoneField.clear();
        regPasswordField.clear();
        registerMessageLabel.setText("");
    }

    private void goToStartPage() {
        loginPane.setVisible(false);
        registerPane.setVisible(false);
    }




    // ===================== SEARCH =====================
    private void handleSearch() {

        // 1️⃣ musi być zalogowany
        if (loggedInUser == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Brak dostępu",
                    "Musisz być zalogowany, aby wyszukiwać loty!");
            return;
        }

        // 2️⃣ pobranie danych z formularza
        String fromText = fromField.getText().trim();
        String toText = toField.getText().trim();
        LocalDate departureDate = fromDatePicker.getValue();
        LocalDate returnDate = toDatePicker.getValue();

        // 3️⃣ walidacja
        if (fromText.isEmpty() || toText.isEmpty()
                || departureDate == null || returnDate == null) {
            showAlert(Alert.AlertType.ERROR,
                    "Błąd",
                    "Wypełnij wszystkie pola wyszukiwania!");
            return;
        }

        if (fromText.equals(toText)) {
            showAlert(Alert.AlertType.ERROR,
                    "Błąd",
                    "Lotnisko wylotu i przylotu nie mogą być takie same!");
            return;
        }

        if (departureDate.isAfter(returnDate)) {
            showAlert(Alert.AlertType.ERROR,
                    "Błąd",
                    "Data wylotu nie może być późniejsza niż data powrotu!");
            return;
        }

        // 4️⃣ zamiana tekstu na Airport
        Airport fromAirport = Airport.fromDisplayName(fromText);
        Airport toAirport = Airport.fromDisplayName(toText);

        if (fromAirport == null || toAirport == null) {
            showAlert(Alert.AlertType.ERROR,
                    "Błąd",
                    "Wybierz lotnisko z listy podpowiedzi (np. Warszawa (WAW))");
            return;
        }

        // 5️⃣ SZUKAMY KONKRETNYCH DAT (ROUND TRIP)
        RoundTripResult result =
                flightService.searchRoundTripOnExactDates(
                        fromAirport,
                        toAirport,
                        departureDate,
                        returnDate
                );

        // 6️⃣ wyświetlenie wyników
        showRoundTripResult(result);
    }


    private void showRoundTripResult(RoundTripResult result) {

        searchResultsPane.getChildren().clear();


        selectedOutbound = null;
        selectedInbound = null;
        selectedOutboundBox = null;
        selectedInboundBox = null;
        bookButton.setDisable(true);

        VBox outboundBox = createFlightsSection("✈ Wylot – " + result.getDepartureDate(), result.getOutboundFlights(), true);
        VBox inboundBox  = createFlightsSection("↩ Powrót – " + result.getReturnDate(), result.getInboundFlights(), false);


        searchResultsPane.add(outboundBox, 0, 0);
        searchResultsPane.add(inboundBox, 1, 0);
    }


    private VBox createFlightsSection(
            String title,
            List<Flight> flights,
            boolean outbound
    ) {
        VBox section = new VBox(8);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        section.getChildren().add(titleLabel);

        if (flights.isEmpty()) {
            Label empty = new Label("Brak lotów w tym dniu");
            empty.setStyle("-fx-text-fill: gray;");
            section.getChildren().add(empty);
            return section;
        }

        for (Flight f : flights) {
            VBox box = outbound
                    ? createOutboundFlightBox(f)
                    : createInboundFlightBox(f);

            section.getChildren().add(box);
        }

        return section;
    }


    private VBox createFlightBox(Flight flight) {

        Label priceLabel = new Label("Cena: " + flight.getPrice() + " zł");
        priceLabel.setStyle("-fx-font-weight: bold;");

        Label timeLabel = new Label(flight.getTime().toString());
        timeLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        Label routeLabel = new Label(
                flight.getFrom().getDisplayName()
                        + " → " +
                        flight.getTo().getDisplayName()
        );

        Label dateLabel = new Label(flight.getDate().toString());
        dateLabel.setStyle("-fx-text-fill: gray;");

        VBox box = new VBox(4, timeLabel, routeLabel, dateLabel, priceLabel);
        box.setStyle("""
        -fx-padding: 10;
        -fx-border-color: lightgray;
        -fx-background-color: #f9f9f9;
        -fx-border-radius: 6;
        -fx-background-radius: 6;
    """);

        return box;
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void applyNormalStyle(VBox box) {
        box.setStyle("""
        -fx-padding: 10;
        -fx-border-color: lightgray;
        -fx-background-color: #f9f9f9;
        -fx-border-radius: 6;
        -fx-background-radius: 6;
    """);
    }

    private void applySelectedStyle(VBox box) {
        box.setStyle("""
        -fx-padding: 10;
        -fx-border-color: #2a7cff;
        -fx-background-color: #eaf2ff;
        -fx-border-radius: 6;
        -fx-background-radius: 6;
    """);
    }

    private VBox createOutboundFlightBox(Flight flight) {
        VBox box = createFlightBox(flight);
        box.setOnMouseClicked(e -> selectOutbound(flight, box));
        return box;
    }

    private VBox createInboundFlightBox(Flight flight) {
        VBox box = createFlightBox(flight);
        box.setOnMouseClicked(e -> selectInbound(flight, box));
        return box;
    }

    private void selectOutbound(Flight flight, VBox box) {

        if (selectedOutboundBox != null) applyNormalStyle(selectedOutboundBox);

        selectedOutbound = flight;
        selectedOutboundBox = box;
        applySelectedStyle(box);

        updateBookButtonState();
    }

    private void selectInbound(Flight flight, VBox box) {

        if (selectedInboundBox != null) applyNormalStyle(selectedInboundBox);

        selectedInbound = flight;
        selectedInboundBox = box;
        applySelectedStyle(box);

        updateBookButtonState();
    }

    private void updateBookButtonState() {
        bookButton.setDisable(selectedOutbound == null || selectedInbound == null);
    }


    private void handleBooking() {

        if (selectedOutbound == null || selectedInbound == null) return;

        int total = selectedOutbound.getPrice() + selectedInbound.getPrice();

        // Sprawdzenie, czy taki sam lot już istnieje
        /* boolean alreadyBooked = loggedInUser.getBookings().stream()
                .anyMatch(b -> b.getOutbound().equals(selectedOutbound)
                        && b.getInbound().equals(selectedInbound));

        if (alreadyBooked) {
            showAlert("Błąd", "Ten lot został już zarezerwowany!");
            return;
        } */

        // Tworzymy rezerwację
        Booking booking = new Booking(selectedOutbound, selectedInbound);
        loggedInUser.addBooking(booking);

        String summary = """
        WYBRANE LOTY:

        Wylot:
        %s %s → %s
        Data: %s
        Cena: %d zł

        Powrót:
        %s %s → %s
        Data: %s
        Cena: %d zł

        -------------------------
        Razem: %d zł
        """.formatted(
                selectedOutbound.getTime(),
                selectedOutbound.getFrom().getDisplayName(),
                selectedOutbound.getTo().getDisplayName(),
                selectedOutbound.getDate(),
                selectedOutbound.getPrice(),

                selectedInbound.getTime(),
                selectedInbound.getFrom().getDisplayName(),
                selectedInbound.getTo().getDisplayName(),
                selectedInbound.getDate(),
                selectedInbound.getPrice(),
                total
        );

        showAlert(Alert.AlertType.INFORMATION, "Rezerwacja", summary);

    }


    // Pop-upy
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Zarezerwowane loty
    private void openBookingsWindow() {
        if (loggedInUser == null) return;

        Stage stage = new Stage();
        stage.setTitle("Twoje loty");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        if (loggedInUser.getBookings().isEmpty()) {
            root.getChildren().add(new Label("Brak rezerwacji"));
        } else {
            for (Booking b : loggedInUser.getBookings()) {
                VBox flightBox = new VBox(5);

                Label outboundLabel = new Label(
                        "Wylot: " + b.getOutbound().getDate() + " "
                                + b.getOutbound().getTime() + " "
                                + b.getOutbound().getFrom().getDisplayName()
                                + " → " + b.getOutbound().getTo().getDisplayName()
                );

                Label inboundLabel = new Label(
                        "Powrót: " + b.getInbound().getDate() + " "
                                + b.getInbound().getTime() + " "
                                + b.getInbound().getFrom().getDisplayName()
                                + " → " + b.getInbound().getTo().getDisplayName()
                );

                flightBox.getChildren().addAll(outboundLabel, inboundLabel);
                flightBox.setStyle("""
                -fx-padding: 8;
                -fx-border-color: lightgray;
                -fx-background-color: #f9f9f9;
                -fx-border-radius: 6;
                -fx-background-radius: 6;
            """);

                root.getChildren().add(flightBox);
            }
        }

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }


    // Panel admina
    private void openAdminToolsWindow() {
        Stage stage = new Stage();
        stage.setTitle("Narzędzia Administratora");

        // Lewy panel – wybór kategorii
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(10));
        Button manageUsersButton = new Button("Zarządzanie użytkownikami");
        Button manageFlightsButton = new Button("Zarządzanie lotami");
        menu.getChildren().addAll(manageUsersButton, manageFlightsButton);

        // Prawy panel – zawartość
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        // Layout główny
        HBox root = new HBox(20, menu, content);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Akcje przycisków menu
        manageUsersButton.setOnAction(e -> showUserManagement(content));
        manageFlightsButton.setOnAction(e -> showFlightManagement(content));
    }

    // Panel admina - zarządzanie użytkownikami
    private void showUserManagement(VBox content) {
        content.getChildren().clear();
        content.getChildren().add(new Label("Lista użytkowników:"));

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            HBox userBox = new HBox(10);
            Label info = new Label(user.getFirstName() + " " + user.getLastName() +
                    " | " + user.getEmail() + " | " + user.getPhone());

            // Tworzymy przycisk Usuń tylko dla zwykłych użytkowników
            if (user.getRole() != User.Role.ADMIN) {
                Button deleteButton = new Button("Usuń");

                deleteButton.setOnAction(e -> {
                    boolean success = userService.removeUserByEmail(user.getEmail());
                    if (success) {
                        showAlert("Sukces", "Użytkownik usunięty");
                        showUserManagement(content); // odśwież listę
                    } else {
                        showAlert("Błąd", "Nie udało się usunąć użytkownika");
                    }
                });

                userBox.getChildren().addAll(info, deleteButton);
            } else {
                // jeśli admin, tylko label
                userBox.getChildren().add(info);
            }

            content.getChildren().add(userBox);
        }
    }


    // Panel admina - zarządzanie lotami (do późniejszej implementacji ?)
    private void showFlightManagement(VBox content) {
        content.getChildren().clear();
        content.getChildren().add(new Label("Zarządzanie lotami (w przygotowaniu)"));

        Button addFlightButton = new Button("Dodaj lot");
        Button editFlightButton = new Button("Edytuj lot");
        Button removeFlightButton = new Button("Usuń lot");

        // na razie nieaktywne
        addFlightButton.setDisable(true);
        editFlightButton.setDisable(true);
        removeFlightButton.setDisable(true);

        content.getChildren().addAll(addFlightButton, editFlightButton, removeFlightButton);
    }

}
