package View;

import javafx.stage.Stage;
import java.io.IOException;

public class NormalDisplay implements ScreenDisplayState{
    SweeperView view;

    /** The constructor for NormalDisplay
     */
    public NormalDisplay() {}

    /** This method reverts the game's stylesheets to its initial state.
     */
    public void activate(Stage stage){
        try {
            view = new SweeperView(stage);
        }
        catch(IOException e){
            return;
        }
    }
}