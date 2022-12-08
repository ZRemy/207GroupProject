package View;

import Model.Leaderboard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Testing site for the Leaderboard
 */
public class LeaderboardTest extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Loads the leaderboard View
        Leaderboard board = Leaderboard.getInstance();

        board.playerScores.put("Marc", 50000);
        board.playerScores.put("Steven", 5000);
        board.playerScores.put("Bennet", 500);
        board.playerScores.put("Remy", 50);



        Parent root = FXMLLoader.load(getClass().getResource("leaderboard.fxml"));
        primaryStage.setTitle("Leaderboard Win");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
