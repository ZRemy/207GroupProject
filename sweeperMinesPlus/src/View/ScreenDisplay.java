package View;

import javafx.stage.Stage;
import java.io.IOException;

/** A screenDisplay object
 *
 */
public class ScreenDisplay implements ScreenDisplayState {

    ScreenDisplayState mode;
    SweeperView view;
    public ScreenDisplay() {}

    /**
     * Activates the ScreenDisplay.
     * @param stage the stage to be rendered on the UI.
     */
    @Override
    public void activate(Stage stage) throws IOException {
        view = new SweeperView(stage);
    }

    /**
     * Changes the display state to have the normal color scheme
     * @param stage the stage to be rendered on the UI.
     */
    public void changeToNormalState(Stage stage) throws IOException {
        mode = new NormalDisplay();
        mode.activate(stage);
    }
    /**
     * Changes the display state to have the dark color scheme
     * @param stage the stage to be rendered on the UI.
     */
    public void changeToDarkState(Stage stage) throws IOException {
        mode = new DarkerDisplay();
        mode.activate(stage);
    }
}
