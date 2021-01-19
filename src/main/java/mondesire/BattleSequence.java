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
import javafx.scene.Node;
import javafx.scene.control.Button;
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


    Image wolf = new Image(getClass().getResource("/WOLF.png").toString());
    Image skeleton = new Image(getClass().getResource("/SKELETON.png").toString());
    Image zombie = new Image(getClass().getResource("/ZOMBIE.png").toString());
    Image wizard = new Image(getClass().getResource("/WIZARD.png").toString());
    Image ghost = new Image(getClass().getResource("/GHOST.png").toString());
    Image orc = new Image(getClass().getResource("/HIGHORC.png").toString());

    Image staff = new Image(getClass().getResource("/staffBuff.png").toString());


    Timeline UI = new Timeline(new KeyFrame(Duration.millis(5), ae -> ui()));
    Timeline pause = new Timeline(new KeyFrame(Duration.millis(1000), ae -> pauseVoid()));

    int health = 100;
    int zombieHealth = 50;

    boolean fight;
    boolean bless;

    int smiteDmg;
    int spearDmg;
    int pray = 2;

    int pauseTimer = 0;

    int zombieAttack;


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
        lblMove1.setText("SMITE");//base dmg: 9-12
        lblMove2.setText("HOLY SPEAR");//base dmg: 6-15
        lblMove3.setText("PRAY("+pray+"/2)");//heals 25 health (Can be used twice per fight)
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
        lblMove3.setText("[LOCKED]");
    }

    void pauseVoid() {
        pauseTimer++;
            if (pauseTimer == 3) {
                zombieAttack();
                pauseTimer = 0;
                pause.stop();
            }
    }

    void zombieAttack() {
        zombieAttack = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        if (zombieAttack == 1) {
            AnimateText(lblMessage, "The Zombie used Scratch!");
            int scratch = ThreadLocalRandom.current().nextInt(10, 15 + 1);
            health = health - scratch;
            lblPlayerHealth.setText("" + health);
            toggleOptions(true, false);
        } else if (zombieAttack == 2) {
            AnimateText(lblMessage, "The Zombie used Bite!");
            int bite = ThreadLocalRandom.current().nextInt(15, 20 + 1);
            health = health - bite;
            lblPlayerHealth.setText("" + health);
            toggleOptions(true, false);
        } else if (zombieAttack == 3) {
            AnimateText(lblMessage, "The Zombie used Lunge!");
            int lunge = ThreadLocalRandom.current().nextInt(5, 20 + 1);
            health = health - lunge;
            lblPlayerHealth.setText("" + health);
            toggleOptions(true, false);
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

        if (fight == true) {
            smiteDmg = ThreadLocalRandom.current().nextInt(10, 14 + 1);
            zombieHealth = zombieHealth - smiteDmg;
            if (zombieHealth <= 0) {
                zombieHealth = 0;
                lblEnemyHealth.setText("" + zombieHealth);
                AnimateText(lblMessage, "You defeated the Zombie!");
                toggleOptions(false, false);
                btnBack.setVisible(true);
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
            if(zombieHealth <= 0) {
                zombieHealth = 0;
                lblEnemyHealth.setText("" + zombieHealth);
                AnimateText(lblMessage, "You defeated the Zombie!");
                toggleOptions(false, false);
                btnBack.setVisible(true);
            }else{
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
                if(health > 100){
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
            if(lblMove3.getText() == "[LOCKED]"){

            }else{
                if(zombieHealth < 0){
                    zombieHealth = 0;
                    lblEnemyHealth.setText("" + zombieHealth);
                    AnimateText(lblMessage, "You defeated the Zombie!");
                    toggleOptions(false, false);
                    btnBack.setVisible(true);
                }else{
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
        UI.setCycleCount(Timeline.INDEFINITE);
        pause.setCycleCount(Timeline.INDEFINITE);
        UI.play();

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

        if (MainApp.healthBuff == true){
            health = 125;
            lblPlayerHealth.setText("" + health);
        }
        if (MainApp.dmgBuff > 0){
            imgStaff.setImage(staff);
        }
        if (MainApp.holyWater == true){

        }

    }
}
