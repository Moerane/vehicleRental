package com.example.demo4;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReportController {

    @FXML
    private TableColumn<?, ?> colCustomer;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colVehicle;

    @FXML
    private TableView<?> rentalTable;

    @FXML
    private LineChart<?, ?> revenueLineChart;

    @FXML
    private PieChart vehiclePieChart;

}
