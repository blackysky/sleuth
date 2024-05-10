package ru.incube.sleuth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.incube.sleuth.async.ConcurrencyManager;
import ru.incube.sleuth.data.recognizers.DataHandlerFactory;
import ru.incube.sleuth.jars.JarAnalyzer;

import java.io.File;
import java.net.URL;

public class UIController {
    private File exampleMod;

    private final ConcurrencyManager concurrencyManager = new ConcurrencyManager();
    private final JarAnalyzer jarAnalyzer = new JarAnalyzer();
    private final DataHandlerFactory dataHandlerFactory = new DataHandlerFactory();

    public Button btnParse;

    public UIController() {
        URL url = getClass().getResource("Example-Mod.jar");
        if (url != null) {
            exampleMod = new File(url.getFile());
        }
    }

    @FXML
    public void handleParseAction() {
        System.out.println("Parse action initiated...");

        if (exampleMod.exists()) {
            System.out.println("Example mod found!");

            concurrencyManager.submit(() -> {
                try {
                    jarAnalyzer.analyzeJar(exampleMod);
                } catch (Exception e) {
                    System.out.println("Error analyzing jar: " + e.getMessage());
                }
                concurrencyManager.shutdown();
                return null;
            });
        } else {
            System.out.println("Example mod not found!");
        }
    }
}