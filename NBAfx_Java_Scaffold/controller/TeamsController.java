package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Teams;
import model.Team;

public class TeamsController extends Controller<Teams> {

    @FXML private Button addButton;
    @FXML private Button manageButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;
    @FXML private GridPane buttonGrid;

    @FXML private TableColumn<Team, String> teamNameColumn;
    @FXML private TableColumn<Team, Integer> numberOfPlayersColumn;
    @FXML private TableColumn<Team, Double> averagePlayerCreditColumn;
    @FXML private TableColumn<Team, Integer> averageAgeColumn;

    @FXML private TableView<Team> teamsTv;

    private Teams teamsModel;

    public Teams getTeams() {
        return this.model;
    }

    public void setTeamsModel(Teams teamsModel) {
        this.teamsModel = teamsModel;
        teamsTv.setItems(teamsModel.currentTeams());
    }

    @FXML
    public void initialize() {
        // teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        // numberOfPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
        // averagePlayerCreditColumn.setCellValueFactory(new PropertyValueFactory<>("averagePlayerCredit"));
        // averageAgeColumn.setCellValueFactory(new PropertyValueFactory<>("averageAge"));
    }

    @FXML public void addTeam() {
        try {
              Stage stage = new Stage();
              stage.setX(ViewLoader.X + 601);
              stage.setY(ViewLoader.Y);
              stage.getIcons().add(new Image("/view/nba.png"));
              ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding Team", stage);
        } catch (IOException ex) {
              Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @FXML public void manageTeam() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(), "/view/ManageTeam.fxml", "Managing Team: ", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML public void deleteTeam() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(), "/view/PlayersView.fxml", "View PLayers", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}

