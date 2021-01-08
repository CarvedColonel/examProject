package mondesire;
/*
Aidan Mason-Mondesire
January 6th 2020
Main Menu for the exam project game
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainMenu implements Initializable {

    @FXML
    private ImageView imgPlay;

    @FXML
    private ImageView imgSound;

    @FXML
    private ImageView imgUser;

    @FXML
    private TextField txtUser;

    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblSound;

    @FXML
    private ImageView imgHelp;

    @FXML
    private ImageView imgSettings;

    @FXML
    private ImageView imgMenu;

    @FXML
    private Label lblError;

    @FXML
    private Label lblUsername;

    @FXML
    private Button btnClear;

    boolean sound = MainApp.sound;


    @FXML
    void clickPlay(MouseEvent event) throws IOException {
        if(lblUsername.getText() == ""){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText(null);
            alert.setContentText("Welcome to Priest's Conquest! Before you start, head over to the settings tab to write in your username!" +
                    " Then, if this is your first time playing, feel free to click on the help button and learn how to play the game!" + "\n" + "\n" +
                    "Thank you for playing and enjoy your time!");
            alert.showAndWait();
        }else{
            MainApp.setRoot("Gameplay","Priest's Conquest");
        }

    }

    @FXML
    void clickHelp(MouseEvent event) {

    }

    @FXML
    void clickSettings(MouseEvent event) {

        toggle(false,true);

        if(MainApp.user != ""){
            btnConfirm.setDisable(true);
            btnClear.setDisable(false);
        }
    }

    @FXML
    void clickConfirm(ActionEvent event){
        if (txtUser.getText().trim() == "") {
            lblError.setText("Please fill out ALL fields.");
        } else if (txtUser.getText().length() > 10) {
            lblError.setText("Username must be 10 characters or less");
        }else {
            MainApp.user = txtUser.getText();
            txtUser.setEditable(false);
            btnConfirm.setDisable(true);
            btnClear.setDisable(false);
            lblUsername.setText("" + MainApp.user);
        }
    }

    @FXML
    void clickClear(ActionEvent event){
        lblUsername.setText("");
        btnConfirm.setDisable(false);
        txtUser.setEditable(true);
        MainApp.user = "";
        btnClear.setDisable(true);
    }

    @FXML
    void clickMenu(MouseEvent event){
        toggle(true,false);
    }

    @FXML
    void clickSound(MouseEvent event) {
        if (sound == true) {
            MainApp.sound = false;
            lblSound.setText("OFF");
        } else if (sound == false) {
            MainApp.sound = true;
            lblSound.setText("ON");
        }
    }


    void toggle(boolean menu , boolean settings ){
        //toggles the menu things either on or off
        imgPlay.setVisible(menu);
        imgHelp.setVisible(menu);
        imgSettings.setVisible(menu);
        //toggles the things on the settings page on or off
        imgUser.setVisible(settings);
        imgSound.setVisible(settings);
        lblSound.setVisible(settings);
        btnConfirm.setVisible(settings);
        txtUser.setVisible(settings);
        imgMenu.setVisible(settings);
        btnConfirm.setVisible(settings);
        btnClear.setVisible(settings);
    }


    @FXML
    void clickExit(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
}
