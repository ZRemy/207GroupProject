package Model;

import Model.AdversarialAI.AdversarialAI;
import Model.Board.SweeperBoard;
import Model.GridItem.Bomb;
import Model.GridItem.BonusLife;
import Model.GridItem.Empty;
import Model.GridItem.GridItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Represents the model for a game of Minesweeper.
 */
public class SweeperModel {
    protected SweeperBoard board;
    protected int count; // How many tiles uncovered
    public int score;
    protected Player player;

    public AdversarialAI computer;

    public boolean won;
    protected boolean gameOver;
    protected Leaderboard leaderboard;

    /**
     * Constructor for SweeperModel
     * @param b the board
     * @param c the number of uncovered cells
     * @param s the score
     * @param p the player
     * @param comp the computer
     */
    public SweeperModel(SweeperBoard b, int c, int s, Player p, AdversarialAI comp){
        board = b;
        count = c;
        score = s;
        player = p;
        leaderboard = Leaderboard.getInstance();
        gameOver = false;
        won = false;
        computer = comp;
    }


    /**
     * Uncovers a tile on the minesweeper grid and applies the item to the player.
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     * @return the number of surrounding bombs if an empty cell is uncovered, -2 if the player has no more lives, -1
     * if it is a bomb or -5 if the tile is uncovered.
     */
    public int uncoverTile(int x, int y){
        if (!board.getSweeperGrid()[x][y].isUncovered()) {
            player.move(board.getSweeperGrid()[x][y]);
            score = player.score;
            board.getSweeperGrid()[x][y].uncover();
            updateLeaderboard();

            if (player.lives <= 0) {
                gameOver = true;
                return -2;
            }
            else if (checkWin()){
                return 10;
            }
            else if (board.getSweeperGrid()[x][y] instanceof Empty) {
                return board.getSweeperGrid()[x][y].applyGridItem();
            }

            return -1;
        }
        return -5;
    }

    /** Uncovers a tile on the minesweeper gird for the AdversarialAI and applies the item to the board
     * @return the number of surrounding bombs if an empty cell is uncovered, -3 if the AI has no more lives,
     * -1 if it's a bomb, -2 if it's a bonus life
     */
    public int uncoverTileAI(GridItem tile) {
        //Return the tile that the AdversarialAI will uncover
        tile.uncover();
        updateLeaderboard();
        //Depending on what kind of GridItem the tile is, react accordingly
        int aiScore;
        if (checkWin()){
            return 10;
        }
        if (tile instanceof Bomb) {
            computer.lives += tile.applyGridItem();
            if (computer.lives <= 0) {
                gameOver = true;
                this.won = true;
                return -3;
            }
            return -1;
        }

        else if (tile instanceof BonusLife) {
            computer.lives += tile.applyGridItem();
            return -2;
        }
        else if (tile instanceof Empty) {
            aiScore = tile.applyGridItem();
            computer.score += aiScore;
            return aiScore;
        }
        return 0;
    }

    /**
     *  Sends the view the tile the computer wants to uncover.
     * @return The tile the computer has moved.
     */
    public GridItem getAIMove() {
        //Return the tile that the AdversarialAI will uncover
        return computer.AIMove(this.board);

    }

    /**
     *  Updates the leaderboard with the player's highscore.
     */
    public void updateLeaderboard(){

        if (leaderboard.playerScores.containsKey(player.name)){
            if (leaderboard.playerScores.get(player.name) > score){
                return;
            }
        }
        leaderboard.playerScores.put(player.name, score);
        sortLeaderboardScores();
    }

    /**
     * Getter for the board
     * @return the board attached to this model.
     */
    public SweeperBoard getBoard() {
        return board;
    }

    /**
     * Sorts the leaderboard scores in descending order; Facilitates the transfer of top scores into the leaderboard UI.
     */
    public void sortLeaderboardScores(){
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : leaderboard.playerScores.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        Collections.reverse(list);
        for (Integer val : list) {
            for (Map.Entry<String, Integer> entry : leaderboard.playerScores.entrySet()) {
                if (entry.getValue().equals(val)) {
                    sortedMap.put(entry.getKey(), val);
                }
            }
        }
        leaderboard.playerScores = sortedMap;
    }

    /**
     * Checks if the player has won.
     * @return true if the player won, false otherwise.
     */
    public boolean checkWin(){
        boolean winner = true;
        for (int x = 0; x < board.getWidth(); x ++){
            for (int y = 0; y < board.getWidth(); y ++){
                winner = winner && (board.getSweeperGrid()[x][y].isUncovered());
            }
        }
        return winner;
    }

    /**
     * Sets the player's name to <name>
     * @param name the desired name of the player.
     */
    public void setPlayerName(String name){
        this.player.name = name;
    }
}
