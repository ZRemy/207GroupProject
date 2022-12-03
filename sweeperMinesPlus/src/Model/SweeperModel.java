package Model;

import java.util.*;
import java.util.Map.Entry;

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
     */
    public SweeperModel(SweeperBoard b, int c, int s, Player p){
        board = b;
        count = c;
        score = s;
        player = p;
        leaderboard = Leaderboard.getInstance();
        gameOver = false;
    }


    /**
     * Uncovers a tile on the minesweeper grid and applies the item to the player.
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     * @return the number of surrounding bombs if an empty cell is uncovered, -2 if the player has no more lives, -1 otherwise.
     */
    public int uncoverTile(int x, int y){
        player.move(board.sweeperGrid[x][y]);
        if (player.lives <= 0){
            gameOver = true;
            return -2;
        }
        if (board.sweeperGrid[x][y] instanceof Empty){
            return board.sweeperGrid[x][y].applygridItem();
        }
        score = player.score;
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
            for (Entry<String, Integer> entry : leaderboard.playerScores.entrySet()) {
                if (entry.getValue().equals(val)) {
                    sortedMap.put(entry.getKey(), val);
                }
            }
        }
        leaderboard.playerScores = sortedMap;
    }
}
