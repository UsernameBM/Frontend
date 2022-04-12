package com.example.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.action.Action;

public class HelloController {

    ConnectionManager connectionManager;


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {

        connectionManager = new ConnectionManager();
        System.out.println(connectionManager.sendRequest("/requestScreening?id=1"));



    }

}