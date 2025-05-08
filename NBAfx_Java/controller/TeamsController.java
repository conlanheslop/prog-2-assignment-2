package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.value.ObservableValue;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.*;

public class TeamsController extends Controller<Teams> {

    @FXML private Button addButton;
    @FXML private Button manageButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;
    @FXML private GridPane buttonGrid;

    @FXML private TableView<Team> teamsTv;

    public void initialize() {
        manageButton.disableProperty().bind(Bindings.isEmpty(teamsTv.getSelectionModel().getSelectedItems()));
        deleteButton.disableProperty().bind(Bindings.isEmpty(teamsTv.getSelectionModel().getSelectedItems()));
        addButton.disableProperty().bind(Bindings.isNotEmpty(teamsTv.getSelectionModel().getSelectedItems()));
    }

    public Teams getTeams() {
        return model;
    }

    public ObservableList<Team> getTeam() {
        return getTeams().currentTeams();
    }

    public Team getSelectedTeam() {
        return teamsTv.getSelectionModel().getSelectedItem();
    }

    @FXML public void addTeam() {
        
        try {
              Stage stage = new Stage();
              stage.setX(ViewLoader.X + 601);
              stage.setY(ViewLoader.Y);
              stage.getIcons().add(new Image("/view/edit.png"));
              ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding Team", stage);
        } catch (IOException ex) {
              Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @FXML public void manageTeam(ActionEvent event) {
        String name = getSelectedTeam().getName();
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getSelectedTeam(), "/view/ManageTeamView.fxml", "Managing Team: " + name, stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML public void deleteTeam(ActionEvent event) {
        ObservableList<Team> teamsToKeep = FXCollections.observableArrayList();

        for (Team team : getTeam()) {
            if (!team.getName().equals(getSelectedTeam().getName())) {
                teamsToKeep.add(team);
            }
        }

        getTeam().clear();
        getTeam().addAll(teamsToKeep);
    }

    @FXML public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

