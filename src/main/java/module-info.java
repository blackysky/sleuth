module ru.incube.sleuth {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.incube.sleuth to javafx.fxml;
    exports ru.incube.sleuth;
}