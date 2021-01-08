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

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Gameplay implements Initializable {
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), ae -> move()));

    @FXML
    private Polygon plgWall;
    @FXML
    private Polygon plgPriest;
    @FXML
    private Pane panPriest;

    @FXML
    private Polygon plgWall2;

    @FXML
    private Polygon plgWell;

    @FXML
    private Polygon plgInn;

    @FXML
    private Polygon plgWall3;

    int dx;
    int dy;

    Label walls[] = new Label[22];

    @FXML
    public void keyPressed(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            dx = 5;

        } else if ((event.getCode() == KeyCode.A)) {
            dx = -5;

        } else if ((event.getCode() == KeyCode.W)) {
            dy = -5;

        } else if ((event.getCode() == KeyCode.S)) {
            dy = 5;
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
        panPriest.setTranslateX(panPriest.getTranslateX() + dx);
        panPriest.setTranslateY(panPriest.getTranslateY() + dy);
        if (collision(plgPriest, plgWall) || collision(plgPriest, plgWall2) || collision(plgPriest, plgWell) || collision(plgPriest, plgInn) || collision(plgPriest, plgWall3)) {
            panPriest.setTranslateX(panPriest.getTranslateX() - dx);
            panPriest.setTranslateY(panPriest.getTranslateY() - dy);
            dx = 0;
            dy = 0;

        }
    }

    public boolean collision(Shape block1, Shape block2) {
//If the objects can be changed to shapes just see if they intersect
        Shape a = Shape.intersect(block1, block2);
        return a.getBoundsInLocal().getWidth() != -1;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}
