package fr.sae202.Controller;

import fr.sae202.Models.Selection;
import fr.sae202.Vue.VBoxMenu;
import fr.sae202.Vue.VBoxRoot;
import fr.sae202.Models.Constants;
import fr.sae202.Vue.VBoxSolutionTable;
import javafx.event.Event;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Button;

import javafx.event.EventHandler;

import java.util.Scanner;

public class MainController implements EventHandler {
    private int SelectedScenario = 0;
    private int SelectedType = 0;
    private int SelectedCriteria = 0;
    private int SelectedOrder = 0;
    private Selection currentSelection = new Selection(SelectedScenario, SelectedType, SelectedCriteria, SelectedOrder);

    @Override
    public void handle(Event event) {
        VBoxMenu menu = VBoxRoot.getVBoxMenu();

        if (event.getSource() instanceof RadioMenuItem) {
            RadioMenuItem selectedMenuItem = (RadioMenuItem) event.getSource();
            String selectedMenu = selectedMenuItem.getParentMenu().getText();
            String selectedChoice = selectedMenuItem.getText();

            if (selectedMenu.equals(Constants.MENU_BAR[0])) {
                int posUnderscore = selectedChoice.indexOf("_");
                if (posUnderscore != -1) {
                    String idScnenario = selectedChoice.substring(posUnderscore + 1);
                    System.out.println("Scenario: " + idScnenario);
                    SelectedScenario = Integer.parseInt(idScnenario);

                    VBoxRoot.getVBoxHeader().update(idScnenario);
                }
            } else if (selectedMenu.equals(Constants.MENU_BAR[1])) {
                System.out.println("Type: " + selectedChoice);
                if (selectedChoice.equals(Constants.MENU_TYPES[0])) {
                    SelectedType = 0;
                } else if (selectedChoice.equals(Constants.MENU_TYPES[1])) {
                    SelectedType = 1;
                }
            } else if (selectedMenu.equals(Constants.MENU_BAR[2])) {
                System.out.println("Criteria: " + selectedChoice);
                if (selectedChoice.equals(Constants.MENU_CRITERIA[0])) {
                    SelectedCriteria = 0;
                } else if (selectedChoice.equals(Constants.MENU_CRITERIA[1])) {
                    SelectedCriteria = 1;
                } else if (selectedChoice.equals(Constants.MENU_CRITERIA[2])) {
                    SelectedCriteria = 2;
                } else if (selectedChoice.equals(Constants.MENU_CRITERIA[3])) {
                    SelectedCriteria = 3;
                }
            } else if (selectedMenu.equals(Constants.MENU_BAR[3])) {
                System.out.println("Order: " + selectedChoice);
                if (selectedChoice.equals(Constants.MENU_ORDERS[0])) {
                    SelectedOrder = 0;
                } else if (selectedChoice.equals(Constants.MENU_ORDERS[1])) {
                    SelectedOrder = 1;
                }
            }
            currentSelection = new Selection(SelectedScenario, SelectedType, SelectedCriteria, SelectedOrder);
        }
        if (event.getSource() instanceof Button) {
            switch (((Button) event.getSource()).getAccessibleText()) {
                case "buttonConfirm":
                    System.out.println("NbSelectionné: " + VBoxRoot.getVBoxHeader().getNbSolutions());
                    try {
                        if (Integer.parseInt(VBoxRoot.getVBoxHeader().getNbSolutions()) > 0) {
                            VBoxRoot.getVBoxHeader().getHeadMessage().setText(Constants.HEADER_MESSAGE[1]);
                            VBoxRoot.getVBoxHeader().getHeadMessage().setId("Confirm");
                            VBoxRoot.getVBoxSolutionTable().update(currentSelection, Integer.parseInt(VBoxRoot.getVBoxHeader().getNbSolutions()));
                        } else {
                            VBoxRoot.getVBoxHeader().getHeadMessage().setText(Constants.HEADER_MESSAGE[0]);
                            VBoxRoot.getVBoxHeader().getHeadMessage().setId("Error");
                        }
                    } catch (NumberFormatException e){
                        VBoxRoot.getVBoxHeader().getHeadMessage().setText(Constants.HEADER_MESSAGE[0]);
                        VBoxRoot.getVBoxHeader().getHeadMessage().setId("Error");
                    }
                    break;
            }
        }
    }
}

