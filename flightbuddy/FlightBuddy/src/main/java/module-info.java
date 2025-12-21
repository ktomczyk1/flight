module com.flightbuddy.flightbuddy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.flightbuddy.flightbuddy to javafx.fxml;
    exports com.flightbuddy.flightbuddy;
}