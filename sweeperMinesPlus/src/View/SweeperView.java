package View;
import Model.*;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
//import javafx.scene.control.Label;

import javax.swing.text.Position;
import java.io.IOException;


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
    Controller controller;

    Parent game;
    Group menu;
    Scene menuScene;
    StackPane gameOver = new StackPane();
    Scene gameOverScene = new Scene(gameOver, 1000, 500);
    boolean vsComputer;

    Player p1;

    Label score;

    Pane initialBoard;


    /**
     * The constructor, which initializes the stage of where and how the scenes will be set up.
     * @param stage
     */
    public SweeperView(Stage stage) throws IOException {
        this.stage = stage;
        initUI();
    }


    /**
     * Initializes the SweeperView. This will create three scenes (basically windows), which the user will interact
     * with as the user runs the application.
     */
    private void initUI() throws IOException {
        createMenu();
        game = createGrid();
        scene = new Scene(game, 600, 500);
        this.stage.setTitle("CSC207 MineSweeper");

        this.stage.show();
    }

    /**
     * Creates a menu window for the MineSweeper game. This will act as the Introduction window that the user
     * will see once the user runs the application.
     */
    protected void createMenu() {
        menu = new Group();
        menuScene = new Scene(menu, 1000, 500);

        createTitle(menu);
        createSettings(menu);


        // This creates a box where the current user can input their name.
        StackPane user = new StackPane();
        user.setLayoutX(420);
        user.setLayoutY(380);
        TextField text = new TextField();
        Text t = new Text();
        user.getChildren().add(text);
        user.getChildren().add(t);
        menu.getChildren().add(user);

        Button create = new Button("Create");
        create.setLayoutX(470);
        create.setLayoutY(410);
        user.getChildren().add(create);

        menu.getChildren().add(create);

        create.setOnAction(actionEvent -> this.model.setPlayerName(text.getText()));

        p1 = new Player(0, text.getText(), 1);


        // Select button to let the user play a single game
        Button game = new Button("Single Player");
        game.setPrefSize(100, 100);
        game.setStyle("-fx-background-color: grey");
        game.setLayoutY(200);
        game.setLayoutX(300);
        menu.getChildren().add(game);

        // Select button to let the user play against the AI
        Button game2 = new Button("Against AI");
        game2.setPrefSize(100, 100);
        game2.setStyle("-fx-background-color: grey");
        game2.setLayoutY(200);
        game2.setLayoutX(600);
        menu.getChildren().add(game2);


        this.stage.setScene(menuScene);
        game.setOnAction(actionEvent -> this.stage.setScene(scene));
        game2.setOnAction(actionEvent -> playAgainstAI());
        blinkingAnimation(game);
        blinkingAnimation(game2);
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

        MenuItem normal = new MenuItem("Normal Display");
        MenuItem dark = new MenuItem("Dark Display");
        mode.getItems().add(normal);
        mode.getItems().add(dark);

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

        easy.setOnAction(actionEvent -> this.model.computer.setDifficulty("easy"));
        medium.setOnAction(actionEvent -> this.model.computer.setDifficulty("medium"));
        hard.setOnAction(actionEvent -> this.model.computer.setDifficulty("hard"));

        dark.setOnAction(actionEvent -> darkMode());
        normal.setOnAction(actionEvent -> normalMode());
    }


    /** Helper method that is activated when the user presses the Normal Display in the MODE settings. Note that
     * this is only activated and seen by the user when the game is in Dark Display.
     */
    private void normalMode() {
        NormalDisplay normalDisplay = new NormalDisplay();
        normalDisplay.activate(stage);
    }


    /** Helper method that is activated when the user presses the Dark Display in the MODE settings. This is an
     * accessibility feature which helps a user who suffers from eye issues, still be able to enjoy our Minesweeper
     * Game at its finest.
     */
    private void darkMode() {
        DarkerDisplay darkDisplay = new DarkerDisplay();
        darkDisplay.activate(stage);
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
    private Pane createGrid() throws IOException {
        initialBoard = new Pane();

        Button score1 = new Button();
        score1.setText("Score: " + p1.getScore() + "      Lives: " + p1.getLives());
        score1.setLayoutX(238);
        score1.setLayoutY(0);


        boardGrid.setPadding(new Insets(25, 25, 25, 25));

        board = new SweeperBoard(16, 16, 40, 9);
        AdversarialAI ai = new AdversarialAI(0, 1);
        ai.setDifficulty("easy");
        model = new SweeperModel(board, 0, 0, p1, ai);
        score = new Label();

        for (int row = 0; row < board.getWidth(); row++) {
            for (int col = 0; col < board.getHeight(); col++) {

                if (model.won){
                    win(this.stage);
                }
                Button button = new Button();
                Image emptyImage = new Image("Empty.png");
                resizeButton(button, emptyImage, row, col);
                int finalY = col;
                int finalX = row;

                button.setOnAction(actionEvent ->
                {
                    try {
                        revealButton(finalX, finalY, button);
                        score1.setText("Score: " + model.score + "      Lives: " + p1.getLives());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        boardGrid.setLayoutX(20);
        boardGrid.setLayoutY(40);

        initialBoard.getChildren().add(score1);
        initialBoard.getChildren().add(boardGrid);

        return initialBoard;
    }


    /**
     * Helper method for createGrid() in order to update the image of the tile and whether it's a Bomb, BonusLife, or
     * still an empty cell.
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     */
    private void revealButton(int x, int y, Button button) throws IOException {

        if (!model.getBoard().getSweeperGrid()[x][y].isUncovered()) {
            Image image = null;
            String type = model.getBoard().getSweeperGrid()[x][y].toString();
            int val = model.uncoverTile(x, y);
            // If lose
            if (val == -2) {
                gameOver(this.stage);

            } else if (val == 10) {
                win(this.stage);
            }
            else if (val >= 0) {
                image = new Image("/num" + val + ".png");
            } else {
                image = new Image("/" + type + ".png");
            }
            ImageView img = new ImageView(image);
            img.setFitWidth(15);
            img.setFitHeight(15);
            img.setX(x);
            img.setY(y);
            button.setGraphic(img);
            if (vsComputer){
                revealAIButton();
            }

        }
    }

    /**
     *
     * @throws IOException
     */
    private void revealAIButton() throws IOException {
            String type = "";
            if (vsComputer) {
                GridItem tile_move = this.model.getAIMove();
                int comp_val = this.model.uncoverTileAI(tile_move);
                if (comp_val == -3) {
                    System.out.println("loser");
                    this.win(this.stage);
                } else if (comp_val == 10) {
                    this.gameOver(this.stage);
                } else {
                    int a = 0;
                    int b = 0;
                    if (comp_val == -1) {
                        type = "Bomb";
                        a = ((Bomb) tile_move).x;
                        b = ((Bomb) tile_move).y;
                    } else if (comp_val == -2) {
                        type = "BonusLife";
                        a = ((BonusLife) tile_move).x;
                        b = ((BonusLife) tile_move).y;
                    }
                    Image image1;
                    if (comp_val >= 0 && comp_val < 10) {
                        a = ((Empty) tile_move).x;
                        b = ((Empty) tile_move).y;
                        image1 = new Image("/num" + comp_val + ".png");
                    } else {
                        image1 = new Image("/" + type + ".png");
                    }
                    Button button = new Button();
                    resizeButton(button, image1, a, b);
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
        boardGrid.add(currButton, x, y);
    }


    /**
     * This method is used when a bomb is clicked on the board. This sets the current scene to the Leaderboard scene.
     */

    private void gameOver(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("leaderboard.fxml"));
        primaryStage.setTitle("Leaderboard");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    //TODO: CREATE A "CHECK IF ALL TILES ARE UNCOVERED" METHOD (MAKE SURE TO PASS IN THIS.STAGE)
    //TODO: ADD RETURN 10 IF WIN WHICH THEREFORE CALLS THE WIN METHOD, WHICH CALLS THE DIFFERENT FXML FILE.
    /**
     * This method is used when all tiles are uncovered, thus the user wins
     */
    private void win(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("leaderboardWin.fxml"));
        primaryStage.setTitle("Leaderboard");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void playAgainstAI(){
        vsComputer = true;
        this.stage.setScene(scene);
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