package com.example.frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * VAD VI BEHÖVER GÖRA
 */


public class StartPageForPersonal {
        ConnectionManager connectionManager = new ConnectionManager();

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

        @FXML
        private ChoiceBox <String> CBBiograf;
        @FXML
        private TextField TFFirstName;
        @FXML
        private TextField TFLastName;
        @FXML
        private TextField TFTele_number;
        @FXML
        private TextField TFEmail;
        @FXML
        private TextField TFBank;
        @FXML
        private TextField TFBanknumber;
        @FXML
        private TextField TFUsername;
        @FXML
        private TextField TFPassword;
        @FXML
        private TextField TFRePassword;

        final FileChooser fileChooser = new FileChooser();

        @FXML
        private TableView<String> TVScreening;
        @FXML
        private TableColumn <String, String> cFilm;
        @FXML
        private TableColumn <String, Integer> cSalong;
        @FXML
        private TableColumn <String, Integer> cTid;



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
            addMovie();
        }

    public void TFRePassword(ActionEvent event) {
        if (TFPassword.getText().equals(TFRePassword.getText()  )){
            registerPersonal();
        } else {
            //cConfirmPasswordLabel.setText("Lösenorden stämmer inte överens!");
        }
    }

    public void registerPersonal(){
        String firstName = TFFirstName.getText();
        String lastName = TFLastName.getText();
        String telephone_number = TFTele_number.getText();
        String email = TFEmail.getText();
        String bank = TFBank.getText();
        String banknumber = TFBanknumber.getText();
        String userName = TFUsername.getText();
        String password = TFPassword.getText();

        if (connectionManager.sendRequst("/verifyPersonalUsername?username=" + userName).equals("Personal exist")){
            //cConfirmRegistrationLabel.setText("Användarnamn upptagen, välj ett annat!");
        } else {
            connectionManager.sendRequst("/insertPersonal?firstname=" + firstName + "&lastname=" + lastName + "&tel_number=" + telephone_number + "&email=" + email + "&bank=" + bank + "&banknumber=" + banknumber + "&user_name=" + userName + "&password=" + password);
           //cConfirmRegistrationLabel.setText("Användare har registrerats. Gå tillbaka för att logga in!");
        }
    }

    public void addMovie(){
        String title = getMovieTitleTextField.getText();
        String description = getMovieDescriptionTextField.getText();
        String length = getMovieLengthTextField.getText();

        connectionManager.sendRequst("/insertMovie?name=" + title + "&description=" + description + "&length=" + length);
    }

    public ObservableList<String> getScreeningList(){
        connectionManager.sendRequst("/getAllScreening");
        ObservableList<String> screeningList = FXCollections.observableArrayList(connectionManager.sendRequst("/getAllScreening"));
        System.out.println("Screeninglist=" + screeningList);
        return screeningList;
    }

    public void showScreening(){
        ObservableList<String> list = getScreeningList();
        cFilm.setCellValueFactory(new PropertyValueFactory<String, String>("time"));
        cSalong.setCellValueFactory(new PropertyValueFactory<String, Integer>("movie_id"));
        cTid.setCellValueFactory(new PropertyValueFactory<String, Integer>("length"));

        TVScreening.setItems(list);
    }
}
