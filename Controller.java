package com.example.exam1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class Controller {

    @FXML
    private TextField patientIdField;
    @FXML
    private TextField patientNameField;
    @FXML
    private TextField diagnosisField;
    @FXML
    private TextField symptomsField;
    @FXML
    private TextField medicinesField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TableView<Diagnosis> tableView;
    @FXML
    private TableColumn<Diagnosis, String> patientIdColumn;
    @FXML
    private TableColumn<Diagnosis, String> patientNameColumn;
    @FXML
    private TableColumn<Diagnosis, String> diagnosisColumn;
    @FXML
    private TableColumn<Diagnosis, String> symptomsColumn;
    @FXML
    private TableColumn<Diagnosis, String> medicinesColumn;
    @FXML
    private TableColumn<Diagnosis, String> dateColumn;

    private ObservableList<Diagnosis> data;

    private DatabaseHandler databaseHandler;

    @FXML
    public void initialize() {
        databaseHandler = new DatabaseHandler();

        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        symptomsColumn.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        medicinesColumn.setCellValueFactory(new PropertyValueFactory<>("medicines"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    @FXML
    public void addDiagnosis() {
        String patientId = patientIdField.getText();
        String patientName = patientNameField.getText();
        String diagnosis = diagnosisField.getText();
        String symptoms = symptomsField.getText();
        String medicines = medicinesField.getText();
        String date = dateField.getValue().toString();

        String query = "INSERT INTO Diagnoses (patientId, patientName, diagnosis, symptoms, medicines, date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, patientId);
            preparedStatement.setString(2, patientName);
            preparedStatement.setString(3, diagnosis);
            preparedStatement.setString(4, symptoms);
            preparedStatement.setString(5, medicines);
            preparedStatement.setString(6, date);

            preparedStatement.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Diagnosis added successfully!");

            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add diagnosis.");
        }
    }

    @FXML
    public void searchDiagnoses() {
        data.clear(); // Clear existing data before fetching new data

        String query = "SELECT * FROM Diagnoses";

        try (Connection connection = databaseHandler.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String patientId = resultSet.getString("patientId");
                String patientName = resultSet.getString("patientName");
                String diagnosis = resultSet.getString("diagnosis");
                String symptoms = resultSet.getString("symptoms");
                String medicines = resultSet.getString("medicines");
                String date = resultSet.getString("date");

                Diagnosis diagnosisEntry = new Diagnosis(patientId, patientName, diagnosis, symptoms, medicines, date);
                data.add(diagnosisEntry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve diagnoses.");
        }
    }

    private void clearFields() {
        patientIdField.clear();
        patientNameField.clear();
        diagnosisField.clear();
        symptomsField.clear();
        medicinesField.clear();
        dateField.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}