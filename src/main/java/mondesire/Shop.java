package mondesire;
/*
Put header here


 */

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Shop<NFANode> implements Initializable {

    @FXML
    private ImageView imgShopKeeper;

    @FXML
    private Label lblShopText;

    @FXML
    private ImageView imgHat;

    @FXML
    private ImageView imgStaff;

    @FXML
    private Label lblBuy;

    @FXML
    private Label lblLeave;

    @FXML
    private Label lblItem1;

    @FXML
    private Label lblInfo;
    @FXML
    private Label lblPrice;

    @FXML
    private Label lblItem2;

    @FXML
    private ImageView imgHolyWater;

    @FXML
    private Label lblPrice2;
    @FXML
    private Label lblItem3;

    @FXML
    private ImageView imgHealthScroll;

    @FXML
    private Label lblPrice3;
    @FXML
    private ImageView imgBitcoin;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblCurrency;
    Label text[] = new Label[6];

    public void animateText(Label lbl, String message) {

        String content = message;
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(5000));
            }

            public void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(content.substring(0, n));

            }

        };
        animation.setOnFinished(e -> {
            lblBuy.setVisible(true);
            lblLeave.setVisible(true);
        });
        animation.play();

    }

    @FXML
    void buy(MouseEvent event) {
        lblShopText.setVisible(false);
        lblLeave.setVisible(false);
        lblBuy.setVisible(false);
        imgStaff.setVisible(true);
        imgHolyWater.setVisible(true);
        imgHealthScroll.setVisible(true);
        lblBack.setVisible(true);
        for (Label visible : text) {
        visible.setVisible(true);
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5), ae -> check()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        if (MainApp.dmgBuff == 10) {
            imgStaff.setVisible(false);
            lblPrice.setVisible(false);
            lblItem1.setVisible(false);
        }
        if (MainApp.holyWater == true) {
            imgHolyWater.setVisible(false);
            lblPrice2.setVisible(false);
            lblItem2.setVisible(false);
        }
        if (MainApp.healthBuff == true) {
            imgHealthScroll.setVisible(false);
            lblPrice3.setVisible(false);
            lblItem3.setVisible(false);
        }
    }

    void check() {
        if (imgStaff.isHover()) {
            lblInfo.setText("Damage Increased");
        } else if (imgHolyWater.isHover() == true) {
            lblInfo.setText("Does Extra Damge (One Time Use)");
        } else if (imgHealthScroll.isHover() == true) {
            lblInfo.setText("Increases Health");
        } else if (!imgStaff.isHover() || !imgHolyWater.isHover() || !imgHealthScroll.isHover()) {
            lblInfo.setText("");
        }
        if (MainApp.gold >= 10) {
            lblPrice.setTextFill(Color.LIMEGREEN);
            lblPrice2.setTextFill(Color.LIMEGREEN);
            lblPrice3.setTextFill(Color.LIMEGREEN);
        } else {
            lblPrice.setTextFill(Color.RED);
            lblPrice2.setTextFill(Color.RED);
            lblPrice3.setTextFill(Color.RED);
        }
    }

    @FXML
    void leave(MouseEvent event) throws IOException {
        MainApp.setRoot("Gameplay");
    }

    @FXML
    void staff(MouseEvent event) {
        if (MainApp.gold >= 10) {
            MainApp.dmgBuff = 10;
            MainApp.gold = MainApp.gold - 10;
            lblCurrency.setText("" + MainApp.gold + " x");
            imgStaff.setVisible(false);
            lblPrice.setVisible(false);
            lblItem1.setVisible(false);
        }
    }

    @FXML
    void holyWater(MouseEvent event) {
        if (MainApp.gold >= 10) {
            MainApp.holyWater = true;
            MainApp.gold = MainApp.gold - 10;
            lblCurrency.setText("" + MainApp.gold + " x");
            imgHolyWater.setVisible(false);
            lblPrice2.setVisible(false);
            lblItem2.setVisible(false);
        }
    }

    @FXML
    void healthScroll(MouseEvent event) {
        if (MainApp.gold >= 10) {
            MainApp.healthBuff = true;
            MainApp.gold = MainApp.gold - 10;
            lblCurrency.setText("" + MainApp.gold + " x");
            imgHealthScroll.setVisible(false);
            lblPrice3.setVisible(false);
            lblItem3.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animateText(lblShopText, "My name's Rick Harrison and this is my \n Pawn Shop");
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(3000));
        fade.setFromValue(0);
        fade.setToValue(10);
        fade.setNode(imgShopKeeper);
        fade.play();
        FadeTransition fade2 = new FadeTransition();
        fade2.setDuration(Duration.millis(3000));
        fade2.setFromValue(0);
        fade2.setToValue(10);
        fade2.setNode(imgHat);
        fade2.play();
        lblCurrency.setText("" + MainApp.gold + " x");
        text[0] = lblItem1;
        text[1] = lblItem2;
        text[2] = lblItem3;
        text[3] = lblPrice;
        text[4] = lblPrice2;
        text[5] = lblPrice3;
        lblShopText.setTextAlignment(TextAlignment.CENTER);
    }
}