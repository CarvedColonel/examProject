package mondesire;
/*
Put header here


 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleSequence implements Initializable {

    Image wolf = new Image(getClass().getResource("/WOLF.png").toString());
    Image skeleton = new Image(getClass().getResource("/SKELETON.png").toString());
    Image zombie = new Image(getClass().getResource("/ZOMBIE.png").toString());
    Image wizard = new Image(getClass().getResource("/WIZARD.png").toString());
    Image ghost = new Image(getClass().getResource("/GHOST.png").toString());
    Image orc = new Image(getClass().getResource("/HIGHORX.png").toString());





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
