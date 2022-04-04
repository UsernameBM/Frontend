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
import java.sql.Statement;

public class RegisterController {

   /** private Parent root;

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

        String verifyUsername = "SELECT EXISTS (SELECT user_name FROM costumer WHERE user_name = '" + firstname + "')";

        String insertFields = "INSERT INTO Costumer(firstName, lastName, user_name, password) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();

            if (verifyUsername.equals(username)) {
                cConfirmRegistrationLabel.setText("Användarnamn upptagen, välj ett annat!");
                //  statement.
            } else {
                statement.executeUpdate(insertToRegister);
                cConfirmRegistrationLabel.setText("Användare har registrerats. Gå tillbaka för att logga in!");
            }

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();

        }
    }

    public void cGoBackToLoginButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
} **/

}
