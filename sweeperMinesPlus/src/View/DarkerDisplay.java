package View;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class DarkerDisplay implements ScreenDisplayState{
    SweeperView view;

    /** The constructor for DarkerDisplay
     */
    public DarkerDisplay() {}


    /** This method uses CSS to change the colour state of the game to a darker state, which allows the user to have
     * the option to play the game in a darker setting.
     * @param s the primary stage where the other scenes and nodes are located.
     */
    public void activate(Stage s){
        try {
            view = new SweeperView(s);
        }
        catch(IOException e){
            return;
        }
        view.menu.getStylesheets().add("/darkMode.css");
        view.menuScene.setFill(Color.valueOf("#3f474f"));
        view.game.getStylesheets().add("/darkMode.css");
    }
}