package Model.AdversarialAI;

import Model.GridItem.GridItem;
import Model.Board.SweeperBoard;

/**
 * AdversarialAI that the player will play against if a certain mode is chosen
 */
public class AdversarialAI {

    // AIDiffiulty
    protected AIDifficulty difficulty;

    // Base player attributes

    public int lives;

    /**
     * Constructor for a MineSweeper Adversarial AI.
     */
    public AdversarialAI()  {
        lives = 1;
    }

    /**
     * Have the AI execute a move and apply the grid item to a AI
     * @param board difficulty of the AI
     */
    public GridItem move(SweeperBoard board) {
        return this.difficulty.aiMove(board);
    }

    /**
     * Sets the difficulty level for the AdversarialAI
     * @param d the difficulty
     */
    public void setDifficulty(String d) {
        switch (d.toLowerCase()) {
            case "easy" -> this.difficulty = new DifficultyEasy();
            case "medium" -> this.difficulty = new DifficultyMedium();
            case "hard" -> this.difficulty = new DifficultyHard();
            default -> System.out.println("Invalid difficulty");
        }
    }
}
