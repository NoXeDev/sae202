package fr.sae202.Vue;

import fr.sae202.Controller.MainController;
import fr.sae202.Models.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.event.ActionEvent;

/**
 * Contains the header of the application.
 */
public class VBoxHeader extends VBox {
    private Label scenario;
    private TextField nbSolutions;
    private Label headMessage;

    /**
     * Constructor of the header. Contains :
     * - The selected scenario label
     * - The input for the number of solutions
     * - The message label
     * - The confirm button
     */
    public VBoxHeader(){
        super(10);
        MainController mainController = VBoxRoot.getController();

        //Creation of the selected scenario label
        scenario = new Label(Constants.TEXT_HEADER[0] + "0");
        scenario.setId("title");
        this.setAlignment(Pos.CENTER);

        //Creation of the input for the number of solutions
        HBox inputSolutions = new HBox();
        inputSolutions.setAlignment(Pos.CENTER);
        Label solutions = new Label(Constants.TEXT_HEADER[1]);
        solutions.setId("label");

        nbSolutions = new TextField();
        nbSolutions.setId("TextField");

        inputSolutions.getChildren().addAll(solutions, nbSolutions);

        //Creation of the confirm button
        Button buttonConfirm = new Button(Constants.BUTTON[0]);
        buttonConfirm.setAccessibleText("buttonConfirm");
        buttonConfirm.addEventHandler(ActionEvent.ACTION, mainController);

        //Creation of the message label
        headMessage = new Label("");

        this.getChildren().addAll(scenario, inputSolutions, headMessage, buttonConfirm);

    }

    /**
     * Update the selected scenario label.
     * @param idScenario the id of the selected scenario.
     */
    public void update(String idScenario){
        scenario.setText("Scenario " + idScenario);
    }

    /**
     * Get the content of the input for the wished number of solutions (called when the confirm button is clicked).
     * @return the text in the input.
     */
    public String getNbSolutions(){
        return nbSolutions.getText();
    }

    /**
     * Get the message label.
     * @return the message label.
     */
    public Label getHeadMessage(){
        return headMessage;
    }
}
