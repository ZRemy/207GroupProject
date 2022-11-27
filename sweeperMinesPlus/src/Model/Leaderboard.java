package Model;

import java.util.HashMap;

public final class Leaderboard {

    private static final Leaderboard leaderboard = new Leaderboard();
    public HashMap<String, Integer> playerScores = new HashMap<>();

    private Leaderboard(){}

    public static Leaderboard getInstance(){
        return leaderboard;
    }

}
