package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.value.ObservableValue;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.*;

public class TeamsRoundController extends Controller<Season> {
    
    @FXML private Label title;
    @FXML private ListView<Team> teamsLv;
    @FXML private Button moveButton;
    @FXML private TableView<Game> roundTv;
    @FXML private Button arrangeButton;

    private boolean canMove = false;
    
    @FXML private TableColumn<Game, Integer> gameColumn;
    @FXML private TableColumn<Game, String> team1Column;
    @FXML private TableColumn<Game, String> team2Column;

    public Season getSeason() { return model; }

    @FXML
    public void initialize() {
        title.setText("Round: " + (getSeason().round() + 1));
        teamsLv.setItems(getSeason().getCurrentTeams());
        teamsLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        teamsLv.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<?> change) -> {
            if (teamsLv.getSelectionModel().getSelectedItems().size() == 2) {
                moveButton.setDisable(false);
                canMove = true;
            } else if (teamsLv.getItems().size() == 0) {
                moveButton.setDisable(true);
                arrangeButton.setDisable(false);
            } else {
                moveButton.setDisable(true);
                canMove = false;

            }
        });

        if (teamsLv.getItems().size() == 0) {
            arrangeButton.setDisable(false);
        }

        roundTv.setItems(getSeason().getCurrentSchedule());
        roundTv.getSelectionModel().select(null);

        // Bindings
        gameColumn.setCellValueFactory(cellData -> cellData.getValue().termProperty().asObject());
        team1Column.setCellValueFactory(cellData -> cellData.getValue().team1());
        team2Column.setCellValueFactory(cellData -> cellData.getValue().team2());
    }

    
    @FXML
    public void move() {
        if (canMove) {
            getSeason().addTeams(teamsLv.getSelectionModel().getSelectedItems());
            teamsLv.getSelectionModel().select(-1);
            teamsLv.setItems(getSeason().getCurrentTeams());
        }
    }

    @FXML
    public void arrange() {
        stage.close();
    }
}



