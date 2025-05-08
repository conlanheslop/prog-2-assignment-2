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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.*;

public class ManageTeamController extends Controller<Team> {

    @FXML private Label teamNameLbl;
    @FXML private TextField teamNameTf;

    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;
    @FXML private GridPane buttonGrid;

    @FXML private TableView<Player> teamTv;

    private Parent root;

    public Team getTeam() { return this.model; }
    public ObservableList<Player> getPlayers() { return getTeam().getCurrentPlayers(); }

    private String getPlayerName() {
        return getSelectedPlayer().getName();
    }

    private Player getSelectedPlayer() {
        return teamTv.getSelectionModel().getSelectedItem();
    }

    private Boolean validName(String name) {
        return name.matches("^[A-Z][a-zA-Z ]+$");
    }

    private void newTeamList() {
        String name = getSelectedPlayer().getName();
        String level = getSelectedPlayer().getLevel();
        Integer ag1 = getSelectedPlayer().getAge();
        Integer ag2 = getSelectedPlayer().getAge();
        getTeam().getPlayers().filterList(name, level, ag1, ag2);
    }

    public void initialize() {
        teamNameTf.setText(getTeam().getName());
        teamTv.setItems(getPlayers());
        updateButton.setDisable(true);
        deleteButton.setDisable(true);

        teamTv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                addButton.setDisable(true);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
            } else {
                addButton.setDisable(false);
                updateButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });
    }

    @FXML public void addTeam(ActionEvent event) {

        try {
              Stage stage = new Stage();
              stage.setX(ViewLoader.X + 601);
              stage.setY(ViewLoader.Y);
              stage.getIcons().add(new Image("/view/edit.png"));
              ViewLoader.showStage(getTeam(), "/view/PlayerUpdateView.fxml", "Adding New Player", stage);
        } catch (IOException ex) {
              Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @FXML public void updateTeam(ActionEvent event) {
        String name = getPlayerName();
        newTeamList();
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getTeam(), "/view/PlayerUpdateView.fxml", "Updating Player: " + name, stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML public void deleteTeam(ActionEvent event) {
        ObservableList<Player> morePlayers = FXCollections.observableArrayList(getPlayers());
        for (Player player : morePlayers) {
            if (player.getName().equals(getSelectedPlayer().getName())) {
                getPlayers().remove(player);
                break;
            }
        }
    }

    @FXML public void saveClose() {
        if (!validName(teamNameTf.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
                root = loader.load();
                ErrorController errorController = loader.getController();
                errorController.generateNameError();
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                stage.setTitle("Error!");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        else {
            getTeam().setName(teamNameTf.getText());
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
        
        
        
        
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}

