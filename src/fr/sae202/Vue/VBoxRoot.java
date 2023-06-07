package fr.sae202.Vue;


import fr.sae202.Controller.MainController;
import fr.sae202.Models.Selection;
import fr.sae202.Models.Solves;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.util.HashMap;

public class VBoxRoot extends VBox {
    private static VBoxMenu menu;
    private static VBoxHeader header;
    private static VBoxSolutionTable solutionTable;
    private static MainController mainController = new MainController();
    public VBoxRoot(){
        super(40);
        menu = new VBoxMenu();
        header = new VBoxHeader();
        solutionTable = new VBoxSolutionTable();
        this.getChildren().addAll(menu, header, solutionTable);
    }

    public static VBoxMenu getVBoxMenu(){
        return menu;
    }

    public static MainController getController(){
        return mainController;
    }

    public static VBoxHeader getVBoxHeader(){
        return header;
    }

    public static VBoxSolutionTable getVBoxSolutionTable(){
        return solutionTable;
    }
}
