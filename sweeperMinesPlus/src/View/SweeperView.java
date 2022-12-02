package View;


import Model.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SweeperView{
    SweeperModel model;
    SweeperBoard board;
    Stage stage;
    Canvas canvas;
    GraphicsContext gc;
    private Scene scene;
    GridPane boardGrid = new GridPane();


    public SweeperView(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        scene = new Scene(createGrid(), 1000, 500);
        this.stage.setTitle("CSC207 MineSweeper");
        this.stage.setScene(scene);
        this.stage.show();

    }

    /**
     * Given the SweeperBoard, create the MineSweeper Grid and display it on the window. Each cell grid
     * will either be a Bomb, BonusLife, or Empty.
     */
    private GridPane createGrid() {

        // Set the background color of the game.
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        boardGrid.setBackground(background);

        board = new SweeperBoard(16, 16, 40, 9);
        Player p1 = new Player(0, "Bennet", 1);

        model = new SweeperModel(board, 0, 0, p1);


        // Create a list of coordinates where the bombs are located in the grid.
//        ArrayList<Integer> bombCoordinates = new ArrayList<>();
//        for (int y = 0; y < board.getWidth(); y++) {
//            for (int x = 0; x < board.getHeight(); x++) {
//                if (board.getSweeperGrid()[y][x].toString() == "Bomb") {
//                    bombCoordinates.add(y);
//                    bombCoordinates.add(x);
//                }
//            }
//        }

        for (int y = 0; y < board.getWidth(); y++) {
            for (int x = 0; x < board.getHeight(); x++) {

                if (board.getSweeperGrid()[y][x].toString() == "Bomb") {

                    // Set the buttons as initially "blank/empty", and when a Mouse click event happens, the method
                    // revealButton will be activated and reveal an image of the bomb.
                    Button bomb = new Button();
                    Image emptyImage = new Image("/bomb10.png");
                    resizeButton(bomb, emptyImage, x, y);
                    int finalY = y;
                    int finalX = x;
                    bomb.setOnAction(actionEvent ->
                            revealButton("Bomb", finalX, finalY));


                    // Edit later and add method that opens all the bomb when a single bomb is clicked.
                }
                else if (board.getSweeperGrid()[y][x].toString() == "BonusLife") {
                    Button bonus = new Button();
                    Image emptyImage = new Image("/Empty.png");
                    resizeButton(bonus, emptyImage, x, y);
                    int finalY = y;
                    int finalX = x;
                    bonus.setOnAction(actionEvent ->
                            revealButton("BonusLife", finalX, finalY));
                }
                else if (board.getSweeperGrid()[y][x].toString() == "Empty") {
                    Button emptyCell = new Button();
                    Image emptyImage = new Image("/Empty.png");
                    resizeButton(emptyCell, emptyImage, x, y);
                    int finalY1 = y;
                    int finalX1 = x;
                    emptyCell.setOnAction(actionEvent ->
                            revealButton("Empty", finalX1, finalY1));
                }
                else {
                    Button bomb = new Button(board.getSweeperGrid()[y][x].toString());
                    boardGrid.add(bomb, y, x);
                }
            }
        }
        return boardGrid;
    }

    /**
     * Helper method for createGrid() in order to update the image of the tile and whether it's a Bomb, BonusLife, or
     * still an empty cell.
     * @param type the type of image that will be displayed (this will be a Number, Bomb, or BonusLife)
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     */
    private void revealButton(String type, int x, int y) {

        if (type == "Bomb") {
            Button bomb = new Button();
            Image image = new Image("/bomb10.png");
            resizeButton(bomb, image, x, y);
            System.out.println("GAME OVER");
            gameOver();


        } else if (type == "BonusLife") {
            Button bonus = new Button();
            Image bonusImage = new Image("/bonus.png");
            resizeButton(bonus, bonusImage, x, y);

        } else {
            Empty tile = new Empty(board, y, x);
            if (tile.applygridItem() != 0) {
                if (tile.applygridItem() == 1) {
                    Button one = new Button();
                    Image oneImage = new Image("/num1.png");
                    resizeButton(one, oneImage, x, y);

                } else if (tile.applygridItem() == 2) {
                    Button one = new Button();
                    Image oneImage = new Image("/num2.png");
                    resizeButton(one, oneImage, x, y);

                } else if (tile.applygridItem() == 3) {
                    Button one = new Button();
                    Image oneImage = new Image("/num3.png");
                    resizeButton(one, oneImage, x, y);

                } else if (tile.applygridItem() == 4) {
                    Button one = new Button();
                    Image oneImage = new Image("/num4.png");
                    resizeButton(one, oneImage, x, y);

                } else if (tile.applygridItem() == 5) {
                    Button one = new Button();
                    Image oneImage = new Image("/num5.png");
                    resizeButton(one, oneImage, x, y);
                } else if (tile.applygridItem() == 6) {
                    Button one = new Button();
                    Image oneImage = new Image("/num6.png");
                    resizeButton(one, oneImage, x, y);
                } else if (tile.applygridItem() == 7) {
                    Button one = new Button();
                    Image oneImage = new Image("/num7.png");
                    resizeButton(one, oneImage, x, y);
                } else if (tile.applygridItem() == 8) {
                    Button one = new Button();
                    Image oneImage = new Image("/num8.png");
                    resizeButton(one, oneImage, x, y);
                }
            }
        }
    }

    /**
     * Helper method for resizing the image and button so that all buttons in the board have the same dimensions.
     * @param currButton the current button that needs resizing.
     * @param image the image is resized to fit inside the current button.
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     */
    private void resizeButton(Button currButton, Image image, int x, int y) {
        ImageView view = new ImageView(image);

        // Set the dimensions of the image so that it doesn't get distorted.
        view.setFitHeight(15);
        view.setFitWidth(15);
        view.setPreserveRatio(true);

        // Set the imageview to the button and set the preferred size of the button. Note that all the
        // sizes of the buttons must match up.
        currButton.setPrefSize(15, 15);
        currButton.setGraphic(view);
        boardGrid.add(currButton, y, x);
    }

    /**
     * This method is used when a bomb is clicked on the board. This sets the current scene to a different scene
     * that d
     */
    private void gameOver() {
        StackPane gameOver = new StackPane();
        Scene gameOverScene = new Scene(gameOver, 1000, 500);

        Text gameOverText = new Text("GAME OVER LOSER");
        gameOverText.setTextAlignment(TextAlignment.CENTER);
        gameOverText.setFont(new Font(20));
        gameOver.getChildren().add(gameOverText);
        this.stage.setScene(gameOverScene);
        this.stage.show();
    }


    private void updateBoard() {

    }

    /**
     * Update score on UI
     */
    private void updateScore() {

    }
}