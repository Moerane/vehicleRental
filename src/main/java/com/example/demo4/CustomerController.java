package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {

    @FXML
    private AnchorPane Anchorpane;

    @FXML
    private Label lblContact, lblCustomerManagement, lblLicence, lblName, lblRental;

    @FXML
    private TableView<Customer> tableview;

    @FXML
    private TableColumn<Customer, String> tcName;

    @FXML
    private TableColumn<Customer, String> tcContact;

    @FXML
    private TableColumn<Customer, String> tcLicence;

    @FXML
    private TableColumn<Customer, String> tcRental;

    @FXML
    private TextField txtName, txtContact, txtLicence, txtRental;

    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Connect table columns to Customer fields
        tcName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        tcContact.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContact()));
        tcLicence.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLicence()));
        tcRental.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRentalInfo()));

        tableview.setItems(customerList);

        tableview.setOnMouseClicked(event -> {
            Customer selected = tableview.getSelectionModel().getSelectedItem();
            if (selected != null) {
                txtName.setText(selected.getName());
                txtContact.setText(selected.getContact());
                txtLicence.setText(selected.getLicence());
                txtRental.setText(selected.getRentalInfo());
            }
        });
    }

    @FXML
    void Register(ActionEvent event) {
        String name = txtName.getText();
        String contact = txtContact.getText();
        String licence = txtLicence.getText();
        String rental = txtRental.getText();

        if (name.isEmpty() || contact.isEmpty() || licence.isEmpty() || rental.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        Customer newCustomer = new Customer(name, contact, licence, rental);
        customerList.add(newCustomer);
        clearFields();
    }

    @FXML
    void Update(ActionEvent event) {
        Customer selected = tableview.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setName(txtName.getText());
            selected.setContact(txtContact.getText());
            selected.setLicence(txtLicence.getText());
            selected.setRentalInfo(txtRental.getText());
            tableview.refresh();
            clearFields();
        } else {
            showAlert("Please select a customer to update.");
        }
    }

    @FXML
    void Exit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) Anchorpane.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtName.clear();
        txtContact.clear();
        txtLicence.clear();
        txtRental.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
