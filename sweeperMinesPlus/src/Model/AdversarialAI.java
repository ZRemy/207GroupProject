package Model;

import java.io.Serializable;

public class AdversarialAI {

    // AIDiffiulty
    private AIDifficulty difficulty;

    // Base player attributes
    protected int score;
    protected String name;
    protected int lives;

    /**
     * Constructor for a MineSweeper Adversarial AI.
     */
    public AdversarialAI(int s, String n, int l, String d)  {
        score = s;
        name = n;
        lives = l;
        setDifficulty(d);
    }

    /**
     * Have the AI execute a move and apply the grid item to a AI
     * @param model difficulty of the AI
     */
    public GridItem AIMove(SweeperBoard model) {
        return this.difficulty.AIMove(model);
    }

    public void setDifficulty(String d) {
        switch (d.toLowerCase()) {
            case "easy" -> this.difficulty = new DifficultyEasy();
            case "medium" -> this.difficulty = new DifficultyMedium();
            case "hard" -> this.difficulty = new DifficultyHard();
            default -> System.out.println("Invalid difficulty");
        }
    }
}
