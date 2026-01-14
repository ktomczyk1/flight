module com.flightbuddy.flightbuddy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.flightbuddy.flightbuddy to javafx.fxml;
    exports com.flightbuddy.flightbuddy;
}
