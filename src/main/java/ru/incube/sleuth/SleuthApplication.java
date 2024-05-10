package ru.incube.sleuth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SleuthApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(SleuthApplication.class.getResource("MainView.fxml"));
        BorderPane root = loader.load();

        Scene scene = new Scene(root);
        URL stylesResource = getClass().getResource("styles.css");
        if (stylesResource != null) {
            scene.getStylesheets().add(stylesResource.toExternalForm());
        } else {
            System.out.println("Styles not loaded!");
        }

        stage.setTitle("Sleuth");
        stage.setScene(scene);
        stage.show();
    }

    private boolean isStylesLoaded() {
        return getClass().getResource("styles.css") != null;
    }
}