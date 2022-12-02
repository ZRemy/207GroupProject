package Model;

import java.util.HashMap;

/**
 * Leaderboard model for minesweeper game.
 */

//TODO: put this back into the singleton pattern

public final class Leaderboard {

    private String playerName;
    private int age;
    private int number;
    private static final Leaderboard leaderboard = new Leaderboard();

    public Leaderboard() {

    }



    /**
     * Getter method.
     *
     * @return the leaderboard singleton.
     */
    public static Leaderboard getInstance(){
        return leaderboard;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        this.playerName = name;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
