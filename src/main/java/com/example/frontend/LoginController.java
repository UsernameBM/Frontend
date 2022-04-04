package com.example.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    private Parent root;

    @FXML
    private Button cLogInButton;
    @FXML
    private Button cSignInButton;
    @FXML
    private Label cLoginMessageLabel;
    @FXML
    private Label eLoginMessageLabel;
    @FXML
    private TextField customerUsernameField;
    @FXML
    private PasswordField customerPasswordField;
    @FXML
    private TextField eUsernameField;
    @FXML
    private PasswordField ePasswordField;


    public void cLogInButtonOnAction(ActionEvent event) {
        if (!customerUsernameField.getText().isBlank() && !customerPasswordField.getText().isBlank()) {
            customerValidateLogin();
        } else {
            cLoginMessageLabel.setText("Skriv in ditt användarnamn och lösenord!");
        }
    }

    public void eLoginButtonOnAction(ActionEvent event) {
        if (!eUsernameField.getText().isBlank() && !ePasswordField.getText().isBlank()) {
            customerValidateLogin();
        } else {
            eLoginMessageLabel.setText("Skriv in ditt användarnamn och lösenord!");
        }
    }

    public void cSignInButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CRegister.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void customerValidateLogin() {
        ConnectionManager connectionManager = new ConnectionManager();

        String userNameTextField = customerUsernameField.getText();
        String passwordField = customerPasswordField.getText();

        try {
            connectionManager.sendRequst("/verifyLogin?username=" + userNameTextField + "&password=" + passwordField);
            cLoginMessageLabel.setText("Grattis!"); //Här ska vi komma till boka film sidan!

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        cLoginMessageLabel.setText("Fel användarnamn eller lösenord. Försök igen!");
    }


   /** public void employeeValidateLogin() {
        DatabaseConnecter ConnectNow = new DatabaseConnecter();
        Connection connectDB = ConnectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM personalInfo WHERE user_name = ' " + eUsernameField.getText()
                + " ' AND password = '" + ePasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    eLoginMessageLabel.setText("Grattis!"); // Här ska vi komma till admin sidan :)
                } else {
                    eLoginMessageLabel.setText("Fel användarnamn och lösenord. Försök igen!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

              **/
}

