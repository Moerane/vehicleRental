package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentBillingController {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private CheckBox chechboxlatefee;

    @FXML
    private CheckBox checkboxAddadditional;

    @FXML
    private ComboBox<String> comboboxPayment;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblPayment;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblRentalDays;

    @FXML
    private Label lblVehicle;

    @FXML
    private Label lblpaymentmethod;

    @FXML
    private Label lbltotal;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtCustomename;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtRentaldays;

    @FXML
    private TextField txtvihicle;

    @FXML
    public void initialize() {
        comboboxPayment.getItems().addAll("Cash", "Credit Card", "Bank Transfer");
    }

    @FXML
    void GenerateInvoice(ActionEvent event) {
        String customer = txtCustomename.getText();
        String vehicle = txtvihicle.getText();
        String priceStr = txtPrice.getText();
        String daysStr = txtRentaldays.getText();
        String paymentMethod = comboboxPayment.getValue();

        if (customer.isEmpty() || vehicle.isEmpty() || priceStr.isEmpty() || daysStr.isEmpty() || paymentMethod == null) {
            showAlert("Missing Info", "Please fill in all fields and select payment method.");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int days = Integer.parseInt(daysStr);
            double total = price * days;

            if (chechboxlatefee.isSelected()) {
                total += 50; // Late fee
            }

            if (checkboxAddadditional.isSelected()) {
                total += 30; // Additional charge
            }

            lblCustomerName.setText(customer);
            lblVehicle.setText(vehicle);
            lblPrice.setText(String.format("%.2f", price));
            lblRentalDays.setText(String.valueOf(days));
            lblPayment.setText(String.format("%.2f", total));
            lblpaymentmethod.setText(paymentMethod);
            lbltotal.setText(String.format("Total: R%.2f", total));

            StringBuilder invoice = new StringBuilder();
            invoice.append("==VEHICLE RENTAL INVOICE==\n");
            invoice.append("Customer: ").append(customer).append("\n");
            invoice.append("Vehicle: ").append(vehicle).append("\n");
            invoice.append("Price per day: R").append(price).append("\n");
            invoice.append("Rental days: ").append(days).append("\n");
            if (chechboxlatefee.isSelected()) {
                invoice.append("Late Fee: R50.00\n");
            }
            if (checkboxAddadditional.isSelected()) {
                invoice.append("Additional Charges: R30.00\n");
            }
            invoice.append("Payment Method: ").append(paymentMethod).append("\n");
            invoice.append("TOTAL AMOUNT: R").append(String.format("%.2f", total)).append("\n");
            invoice.append("===============");

            txtArea.setText(invoice.toString());

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for price and rental days.");
        }
    }

    @FXML
    void handleExit(ActionEvent event) {
        // Clear all fields and labels
        txtCustomename.clear();
        txtvihicle.clear();
        txtPrice.clear();
        txtRentaldays.clear();
        comboboxPayment.getSelectionModel().clearSelection();
        chechboxlatefee.setSelected(false);
        checkboxAddadditional.setSelected(false);
        txtArea.clear();

        lblCustomerName.setText("");
        lblVehicle.setText("");
        lblPrice.setText("");
        lblRentalDays.setText("");
        lblPayment.setText("");
        lblpaymentmethod.setText("");
        lbltotal.setText("");

        loadDashboard();
    }

    private void loadDashboard() {
        try {

            String dashboardFXML = "AdminDashboard.fxml"; // Replace with actual condition if you need to switch between Admin and Employee


            FXMLLoader loader = new FXMLLoader(getClass().getResource(dashboardFXML));
            AnchorPane dashboard = loader.load();

            Stage stage = (Stage) anchorpane.getScene().getWindow();
            Scene scene = new Scene(dashboard);
            stage.setScene(scene);
            stage.setTitle("Dashboard");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Error loading the dashboard.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
