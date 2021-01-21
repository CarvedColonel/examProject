package mondesire;
/*
Aidan Mason-Mondesire
January 18th 2021
The first battle sequence of six where you fight the zombie
 */


/*

                                                        DISCLAIMER!!!
    A LOT OF CODE GETS REPEATED BETWEEN SCENES SO SOME OF IT WON'T BE COMMENTED DUE TO IT BEING COMMENTED ELSEWHERE IN THE CODE

 */


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private Label lblPlayerHealth;

    @FXML
    private Label lblEnemyHealth;

    @FXML
    private Label lblFight;

    @FXML
    private Label lblBless;

    @FXML
    private Button btnBack;

    //imports for music
    MediaPlayer battle;
    MediaPlayer victory;

    //importing zombie image
    Image zombie = new Image(getClass().getResource("/ZOMBIE.png").toString());

    //the timer for the animation of the text and the delay before the enemy attacks
    Timeline UI = new Timeline(new KeyFrame(Duration.millis(5), ae -> ui()));
    Timeline pause = new Timeline(new KeyFrame(Duration.millis(1000), ae -> pauseVoid()));

    //player and enemy health
    int health = 100;
    int zombieHealth = 50;

    //whether you are attempting to fight the enemy or find a cheeky way out of the fight
    boolean fight;
    boolean bless;

    //variables for your abilities damage
    int smiteDmg;
    int spearDmg;
    int pray = 2;

    //timer for seconds
    int pauseTimer = 0;

    //enemy's attack damage
    int zombieAttack;


    @FXML
    void clickBack(ActionEvent event) throws IOException {
        //puts you back to the main menu and stops the music
        MainApp.setRoot("Gameplay", "Priest's Conquest");
        if (MainApp.sound == true) {
            victory.stop();
        }
    }

    void die() {
        //if your health goes below zero, set the health bar back to empty, turn off all the labels, allow the return to map, and stop the music
        if (health <= 0) {

            health = 0;
            lblPlayerHealth.setText("" + health);
            toggleOptions(false, false);
            AnimateText(lblMessage, "You Have Died! Returning to checkpoint.");
            btnBack.setVisible(true);

            if (MainApp.sound == true) {
                battle.stop();
            }

        }
    }

    void win() {
        //if you win, set the enemy's health to zero, add one to wincount so we know player's progress, toggle the labels, allow them to return to map, add gold, and stop the music
        zombieHealth = 0;
        lblEnemyHealth.setText("" + zombieHealth);
        AnimateText(lblMessage, "You defeated the Zombie!");
        MainApp.winCount = 1;
        toggleOptions(false, false);
        btnBack.setVisible(true);
        MainApp.gold = MainApp.gold + 10;

        if (MainApp.sound == true) {
            battle.stop();
            victory.play();
        }
    }

    @FXML
    void clickFight(MouseEvent event) {
        //set the variables so that fight is the stance they're taking and bring up the moves
        fight = true;
        bless = false;
        lblMove1.setVisible(true);
        lblMove2.setVisible(true);
        lblMove3.setVisible(true);
        lblMove1.setText("SMITE");//base dmg: 9-12
        lblMove2.setText("HOLY SPEAR");//base dmg: 6-20
        lblMove3.setText("PRAY(" + pray + "/2)");//heals 25 health (Can be used twice per fight)
    }


    @FXML
    void clickBless(MouseEvent event) {
        //set the variables so that bless is the stance they're taking and bring up the moves
        bless = true;
        fight = false;
        lblMove1.setVisible(true);
        lblMove2.setVisible(true);
        lblMove3.setVisible(true);
        lblMove1.setText("JOKE");
        lblMove2.setText("EXORCISE");
        lblMove3.setText("[LOCKED]");
    }

    void pauseVoid() {
        //when the timer is running, count up by 1. When it hits 3 seconds let the enemy make their move and reset the timer.
        pauseTimer++;
        if (pauseTimer == 3) {
            zombieAttack();
            pauseTimer = 0;
            pause.stop();
        }
    }

    void zombieAttack() {
        //roll rng to see which attack the enemy uses, then use the rng of said move to determine how much damage it does. Toggle the players move options and check if they've died
        zombieAttack = ThreadLocalRandom.current().nextInt(1, 3 + 1);

        if (zombieAttack == 1) {

            AnimateText(lblMessage, "The Zombie used Scratch!");
            int scratch = ThreadLocalRandom.current().nextInt(10, 15 + 1);
            health = health - scratch;
            lblPlayerHealth.setText("" + health);
            toggleOptions(true, false);
            die();

        } else if (zombieAttack == 2) {

            AnimateText(lblMessage, "The Zombie used Bite!");
            int bite = ThreadLocalRandom.current().nextInt(15, 20 + 1);
            health = health - bite;
            lblPlayerHealth.setText("" + health);
            toggleOptions(true, false);
            die();

        } else if (zombieAttack == 3) {

            AnimateText(lblMessage, "The Zombie used Lunge!");
            int lunge = ThreadLocalRandom.current().nextInt(5, 20 + 1);
            health = health - lunge;
            lblPlayerHealth.setText("" + health);
            toggleOptions(true, false);
            die();
        }
    }

    void toggleOptions(boolean tf, boolean all) {
        //toggles the options based on what you set the variables. tf will set the two main options, and all changes the moves that come up after clicking the two main options
        lblMove1.setVisible(all);
        lblMove2.setVisible(all);
        lblMove3.setVisible(all);
        lblFight.setVisible(tf);
        lblBless.setVisible(tf);

    }


    @FXML
    void clickMove1(MouseEvent event) {
        //if they chose to fight, then use smite move that does 10-14 damage, run the animations, and toggle the UI. if they choose bless nothing (zombies dont understand jokes)
        //if the zombie dies run win code, if not then subtract the health and play the zombies move
        if (fight == true) {

            smiteDmg = ThreadLocalRandom.current().nextInt(10, 14 + 1);
            zombieHealth = zombieHealth - smiteDmg;

            if (zombieHealth <= 0) {

                win();

            } else {

                lblEnemyHealth.setText("" + zombieHealth);
                AnimateText(lblMessage, "You did " + smiteDmg + " damage to the Zombie!");
                pause.play();

                toggleOptions(false, false);

            }

        } else if (bless = true) {

            AnimateText(lblMessage, "The Zombie didn't understand the joke...");
            toggleOptions(true, false);
        }
    }

    @FXML
    void clickMove2(MouseEvent event) {
//if they chose to fight, then use the holy spear move that does 6-20 damage, run the animations, and toggle the UI. if they choose bless nothing (zombies can't be exorcised)
        if (fight == true) {

            spearDmg = ThreadLocalRandom.current().nextInt(6, 20 + 1);
            zombieHealth = zombieHealth - spearDmg;

            if (zombieHealth <= 0) {

                win();

            } else {

                lblEnemyHealth.setText("" + zombieHealth);
                AnimateText(lblMessage, "You did " + spearDmg + " damage to the Zombie!");
                pause.play();
                toggleOptions(false, false);

            }


        } else if (bless = true) {
            AnimateText(lblMessage, "Zombies can't be exorcised...");
            pause.play();
            toggleOptions(false, false);
        }
    }

    @FXML
    void clickMove3(MouseEvent event) {
//if they chose to fight, then use the Pray move that heals you for 25 health, run the animations, and toggle the UI. if bless and they have it unlocked, do 50 damage
        if (fight == true) {
            if ((pray > 0) && ((health < 100))) {
                pray--;
                health = health + 25;

                if (health > 100) {
                    health = 100;
                }

                lblPlayerHealth.setText("" + health);
                AnimateText(lblMessage, "You healed 25 health!");
                pause.play();

                toggleOptions(false, false);

            } else {
                lblMessage.setText("Can't perform that action");
            }

        } else if (bless = true) {
            if (lblMove3.getText() == "[LOCKED]") {
//do nothing
            } else {
                if (zombieHealth < 0) {

                    win();

                } else {

                    zombieHealth = zombieHealth - 50;
                    lblEnemyHealth.setText("" + zombieHealth);
                    AnimateText(lblMessage, "You did 50 damage to the Zombie!");
                    pause.play();
                    toggleOptions(false, false);
                }
            }
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
        //these are code for hovering over the options, when you hover over an option text with a description will pop up
        if (fight == true) {

            changePrompt(true, false);

            lblInfo.textProperty().bind(Bindings.when(lblMove1.hoverProperty()).then("10-14dmg").otherwise(""));
            lblInfo2.textProperty().bind(Bindings.when(lblMove2.hoverProperty()).then("6-20dmg").otherwise(""));
            lblInfo3.textProperty().bind(Bindings.when(lblMove3.hoverProperty()).then("Heal 25 Health " + "\n" + " (Uses Charge)").otherwise(""));

        } else if (fight == false) {

            changePrompt(false, true);

            lblBInfo.textProperty().bind(Bindings.when(lblMove1.hoverProperty()).then("Tell a joke, maybe they'll like it").otherwise(""));
            lblBInfo2.textProperty().bind(Bindings.when(lblMove2.hoverProperty()).then("Some enemies may be susceptible to an exorcism").otherwise(""));

            if (MainApp.holyWater == true) {

                lblBInfo3.textProperty().bind(Bindings.when(lblMove3.hoverProperty()).then("Does 50 Damage flat to your enemy (1 USE)").otherwise(""));

            } else {

                lblBInfo3.textProperty().bind(Bindings.when(lblMove3.hoverProperty()).then("[LOCKED]").otherwise(""));

            }
        }
    }

    void imageSize(double height, double width, double x, double y) {
        //sets height and width
        imgEnemy.setFitHeight(height);
        imgEnemy.setFitWidth(width);

        imgEnemy.setTranslateX(x);
        imgEnemy.setTranslateY(y);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //declaring what files battle and victory music are set to
        battle = new MediaPlayer((new Media(getClass().getResource("/BattleMusic.mp3").toString())));
        victory = new MediaPlayer((new Media(getClass().getResource("/WinMusic.mp3").toString())));
        //sets the music volume
        battle.setVolume(25);
        victory.setVolume(25);
        //if sound is on then play the music
        if (MainApp.sound == true) {
            battle.play();
        }
        //set the clycle count of the timers
        UI.setCycleCount(Timeline.INDEFINITE);
        pause.setCycleCount(Timeline.INDEFINITE);
        UI.play();
        //set the image to be the zombie, set the height,width, and position, and show the prompt text
        if (MainApp.battleStage == 1) {
            imgEnemy.setImage(zombie);
            imageSize(238, 146, 1011, 305);
            AnimateText(lblMessage, "A Zombie has appeared! You will...");
        }


    }
}
