package View;


import Model.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class SweeperView{
    SweeperModel model;
    SweeperBoard board;
    Stage stage;
    BorderPane borderPane;
    Canvas canvas;
    GraphicsContext gc;
    private Scene scene;


    public SweeperView(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        scene = new Scene(createGrid());
        this.stage.setTitle("CSC207 MineSweeper");
        this.stage.setScene(scene);
        this.stage.show();

    }
    /**
     * Given the SweeperBoard, create the MineSweeper Grid and display it on the window. Each cell grid
     * will either be a Bomb, BonusLife, or Empty.
     */
    private GridPane createGrid() {
        GridPane boardGrid = new GridPane();

        board = new SweeperBoard(board.getHeight(), board.getWidth(), 40, 9);

        // How the game will be displayed when the app is launched
        boardGrid.setPrefSize(600, 600);

        for (int y = 0; y < board.getWidth(); y++) {
            for (int x = 0; x < board.getHeight(); x++) {

                if (board.getSweeperGrid()[y][x].toString() == "Bomb") {
                    Button bomb = new Button("X");
                    boardGrid.add(bomb, y, x);

                } else if (board.getSweeperGrid()[y][x].toString() == "BonusLife") {
                    Button bonus = new Button("B");
                    boardGrid.add(bonus, y, x);

                } else if (board.getSweeperGrid()[y][x].toString() == "Empty") {
                    Button emptyCell = new Button("E");
                    boardGrid.add(emptyCell, y, x);
                } else {
                    Button bomb = new Button(board.getSweeperGrid()[y][x].toString());
                    boardGrid.add(bomb, y, x);
                }
            }
        }
        return boardGrid;
    }

    private void updateBoard() {

    }

    /**
     * Update score on UI
     */
    private void updateScore() {

    }
}