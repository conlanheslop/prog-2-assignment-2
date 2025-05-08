package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

public class PlayerUpdateController extends Controller<Team>  {
    @FXML private Button updateButton;
    @FXML private Button addButton;
    @FXML private Button closeButton;

    @FXML private TextField nameTf;
    @FXML private TextField creditTf;
    @FXML private TextField ageTf;
    @FXML private TextField noTf;

    private Parent root;

    public Team getTeam() { return model; }

    private Boolean validName(String text) {
        return text.matches("^[A-Z][a-zA-Z ]+$");
    }
    private Boolean validCredit(String text) {
        return text.matches("^-?\\d+(\\.\\d+)?$");
    }
    private Boolean validAge(String text) {
        return text.matches("^\\d+$");
    }
    private Boolean validNo(String text) {
        return text.matches("^\\d+$");
    }

    @FXML
    public void initialize() {
        
        try {
            for (Player player : getTeam().getPlayers().getFilteredPlayersList()) {
                nameTf.setText(player.getName());
                creditTf.setText(String.valueOf(player.getCredit()));
                ageTf.setText(String.valueOf(player.getAge()));
                noTf.setText(String.valueOf(player.getNo()));
            }
        } catch (NullPointerException e) {
            
        }  

        if (("").equals(nameTf.getText())) {
            updateButton.setDisable(true);
        }
        else {
            addButton.setDisable(true);
        }
    }

    private void errorGenerator(String name, String credit, String age, String no) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
            root = loader.load();
            ErrorController errorController = loader.getController();

            if (!validName(name)) {
                errorController.generateNameError();
            }
            if (!validCredit(credit)) {
                errorController.generateCreditError();
            }
            if (!validAge(age)) {
                errorController.generateAgeError();
            }
            if (!validNo(no)) {
                errorController.generateNoError();
            }

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

    @FXML
    public void add(ActionEvent event) {
        String name = nameTf.getText();
        String credit = creditTf.getText();
        String age = ageTf.getText();
        String no = noTf.getText();
        
        if (!validName(name) ||
            !validCredit(credit) ||
            !validAge(age) || 
            !validNo(no)) {
            errorGenerator(name, credit, age, no);
        } else {
            Player player = new Player(name, Double.valueOf(credit), Integer.valueOf(age), Integer.valueOf(no));
            getTeam().getPlayers().addPlayer(player);
            player.setTeam(getTeam());
        }

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void update(ActionEvent event) {
        String name = nameTf.getText();
        String credit = creditTf.getText();
        String age = ageTf.getText();
        String no = noTf.getText();
        
        if (!validName(name) || 
            !validCredit(credit) || 
            !validAge(age) || 
            !validNo(no)) {
            errorGenerator(name, credit, age, no);
        } else {
            for (Player player: getTeam().getPlayers().getFilteredPlayersList()) {
                player.setName(name);
                player.setCredit(Double.valueOf(credit));
                player.setAge(Integer.valueOf(age));
                player.setNo(Integer.valueOf(no));
            }
            
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
