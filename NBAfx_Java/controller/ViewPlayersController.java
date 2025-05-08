package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Player;
import model.Teams;

public class ViewPlayersController extends Controller<Teams> {

    @FXML private TextField levelTf;
    @FXML private TextField nameTf;
    @FXML private TextField fromTf;
    @FXML private TextField toTf;
    @FXML private TableView<Player> playersTv;

    @FXML private Button closeButton;

    public Teams getTeams() {
        return model;
    }
 
    @FXML
    public void initialize() {
        ObservableList<Player> players = getTeams().allPlayersList();
        ObservableList<Player> filteredPlayers = FXCollections.observableArrayList();

        levelTf.textProperty().addListener((observable, oldV, newV) -> filter(players, filteredPlayers));
        nameTf.textProperty().addListener((observable, oldV, newV) -> filter(players, filteredPlayers));
        fromTf.textProperty().addListener((observable, oldV, newV) -> filter(players, filteredPlayers));
        toTf.textProperty().addListener((observable, oldV, newV) -> filter(players, filteredPlayers));
    }

    private void filter(ObservableList<Player> players, ObservableList<Player> filtered) {
        String levelIn = levelTf.getText();
        String nameIn = nameTf.getText();
        int ageFrom = 0;
        int ageTo = Integer.MAX_VALUE;

        try {
            if (!fromTf.getText().isEmpty()) {
                ageFrom = Integer.parseInt(fromTf.getText());
            }
            if (!toTf.getText().isEmpty()) {
                ageTo = Integer.parseInt(toTf.getText());
            }
        } catch (NumberFormatException e) {

        }

        filtered.clear();

        for (Player player : players) {
            boolean levelMatch = levelIn.isEmpty() || player.getLevel().toLowerCase().contains(levelIn);
            boolean nameMatch = nameIn.isEmpty() || player.getName().toLowerCase().contains(nameIn);
            boolean ageIgnore = ageFrom == 0 && ageTo == 0;
            boolean ageMatch = ageIgnore || player.getAge() >= ageFrom && player.getAge() <= ageTo;
            if (levelMatch && nameMatch && ageMatch) {
                filtered.add(player);
            }
        }

        playersTv.setItems(filtered);
    }

    @FXML public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

