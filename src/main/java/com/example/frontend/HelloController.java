package com.example.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.action.Action;

public class HelloController {



    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {

        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.sendRequst("/hello");


    }

}