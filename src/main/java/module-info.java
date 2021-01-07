module mondesire {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    opens mondesire to javafx.fxml;
    exports mondesire;
}