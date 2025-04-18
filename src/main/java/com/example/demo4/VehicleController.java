package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VehicleController {

    @FXML
    private AnchorPane Anchorpane;

    @FXML
    private ComboBox<String> comboBx;

    @FXML
    private TableView<Vehicle> tbleView;

    @FXML
    private TableColumn<Vehicle, String> tcVehicleID;
    @FXML
    private TableColumn<Vehicle, String> tcBrand;
    @FXML
    private TableColumn<Vehicle, String> tcCategory;
    @FXML
    private TableColumn<Vehicle, Double> tcRental;
    @FXML
    private TableColumn<Vehicle, String> tcAvailability;

    @FXML
    private TextField txtVehicleID, txtBrand, txtRental, txtSearch, txtAvailability;

    final ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        comboBx.setItems(FXCollections.observableArrayList("Car", "Bike", "Van", "Truck"));

        tcVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        tcBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tcRental.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));
        tcAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        tbleView.setItems(vehicleList);
    }

    @FXML
    void add(ActionEvent event) {
        String id = txtVehicleID.getText();
        String brand = txtBrand.getText();
        String category = comboBx.getValue();
        String availability = txtAvailability.getText();
        double price;

        try {
            price = Double.parseDouble(txtRental.getText());
        } catch (NumberFormatException e) {
            return;
        }

        Vehicle vehicle = new Vehicle(id, brand, category, price, availability);
        vehicleList.add(vehicle);
        clearFields();
    }

    @FXML
    void Update(ActionEvent event) {
        Vehicle selected = tbleView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setVehicleID(txtVehicleID.getText());
            selected.setBrand(txtBrand.getText());
            selected.setCategory(comboBx.getValue());
            selected.setRentalPrice(Double.parseDouble(txtRental.getText()));
            selected.setAvailability(txtAvailability.getText());
            tbleView.refresh();
            clearFields();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Vehicle selected = tbleView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            vehicleList.remove(selected);
            clearFields();
        } else {
            showAlert("Select a vehicle to delete.");
        }
    }

    @FXML
    void Search(ActionEvent event) {
        String keyword = txtSearch.getText().toLowerCase();
        ObservableList<Vehicle> filtered = FXCollections.observableArrayList();
        for (Vehicle v : vehicleList) {
            if (v.getVehicleID().toLowerCase().contains(keyword) || v.getBrand().toLowerCase().contains(keyword)) {
                filtered.add(v);
            }
        }
        tbleView.setItems(filtered);
    }

    @FXML
    void Exit(ActionEvent event) {
        // Load the Admin Dashboard
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtVehicleID.clear();
        txtBrand.clear();
        txtRental.clear();
        txtAvailability.clear();
        comboBx.getSelectionModel().clearSelection();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Warning");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
