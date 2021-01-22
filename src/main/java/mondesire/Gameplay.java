package mondesire;
/*
Milan Hennessy
2021-01-21
Rpg game
 */

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.Initializable;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;

import java.util.Optional;
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

    @FXML
    private Polygon plgEnemy4;

    @FXML
    private Pane panEnemy4;
    @FXML
    private Pane panEnemy3;
    @FXML
    private Pane panEnemy5;

    @FXML
    private Polygon plgEnemy5;
    @FXML
    private Polygon plgLogs;
    @FXML
    private Polygon plgScareCrow;
    @FXML
    private Polygon plgScareCrow2;

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
    //Custom object used for saving
    username info = new username();
    MediaPlayer player;

    @FXML
    public void keyPressed(KeyEvent event) throws InterruptedException {
//Movement code that also allows user to move diagonally
        //Also uses a disable interact method which stops the user from interacting with things that are not within it's range by making a plgInteract visible
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
        //Interact code for gravestones and runs an animation
        //plg interact is a polygon that turns invisible when you collide with something to detect when you are colliding with it
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
        //Disables the anchorpane to stop the user from being able to push buttons and stop the decision from being recognised
        //Also uses booleans to detect which building is selected for the user to find secrets
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
        }else if ((event.getCode() == KeyCode.E) && buildings == 3 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "Just some logs");

        } else if ((event.getCode() == KeyCode.E) && buildings == 4 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "Just Scary the scarecrow doing his job");

        }  else if ((event.getCode() == KeyCode.E) && buildings == 5 && plgInteract.isVisible() == false) {
            interact();
            animateText(lblLetter1, "Just Crow the scarecrow doing his thing");

        }
        //Allows the user to use the e to close the dialogue box when there are no options or even when there are options
        else if ((event.getCode() == KeyCode.E) && interactGrave == 0 || (event.getCode() == KeyCode.E) && buildings == 0) {
            off();
        }
    }
//Returns back to the main menu
    @FXML
    void clickExit(MouseEvent event) throws IOException {
        player.stop();
        //Alert box warning the player that they will lose unsaved progress
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("You will lose all unsaved progress");
        alert.setContentText("");
        ButtonType buttonTypeOne = new ButtonType("Exit");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            MainApp.setRoot("MainMenu");
        }

    }
//stops the timeline and makes all the dialogue visible while resetting the variables to allow the user to turn it all of pressing e or clicking one of the options
    void interact() {
        ancGame.setDisable(true);
        imgText.setVisible(true);
        timeline.stop();
        lblLetter1.setVisible(true);
        interactGrave = 0;
        buildings = 0;
    }
//Basic key released which just stops the character from moving when you let go of the key
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

