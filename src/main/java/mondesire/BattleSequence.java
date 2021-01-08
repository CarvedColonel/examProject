package mondesire;
/*
Put header here


 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleSequence implements Initializable {

    @FXML
    private ImageView imgPlayer;

    @FXML
    private ImageView imgEnemy;


    Image wolf = new Image(getClass().getResource("/WOLF.png").toString());
    Image skeleton = new Image(getClass().getResource("/SKELETON.png").toString());
    Image zombie = new Image(getClass().getResource("/ZOMBIE.png").toString());
    Image wizard = new Image(getClass().getResource("/WIZARD.png").toString());
    Image ghost = new Image(getClass().getResource("/GHOST.png").toString());
    Image orc = new Image(getClass().getResource("/HIGHORC.png").toString());

    @FXML
    void clickBack(ActionEvent event) throws IOException {
        MainApp.setRoot("Gameplay", "Priest's Conquest");
    }

    void imageSize(double height, double width, double x, double y){
        imgEnemy.setFitHeight(height);
        imgEnemy.setFitWidth(width);

        imgEnemy.setTranslateX(x);
        imgEnemy.setTranslateY(y);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (MainApp.battleStage == 1) {
            imgEnemy.setImage(zombie);
            imageSize(238,146, 1011, 305);
        } else if (MainApp.battleStage == 2) {
            imgEnemy.setImage(skeleton);
            imageSize(238,146, 1011, 305);
        } else if (MainApp.battleStage == 3) {
            imgEnemy.setImage(ghost);
            imageSize(238,146, 1011, 305);
        } else if (MainApp.battleStage == 4) {
            imgEnemy.setImage(wolf);
            imageSize(143,142, 1011, 420);
        } else if (MainApp.battleStage == 5) {
            imgEnemy.setImage(wizard);
            imageSize(161,124, 1011, 420);
        } else if (MainApp.battleStage == 6) {
            imgEnemy.setImage(orc);
            imageSize(386,343,856,195);
        }
    }
}
