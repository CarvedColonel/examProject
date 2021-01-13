package mondesire;
/*
Put header here


 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
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
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class BattleSequence implements Initializable {

    @FXML
    private ImageView imgPlayer;

    @FXML
    private ImageView imgEnemy;

    @FXML
    private Polygon plgBorder;

    @FXML
    private Pane panPlayer;

    @FXML
    private Polygon plgPlayer;

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

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblInfo2;

    @FXML
    private Label lblInfo3;

    @FXML
    private Label lblBInfo;

    @FXML
    private Label lblBInfo2;

    @FXML
    private Label lblBInfo3;

    @FXML
    private Pane panArm1;

    @FXML
    private Pane panArm2;

    @FXML
    private Pane panArm3;

    @FXML
    private Pane panArm4;

    @FXML
    private Polyline plgArm1;

    @FXML
    private Polyline plgArm2;

    @FXML
    private Polyline plgArm3;

    @FXML
    private Polyline plgArm4;


    Image wolf = new Image(getClass().getResource("/WOLF.png").toString());
    Image skeleton = new Image(getClass().getResource("/SKELETON.png").toString());
    Image zombie = new Image(getClass().getResource("/ZOMBIE.png").toString());
    Image wizard = new Image(getClass().getResource("/WIZARD.png").toString());
    Image ghost = new Image(getClass().getResource("/GHOST.png").toString());
    Image orc = new Image(getClass().getResource("/HIGHORC.png").toString());

    Timeline movement = new Timeline(new KeyFrame(Duration.millis(20), ae -> move()));

    Timeline UI = new Timeline(new KeyFrame(Duration.millis(5), ae -> ui()));

    int x = 0;
    int y = 0;
    int lives = 3;
    boolean fight;
    boolean bless;

    int smiteDmg;
    int spearDmg;
    int pray = 2;


    Shape projectile[] = new Shape[6];


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
        fight = true;
        bless = false;
        lblMove1.setVisible(true);
        lblMove2.setVisible(true);
        lblMove3.setVisible(true);
        lblMove1.setText("SMITE");//base dmg: 9-12
        lblMove2.setText("HOLY SPEAR");//base dmg: 6-15
        lblMove3.setText("PRAY(2/2)");//heals one of three lives
    }


    @FXML
    void clickBless(MouseEvent event) {
        bless = true;
        fight = false;
        lblMove1.setVisible(true);
        lblMove2.setVisible(true);
        lblMove3.setVisible(true);
        lblMove1.setText("JOKE");
        lblMove2.setText("LAY TO REST");
        lblMove3.setText("[LOCKED]");
    }

    @FXML
    void clickMove1(MouseEvent event) {
        if (fight == true) {
            smiteDmg = ThreadLocalRandom.current().nextInt(9, 12 + 1);
            movement.play();
        } else if (bless = true) {

        }
    }

    @FXML
    void clickMove2(MouseEvent event) {
        if (fight == true) {
            spearDmg = ThreadLocalRandom.current().nextInt(6, 15 + 1);
            movement.play();
        } else if (bless = true) {

        }
    }

    @FXML
    void clickMove3(MouseEvent event) {
        if (fight == true) {
            if ((pray > 0) || ((lives < 3))) {
                pray--;
                lives++;
                movement.play();
            } else {
                lblMessage.setText("Can't perform that action");
            }

        } else if (bless = true) {

        }
    }

    void resetArms(int arm) {
        if (arm == 1) {
            int armspeed1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            panArm1.setTranslateX(panArm1.getTranslateX() - armspeed1);
            panArm1.setTranslateX(704);
            panArm1.setTranslateY(301);
        } else if (arm == 2) {
            int armspeed2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            panArm2.setTranslateX(panArm2.getTranslateX() - armspeed2);
            panArm2.setTranslateX(704);
            panArm2.setTranslateY(301);
        } else if (arm == 3) {
            int armspeed3 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            panArm3.setTranslateX(panArm3.getTranslateX() + armspeed3);
            panArm3.setTranslateX(317);
            panArm3.setTranslateY(81);
        } else if (arm == 4) {
            int armspeed4 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            panArm4.setTranslateX(panArm4.getTranslateX() + armspeed4);
            panArm4.setTranslateX(317);
            panArm4.setTranslateY(81);
        }


    }

    void moveArm() {
        int armspeed1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        int armspeed2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        int armspeed3 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        int armspeed4 = ThreadLocalRandom.current().nextInt(1, 10 + 1);

        panArm1.setTranslateX(panArm2.getTranslateX() - armspeed1);
        panArm2.setTranslateX(panArm2.getTranslateX() - armspeed2);
        panArm3.setTranslateX(panArm3.getTranslateX() + armspeed3);
        panArm4.setTranslateX(panArm4.getTranslateX() + armspeed4);

    }


    void move() {
        //timer for movement and battle sequence
        panPlayer.setTranslateX(panPlayer.getTranslateX() + x);
        panPlayer.setTranslateY(panPlayer.getTranslateY() + y);

        moveArm();

        if (collision(plgPlayer, plgBorder)) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() - x);
            panPlayer.setTranslateY(panPlayer.getTranslateY() - y);
        }

        if ((collision(plgPlayer, plgArm1)) || (collision(plgPlayer, plgArm2)) || (collision(plgPlayer, plgArm3)) || (collision(plgPlayer, plgArm4))) {

            movement.stop();

            resetArms(1);resetArms(2);resetArms(3);resetArms(4);

            AnimateText(lblMessage, "YOU'VE LOST A LIFE, WHAT WILL YOU DO?");

            panPlayer.setTranslateX(panPlayer.getTranslateX() - x);
            panPlayer.setTranslateY(panPlayer.getTranslateY() - y);
        }

        if(panArm1.getLayoutX() == -387){
            resetArms(1);
        }else if(panArm2.getLayoutX() == -387){
            resetArms(2);
        }else if(panArm2.getLayoutX() == 387){
            resetArms(3);
        }else if(panArm2.getLayoutX() == 387){
            resetArms(4);
        }


    }

    void changePrompt(boolean fight, boolean bless) {
        //changes the state of the two groups of labels
        lblInfo.setVisible(fight);
        lblInfo2.setVisible(fight);
        lblInfo3.setVisible(fight);
        lblBInfo.setVisible(bless);
        lblBInfo2.setVisible(bless);
        lblBInfo3.setVisible(bless);
    }

    public void AnimateText(Label lbl, String descImp) {
        String content = descImp;
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(content.substring(0, n));
            }
        };
        animation.play();

    }

    void ui() {
        //timer for the Interface stuff
        if (fight == true) {
            changePrompt(true, false);
            lblInfo.textProperty().bind(Bindings.when(lblMove1.hoverProperty()).then("9-12dmg").otherwise(""));
            lblInfo2.textProperty().bind(Bindings.when(lblMove2.hoverProperty()).then("6-15dmg").otherwise(""));
            lblInfo3.textProperty().bind(Bindings.when(lblMove3.hoverProperty()).then("Heal 1 Health " + "\n" + " (Uses Turn)").otherwise(""));
        } else if (fight == false) {
            changePrompt(false, true);
            lblBInfo.textProperty().bind(Bindings.when(lblMove1.hoverProperty()).then("Tell a joke, maybe they'll like it").otherwise(""));
            lblBInfo2.textProperty().bind(Bindings.when(lblMove2.hoverProperty()).then("Some enemies may be susceptible to an exorcism").otherwise(""));
            if (MainApp.holyWater == true) {
                lblBInfo3.textProperty().bind(Bindings.when(lblMove3.hoverProperty()).then("Automatically Exorcises 1 enemy " + "\n" + "(excludes boss types)").otherwise(""));
            } else {
                lblBInfo3.textProperty().bind(Bindings.when(lblMove3.hoverProperty()).then("[LOCKED]").otherwise(""));

            }
        }
    }

    void imageSize(double height, double width, double x, double y) {
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
        UI.setCycleCount(Timeline.INDEFINITE);
        UI.play();
        movement.setCycleCount(Timeline.INDEFINITE);

        projectile[0] = plgArm1;
        projectile[1] = plgArm2;
        projectile[2] = plgArm3;
        projectile[3] = plgArm4;


        if (MainApp.battleStage == 1) {
            imgEnemy.setImage(zombie);
            imageSize(238, 146, 1011, 305);
            AnimateText(lblMessage, "A Zombie has appeared! You will...");
        } else if (MainApp.battleStage == 2) {
            imgEnemy.setImage(skeleton);
            imageSize(238, 146, 1011, 305);
            AnimateText(lblMessage, "A Skeleton has appeared! You will...");
        } else if (MainApp.battleStage == 3) {
            imgEnemy.setImage(ghost);
            imageSize(238, 146, 1011, 305);
            AnimateText(lblMessage, "A Ghost has appeared! You will...");
        } else if (MainApp.battleStage == 4) {
            imgEnemy.setImage(wolf);
            imageSize(143, 142, 1011, 420);
            AnimateText(lblMessage, "A Wolf has appeared! You will...");
        } else if (MainApp.battleStage == 5) {
            imgEnemy.setImage(wizard);
            imageSize(161, 124, 1011, 420);
            AnimateText(lblMessage, "A Wizard has appeared! You will...");
        } else if (MainApp.battleStage == 6) {
            imgEnemy.setImage(orc);
            imageSize(386, 343, 856, 195);
            AnimateText(lblMessage, "The High Orc has appeared! You will...");
        }
    }
}
