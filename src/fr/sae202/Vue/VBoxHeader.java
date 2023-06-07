package fr.sae202.Vue;

import fr.sae202.Controller.MainController;
import fr.sae202.Models.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.event.ActionEvent;

public class VBoxHeader extends VBox {
    private Label scenario;
    private TextField nbSolutions;

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

        nbSolutions = new TextField();
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
        return nbSolutions.getText();
    }
}
