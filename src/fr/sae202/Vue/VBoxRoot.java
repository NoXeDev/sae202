package fr.sae202.Vue;


import fr.sae202.Controller.MainController;
import javafx.scene.layout.VBox;

/**
 * The main entry point for JavaFX application.
 */
public class VBoxRoot extends VBox {
    private static VBoxMenu menu;
    private static VBoxHeader header;
    private static VBoxSolutionTable solutionTable;
    private static MainController mainController = new MainController(); //initialization of the controller

    /**
     * The main entry point for JavaFX application. Contains :
     * - The menu bar (VBoxMenu)
     * - The header (VBoxHeader)
     * - The solution table (VBoxSolutionTable)
     */
    public VBoxRoot(){
        super(10);
        menu = new VBoxMenu(); //initialization of the menu
        header = new VBoxHeader(); //initialization of the header
        solutionTable = new VBoxSolutionTable(); //initialization of the solution table
        this.getChildren().addAll(menu, header, solutionTable);
    }

    /**
     * @return the VBoxMenu.
     */
    public static VBoxMenu getVBoxMenu(){
        return menu;
    }

    /**
     * @return the Controller.
     */
    public static MainController getController(){
        return mainController;
    }

    /**
     * @return the VBoxHeader.
     */
    public static VBoxHeader getVBoxHeader(){
        return header;
    }

    /**
     * @return the VBoxSolutionTable.
     */
    public static VBoxSolutionTable getVBoxSolutionTable(){
        return solutionTable;
    }
}
