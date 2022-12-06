package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Medium Difficulty for the AdversarialAI
 */
public class DifficultyMedium implements AIDifficulty{

    private final double bombChance;

    /**
     * Constructor for DifficultyMedium
     */
    public DifficultyMedium() {
        Random rand = new Random();
        this.bombChance = rand.nextInt(0,100);
    }

    /**
     * The AI takes a move based on the Easy Difficulty
     * @param model The current board configuration
     * @return the corresponding GridItem that the AI will land on within the board
     */
    @Override
    public GridItem AIMove(SweeperBoard model) {
        // Initialize griditem variables
        ArrayList<GridItem> bomb = new ArrayList<>();
        ArrayList<GridItem> bonus = new ArrayList<>();
        ArrayList<GridItem> empty = new ArrayList<>();
        // Iterate through the current SweeperBoard and find an instance of a bomb, bonus life, and empty
        for (int row = 0; row <= model.height - 1; row++) {
            for (int col = 0; col <= model.width - 1; col++) {
                if (model.sweeperGrid[row][col] instanceof Bomb && !(((Bomb) model.sweeperGrid[row][col]).uncovered)) {
                    bomb.add(model.sweeperGrid[row][col]);
                }
                if (model.sweeperGrid[row][col] instanceof BonusLife && !((BonusLife) model.sweeperGrid[row][col]).uncovered) {
                    bonus.add(model.sweeperGrid[row][col]);
                }
                if (model.sweeperGrid[row][col] instanceof Empty && !((Empty) model.sweeperGrid[row][col]).uncovered) {
                    empty.add(model.sweeperGrid[row][col]);
                }
            }
        }
        // On medium mode, there is a 10% chance that the AI clicks a bomb on their turn
        Random random = new Random();
        if (0 <= this.bombChance && this.bombChance <= 10) {
            return bomb.get(random.nextInt(0,bomb.size()));
        }
        else if (this.bombChance > 10 && this.bombChance <= 20) {
            return bonus.get(random.nextInt(0,bonus.size()));
        }
        else {
            return empty.get(random.nextInt(0,empty.size()));
        }
    }
}
