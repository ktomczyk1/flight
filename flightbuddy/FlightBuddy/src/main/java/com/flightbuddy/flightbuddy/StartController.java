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


public class StartController {

    private Flight selectedOutbound = null;
    private Flight selectedInbound = null;

    private VBox selectedOutboundBox = null;
    private VBox selectedInboundBox = null;

    @FXML private Button bookButton; // dodamy w FXML


    // ===================== GLOBAL =====================
    private FlightService flightService;

    // ===================== UI =====================
    @FXML private Button showLoginButton;
    @FXML private Button showRegisterButton;
    @FXML private Button showMapButton;
    @FXML private Label loggedInLabel;

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
    private final List<User> users = new ArrayList<>();
    private User loggedInUser = null;

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

        // 🔑 TWORZYMY ŚWIAT LOTÓW – RAZ
        RouteGenerator generator = new RouteGenerator();
        flightService = new FlightService(
                generator.generateRoutes(List.of(Airport.values()))
        );

        // autocomplete
        new AutoCompleteSupport(fromField, fromSuggestions, cities);
        new AutoCompleteSupport(toField, toSuggestions, cities);

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

        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegister());
        searchButton.setOnAction(e -> handleSearch());

        showMapButton.setOnAction(e -> openMapWindow());
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
    private void handleLogin() {
        for (User u : users) {
            if (u.email.equals(loginEmailField.getText())
                    && u.password.equals(loginPasswordField.getText())) {
                loggedInUser = u;
                loggedInLabel.setText("Zalogowano: " + u.firstName);
                loginPane.setVisible(false);
                return;
            }
        }
        loginMessageLabel.setText("Błędne dane");
    }

    private void handleRegister() {
        users.add(new User(
                regFirstNameField.getText(),
                regLastNameField.getText(),
                regEmailField.getText(),
                regPhoneField.getText(),
                regPasswordField.getText()
        ));
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


    // ===================== USER =====================
    private static class User {
        String firstName, lastName, email, phone, password;
        User(String f, String l, String e, String p, String pw) {
            firstName = f; lastName = l; email = e; phone = p; password = pw;
        }
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





}
