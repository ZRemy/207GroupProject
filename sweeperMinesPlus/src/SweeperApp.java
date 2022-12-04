import Model.SweeperModel;
import View.SweeperView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class SweeperApp extends Application {

    SweeperModel model;
    SweeperView view;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {

        //Loads the leaderboard View
        Parent root = FXMLLoader.load(getClass().getResource("leaderboard.fxml"));
        primaryStage.setTitle("Leaderboard");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
