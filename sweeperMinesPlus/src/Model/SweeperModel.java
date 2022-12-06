package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SweeperModel {
    protected SweeperBoard board;
    protected int count; // How many tiles uncovered
    protected int score;
    protected Player player;

    protected AdversarialAI computer;

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
        if (!board.sweeperGrid[x][y].isUncovered()) {
            player.move(board.sweeperGrid[x][y]);
            board.sweeperGrid[x][y].uncover();
            if (player.lives <= 0) {
                gameOver = true;
                return -2;
            }
            if (board.sweeperGrid[x][y] instanceof Empty) {
                return board.sweeperGrid[x][y].applygridItem();
            }
            score = player.score;
            return -1;
        }
        return -5;
    }

    /**
     * Uncovers a tile on the minesweeper gird for the AdversarialAI and applies the item to the board
     * @return the number of surrounding bombs if an empty cell is uncovered, -3 if the AI has no more lives,
     * -1 if it's a bomb or bonus life
     */
    public int uncoverTileAI() {
        //Return the tile that the AdversarialAI will uncover
        GridItem tile = computer.AIMove(this.board);
        tile.uncover();
        //Depending on what kind of GridItem the tile is, react accordingly
        int aiScore;
        if (tile instanceof Bomb || tile instanceof BonusLife) {
            computer.lives += tile.applygridItem();
        }
        else if (tile instanceof Empty) {
            aiScore = tile.applygridItem();
            computer.score += aiScore;
            return aiScore;
        }
        if (computer.lives <= 0) {
            gameOver = true;
            return -3;
        }
        return -1;
    }

    /**
     *  Updates the leaderboard with the player's highscore.
     */
    public void updateLeaderboard(){
        if (leaderboard.playerScores.containsKey(player.name)){
            if (leaderboard.playerScores.get(player.name) > player.score){
                return;
            }
        }
        leaderboard.playerScores.put(player.name, player.score);
        sortLeaderboardScores();
    }

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
}
