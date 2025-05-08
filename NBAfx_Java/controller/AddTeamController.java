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

public class AddTeamController extends Controller<Teams> {

    @FXML private Label title;
    @FXML private Label name;
    @FXML private TextField nameTf;
    @FXML private Button addButton;

    @FXML private TableView<Team> teamTv;

    private Parent root;

    public Teams getTeams() { return model; }

    private String getNameText() {
        return nameTf.getText();
    }

    // same as validator
    private Boolean validName(String name) {
        return name.matches("^[A-Z][a-zA-Z ]+$");
    }

    @FXML private void add(ActionEvent event) {
        String name = getNameText();

        if (getTeams().hasTeam(name)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
                root = loader.load();
                ErrorController errorController = loader.getController();
                errorController.displayTeamName(name);
                stageSetup(root);
            } catch (IOException e) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        else if (!validName(name)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
                root = loader.load();
                ErrorController errorController = loader.getController();
                errorController.generateNameError();
                stageSetup(root);
            } catch (IOException e) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        else {
            getTeams().addTeam(new Team(name));
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }

    private void stageSetup(Parent root) {
        Stage stage = new Stage();
        stage.setX(ViewLoader.X + 601);
        stage.setY(ViewLoader.Y);
        stage.getIcons().add(new Image("/view/error.png"));
        stage.setTitle("Error!");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
