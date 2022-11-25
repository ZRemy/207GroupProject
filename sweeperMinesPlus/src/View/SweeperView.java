package View;

import Model.SweeperModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SweeperView{
    SweeperModel model;
    Stage stage;
    BorderPane borderPane;
    Canvas canvas;
    GraphicsContext gc;
    public SweeperView(SweeperModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
        initUI();
    }
    private void initUI() {

    }
    private void updateBoard() {

    }

    /**
     * Update score on UI
     */
    private void updateScore() {

    }
    private void createLeaderboard(){
        Leaderboard leaderboard = new Leaderboard();
    }
}
