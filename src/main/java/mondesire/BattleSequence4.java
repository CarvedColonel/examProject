package mondesire;
/*
Aidan Mason-Mondesire
January 18th 2021
The fourth battle sequence of six where you fight the wizard
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class BattleSequence4 implements Initializable {

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

    @FXML
    private ImageView imgStaff;

    @FXML
    private ImageView imgHoly;

    @FXML
    private ImageView imgHealth;

    MediaPlayer battle;
    MediaPlayer victory;

    Image wizard = new Image(getClass().getResource("/WIZARD.png").toString());

    Image staff = new Image(getClass().getResource("/staffBuff.png").toString());
    Image potion = new Image(getClass().getResource("/holyWater.png").toString());
    Image scroll = new Image(getClass().getResource("/healthScroll.png").toString());


    Timeline UI = new Timeline(new KeyFrame(Duration.millis(5), ae -> ui()));
    Timeline pause = new Timeline(new KeyFrame(Duration.millis(1000), ae -> pauseVoid()));

    int health = 100;
    int wizardHealth = 80;

    boolean fight;
    boolean bless;

    int smiteDmg;
    int spearDmg;
    int pray = 2;

    int pauseTimer = 0;

    int wizardAttack;

    int animateLength = 2000;//milliseconds


    @FXML
    void clickBack(ActionEvent event) throws IOException {
        MainApp.setRoot("Gameplay", "Priest's Conquest");
    }


    @FXML
    void clickFight(MouseEvent event) {
        fight = true;
        bless = false;
        lblMove1.setVisible(true);
        lblMove2.setVisible(true);
        lblMove3.setVisible(true);
        lblMove1.setText("SMITE");//base dmg: 10-14
        lblMove2.setText("HOLY SPEAR");//base dmg: 6-20
        lblMove3.setText("PRAY(" + pray + "/2)");//heals 25 health (Can be used twice per fight)
    }


    @FXML
    void clickBless(MouseEvent event) {
        bless = true;
        fight = false;
        lblMove1.setVisible(true);
        lblMove2.setVisible(true);
        lblMove3.setVisible(true);
        lblMove1.setText("JOKE");
        lblMove2.setText("EXORCISE");
        if (MainApp.holyWater == true) {
            lblMove3.setText("Holy Water");
        } else {
            lblMove3.setText("[LOCKED]");
        }
    }

    void pauseVoid() {
        //timer to add some delay and then run the attack code
        pauseTimer++;
        if (pauseTimer == 3) {
            wizardAttack();
            pauseTimer = 0;
            pause.stop();
        }
    }


    void wizardAttack() {

        wizardAttack = ThreadLocalRandom.current().nextInt(1, 3 + 1);

        if (wizardAttack == 1) {

            AnimateText(lblMessage, "The wizard used Fireball!");
            int fire = ThreadLocalRandom.current().nextInt(10, 25 + 1);
            health = health - fire;
            pause.play();
            lblPlayerHealth.setText("" + health);
            toggleOptions(true, false);
            die();

        } else if (wizardAttack == 2) {

            AnimateText(lblMessage, "The wizard used Dark Magic!");
            int magic = ThreadLocalRandom.current().nextInt(10, 19 + 1);
            health = health - magic;
            lblPlayerHealth.setText("" + health);
            toggleOptions(true, false);
            die();

        } else if (wizardAttack == 3) {
            //the wizard as well as the orc(final fight) have a healing move which will grant them extra health if used. they do not have a limit to using this move
            AnimateText(lblMessage, "The wizard used Healing Aura!");
            int heal = ThreadLocalRandom.current().nextInt(5, 15 + 1);
            lblPlayerHealth.setText("" + health);
            wizardHealth = wizardHealth + heal;
            lblEnemyHealth.setText("" + wizardHealth);
            toggleOptions(true, false);

        }
    }

    void die() {

        if (health <= 0) {

            health = 0;
            lblPlayerHealth.setText("" + health);
            toggleOptions(false, false);
            AnimateText(lblMessage, "You Have Died! Returning to checkpoint.");
            btnBack.setVisible(true);

        }
    }

    void win() {

        wizardHealth = 0;
        lblEnemyHealth.setText("" + wizardHealth);
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

    void toggleOptions(boolean tf, boolean all) {

        lblMove1.setVisible(all);
        lblMove2.setVisible(all);
        lblMove3.setVisible(all);
        lblFight.setVisible(tf);
        lblBless.setVisible(tf);

    }


    @FXML
    void clickMove1(MouseEvent event) {


        animateLength = 2000;

        if (fight == true) {

            smiteDmg = ThreadLocalRandom.current().nextInt(10, 15 + 1);
            wizardHealth = wizardHealth - (smiteDmg + MainApp.dmgBuff);

            if (wizardHealth <= 0) {
                win();
            } else {

                lblEnemyHealth.setText("" + wizardHealth);
                AnimateText(lblMessage, "You did " + (smiteDmg + MainApp.dmgBuff) + " damage to the wizard!");
                pause.play();
                toggleOptions(false, false);
            }

        } else if (bless = true) {

            animateLength = 3000;
            AnimateText(lblMessage, "'This is no time for jokes'" + "\n" + "The wizard exclaims.");
            pause.play();
            toggleOptions(false, false);

        }
    }

    @FXML
    void clickMove2(MouseEvent event) {
//if they chose to fight, then use the holy spear move that does 6-20 damage, run the animations, and toggle the UI. if they choose bless nothing (wizards can't be exorcised)

        animateLength = 2000;

        if (fight == true) {
            spearDmg = ThreadLocalRandom.current().nextInt(10, 20 + 1);
            wizardHealth = wizardHealth - (spearDmg + MainApp.dmgBuff);
            if (wizardHealth <= 0) {
                win();
            } else {
                lblEnemyHealth.setText("" + wizardHealth);
                AnimateText(lblMessage, "You did " + (spearDmg + MainApp.dmgBuff) + " damage to the wizard!");
                pause.play();
                toggleOptions(false, false);
            }

        } else if (bless = true) {

            animateLength = 3000;
            AnimateText(lblMessage, "Exorcisms don't work" + "\n" + "on wizards");
            pause.play();
            toggleOptions(false, false);

        }
    }

    @FXML
    void clickMove3(MouseEvent event) {

        animateLength = 2000;

        System.out.println(MainApp.dmgBuff);
        int maxHealth;
//if they chose to fight, then use the Pray move that heals you for 25 health, run the animations, and toggle the UI. if bless and they have it unlocked, do 50 damage
        if (fight == true) {

            if (MainApp.healthBuff == true) {
                maxHealth = 125;
            } else {
                maxHealth = 100;
            }

            if ((pray > 0) && ((health < maxHealth))) {
                pray--;
                health = health + 25;
                if (MainApp.healthBuff == true) {
                    if (health > maxHealth) {
                        health = maxHealth;
                    }
                }
                lblPlayerHealth.setText("" + health);
                AnimateText(lblMessage, "You healed 25 health!");
                pause.play();
                toggleOptions(false, false);
            } else {
                AnimateText(lblMessage, "Can't perform that action");
            }

        } else if (bless = true) {
            if (lblMove3.getText() == "[LOCKED]") {

            } else {
                wizardHealth = wizardHealth - (50 + MainApp.dmgBuff);
                lblEnemyHealth.setText("" + wizardHealth);
                if (wizardHealth <= 0) {
                    win();
                } else {
                    wizardHealth = wizardHealth - (50 + MainApp.dmgBuff);
                    lblEnemyHealth.setText("" + wizardHealth);
                    AnimateText(lblMessage, "You did 50 damage to the wizard!");
                    pause.play();
                    toggleOptions(false, false);
                }
                MainApp.holyWater = false;

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
                setCycleDuration(Duration.millis(animateLength));
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
            lblInfo.textProperty().bind(Bindings.when(lblMove1.hoverProperty()).then("10-15dmg").otherwise(""));
            lblInfo2.textProperty().bind(Bindings.when(lblMove2.hoverProperty()).then("10-20dmg").otherwise(""));
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
        UI.setCycleCount(Timeline.INDEFINITE);
        pause.setCycleCount(Timeline.INDEFINITE);
        UI.play();

        lblEnemyHealth.setText("" + wizardHealth);

        battle = new MediaPlayer((new Media(getClass().getResource("/BattleMusic.mp3").toString())));
        victory = new MediaPlayer((new Media(getClass().getResource("/WinMusic.mp3").toString())));

        battle.setVolume(25);
        victory.setVolume(25);

        if (MainApp.sound == true) {
            battle.play();
        }

        if (MainApp.battleStage == 4) {
            imgEnemy.setImage(wizard);
            imageSize(161, 124, 1011, 420);
            AnimateText(lblMessage, "A Wizard has appeared! You will...");
        }

        if (MainApp.healthBuff == true) {
            health = 125;
            lblPlayerHealth.setText("" + health);
            imgHealth.setImage(scroll);
        }
        if (MainApp.dmgBuff > 0) {
            imgStaff.setImage(staff);
        }
        if (MainApp.holyWater == true) {
            imgHoly.setImage(potion);
        }
    }
}
