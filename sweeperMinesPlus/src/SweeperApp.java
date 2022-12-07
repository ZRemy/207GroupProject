import View.SweeperView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SweeperApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            new SweeperView(primaryStage);
        }
        catch(IOException e){
            return;
        }

    }
}
