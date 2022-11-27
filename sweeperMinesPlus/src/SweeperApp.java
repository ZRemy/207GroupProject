import Model.SweeperModel;
import View.LeaderboardView;
import View.SweeperView;
import javafx.application.Application;
import javafx.stage.Stage;

public class SweeperApp extends Application {

    SweeperModel model;
    SweeperView view;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.model = new SweeperModel();
        SweeperView sweeperView = new SweeperView(model, primaryStage);
        this.model.startGame();

    }
}
