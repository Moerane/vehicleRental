package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login {

    @FXML
    private AnchorPane Anchorpane;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private Label lbPassword;

    @FXML
    private Label lbUsername;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblVehicle;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    public void initialize() {
        combobox.getItems().addAll("Admin", "Employee");
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String role = combobox.getValue();

        if (role == null) {
            showErrorMessage("Please select a role.");
            return;
        }

        if (role.equals("Admin")) {
            loadDashboard("adminDashboard.fxml", "Admin Dashboard");
        } else if (role.equals("Employee")) {
            loadDashboard("EmployeeDashboard.fxml", "Employee Dashboard");
        } else {
            showErrorMessage("Unknown role selected.");
        }

        // Optional: clear fields
        txtUsername.clear();
        txtPassword.clear();
    }

    private void loadDashboard(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) Anchorpane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Error loading the dashboard: " + e.getMessage());
        }
    }

    private void showErrorMessage(String message) {
        lblVehicle.setText(message);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
