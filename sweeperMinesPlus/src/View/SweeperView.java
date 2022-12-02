package View;

import Model.SweeperModel;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SweeperView{
    SweeperModel model; //reference to model
    Stage stage;

    BorderPane borderPane;
    Canvas canvas;
    GraphicsContext gc;

    Boolean pause;
    Timeline timeline;

    /**
     * Constructor
     *
     * @param model reference to sweeper model
     * @param stage application stage
     */
    public SweeperView(SweeperModel model, Stage stage) {

        this.model = model;
        this.stage = stage;
        initUI();
    }
    private void initUI() {

        this.pause = false;

        //Create Leaderboard


    }
    private void updateBoard() {

    }

    /**
     * Update score on UI
     */
    private void updateScore() {

    }

}
