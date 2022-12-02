package Model;

import java.util.HashMap;

/**
 * Leaderboard model for minesweeper game.
 */

//TODO: put this back into the singleton pattern

public final class Leaderboard {

    private static final Leaderboard leaderboard = new Leaderboard();
    public HashMap<String, Integer> playerScores = new HashMap<>();

    private Leaderboard(){}

    /**
     * Getter method.
     *
     * @return the leaderboard singleton.
     */
    public static Leaderboard getInstance(){
        return leaderboard;
    }
}
