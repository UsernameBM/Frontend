package com.example.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label cConfirmRegistrationLabel;
    @FXML
    private PasswordField cSetPasswordField;
    @FXML
    private PasswordField cConfirmPasswordField;
    @FXML
    private Label cConfirmPasswordLabel;
    @FXML
    private TextField cFirstnameTextField;
    @FXML
    private TextField cLastnameTextfield;
    @FXML
    private TextField cUsernameTextField;

    ConnectionManager connectionManager = new ConnectionManager();

    public void cRegisterButtonOnAction(ActionEvent event) {
        if (cSetPasswordField.getText().equals(cConfirmPasswordField.getText() )) {
            registerUser();
        } else {
            cConfirmPasswordLabel.setText("Lösenorden stämmer inte överens!");
        }
    }

    public void registerUser() {
        String firstname = cFirstnameTextField.getText();
        String lastname = cLastnameTextfield.getText();
        String username = cUsernameTextField.getText();
        String password = cSetPasswordField.getText();


        //Funkar
        if (connectionManager.sendRequst("/verifyCustomerUsername?username=" + username).equals("Customer exist")){
            cConfirmRegistrationLabel.setText("Användarnamn upptagen, välj ett annat!");

        } else {
            connectionManager.sendRequst("/insertCustomer?firstname=" + firstname + "&lastname="+ lastname +"&username="+ username +"&password=" + password);
            cConfirmRegistrationLabel.setText("Användare har registrerats. Gå tillbaka för att logga in!");
        }
        /*

        String usernameRequest = connectionManager.sendRequst("/verifyCustomerUsername" + username);

        if(!usernameRequest.equals(null)) {
            cConfirmRegistrationLabel.setText("Användarnamn upptagen, välj ett annat!");
        } else {
        connectionManager.sendRequst("/addCustomer" + firstname + lastname + username + password);
        cConfirmRegistrationLabel.setText("Användare har registrerats. Gå tillbaka för att logga in!");
        }

         */
    }

    @FXML
    public void cGoBackToLoginButtonOnAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

