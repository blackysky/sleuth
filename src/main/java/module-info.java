module ru.incube.sleuth {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.slf4j;

    opens ru.incube.sleuth to javafx.fxml;
    exports ru.incube.sleuth;
}