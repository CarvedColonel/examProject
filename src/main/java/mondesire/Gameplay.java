package mondesire;
/*
Put header here


 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Gameplay implements Initializable {

    @FXML
    void clickBattle1(ActionEvent event) throws IOException {
        MainApp.setRoot("BattleSequence", "Priest's Conquest");
        MainApp.battleStage = 1;
    }

    @FXML
    void clickBattle2(ActionEvent event) throws IOException {
        MainApp.setRoot("BattleSequence", "Priest's Conquest");
        MainApp.battleStage = 2;
    }

    @FXML
    void clickBattle3(ActionEvent event) throws IOException {
        MainApp.setRoot("BattleSequence", "Priest's Conquest");
        MainApp.battleStage = 3;
    }

    @FXML
    void clickBattle4(ActionEvent event) throws IOException {
        MainApp.setRoot("BattleSequence", "Priest's Conquest");
        MainApp.battleStage = 4;
    }

    @FXML
    void clickBattle5(ActionEvent event) throws IOException {
        MainApp.setRoot("BattleSequence", "Priest's Conquest");
        MainApp.battleStage = 5;
    }

    @FXML
    void clickBattle6(ActionEvent event) throws IOException {
        MainApp.setRoot("BattleSequence", "Priest's Conquest");
        MainApp.battleStage = 6;
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
}
