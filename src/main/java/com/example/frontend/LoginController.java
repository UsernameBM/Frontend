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
    private Stage stage;
    private Scene scene;

    ConnectionManager connectionManager;

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


    @FXML
    void cLogInButtonOnAction(ActionEvent event) throws IOException {


         connectionManager = new ConnectionManager();

        String resultString = connectionManager
                .sendRequst("/authorize?username="+customerUsernameField.getText()+"&password="+customerPasswordField.getText());


         if (customerUsernameField.getText().isBlank() || customerPasswordField.getText().isBlank()){
            cLoginMessageLabel.setText("Skriv in ditt användarnamn och lösenord!");}


        else if(resultString.equals("right password")) {

                root = FXMLLoader.load(getClass().getResource("startForCustomer.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();}


        else if(resultString.equals("wrong password or username")){
            cLoginMessageLabel.setText("wrong password or username");}

        else {
             cLoginMessageLabel.setText("wrong password or username");
         }

    }






    @FXML
    void eLoginButtonOnAction(ActionEvent event) throws IOException {
        if (!eUsernameField.getText().isBlank() && !ePasswordField.getText().isBlank()) {
            root = FXMLLoader.load(getClass().getResource("startPageForPersonal.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            eLoginMessageLabel.setText("Skriv in ditt användarnamn och lösenord!");
        }
    }

    @FXML
    void cSignInButtonOnAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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

