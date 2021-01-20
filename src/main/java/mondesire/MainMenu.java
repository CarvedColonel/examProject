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
import javafx.scene.text.Text;

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
    private ImageView imgLoad;

    @FXML
    private TextField txtLoad;

    @FXML
    private Button btnLoad;



    boolean sound = MainApp.sound;


    @FXML
    void clickPlay(MouseEvent event) throws IOException {
        if (lblUsername.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText(null);
            alert.setContentText("Welcome to Priest's Conquest! Before you start, head over to the settings tab to write in your username!" +
                    " Then, if this is your first time playing, feel free to click on the help button and learn how to play the game!" + "\n" + "\n" +
                    "Thank you for playing and enjoy your time!");
            alert.showAndWait();
        } else {
            MainApp.setRoot("Gameplay", "Priest's Conquest");
        }

    }

    @FXML
    void clickHelp(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("Welcome!" + "\n" +
                "GAMEPLAY: Use W, A, S, D, to move around the map. Reach the Skull Icon to start a battle. Go through the map clearing your way through the town" +
                " to liberate it of the devilish monsters that have come ransacking your village. Get through all 6 battles to beat the game!"
                + " Look around for secrets or easter eggs as well, or maybe even visit the shopkeep near the north side of the town!" + "\n"
                + "BATTLES: A turn based combat system where you can choose to fight, or attempt to bless the monster. Fighting are standard damage" +
                " moves, bless moves are ones that aren't your typical moves, you can joke with the monster, or attempt an exorcism. But be weary" +
                " because these moves won't always work, so sometimes it might not be worth taking the damage to try to reason with a monster.");
        alert.showAndWait();
    }

    @FXML
    void clickSettings(MouseEvent event) {
        toggle(false, true);
        if (MainApp.user == "") {
            imgUser.setVisible(false);
            txtUser.setVisible(false);
            btnConfirm.setVisible(false);
            lblError.setVisible(false);
        } else {
        }
    }

    @FXML
    void clickConfirm(ActionEvent event) {
        if (txtUser.getText().isEmpty()) {
            lblError.setText("Please fill out ALL fields.");
        } else if (txtUser.getText().length() > 10) {
            lblError.setText("Username must be 10 characters or less");
        } else {
            MainApp.user = txtUser.getText();
            txtUser.setEditable(false);
            txtUser.setVisible(false);
            btnConfirm.setVisible(false);
            imgPlay.setVisible(true);
            imgUser.setVisible(false);
            lblUsername.setText("" + MainApp.user);
        }
    }

    @FXML
    void clickMenu(MouseEvent event) {
        toggle(true, false);
        if (MainApp.user == "") {
            imgPlay.setVisible(false);
            imgUser.setVisible(true);
            txtUser.setVisible(true);
            btnConfirm.setVisible(true);
        } else {
            imgUser.setVisible(false);
            btnConfirm.setVisible(false);
            txtUser.setVisible(false);
        }
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


    void toggle(boolean menu, boolean settings) {
        //toggles the menu things either on or off
        imgPlay.setVisible(menu);
        imgHelp.setVisible(menu);
        imgSettings.setVisible(menu);
        //toggles the things on the settings page on or off
        imgSound.setVisible(settings);
        lblSound.setVisible(settings);
        imgMenu.setVisible(settings);
    }


    @FXML
    void clickExit(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
