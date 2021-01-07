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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainMenu implements Initializable {

    @FXML
    private ImageView imgPlay;

    @FXML
    void clickPlay(MouseEvent event) throws IOException {
        MainApp.setRoot("Gameplay","Priest's Conquest");
    }

    @FXML
    void clickHelp(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("Welcome!" + "\n" +
                "GAMEPLAY: Use W, A, S, D, to move around the map. Reach the Sword Icon to start a battle. Go through the map clearing your way through the town" +
                " to liberate it of the devilish monsters that have come ransacking your village. Get through all 6 battles to beat the game!" + "\n"
        + "BATTLES: Use W, A, S, D, to move your icon around. Choose to fight the enemy, or to attempt a blessing to try and save them. Blessings will take multiple attempts and may " +
                "not work depending on how you've treated the enemy so choose your path wisely.");
        alert.showAndWait();
    }

    @FXML
    void clickSettings(MouseEvent event) {

    }




    @FXML
    void clickExit(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
}
