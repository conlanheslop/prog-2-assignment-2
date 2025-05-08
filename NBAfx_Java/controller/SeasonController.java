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

public class SeasonController extends Controller<Season> {
    @FXML private Button roundButton;
    @FXML private Button currentButton;
    @FXML private Button gameButton;
    @FXML private Button resultButton;
    @FXML private Button closeButton;

    private Parent root;

    public Season getSeason() { return model; }

    private String getWinner() {
        return getSeason().getCurrentTeams().get(0).getName();
    }

    @FXML
    public void round(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
        } catch (IOException e) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void current(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/CurrentRoundTeams.fxml", "Tournament", stage);
        } catch (IOException e) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void game() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
            root = loader.load();
            ErrorController error = loader.getController();
            
            if (getSeason().getCurrentSchedule().isEmpty()) {
                error.noGames();
            } else {
                if (getSeason().round() == 0) {
                    getSeason().playGame();
                    error.oneGame();
                } else if (getSeason().round() == 1) {
                    getSeason().playGame();
                    error.finalGame(getWinner());
                }
            }
            // Stage setup
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            stage.setTitle("All Games Played!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void result() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/RecordView.fxml", "Season Record", stage);
        } catch (IOException e) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

