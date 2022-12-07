package View;

import Model.Leaderboard;
import Model.Player;
import View.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 *  Controller class for Leaderboard View.
 *
 */
public class Controller implements Initializable {

    SweeperView view;
    //Table
    @FXML
    private TableView<Player> tableView;

    //Columns
    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, Integer> scoreColumn;

    //Leaderboard pointer
    Leaderboard leaderboardPointer = Leaderboard.getInstance();

    @FXML
    private Button playAgainButton, exitButton;

    /**
     *  Automatically updates the leaderboard.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("score"));


        for(int index = 0; index < leaderboardPointer.playerScores.size(); index++){
            String name = (String) leaderboardPointer.playerScores.keySet().toArray()[index];
            Player newPlayer = new Player(leaderboardPointer.playerScores.get(name), name, index);

            ObservableList<Player> leaderboardRanks = tableView.getItems();
            leaderboardRanks.add(newPlayer);
            tableView.setItems(leaderboardRanks);
        }
    }

    /**
     * If the user selects this button, the whole game starts again.
     */
    public void playAgain() throws IOException {
        view = new SweeperView(new Stage());
        view.createMenu();
    }

    /**
     * If the user selects this button, the game exits.
     */
    public void exit(){
        System.exit(1);
    }

    /**
     * Underlines the button.
     */
    public void animatePlay(){
        playAgainButton.setUnderline(true);
    }

    /**
     * undoes the effect.
     */
    public void unanimatePlay(){
        playAgainButton.setUnderline(false);
    }

    /**
     * Underlines the button.
     */
    public void animateExit(){
        exitButton.setUnderline(true);
    }

    /**
     * undoes the effect.
     */
    public void unanimateExit(){
        exitButton.setUnderline(false);
    }
}
