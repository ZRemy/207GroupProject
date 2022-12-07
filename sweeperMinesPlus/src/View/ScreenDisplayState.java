
package View;

import javafx.stage.Stage;

import java.io.IOException;

public interface ScreenDisplayState {
    void activate(Stage s) throws IOException;
}