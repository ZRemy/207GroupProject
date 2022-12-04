package View;

import Model.Leaderboard;
import Model.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *  Controller class for Leaderboard View.
 *
 */
public class Controller implements Initializable {

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
            index++;
        }

    }





}
