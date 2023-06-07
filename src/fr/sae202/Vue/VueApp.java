package fr.sae202.Vue;

import fr.sae202.Models.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.layout.VBox;

/**
 * The main entry point for JavaFX application.
 */
public class VueApp extends Application{
    /**
     * The main entry point for JavaFX application.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     */
    public void start(Stage stage){
        VBox root = new VBoxRoot();
        Scene scene = new Scene(root,1000,700);
        File [] fichierCss = new File("css").listFiles();
        for (File fichier : fichierCss){
            scene.getStylesheets().add(fichier.toURI().toString());
        }
        stage.setScene(scene);
        stage.setTitle(Constants.APP_NAME);

        stage.show();
    }

    /**
     * Launches the application.
     * @param args
     */
    public static void run(String[] args){
        Application.launch(args);
    }
}
