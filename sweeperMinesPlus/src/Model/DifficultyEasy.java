package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Easy Difficulty for the AdversarialAI
 */
public class DifficultyEasy implements AIDifficulty {

    private double bombChance;

    /**
     * Constructor for DifficultyEasy
     */
    public DifficultyEasy() {
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
                if (model.sweeperGrid[row][col] instanceof Bomb && !((Bomb) model.sweeperGrid[row][col]).uncovered) {
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
        // On easy mode, there is a 20% chance that the AI clicks a bomb on their turn
        Random random = new Random();
        if (0 <= this.bombChance && this.bombChance <= 20) {
            //Reset BombChance for the next time that AIMove is called
            this.bombChance = random.nextInt(0,100);
            return bomb.get(random.nextInt(0,bomb.size()));
        }
        else if (this.bombChance > 20 && this.bombChance <= 35) {
            this.bombChance = random.nextInt(0,100);
            return bonus.get(random.nextInt(0,bonus.size()));
        }
        else {
            this.bombChance = random.nextInt(0,100);
            return empty.get(random.nextInt(0,empty.size()));
        }
    }
}