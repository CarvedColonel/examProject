package mondesire;
/*
Put header here


 */

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOver implements Initializable {

    @FXML
    private Label lblMessage;

    @FXML
    private void clickExit(MouseEvent event){
        System.exit(0);
    }

    public void AnimateText(Label lbl, String descImp) {
        String content = descImp;
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(15000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(content.substring(0, n));
            }
        };
        animation.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AnimateText(lblMessage,"Thank you for saving us" + "\n" + "Sir Priest! We wish you" + "\n" + "safe travels!" + "\n" + "\n" + "Thank you for playing!"
                + "\n" + "Game made by:" + "\n" + "Aidan Mason-Mondesire" + "\n" + "and Milan Hennessy.");

    }
}
