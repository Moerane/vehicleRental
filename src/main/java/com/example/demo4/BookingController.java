package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingController {

    @FXML
    AnchorPane anchorpane;

    @FXML
    ComboBox<String> comboboxSlect;

    @FXML
    ComboBox<String> comboboxVehicle;

    @FXML
    DatePicker dateEndDate;

    @FXML
    DatePicker dateRental;

    @FXML
    Label lblBookingSummary;

    @FXML
    Label lblBookingsystem;

    @FXML
    Label lblCustomer;

    @FXML
    Label lblEnd;

    @FXML
    Label lblRentalDuration;

    @FXML
    Label lblrental;

    @FXML
    Label lblvehicle;

    @FXML
    TextArea textarea;

    private String sourceDashboard; // Track where Booking screen was opened from

    String[] customers = {"Customer1", "Customer2", "Customer3"};
    String[] vehicles = {"Car", "Van", "Bike"};

    @FXML
    public void initialize() {
        comboboxSlect.getItems().addAll(customers);
        comboboxVehicle.getItems().addAll(vehicles);
    }

    public void setSourceDashboard(String sourceDashboard) {
        this.sourceDashboard = sourceDashboard;
    }

    @FXML
    void confirmBooking(ActionEvent event) {
        String customer = comboboxSlect.getValue();
        String vehicle = comboboxVehicle.getValue();
        LocalDate rentalDate = dateRental.getValue();
        LocalDate endDate = dateEndDate.getValue();

        if (customer != null && vehicle != null && rentalDate != null && endDate != null) {
            long rentalDuration = ChronoUnit.DAYS.between(rentalDate, endDate);

            String summary = "Customer: " + customer + "\n" +
                    "Vehicle: " + vehicle + "\n" +
                    "Rental Start Date: " + rentalDate + "\n" +
                    "Rental End Date: " + endDate + "\n" +
                    "Duration: " + rentalDuration + " days";

            textarea.setText(summary);
        }
    }

    @FXML
    void book(ActionEvent event) {
        if (!textarea.getText().isEmpty()) {
            showAlert("Booking saved successfully.");
        } else {
            showAlert("Please confirm the booking first.");
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        comboboxSlect.getSelectionModel().clearSelection();
        comboboxVehicle.getSelectionModel().clearSelection();
        dateRental.setValue(null);
        dateEndDate.setValue(null);
        textarea.clear();
    }

    @FXML
    void modify(ActionEvent event) {

    }

    @FXML
    void Exit(ActionEvent event) {
        try {
            String dashboardFXML = "AdminDashboard.fxml"; // default
            if ("employee".equals(sourceDashboard)) {
                dashboardFXML = "EmployeeDashboard.fxml";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(dashboardFXML));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking Info");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
