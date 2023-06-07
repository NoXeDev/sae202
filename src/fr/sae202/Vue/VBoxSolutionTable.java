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
import java.util.Map;
public class VBoxSolutionTable extends VBox{
    private Selection selection;
    private TableView <Solves> solutionTable;
    public VBoxSolutionTable(){
        super(15);
        solutionTable = new TableView<>();
        /**
        TableColumn <Solves, Integer> solveIdColumn = new TableColumn<>(Constants.TABLE_HEADER[0]);
        solveIdColumn.setCellValueFactory((new PropertyValueFactory<>("solveId")));
        solveIdColumn.setReorderable(false);
        solveIdColumn.setResizable(false);
        solveIdColumn.setPrefWidth(100);
        solveIdColumn.setId("Column");
         */
        TableColumn <Solves, ArrayList<Integer>> solveQuestListColumn = new TableColumn<>(Constants.TABLE_HEADER[1]);
        solveQuestListColumn.setCellValueFactory(new PropertyValueFactory<>("solveList"));
        solveQuestListColumn.setReorderable(false);
        solveQuestListColumn.setResizable(false);
        solveQuestListColumn.setPrefWidth(400);
        solveQuestListColumn.setId("Column");
        TableColumn <Solves, Integer> solveDurationColumn = new TableColumn<>(Constants.TABLE_HEADER[2]);
        solveDurationColumn.setCellValueFactory(new PropertyValueFactory<>("solveDuration"));
        solveDurationColumn.setReorderable(false);
        solveDurationColumn.setResizable(false);
        solveDurationColumn.setPrefWidth(100);
        solveDurationColumn.setId("Column");
        TableColumn <Solves, Integer> solveXpColumn = new TableColumn<>(Constants.TABLE_HEADER[3]);
        solveXpColumn.setCellValueFactory(new PropertyValueFactory<>("solveXp"));
        solveXpColumn.setReorderable(false);
        solveXpColumn.setResizable(false);
        solveXpColumn.setPrefWidth(100);
        solveXpColumn.setId("Column");
        TableColumn <Solves, Integer> solveDistanceColumn = new TableColumn<>(Constants.TABLE_HEADER[4]);
        solveDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("sumDistancesTraveled"));
        solveDistanceColumn.setReorderable(false);
        solveDistanceColumn.setResizable(false);
        solveDistanceColumn.setPrefWidth(100);
        solveDistanceColumn.setId("Column");
        TableColumn <Solves,Integer> solveQuestsColumn = new TableColumn<>(Constants.TABLE_HEADER[5]);
        solveQuestsColumn.setCellValueFactory(new PropertyValueFactory<>("solveQuestNumber"));
        solveQuestsColumn.setReorderable(false);
        solveQuestsColumn.setResizable(false);
        solveQuestsColumn.setPrefWidth(100);
        solveQuestsColumn.setId("Column");

        solutionTable.getColumns().addAll(solveQuestListColumn,solveDurationColumn,solveXpColumn,solveDistanceColumn,solveQuestsColumn);
        solutionTable.setId("tab");
        this.setPrefWidth(800);
        this.setPadding(new Insets(50,50,50,50));


        this.update(new Selection(0,0,0,0),1);

        this.getChildren().add(solutionTable);
    }

    public void update(Selection updatedSelection, int nbSolves){
        this.selection = updatedSelection;
        solutionTable.getItems().clear();
        solutionTable.getItems().addAll(selection.getFinalSolves(nbSolves));
    }


}
