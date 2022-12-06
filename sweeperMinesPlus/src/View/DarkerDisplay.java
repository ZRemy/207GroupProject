package View;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DarkerDisplay implements ScreenDisplayState{
    private ScreenDisplayState display;
    SweeperView view;
    Stage stage;

    public void activate(){
        System.out.println("DARK MODE");
        stage = new Stage();
        view = new SweeperView(stage);
        view.menu.getStylesheets().add("/darkMode.css");
        view.menuScene.setFill(Color.valueOf("#3f474f"));
        view.game.getStylesheets().add("/darkMode.css");
    }
}
