package fr.sae202.Vue;

import fr.sae202.Models.Constants;
import fr.sae202.Models.Selection;
import fr.sae202.Models.Solves;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains the solution table.
 */
public class VBoxSolutionTable extends VBox{
    private Selection selection;
    private TableView <Solves> solutionTable;

    /**
     * Constructor of the solution table. Contains :
     * - Quest list column
     * - Duration column
     * - Experience column
     * - Distance travelled column
     * - Number of quests column
     */
    public VBoxSolutionTable(){
        super(15);
        solutionTable = new TableView<>();

        //Creation of the quest list column
        TableColumn <Solves, ArrayList<Integer>> solveQuestListColumn = new TableColumn<>(Constants.TABLE_HEADER[1]);
        solveQuestListColumn.setCellValueFactory(new PropertyValueFactory<>("solveList"));
        solveQuestListColumn.setReorderable(false);
        solveQuestListColumn.setResizable(false);
        solveQuestListColumn.setPrefWidth(420);
        solveQuestListColumn.setId("Column");

        //Creation of the duration column
        TableColumn <Solves, Integer> solveDurationColumn = new TableColumn<>(Constants.TABLE_HEADER[2]);
        solveDurationColumn.setCellValueFactory(new PropertyValueFactory<>("solveDuration"));
        solveDurationColumn.setReorderable(false);
        solveDurationColumn.setResizable(false);
        solveDurationColumn.setPrefWidth(120);
        solveDurationColumn.setId("Column");

        //Creation of the experience column
        TableColumn <Solves, Integer> solveXpColumn = new TableColumn<>(Constants.TABLE_HEADER[3]);
        solveXpColumn.setCellValueFactory(new PropertyValueFactory<>("solveXp"));
        solveXpColumn.setReorderable(false);
        solveXpColumn.setResizable(false);
        solveXpColumn.setPrefWidth(120);
        solveXpColumn.setId("Column");

        //Creation of the distance travelled column
        TableColumn <Solves, Integer> solveDistanceColumn = new TableColumn<>(Constants.TABLE_HEADER[4]);
        solveDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("sumDistancesTraveled"));
        solveDistanceColumn.setReorderable(false);
        solveDistanceColumn.setResizable(false);
        solveDistanceColumn.setPrefWidth(120);
        solveDistanceColumn.setId("Column");

        //Creation of the number of quests column
        TableColumn <Solves,Integer> solveQuestsColumn = new TableColumn<>(Constants.TABLE_HEADER[5]);
        solveQuestsColumn.setCellValueFactory(new PropertyValueFactory<>("solveQuestNumber"));
        solveQuestsColumn.setReorderable(false);
        solveQuestsColumn.setResizable(false);
        solveQuestsColumn.setPrefWidth(120);
        solveQuestsColumn.setId("Column");

        solutionTable.getColumns().addAll(Arrays.asList(solveQuestListColumn,solveDurationColumn,solveXpColumn,solveDistanceColumn,solveQuestsColumn));
        solutionTable.setId("tab");
        this.setPrefWidth(800);
        this.setPadding(new Insets(50,50,50,50));

        //Primary initialization of a default selection in the table
        this.update(new Selection(0,0,0,0),1);

        this.getChildren().add(solutionTable);
    }

    /**
     * Update the solution table with the new selection and the number of solutions to display.
     * @param updatedSelection the new selection. (selected in the menu bar)
     * @param nbSolves the number of solutions to display. (selected in the header's input)
     */
    public void update(Selection updatedSelection, int nbSolves){
        this.selection = updatedSelection;
        solutionTable.getItems().clear();
        solutionTable.getItems().addAll(selection.getFinalSolves(nbSolves));
    }

    public int getSize(){
        return solutionTable.getItems().size();
    }
}
