package View;

import javafx.stage.Stage;
import java.io.IOException;

public class ScreenDisplay implements ScreenDisplayState {

    ScreenDisplayState mode;
    SweeperView view;
    public ScreenDisplay() {}

    @Override
    public void activate(Stage stage) throws IOException {
        view = new SweeperView(stage);
    }

    public void changeToNormalState(Stage stage) throws IOException {
        mode = new NormalDisplay();
        mode.activate(stage);
    }

    public void changeToDarkState(Stage stage) throws IOException {
        mode = new DarkerDisplay();
        mode.activate(stage);
    }
}
