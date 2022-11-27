package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LeaderboardView{
    public LeaderboardView(SweeperView sweeperView){

        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.LIGHTBLUE);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }
}
