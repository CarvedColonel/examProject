module mondesire {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens mondesire to javafx.fxml;
    exports mondesire;
}