package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ErrorController extends Controller<Validator> {
    @FXML private Button okayButton;
    
    private Text teamExists;
    private Text nameError;
    private Text ageError;
    private Text creditError;
    private Text noError;
    private Text noGames;
    private Text oneGame;
    private Text finalGame;

    @FXML private VBox container;

    public Validator getValidator() { return model; }


    @FXML
    public void displayTeamName(String name) {
        teamExists = new Text(name + " Team already exists");
        teamExists.getStyleClass().add("text-out");
        container.getChildren().add(teamExists);
    }

    @FXML
    public void generateNameError() {
        nameError = new Text("Incorrect name pattern!");
        nameError.getStyleClass().add("text-out");
        container.getChildren().add(nameError);
    }

    @FXML
    public void generateCreditError() {
        creditError = new Text ("Incorrect credit pattern!");
        creditError.getStyleClass().add("text-out");
        container.getChildren().add(creditError);
    }
    @FXML
    public void generateAgeError() {
        ageError = new Text("Incorrect age pattern!");
        ageError.getStyleClass().add("text-out");
        container.getChildren().add(ageError);
    }
    @FXML
    public void generateNoError() {
        noError = new Text("Incorrect number pattern!");
        noError.getStyleClass().add("text-out");
        container.getChildren().add(noError);
    }
    @FXML
    public void noGames() {
        noGames = new Text("No Games to play!\nPlease add games to this round.");
        noGames.getStyleClass().add("text-out");
        container.getChildren().add(noGames);
    }
    @FXML
    public void oneGame() {
        oneGame = new Text("All games finished! You can check results now!");
        oneGame.getStyleClass().add("text-out");
        container.getChildren().add(oneGame);
    }
    
    @FXML
    public void finalGame(String name) {
        finalGame = new Text("All games finished! You can check results now!\nThis season ends!\n" + name + " is the Champion!!");
        finalGame.getStyleClass().add("text-out");
        container.getChildren().add(finalGame);
    }

    @FXML
    public void close() {
        Stage stage = (Stage) okayButton.getScene().getWindow();
        stage.close();
    }
}
