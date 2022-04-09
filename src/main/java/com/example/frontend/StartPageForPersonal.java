package com.example.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * VAD VI BEHÖVER GÖRA
 */


public class StartPageForPersonal implements Initializable {
        ConnectionManager connectionManager = new ConnectionManager();

        @FXML
        private ChoiceBox<String> tid;
        private String[] tider = {"2020-04-08 09:00-15:00","2020-04-08 15:00-23:00","2020-04-09 09:00-15:00","2020-04-09 15:00-23:00"};
        @FXML
        private ChoiceBox<String> id;
        private String[] personal = {"Henning", "Bryan", "Johanna", "Killian", "Isabella"};
        @FXML
        private ChoiceBox<String> salon;
        private String[] auditorium = {"1","2","3","4","5"};
        @FXML
        private ChoiceBox<String> kök;
        private String[] yesKök = {"Ja", "Nej"};
        @FXML
        private ChoiceBox<String> kassa;
        private String[] yesKassa = {"Ja", "Nej"};

        @FXML
        private Button addMovieButton;
        @FXML
        private Label getMovieDescriptionLabel;
        @FXML
        private TextField getMovieDescriptionTextField;
        @FXML
        private Label getMovieImageLabel;
        @FXML
        private Label getMovieLengthLabel;
        @FXML
        private TextField getMovieLengthTextField;
        @FXML
        private Label getMovieTitleLabel;
        @FXML
        private TextField getMovieTitleTextField;
        @FXML
        private ImageView movieImageView;
        @FXML
        private ImageView movieImageView1;
        @FXML
        private ListView<String> movieListView;
        @FXML
        private Button openImageButton;
        @FXML
        private Button removeMovieButton;
        @FXML
        private Button removeMovieButton1;

        final FileChooser fileChooser = new FileChooser();


    @FXML
    private void openImageFileOnAction(ActionEvent event) {
        // Set title of
        fileChooser.setTitle("File chooser");

        //Set the initial directory for the displayed file dialog
        //user-home refers to the path to the user directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*,png", "*,jpg", "*,gif"));
        fileChooser.getExtensionFilters().clear(); //removes all of the elements from this list

        //the selected file or null if no file has been selected.
        File file = fileChooser.showOpenDialog(null); //Shows a new file open dialog

        if(file != null) {
            movieImageView.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("A file is invalid");
        }

    }


        @FXML
        void uploadMovieOnAction(ActionEvent event) {
        String getMovieTitle = getMovieTitleTextField.getText();
        String getMovieDescription = getMovieDescriptionTextField.getText();
        String getMovieLength = getMovieLengthTextField.getText();

        String movieInformation = getMovieTitle + ", " + getMovieDescription + ", " + getMovieLength;

        movieListView.getItems().add(movieInformation);
    }

        @FXML
        void removeMovieOnAction(ActionEvent event) {
            int selectedMovie = movieListView.getSelectionModel().getSelectedIndex();
            movieListView.getItems().remove(selectedMovie);
        }

        @FXML
        void addMovieOnAction(ActionEvent event) {

        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tid.getItems().addAll(tider);
        id.getItems().addAll(personal);
        salon.getItems().addAll(auditorium);
        kök.getItems().addAll(yesKök);
        kassa.getItems().addAll(yesKassa);

    }
}
