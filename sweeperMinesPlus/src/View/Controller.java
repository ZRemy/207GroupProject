
package View;

import Model.Leaderboard;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *  Controller class for Leaderboard View.
 *  Sourced from: https://gist.github.com/Da9el00/3af0ebbacee5edbc70b67ab2c4782866
 */
public class Controller implements Initializable {

    //Table
    @FXML
    private TableView<Leaderboard> tableView;

    //Columns
    @FXML
    private TableColumn<Leaderboard, Integer> rankColumn;

    @FXML
    private TableColumn<Leaderboard, String> nameColumn;

    @FXML
    private TableColumn<Leaderboard, Integer> scoreColumn;

    //Text input
    @FXML
    private TextField nameInput;

    @FXML
    private TextField ageInput;

    @FXML
    private TextField numberInput;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rankColumn.setCellValueFactory(new PropertyValueFactory<Leaderboard, Integer>("Rank"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Leaderboard, String>("Player"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Leaderboard, Integer>("Score"));
    }

    //Submit button
    @FXML
    void submit(ActionEvent event) {
        //TODO: instead of creating new instance, call the getter method
        Leaderboard leaderboardPointer = Leaderboard.getInstance();

        //Turns into an Observable list
        ObservableList<Leaderboard> leaderboardRanks = tableView.getItems();
        leaderboardRanks.add(leaderboardPointer);
        tableView.setItems(leaderboardRanks);
    }

    @FXML
    void removeEntry(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }


//    @FXML
//    void updateView()
}
