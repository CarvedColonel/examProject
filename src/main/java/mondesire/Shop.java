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


    public void animateText(Label lbl, String message) {

        String content = message;
final Animation animation = new Transition()
{
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
lblItem1.setVisible(true);
imgStaff.setVisible(true);
lblPrice.setVisible(true);
lblItem2.setVisible(true);
imgHolyWater.setVisible(true);
lblPrice2.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), ae -> check()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    void check() {
        if (imgStaff.isHover()) {
            lblInfo.setText("Damage Increased");
        } else if (imgHolyWater.isHover() == true) {
            lblInfo.setText("Does Extra Damge (One Time Use)");
        } else if (!imgStaff.isHover() || !imgHolyWater.isHover()) {
            lblInfo.setText("");
        }
    }
    @FXML
    void leave(MouseEvent event) throws IOException {
    MainApp.setRoot("Gameplay");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animateText(lblShopText, "My name's Rick Harrison and this is my \n                       Pawn Shop");
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


    }
}