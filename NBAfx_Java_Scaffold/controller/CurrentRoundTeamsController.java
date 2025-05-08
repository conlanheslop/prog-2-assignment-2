package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
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

public class CurrentRoundTeamsController extends Controller<Season> {

    @FXML private Label title;
    @FXML private TableView<Game> teamsTv;
    @FXML private Button closeButton;

    public Season getSeason() { return model; }

    public ObservableList<Team> getTeams() {
        return getSeason().getCurrentTeams();
    }

    public ObservableList<Game> getSchedule() {
        return getSeason().getCurrentSchedule();
    }

    public int getRound() {
        return getSeason().round() + 1;
    }


    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close(); 
    }
}







