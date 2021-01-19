package mondesire;
/*
Put header here


 */

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Gameplay implements Initializable {

    @FXML
    private AnchorPane ancGame;
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

    @FXML
    private Polygon plgGrave1;

    @FXML
    private Polygon plgGrave2;


    @FXML
    private Label lblUsername;
    @FXML
    private ImageView imgText;


    @FXML
    private Label lblLetter1;


    @FXML
    private Polygon plgShop;
    @FXML
    private Label lblOption1;

    @FXML
    private Label lblOption2;

    @FXML
    private Polygon plgInteract;

    @FXML
    private Polygon plgGrave3;

    @FXML
    private Polygon plgGrave4;

    @FXML
    private Polygon plgGrave5;

    @FXML
    private Polygon plgGrave6;


    @FXML
    private ImageView imgHealthScroll;

    @FXML
    private ImageView imgStaff;

    @FXML
    private ImageView imgHolyWater;

    @FXML
    private ImageView imgBitcoin;

    @FXML
    private Label lblCurrency;
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), ae -> {
        try {
            move();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }));
    int dx;
    int dy;
    int interactGrave;
    int buildings;
    boolean well = false;
    int decision;

    @FXML
    public void keyPressed(KeyEvent event) throws InterruptedException {

        if ((event.getCode() == KeyCode.D)) {
            dx = 5;
            disableInteract();
        } else if ((event.getCode() == KeyCode.A)) {
            dx = -5;
            disableInteract();
        } else if ((event.getCode() == KeyCode.W)) {
            dy = -5;
            disableInteract();
        } else if ((event.getCode() == KeyCode.S)) {
            dy = 5;
            disableInteract();
        } else if ((event.getCode() == KeyCode.P)) {
            MainApp.gold = MainApp.gold + 1;
            lblCurrency.setText("" + MainApp.gold + " x");
        }
        //Interact code
        else if ((event.getCode() == KeyCode.E) && interactGrave == 1 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "'R.I.P' \n 'Aidan Mason-Mondesire' \n 'Died programming the battle sequence'");

        } else if ((event.getCode() == KeyCode.E) && interactGrave == 2 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "'R.I.P' \n 'Milan Hennessy' \n 'Died creating the map'");

        } else if ((event.getCode() == KeyCode.E) && interactGrave == 3 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "'R.I.P' \n 'Max Hepner' \n 'Died from lack of sleep'");

        } else if ((event.getCode() == KeyCode.E) && interactGrave == 4 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "'R.I.P' \n 'The 8 backgrounds' \n 'Never made it into the game'");

        } else if ((event.getCode() == KeyCode.E) && interactGrave == 5 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "'R.I.P' \n 'Plague Doctor' \n 'Aidan said no'");

        } else if ((event.getCode() == KeyCode.E) && interactGrave == 6 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "'R.I.P' \n 'A normal shop' \n 'Rick Harrison was better'");

        } else if ((event.getCode() == KeyCode.E) && buildings == 1 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "Would you like to enter my shop?");
            ancGame.setDisable(true);
        } else if ((event.getCode() == KeyCode.E) && buildings == 2 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "You found 5 coins in the well");
            MainApp.gold = MainApp.gold + 5;
            lblCurrency.setText("" + MainApp.gold + " x");
            ancGame.setDisable(true);
            well = true;
        } else if ((event.getCode() == KeyCode.E) && interactGrave == 0 || (event.getCode() == KeyCode.E) && buildings == 0) {
            imgText.setVisible(false);
            timeline.play();
            lblLetter1.setVisible(false);
            lblOption1.setVisible(false);
            lblOption2.setVisible(false);

        }
    }

    void interact() {
        imgText.setVisible(true);
        timeline.stop();
        lblLetter1.setVisible(true);
        interactGrave = 0;
        buildings = 0;
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


    public void animateText(Label lbl, String message) {
        String content = message;

        final Animation animation = new Transition() {
            {
                if (interactGrave > 0) {

                    setCycleDuration(Duration.millis(5000));
                } else {

                    setCycleDuration(Duration.millis(3000));
                }

            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(content.substring(0, n));

            }
        };
        if (decision == 1) {
            animation.setOnFinished(e -> {
                lblOption1.setVisible(true);
                lblOption2.setVisible(true);
                lblOption1.setText("Yes");
                lblOption2.setText("No");
                ancGame.setDisable(false);
                ancGame.requestFocus();
            });
        } else if (decision == 2) {
            animation.setOnFinished(e -> {
                lblOption1.setVisible(true);
                lblOption1.setText("Great!");
                ancGame.setDisable(false);
                ancGame.requestFocus();
            });
        }

        animation.play();

    }

    private void move() throws IOException {
        //Basic movement for all characters
        panPriest.setTranslateX(panPriest.getTranslateX() + dx);
        panPriest.setTranslateY(panPriest.getTranslateY() + dy);


        if (collision(plgPriest, plgWall) || collision(plgPriest, plgWall2) || collision(plgPriest, plgInn) || collision(plgPriest, plgWall3) || collision(plgPriest, plgWell) && well == true) {
            panPriest.setTranslateX(panPriest.getTranslateX() - dx);
            panPriest.setTranslateY(panPriest.getTranslateY() - dy);
            dx = 0;
            dy = 0;

        }/* else if (collision(plgPriest, plgEnemy1)) {
            MainApp.battleStage = 1;
            MainApp.setRoot("BattleSequence", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } else if (collision(plgPriest, plgEnemy2)) {
            MainApp.battleStage = 2;
            MainApp.setRoot("BattleSequence", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } */ else if (collision(plgPriest, plgGrave1)) {
            interactCheck();
            interactGrave = 1;
decision = 3;

        } else if (collision(plgPriest, plgGrave2)) {
            interactCheck();
            interactGrave = 2;


        } else if (collision(plgPriest, plgGrave3)) {
            interactCheck();
            interactGrave = 3;


        } else if (collision(plgPriest, plgGrave4)) {
            interactCheck();
            interactGrave = 4;


        } else if (collision(plgPriest, plgGrave5)) {
            interactCheck();
            interactGrave = 5;


        } else if (collision(plgPriest, plgGrave6)) {
            interactCheck();
            interactGrave = 6;


        } else if (collision(plgPriest, plgShop)) {
            interactCheck();
            buildings = 1;

            decision = 1;


        } else if (collision(plgPriest, plgWell) && well == false) {
            interactCheck();
            buildings = 2;
decision = 2;
        }

    }

    void disableInteract() {
        buildings = 0;
        interactGrave = 0;
        decision = 0;
        plgInteract.setVisible(true);
    }

    void interactCheck() {
        panPriest.setTranslateX(panPriest.getTranslateX() - dx);
        panPriest.setTranslateY(panPriest.getTranslateY() - dy);
        plgInteract.setVisible(false);
        dx = 0;
        dy = 0;
    }

    @FXML
    void decision1(MouseEvent event) throws IOException {
        if (decision == 1) {
            MainApp.x = 985;
            MainApp.y = 278;
            timeline.stop();
            MainApp.setRoot("Shop", "Priest's Conquest");
            lblOption1.setVisible(false);
            lblOption2.setVisible(false);
            decision = 0;
        } else if (decision == 2) {
            imgText.setVisible(false);
            timeline.play();
            lblLetter1.setVisible(false);
            lblOption1.setVisible(false);
        }
    }

    @FXML
    void decision2(MouseEvent event) {
        if (decision == 1) {
            imgText.setVisible(false);
            timeline.play();
            lblLetter1.setVisible(false);
            lblOption1.setVisible(false);
            lblOption2.setVisible(false);
            decision = 0;
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
        lblUsername.setText(MainApp.user);
        lblLetter1.setTextAlignment(TextAlignment.CENTER);
        lblCurrency.setText("" + MainApp.gold + " x");
        panPriest.setLayoutX(MainApp.x);
        panPriest.setLayoutY(MainApp.y);
        if (MainApp.dmgBuff == 10) {
            imgStaff.setVisible(true);
        }
        if (MainApp.holyWater == true) {
            imgHolyWater.setVisible(true);
        }
        if (MainApp.healthBuff == true) {
            imgHealthScroll.setVisible(true);
        }
    }
}
