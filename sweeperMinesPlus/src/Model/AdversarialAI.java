package Model;

/**
 * AdversarialAI that the player will play against if a certain mode is chosen
 */
public class AdversarialAI {

    // AIDiffiulty
    protected AIDifficulty difficulty;

    // Base player attributes
    protected int score;
    protected String name;
    protected int lives;

    /**
     * Constructor for a MineSweeper Adversarial AI.
     */
    public AdversarialAI(int s, int l)  {
        score = s;
        lives = l;
    }

    /**
     * Have the AI execute a move and apply the grid item to a AI
     * @param model difficulty of the AI
     */
    public GridItem AIMove(SweeperBoard model) {
        return this.difficulty.AIMove(model);
    }

    /**
     * Sets the difficulty level for the AdversarialAI
     * @param d
     */
    public void setDifficulty(String d) {
        switch (d.toLowerCase()) {
            case "easy" -> this.difficulty = new DifficultyEasy();
            case "medium" -> this.difficulty = new DifficultyMedium();
            case "hard" -> this.difficulty = new DifficultyHard();
            default -> System.out.println("Invalid difficulty");
        }
    }
    public int getScore() {
        return score;
    }
}
