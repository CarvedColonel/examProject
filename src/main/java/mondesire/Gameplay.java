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
import javafx.scene.control.Button;
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
    private Pane panEnemy1;

    @FXML
    private ImageView imgHealthScroll;

    @FXML
    private ImageView imgStaff;

    @FXML
    private ImageView imgHolyWater;

    @FXML
    private ImageView imgBitcoin;
    @FXML
    private Polygon plgEnemy1;

    @FXML
    private Polygon plgEnemy2;

    @FXML
    private Polygon plgEnemy3;

    @FXML
    private Label lblCurrency;

    @FXML
    private Pane panEnemy2;

    @FXML
    private Label lblSave;

    @FXML
    private Label lblSave2;

    @FXML
    private Label lblSave3;

    @FXML
    private Label lblSave4;

    @FXML
    private Label lblSave5;

    @FXML
    private Label lblSave6;

    @FXML
    private Polygon plgGate;

    @FXML
    private Polygon plgGate2;
    //Array for the labels used for saving and the timeline used for moving
    Label sLabel[] = new Label[6];
    //In try catch due to collision opening up a new scene
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), ae -> {
        try {
            move();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }));
    //Movement Variables
    int dx;
    int dy;
    int interactGrave;
    //Variables for interaction
    int buildings;
    boolean well = false;
    int decision;
    boolean pause;
    //Custom object used for saving
    username info = new username();

    @FXML
    public void keyPressed(KeyEvent event) throws InterruptedException {
//Movement code that also allows user to move diagonally
        //Also uses a disable interact method which stops the user from interacting with things that are not within it's range
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
        //Interact code for gravestones
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

        }
        //Interact code for buildings
        else if ((event.getCode() == KeyCode.E) && buildings == 1 && plgInteract.isVisible() == false) {
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
        }
        //Allows the user to use the e to close the dialogue box when there are no options or even when there are options
        else if ((event.getCode() == KeyCode.E) && interactGrave == 0 || (event.getCode() == KeyCode.E) && buildings == 0) {
            off();
        }
    }

    @FXML
    void clickExit(MouseEvent event) throws IOException {
        MainApp.setRoot("MainMenu");
    }

    void interact() {
        ancGame.setDisable(true);
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
                setCycleDuration(Duration.millis(3000));
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
        } else if (decision == 3) {
            animation.setOnFinished(e -> {
                lblOption1.setVisible(true);
                lblOption2.setVisible(true);
                lblOption1.setText("Yes");
                lblOption2.setText("No");
                ancGame.setDisable(false);
                ancGame.requestFocus();
            });
        } else if (decision == 4) {
            animation.setOnFinished(e -> {
                ancGame.setDisable(false);
                ancGame.requestFocus();
                for (Label visible : sLabel) {
                    visible.setVisible(true);
                }
            });
        } else {
            animation.setOnFinished(e -> {
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


        if (collision(plgPriest, plgWall) || collision(plgPriest, plgWall2) || collision(plgPriest, plgInn) || collision(plgPriest, plgWall3) || collision(plgPriest, plgWell) && well == true || collision(plgPriest, plgGate) && MainApp.winCount < 3) {
            panPriest.setTranslateX(panPriest.getTranslateX() - dx);
            panPriest.setTranslateY(panPriest.getTranslateY() - dy);
            dx = 0;
            dy = 0;

        }
        if (collision(plgPriest, plgEnemy1) && MainApp.winCount == 0) {
            MainApp.battleStage = 1;
            MainApp.setRoot("BattleSequence", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } else if (collision(plgPriest, plgEnemy2) && MainApp.winCount == 1) {
            MainApp.battleStage = 2;
            MainApp.setRoot("BattleSequence2", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } else if (collision(plgPriest, plgEnemy3) && MainApp.winCount == 2) {
            MainApp.battleStage = 3;
            MainApp.setRoot("BattleSequence3", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } else if (collision(plgPriest, plgGrave1)) {
            interactCheck();
            interactGrave = 1;

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
            decision = 0;
        } else if (decision == 3) {
            decision = 4;
            animateText(lblLetter1, "Please select a spot");
            lblOption1.setVisible(false);
            lblOption2.setVisible(false);
            ancGame.setDisable(true);
        }
    }

    @FXML
    void decision2(MouseEvent event) {
        if (decision == 1) {
            off();
            decision = 0;
        } else if (decision == 3) {
            off();
            decision = 0;

        }
    }

    public boolean collision(Shape block1, Shape block2) {
//If the objects can be changed to shapes just see if they intersect
        Shape a = Shape.intersect(block1, block2);
        return a.getBoundsInLocal().getWidth() != -1;
    }

    void off() {
        imgText.setVisible(false);
        lblLetter1.setVisible(false);
        lblOption1.setVisible(false);
        lblOption2.setVisible(false);
        for (Label visible : sLabel) {
            visible.setVisible(false);
        }
        timeline.play();
    }

    @FXML
    void save(MouseEvent event) {
        MainApp.save = MainApp.save + 1;
        saveInfo();
        off();
        info.save("matt.raf", 1);

    }

    @FXML
    void save2(MouseEvent event) {
        MainApp.save = MainApp.save + 1;
        saveInfo();
        off();
        info.save("matt.raf", 2);
    }

    @FXML
    void save3(MouseEvent event) {
        MainApp.save = MainApp.save + 1;
        saveInfo();
        off();
        info.save("matt.raf", 3);
    }

    @FXML
    void save4(MouseEvent event) {
        MainApp.save = MainApp.save + 1;
        saveInfo();
        off();
        info.save("matt.raf", 4);
    }

    @FXML
    void save5(MouseEvent event) {
        MainApp.save = MainApp.save + 1;
        saveInfo();
        off();
        info.save("matt.raf", 5);
    }

    @FXML
    void save6(MouseEvent event) {
        MainApp.save = MainApp.save + 1;
        saveInfo();
        off();
        info.save("matt.raf", 6);
    }

    void saveInfo() {
        info.setuserName(MainApp.user);
        info.setBitcoin(MainApp.gold);
        info.setWinCounter(MainApp.winCount);
        info.setSaveCounter(MainApp.save);
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
        if (MainApp.winCount >= 1) {
            panPriest.setLayoutX(774);
            panPriest.setLayoutY(478);
            panEnemy1.setVisible(false);
            if (MainApp.save == 0) {
                decision = 3;
                interact();
                animateText(lblLetter1, "Would you like to save?");
                ancGame.setDisable(true);
            } else {

            }
        }
        if (MainApp.winCount >= 2) {
            panPriest.setLayoutX(464);
            panPriest.setLayoutY(365);
            panEnemy2.setVisible(false);
            if (MainApp.save == 0 || MainApp.save == 1) {
                decision = 3;
                interact();
                animateText(lblLetter1, "Would you like to save?");
                ancGame.setDisable(true);
            } else {

            }
        }
        if (MainApp.winCount >= 3) {
            panPriest.setLayoutX(583);
            panPriest.setLayoutY(222);
            panEnemy2.setVisible(false);
            if (MainApp.save == 0 || MainApp.save == 1 || MainApp.save == 2) {
                decision = 3;
                interact();
                animateText(lblLetter1, "Would you like to save?");
                ancGame.setDisable(true);
            } else {

            }
        }
        sLabel[0] = lblSave;
        sLabel[1] = lblSave2;
        sLabel[2] = lblSave3;
        sLabel[3] = lblSave4;
        sLabel[4] = lblSave5;
        sLabel[5] = lblSave6;
    }


}
