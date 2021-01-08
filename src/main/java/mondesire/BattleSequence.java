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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleSequence implements Initializable {

    @FXML
    private ImageView imgPlayer;

    @FXML
    private ImageView imgEnemy;

    @FXML
    private Polygon plgPlayer;

    @FXML
    private Polygon plgBorder;

    @FXML
    private Pane panPlayer;

    @FXML
    private Label lblCoverLeft;

    @FXML
    private Label lblCoverRight;

    @FXML
    private Label lblCoverBottom;

    @FXML
    private Label lblCoverTop;

    @FXML
    private Label lblMessage;

    @FXML
    private Label lblMove1;

    @FXML
    private Label lblMove2;

    @FXML
    private Label lblMove3;

    Image wolf = new Image(getClass().getResource("/WOLF.png").toString());
    Image skeleton = new Image(getClass().getResource("/SKELETON.png").toString());
    Image zombie = new Image(getClass().getResource("/ZOMBIE.png").toString());
    Image wizard = new Image(getClass().getResource("/WIZARD.png").toString());
    Image ghost = new Image(getClass().getResource("/GHOST.png").toString());
    Image orc = new Image(getClass().getResource("/HIGHORC.png").toString());

    Timeline movement = new Timeline(new KeyFrame(Duration.millis(5), ae -> move()));

    int x = 0; int y = 0;
    int pray = 2;
    int lives = 3;
    boolean fight;
    boolean bless;

    @FXML
    void clickBack(ActionEvent event) throws IOException {
        MainApp.setRoot("Gameplay", "Priest's Conquest");
    }

    @FXML
    public void keyPressed(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            x = 5;

        } else if ((event.getCode() == KeyCode.A)) {
            x = -5;

        } else if ((event.getCode() == KeyCode.W)) {
            y = -5;

        } else if ((event.getCode() == KeyCode.S)) {
            y = 5;
        }
    }

    @FXML
    public void keyReleased(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            x = 0;
            y = 0;
        } else if ((event.getCode() == KeyCode.A)) {
            x = 0;
            y = 0;
        } else if ((event.getCode() == KeyCode.W)) {
            x = 0;
            y = 0;
        } else if ((event.getCode() == KeyCode.S)) {
            x = 0;
            y = 0;
        }
    }

    @FXML
    void clickFight(MouseEvent event) {
        boolean fight = true;
        lblMove1.setText("SMITE");//base dmg: 9-12
        lblMove2.setText("HOLY SPEAR");//base dmg: 6-15
        lblMove3.setText("PRAY(2/2)");//heals one of three lives
    }


    @FXML
    void clickBless(MouseEvent event) {
        boolean bless = true;
        lblMove1.setText("JOKE");
        lblMove2.setText("LAY TO REST");
        lblMove3.setText("[LOCKED]");
    }

    @FXML
    void clickMove1(MouseEvent event) {
        if(fight == true){

        }else if(bless = true){

        }
    }

    @FXML
    void clickMove2(MouseEvent event) {

    }

    @FXML
    void clickMove3(MouseEvent event) {

    }

    void move(){
        panPlayer.setTranslateX(panPlayer.getTranslateX() + x);
        panPlayer.setTranslateY(panPlayer.getTranslateY() + y);

        if(collision(plgPlayer, plgBorder)){
            panPlayer.setTranslateX(panPlayer.getTranslateX() - x);
            panPlayer.setTranslateY(panPlayer.getTranslateY() - y);
        }
    }


    void imageSize(double height, double width, double x, double y){
        imgEnemy.setFitHeight(height);
        imgEnemy.setFitWidth(width);

        imgEnemy.setTranslateX(x);
        imgEnemy.setTranslateY(y);
    }

    public boolean collision(Shape block1, Shape block2) {
//If the objects can be changed to shapes just see if they intersect
        Shape a = Shape.intersect(block1, block2);
        return a.getBoundsInLocal().getWidth() != -1;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
        if (MainApp.battleStage == 1) {
            imgEnemy.setImage(zombie);
            imageSize(238,146, 1011, 305);
            lblMessage.setText("A Zombie has appeared! You will...");
        } else if (MainApp.battleStage == 2) {
            imgEnemy.setImage(skeleton);
            imageSize(238,146, 1011, 305);
            lblMessage.setText("A Skeleton has appeared! You will...");
        } else if (MainApp.battleStage == 3) {
            imgEnemy.setImage(ghost);
            imageSize(238,146, 1011, 305);
            lblMessage.setText("A Ghost has appeared! You will...");
        } else if (MainApp.battleStage == 4) {
            imgEnemy.setImage(wolf);
            imageSize(143,142, 1011, 420);
            lblMessage.setText("A Wolf has appeared! You will...");
        } else if (MainApp.battleStage == 5) {
            imgEnemy.setImage(wizard);
            imageSize(161,124, 1011, 420);
            lblMessage.setText("A Wizard has appeared! You will...");
        } else if (MainApp.battleStage == 6) {
            imgEnemy.setImage(orc);
            imageSize(386,343,856,195);
            lblMessage.setText("The High Orc has appeared! You will...");
        }
    }
}
