package View;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class DarkerDisplay implements ScreenDisplayState{
    public SweeperView display;

    /** The constructor for DarkerDisplay
     */
    public DarkerDisplay() {}


    /** This method uses CSS to change the colour state of the game to a darker state, which allows the user to have
     * the option to play the game in a darker setting.
     * @param stage the primary stage where the other scenes and nodes are located.
     */
    public void activate(Stage stage){
        try {
            display = new SweeperView(stage);
        }
        catch(IOException e){
            return;
        }
        display.menu.getStylesheets().add("/darkMode.css");
        display.menuScene.setFill(Color.valueOf("#3f474f"));
        display.game.getStylesheets().add("/darkMode.css");
        display.initialBoard.getStylesheets().add("/darkMode.css");
    }
}