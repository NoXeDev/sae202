package fr.sae202.Vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.layout.VBox;

public class VueApp extends Application{
    public void start(Stage stage){
        VBox root = new VBoxRoot();
        Scene scene = new Scene(root,1000,700);
        File [] fichierCss = new File("css").listFiles();
        for (File fichier : fichierCss){
            scene.getStylesheets().add(fichier.toURI().toString());
        }
        stage.setScene(scene);
        stage.setTitle("Durée de vie du RPG");

        stage.show();
    }

    public static void run(String[] args){
        Application.launch(args);
    }
}
