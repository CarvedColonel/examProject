package mondesire;
/*
Put header here


 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class Gameplay implements Initializable {
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), ae -> move()));

    @FXML
    private ImageView imgPriest;
int dx;
int dy;

    Label walls[] = new Label[22];

    @FXML
    public void keyPressed(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
           dx=5;

        } else if ((event.getCode() == KeyCode.A)) {
            dx=-5;

        } else if ((event.getCode() == KeyCode.W)) {
            dy=-5;

        } else if ((event.getCode() == KeyCode.S)) {
            dy=5;
        }
    }
    @FXML
    public void keyReleased(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            dx = 0;
            dy = 0;
        } else if ((event.getCode() == KeyCode.A)) {
            dx = 0;
            dy = 0;
        } else if ((event.getCode() == KeyCode.W)) {
            dx = 0;
            dy = 0;
        } else if ((event.getCode() == KeyCode.S)) {
            dx = 0;
            dy = 0;
        }
    }
    private void move() {
        //Basic movement for all characters
        imgPriest.setTranslateX(imgPriest.getTranslateX() + dx);
        imgPriest.setTranslateY(imgPriest.getTranslateY() + dy);
if collision(imgPriest,) {

        }
    }
    public boolean collision(ImageView block1, Label block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }    
}
