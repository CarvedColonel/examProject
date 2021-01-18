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
        MainApp.battleStage = 1;
        MainApp.setRoot("BattleSequence", "Priest's Conquest");

        System.out.println(MainApp.battleStage);
    }

    @FXML
    void clickBattle2(ActionEvent event) throws IOException {
        MainApp.battleStage = 2;
        MainApp.setRoot("BattleSequence2", "Priest's Conquest");

        System.out.println(MainApp.battleStage);
    }

    @FXML
    void clickBattle3(ActionEvent event) throws IOException {
        MainApp.battleStage = 3;
        MainApp.setRoot("BattleSequence3", "Priest's Conquest");

        System.out.println(MainApp.battleStage);
    }

    @FXML
    void clickBattle4(ActionEvent event) throws IOException {
        MainApp.battleStage = 4;
        MainApp.setRoot("BattleSequence4", "Priest's Conquest");

        System.out.println(MainApp.battleStage);
    }

    @FXML
    void clickBattle5(ActionEvent event) throws IOException {
        MainApp.battleStage = 5;
        MainApp.setRoot("BattleSequence5", "Priest's Conquest");

        System.out.println(MainApp.battleStage);
    }

    @FXML
    void clickBattle6(ActionEvent event) throws IOException {
        MainApp.battleStage = 6;
        MainApp.setRoot("BattleSequence6", "Priest's Conquest");
        System.out.println(MainApp.battleStage);
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
}
