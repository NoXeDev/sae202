package fr.sae202.Vue;

import fr.sae202.Controller.MainController;
import fr.sae202.Models.Constants;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import java.io.File;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class VBoxMenu extends VBox{
    private ToggleGroup scenarioToggleGroup;
    private ToggleGroup searchTypeToggleGroup;
    private ToggleGroup searchCriteriaToggleGroup;
    private ToggleGroup searchOrderToggleGroup;

    public VBoxMenu(){
        MenuBar menuBar = new MenuBar();
        MainController mainController = VBoxRoot.getController();

        Menu scenariosMenu = new Menu(Constants.MENU_BAR[0]);
        File[] scenarioFiles = new File("res").listFiles();
        scenarioToggleGroup = new ToggleGroup();
        for (File scenarioFile : scenarioFiles) {
            if (scenarioFile.getName().endsWith(".txt")) {
                String scenarioName = scenarioFile.getName().replace(".txt", "");
                RadioMenuItem scenarioItem = new RadioMenuItem(scenarioName);
                scenarioItem.setToggleGroup(scenarioToggleGroup);
                scenarioItem.addEventHandler(ActionEvent.ACTION, mainController);
                scenariosMenu.getItems().add(scenarioItem);
            }
        }
        ((RadioMenuItem) scenariosMenu.getItems().get(0)).setSelected(true);


        Menu searchTypeMenu = new Menu(Constants.MENU_BAR[1]);
        searchTypeToggleGroup = new ToggleGroup();
        RadioMenuItem efficientItem = new RadioMenuItem(Constants.MENU_TYPES[0]);
        RadioMenuItem exhaustiveItem = new RadioMenuItem(Constants.MENU_TYPES[1]);
        efficientItem.setToggleGroup(searchTypeToggleGroup);
        efficientItem.addEventHandler(ActionEvent.ACTION, mainController);
        exhaustiveItem.setToggleGroup(searchTypeToggleGroup);
        exhaustiveItem.addEventHandler(ActionEvent.ACTION, mainController);
        searchTypeMenu.getItems().addAll(efficientItem, exhaustiveItem);
        ((RadioMenuItem) searchTypeMenu.getItems().get(0)).setSelected(true);

        Menu searchCriteriaMenu = new Menu(Constants.MENU_BAR[2]);
        searchCriteriaToggleGroup = new ToggleGroup();
        RadioMenuItem gluttonyItem = new RadioMenuItem(Constants.MENU_CRITERIA[0]);
        RadioMenuItem durationItem = new RadioMenuItem(Constants.MENU_CRITERIA[1]);
        RadioMenuItem questCountItem = new RadioMenuItem(Constants.MENU_CRITERIA[2]);
        RadioMenuItem movementItem = new RadioMenuItem(Constants.MENU_CRITERIA[3]);
        gluttonyItem.setToggleGroup(searchCriteriaToggleGroup);
        gluttonyItem.addEventHandler(ActionEvent.ACTION, mainController);
        durationItem.setToggleGroup(searchCriteriaToggleGroup);
        durationItem.addEventHandler(ActionEvent.ACTION, mainController);
        questCountItem.setToggleGroup(searchCriteriaToggleGroup);
        questCountItem.addEventHandler(ActionEvent.ACTION, mainController);
        movementItem.setToggleGroup(searchCriteriaToggleGroup);
        movementItem.addEventHandler(ActionEvent.ACTION, mainController);
        searchCriteriaMenu.getItems().addAll(gluttonyItem, durationItem, questCountItem, movementItem);
        ((RadioMenuItem) searchCriteriaMenu.getItems().get(0)).setSelected(true);

        Menu searchOrderMenu = new Menu(Constants.MENU_BAR[3]);
        searchOrderToggleGroup = new ToggleGroup();
        RadioMenuItem bestSolutionsItem = new RadioMenuItem(Constants.MENU_ORDERS[0]);
        RadioMenuItem worstSolutionsItem = new RadioMenuItem(Constants.MENU_ORDERS[1]);
        bestSolutionsItem.setToggleGroup(searchOrderToggleGroup);
        bestSolutionsItem.addEventHandler(ActionEvent.ACTION, mainController);
        worstSolutionsItem.setToggleGroup(searchOrderToggleGroup);
        worstSolutionsItem.addEventHandler(ActionEvent.ACTION, mainController);
        searchOrderMenu.getItems().addAll(bestSolutionsItem, worstSolutionsItem);
        ((RadioMenuItem) searchOrderMenu.getItems().get(0)).setSelected(true);


        menuBar.getMenus().addAll(scenariosMenu, searchTypeMenu, searchCriteriaMenu, searchOrderMenu);

        this.getChildren().add(menuBar);
    }
}
