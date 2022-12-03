package View;
import Model.*;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


/**
 * The SweeperView class is responsible for implementing the GUI of the MineSweeper game. Taking advantage of the
 * implemented backend features, SweeperView will use different attributes and methods that will display the game
 * and its various features.
 */
public class SweeperView{
    SweeperModel model;
    SweeperBoard board;
    Stage stage;
    private Scene scene;
    GridPane boardGrid = new GridPane();


    /**
     * The constructor, which initializes the stage of where and how the scenes will be set up.
     * @param stage
     */
    public SweeperView(Stage stage) {
        this.stage = stage;
        initUI();
    }


    /**
     * Initializes the SweeperView. This will create three scenes (basically windows), which the user will interact
     * with as the user runs the application.
     */
    private void initUI() {
        scene = new Scene(createGrid(), 1000, 500);
        this.stage.setTitle("CSC207 MineSweeper");
        createMenu();
        this.stage.show();
    }

    /**
     * Creates a menu window for the MineSweeper game. This will act as the Introduction window that the user
     * will see once the user runs the application.
     */
    private void createMenu() {
        Group menu = new Group();
        Scene menuScene = new Scene(menu, 1000, 500);

        createTitle(menu);
        createSettings(menu);

        Button game = new Button("Single Player");
        game.setPrefSize(100, 100);
        game.setStyle("-fx-background-color: grey");
        game.setLayoutY(200);
        game.setLayoutX(300);
        menu.getChildren().add(game);

        Button game2 = new Button("Against AI");
        game2.setPrefSize(100, 100);
        game2.setStyle("-fx-background-color: grey");
        game2.setLayoutY(200);
        game2.setLayoutX(600);
        menu.getChildren().add(game2);

        this.stage.setScene(menuScene);
        game.setOnAction(actionEvent -> this.stage.setScene(scene));

        blinkingAnimation(game);
    }


    /**
     * Create a label for the introduction window, which welcomes a user when running the application.
     * @param menu the pane which holds the different nodes
     */
    private void createTitle(Group menu) {
        Label welcome = new Label("Welcome to our MineSweeper Game!");
        welcome.setFont(Font.font("Verdana", FontPosture.REGULAR, 40));
        welcome.setTranslateX(140);
        welcome.setTranslateY(100);

        menu.getChildren().add(welcome);
    }


    /**
     * Create a settings bar which has multiple features to select from depending on what the user likes.
     * These features can include changing a display and increasing the difficulty against the AI.
     * @param menu the pane which holds the different nodes
     */
    private void createSettings(Group menu) {
        Menu mode = new Menu("MODE");
        MenuItem d = new MenuItem("Dark Mode");
        MenuItem n = new MenuItem("Night Shift");
        MenuItem dn = new MenuItem("Dark Mode & Night Shift");
        mode.getItems().add(d);
        mode.getItems().add(n);
        mode.getItems().add(dn);

        Menu diff = new Menu("AI-DIFFICULTY");
        MenuItem easy = new MenuItem("Easy");
        MenuItem medium = new MenuItem("Medium");
        MenuItem hard = new MenuItem("Hard");
        diff.getItems().add(easy);
        diff.getItems().add(medium);
        diff.getItems().add(hard);

        MenuBar mb = new MenuBar();
        mb.getMenus().add(mode);
        mb.getMenus().add(diff);
        menu.getChildren().add(mb);
    }


    /**
     * Animation effect which makes a button fade on or off for a few seconds.
     * @param b the button that is affected by the blinking/fading.
     */
    private void blinkingAnimation(Button b) {
        FadeTransition f = new FadeTransition(Duration.millis(3000), b);
        f.setFromValue(1.0);
        f.setToValue(0.0);

        f.setCycleCount(Timeline.INDEFINITE);
        f.setAutoReverse(true);
        f.play();
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

        for (int row = 0; row < board.getWidth(); row++) {
            for (int col = 0; col < board.getHeight(); col++) {
                Button button = new Button();
                Image emptyImage = new Image("Empty.png");
                resizeButton(button, emptyImage, row, col);
                int finalY = col;
                int finalX = row;
                button.setOnAction(actionEvent ->
                        revealButton(finalX, finalY));
            }
        }
        return boardGrid;
    }


    /**
     * Helper method for createGrid() in order to update the image of the tile and whether it's a Bomb, BonusLife, or
     * still an empty cell.
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     */
    private void revealButton(int x, int y) {
        String type = model.getBoard().getSweeperGrid()[x][y].toString();
        Button button = new Button();
        int val = model.uncoverTile(x,y);
        if (val == -2){
            gameOver();
        }
        Image image;
        if (val > 0) {
            image = new Image("/num" + val + ".png");
        }
        else{
            image = new Image("/" + type + ".png");
        }
        resizeButton(button, image, x, y);
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
        boardGrid.add(currButton, x, y);
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


    /**
     * Update board on UI
     */
    private void updateBoard() {

    }


    /**
     * Update score on UI
     */
    private void updateScore() {

    }
}