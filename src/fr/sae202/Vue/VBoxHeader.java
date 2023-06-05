package fr.sae202.Vue;

import fr.sae202.Controller.MainController;
import fr.sae202.Models.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

public class VBoxHeader extends VBox {
    private Label scenario;
    private ComboBox<String> nbSolutions;

    public VBoxHeader(){
        super(10);
        MainController mainController = VBoxRoot.getController();
        scenario = new Label(Constants.TEXT_HEADER[0] + "0");
        scenario.setId("title");
        this.setAlignment(Pos.CENTER);

        HBox inputSolutions = new HBox();
        inputSolutions.setAlignment(Pos.CENTER);
        Label solutions = new Label(Constants.TEXT_HEADER[1]);
        solutions.setId("label");

        /*int [] tabNbSolutions = [Game.getNbSolutions()];
        for (int i = 0; i < Game.getNbSolutions() ; i++){
            tabNbSolutions[i] = i;
        }
        nbSolutions = peupleComboBox(tabNbSolutions);
        */

        //A COMPLETER APRES IMPLEMENTATION MULTIPLE SOLUTION

        nbSolutions = peupleComboBox(Constants.TEST); //TEMPORAIRE


        nbSolutions.setId("ComboBox");

        inputSolutions.getChildren().addAll(solutions, nbSolutions);

        Button buttonConfirm = new Button(Constants.BUTTON[0]);
        buttonConfirm.setAccessibleText("buttonConfirm");
        buttonConfirm.addEventHandler(ActionEvent.ACTION, mainController);

        this.getChildren().addAll(scenario, inputSolutions, buttonConfirm);

    }

    public void update(String idScenario){
        scenario.setText("Scenario " + idScenario);
    }

    public String getNbSolutions(){
        return nbSolutions.getValue();
    }

    private ComboBox<String> peupleComboBox (String [] strings){
        ComboBox<String> comboBox = new ComboBox<>();
        for (String string : strings) {
            comboBox.getItems().add(string);
        }
        return comboBox;
    }
}
