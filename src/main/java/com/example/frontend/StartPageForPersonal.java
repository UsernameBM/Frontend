package com.example.frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * VAD VI BEHÖVER GÖRA
 */


public class StartPageForPersonal implements Initializable {
        ConnectionManager connectionManager = new ConnectionManager();




    @FXML
    private ChoiceBox<String> tid;
    private String[] tider = {"2020-04-08,09:00-15:00","2020-04-08,15:00-23:00","2020-04-09,09:00-15:00","2020-04-09,15:00-23:00"};
    @FXML
    private ChoiceBox<Integer> id;
    private int[] personal = {1, 2, 3, 4, 5};
    @FXML
    private ChoiceBox<Integer> salon;
    private int[] auditorium = {1,2,3,4,5};
    @FXML
    private ChoiceBox<String> kitchen;
    private String[] yesKitchen = {"Ja", "Nej"};
    @FXML
    private ChoiceBox<String> counter;
    private String[] yesCounter = {"Ja", "Nej"};
    @FXML
    private Label fillInAllLabel;
    @FXML
    private Button AddChoises;



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
        private String[] names = {"Kista"};
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
        private TableColumn<String, String> cFilm;
        @FXML
        private TableColumn<String, Integer> cSalong;
        @FXML
        private TableColumn <String, Integer> cTid;
        @FXML
        private Label PConfirmPasswordLabel;
        @FXML
        private Label PConfirmRegistrationLabel;





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
            PConfirmPasswordLabel.setText("Lösenorden stämmer inte överens!");
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
        String cinema = CBBiograf.getValue();

        if (connectionManager.sendRequst("/verifyPersonalUsername?username=" + userName).equals("Personal exist")){
            PConfirmRegistrationLabel.setText("Användarnamn upptagen, välj ett annat!");
        } else {
            connectionManager.sendRequst("/insertPersonal?firstname=" + firstName + "&lastname=" + lastName + "&tel_number=" + telephone_number + "&email=" + email + "&bank=" + bank + "&banknumber=" + banknumber + "&user_name=" + userName + "&password=" + password + "&Cinema_name=" + cinema);
           PConfirmRegistrationLabel.setText("Personal har registrerats!");
        }
    }

    public void addMovie(){
        String title = getMovieTitleTextField.getText();
        String description = getMovieDescriptionTextField.getText();
        String length = getMovieLengthTextField.getText();

        connectionManager.sendRequst("/insertMovie?name=" + title + "&description=" + description + "&length=" + length);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tid.getItems().addAll(tider);
        id.getItems().addAll(1,2,3,4,5);
        salon.getItems().addAll(1,2,3,4,5);
        kitchen.getItems().addAll(yesKitchen);
        counter.getItems().addAll(yesCounter);
        CBBiograf.getItems().addAll(names);

    }

    public void cSendToSchedualButton(ActionEvent event) {
        try{
            if (!tid.getValue().isBlank() && !kitchen.getValue().isBlank() && !counter.getValue().isBlank() && !id.getValue().toString().isBlank() && !salon.getValue().toString().isBlank() ) {
                fillInAllLabel.setTextFill(Color.GREEN);
                fillInAllLabel.setText("Du har bokat pass!");
                sendToSchedual();
            }
        } catch (Exception e){
            fillInAllLabel.setTextFill(Color.RED);
            fillInAllLabel.setText("du måste fylla i");
            e.printStackTrace();

        }

    }

    private void sendToSchedual() {

        String hej = tid.getValue();
        System.out.println(hej);
        String time = tid.getValue();
        int idd = id.getValue();
        int salond = salon.getValue();
        String kit = kitchen.getValue();
        //String[] kitchend = kitchen.;
        String kassa = counter.getValue();
        // if (connectionManager.sendRequst("/verifyCustomerUsername?username=" + time + idd).equals("Customer exist")){
        // fillInAllLabel.setText("Användarnamn upptagen, välj ett annat!");
        //  fillInAllLabel.setTextFill(Color.RED);

        //  } else {
        System.out.println(("/insertPersonalSchedule?idSalon=" + salond + "&idUser="+ idd +"&Counter="+ kassa +"&Kitchen=" + kit + "&date=" + time));
        connectionManager.sendRequst("/insertPersonalSchedule?idSalon=" + salond + "&idUser="+ idd +"&Counter="+ kassa +"&Kitchen=" + kit + "&date=" + time);
        fillInAllLabel.setText("finns i systemet!");
        //  }

    }
    public ObservableList<String> getScreeningList(){
        ObservableList<String> screeningList = FXCollections.observableArrayList();
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
