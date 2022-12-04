package Model;

import java.util.*;

/**
 * Leaderboard model for minesweeper game.
 */

public final class Leaderboard {

    private static final Leaderboard leaderboard = new Leaderboard();
    public HashMap<String, Integer> playerScores = new HashMap<>();

    /**
     *  Constructor for leaderboard.
     */
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
