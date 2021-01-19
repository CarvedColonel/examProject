package mondesire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class MainApp extends Application {
    private static Stage stage;
    public static boolean sound = true;
    public static String user = "";
    public static int battleStage;
    public static double x = 1222;
    public static double y = 532;
    public static int dmgBuff = 0;
    public static boolean holyWater = false;
    public static boolean healthBuff = false;
    public static int gold = 0;
    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        stage=s;
        setRoot("MainMenu","Priest's Conquest | Menu");

    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
