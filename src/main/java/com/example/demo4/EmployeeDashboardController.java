package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EmployeeDashboardController {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    void handleBooking(ActionEvent event) {
        loadPage("Booking.fxml");
    }

    @FXML
    void handlePayment(ActionEvent event) {
        loadPage("PaymentBilling.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        loadPage("Login.fxml");
    }

    private void loadPage(String fxmlFile) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile));
            anchorpane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