//A typewritter animation method that animates the text for all the dialogue
    public void animateText(Label lbl, String message) {
        String content = message;

        final Animation animation = new Transition() {
            {
                //Sets the speed of the animation
                setCycleDuration(Duration.millis(3000));
            }
            //Has to use a interpolate method to be able to know when to start and stop the animation
            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(content.substring(0, n));

            }
        };
        //When certain decisions have to be made it sets what dialogue options pop up when the animation finishes
        //Also regains focus to the scene
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
//Plays the animation
        animation.play();

    }

    private void move() throws IOException {
        //Basic movement for your character
        panPriest.setTranslateX(panPriest.getTranslateX() + dx);
        panPriest.setTranslateY(panPriest.getTranslateY() + dy);

//Basic boundaries to keep the player from going off the screen or through certain objects
        if (collision(plgPriest, plgWall) || collision(plgPriest, plgWall2) || collision(plgPriest, plgInn) || collision(plgPriest, plgWall3) || collision(plgPriest, plgWell) && well == true) {
            panPriest.setTranslateX(panPriest.getTranslateX() - dx);
            panPriest.setTranslateY(panPriest.getTranslateY() - dy);
            dx = 0;
            dy = 0;

        }
        //If you run into an enemy it will send you to the battle screen
        if (collision(plgPriest, plgEnemy1) && MainApp.winCount == 0) {
            MainApp.battleStage = 1;
            player.stop();
            MainApp.setRoot("BattleSequence", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } else if (collision(plgPriest, plgEnemy2) && MainApp.winCount == 1) {
            MainApp.battleStage = 2;
            player.stop();
            MainApp.setRoot("BattleSequence2", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } else if (collision(plgPriest, plgEnemy3) && MainApp.winCount == 2) {
            MainApp.battleStage = 3;
            player.stop();
            MainApp.setRoot("BattleSequence3", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } else if (collision(plgPriest, plgEnemy4) && MainApp.winCount == 3) {
            MainApp.battleStage = 4;
            player.stop();
            MainApp.setRoot("BattleSequence4", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        } else if (collision(plgPriest, plgEnemy5) && MainApp.winCount == 4) {
            MainApp.battleStage = 5;
            player.stop();
            MainApp.setRoot("BattleSequence5", "Priest's Conquest");
            panPriest.setTranslateX(panPriest.getTranslateX() - 10000);
            panPriest.setTranslateY(panPriest.getTranslateY() - 10000);
        }
        //Collisions for the graves which tell the user if they are in range to be able to interact with it
        else if (collision(plgPriest, plgGrave1)) {
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

//Same is applied to other things you can interact with only these ones have dilogue options
        } else if (collision(plgPriest, plgShop)) {
            interactCheck();
            buildings = 1;

            decision = 1;


        } else if (collision(plgPriest, plgWell) && well == false) {
            interactCheck();
            buildings = 2;
            decision = 2;
        }
        //These ones are just to make the world more interactive
        else if (collision(plgPriest, plgLogs)) {
            interactCheck();
            buildings = 3;
        } else if (collision(plgPriest, plgScareCrow)) {
            interactCheck();
            buildings = 4;
        } else if (collision(plgPriest, plgScareCrow2)) {
            interactCheck();
            buildings = 5;
        }
//These are boundaries to stop the player from skipping fights
        else if (collision(plgPriest, plgGate) && MainApp.winCount < 3 || collision(plgPriest, plgGate2) && MainApp.winCount < 4) {
            timeline.stop();
            interactCheck();
            interact();
            animateText(lblLetter1, "I should go and fight the other monsters first");

        }

    }
//This resets all the variables while making the polygon true so the user can't interact with stuff out of range
    void disableInteract() {
        buildings = 0;
        interactGrave = 0;
        decision = 0;
        plgInteract.setVisible(true);
        MainApp.shop = false;
    }
//This does the opposite of the disableinteract method by bouncing you back and making the polygon visible to allow the user to interact with the thing they want
    void interactCheck() {
        panPriest.setTranslateX(panPriest.getTranslateX() - dx);
        panPriest.setTranslateY(panPriest.getTranslateY() - dy);
        plgInteract.setVisible(false);
        dx = 0;
        dy = 0;
    }

    @FXML
    //This is option 1 out of 2 that the user can pick
    void decision1(MouseEvent event) throws IOException {
        //Decision 1 is for the shop so if they click on it, it will take them to the shop
        if (decision == 1) {
            MainApp.x = 985;
            MainApp.y = 278;
            timeline.stop();
            player.stop();
            MainApp.setRoot("Shop", "Priest's Conquest");
            lblOption1.setVisible(false);
            lblOption2.setVisible(false);
            decision = 0;
        }//Decision 2 is just for when they interact with the well
        else if (decision == 2) {
            imgText.setVisible(false);
            timeline.play();
            lblLetter1.setVisible(false);
            lblOption1.setVisible(false);
            decision = 0;
        }
        //Decision 3 is for when they need to save
        else if (decision == 3) {
            //Decision 4 is only used for the animation and does not have any choice the user needs to make
            decision = 4;
            animateText(lblLetter1, "Please select a spot");
            lblOption1.setVisible(false);
            lblOption2.setVisible(false);
            ancGame.setDisable(true);
        }
    }

    @FXML
    //Decision 2 is the second choice for the user. It's mainly the no option which does the same thing as hitting e on the keyboard to close all the dialogue
    void decision2(MouseEvent event) {
        if (decision == 1 || decision == 3) {
            off();
            decision = 0;
        }
    }
//Collision method to recognise which blocks are colliding
    public boolean collision(Shape block1, Shape block2) {
//If the objects can be changed to shapes just see if they intersect
        Shape a = Shape.intersect(block1, block2);
        return a.getBoundsInLocal().getWidth() != -1;
    }
//The off method clears the whole UI which includes everything dialogue wise that can be made invisible and resumes the game
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
//These are all save slots that the user can pick to save there game
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
//This is the save method which just saves all the user's info from there username to there gold to there progress to there items
    void saveInfo() {
        info.setuserName(MainApp.user);
        info.setBitcoin(MainApp.gold);
        info.setWinCounter(MainApp.winCount);
        info.setSaveCounter(MainApp.save);
        info.setDamageBuff(MainApp.dmgBuff);
        info.setHolyBuff(MainApp.holyWater);
        info.setHealthBuff(MainApp.healthBuff);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Plays the timeline while also setting the scene
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        lblUsername.setText(MainApp.user);
        lblLetter1.setTextAlignment(TextAlignment.CENTER);
        lblCurrency.setText("" + MainApp.gold + " x");
        panPriest.setLayoutX(MainApp.x);
        panPriest.setLayoutY(MainApp.y);
        //These are if the user has any items
        if (MainApp.dmgBuff == 10) {
            imgStaff.setVisible(true);
        }
        if (MainApp.holyWater == true) {
            imgHolyWater.setVisible(true);
        }
        if (MainApp.healthBuff == true) {
            imgHealthScroll.setVisible(true);
        }

//This determines where to put the player when they have either entered the shop or beat any enemies
            if (MainApp.winCount >= 1) {
                if (MainApp.shop == true) {
                        panPriest.setLayoutX(MainApp.x);
                        panPriest.setLayoutY(MainApp.y);
                } else {
                    panPriest.setLayoutX(774);
                    panPriest.setLayoutY(478);
                }
                panEnemy1.setVisible(false);
                //This offers the user the ability to save after each fight they win
                if (MainApp.save == 0) {
                    decision = 3;
                    interact();
                    animateText(lblLetter1, "Would you like to save?");
                    ancGame.setDisable(true);
                } else {

                }
            }
            if (MainApp.winCount >= 2) {
                if (MainApp.shop == true) {
                    panPriest.setLayoutX(MainApp.x);
                    panPriest.setLayoutY(MainApp.y);
                } else {
                    panPriest.setLayoutX(464);
                    panPriest.setLayoutY(365);
                }
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
                if (MainApp.shop == true) {
                    panPriest.setLayoutX(MainApp.x);
                    panPriest.setLayoutY(MainApp.y);
                } else {
                    panPriest.setLayoutX(583);
                    panPriest.setLayoutY(222);
                }
                panEnemy3.setVisible(false);
                if (MainApp.save == 0 || MainApp.save == 1 || MainApp.save == 2) {
                    decision = 3;
                    interact();
                    animateText(lblLetter1, "Would you like to save?");
                    ancGame.setDisable(true);
                } else {

                }
            }
            if (MainApp.winCount >= 4) {
                if (MainApp.shop == true) {
                    panPriest.setLayoutX(MainApp.x);
                    panPriest.setLayoutY(MainApp.y);
                } else {
                    panPriest.setLayoutX(270);
                    panPriest.setLayoutY(303);
                }
                panEnemy4.setVisible(false);
                if (MainApp.save == 0 || MainApp.save == 1 || MainApp.save == 2 || MainApp.save == 3) {
                    decision = 3;
                    interact();
                    animateText(lblLetter1, "Would you like to save?");
                    ancGame.setDisable(true);
                } else {

                }
            }
            if (MainApp.winCount >= 5) {
                if (MainApp.shop == true) {
                    panPriest.setLayoutX(MainApp.x);
                    panPriest.setLayoutY(MainApp.y);
                } else {
                    panPriest.setLayoutX(439);
                    panPriest.setLayoutY(570);
                }
                panEnemy5.setVisible(false);
                if (MainApp.save == 0 || MainApp.save == 1 || MainApp.save == 2 || MainApp.save == 3 || MainApp.save == 4) {
                    decision = 3;
                    interact();
                    animateText(lblLetter1, "Would you like to save?");
                    ancGame.setDisable(true);
                } else {

                }
            }
            //An array that just saves time turning of UI
        sLabel[0] = lblSave;
        sLabel[1] = lblSave2;
        sLabel[2] = lblSave3;
        sLabel[3] = lblSave4;
        sLabel[4] = lblSave5;
        sLabel[5] = lblSave6;
        //Music player that plays music depending on what the user picks
        player = new MediaPlayer((new Media(getClass().getResource("/MoogCity.mp3").toString())));
        player.setVolume(0.05);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        if (MainApp.sound == true) {
            player.play();
        } else if (MainApp.sound == false) {
            player.stop();
        }
    }


}
