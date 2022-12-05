package Model;
import java.util.ArrayList;
import java.util.Random;

/**
 * Hard difficulty for the AdversarialAI
 */
public class DifficultyHard implements AIDifficulty{

    private final double bombChance;

    /**
     * Constructor for DifficultyHard
     */
    public DifficultyHard() {
        Random rand = new Random();
        this.bombChance = rand.nextInt(0,100);
    }

    /**
     * The AI takes a move based on the Hard Difficulty
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
        for (int row = 0; row <= model.height; row++) {
            for (int col = 0; col <= model.width; col++) {
                if (model.sweeperGrid[row][col] instanceof Bomb) {
                    bomb.add(model.sweeperGrid[row][col]);
                }
                if (model.sweeperGrid[row][col] instanceof BonusLife) {
                    bonus.add(model.sweeperGrid[row][col]);
                }
                if (model.sweeperGrid[row][col] instanceof Empty) {
                    empty.add(model.sweeperGrid[row][col]);
                }
            }
        }
        // On hard mode, there is a 5% chance that the AI clicks a bomb on their turn
        Random random = new Random();
        GridItem result;
        if (0 <= this.bombChance && this.bombChance <= 5) {
            return bomb.get(random.nextInt(0,bomb.size()));
        }
        else if (this.bombChance > 5 && this.bombChance <= 20) {
            return bonus.get(random.nextInt(0,bonus.size()));
        }
        else {
            return empty.get(random.nextInt(0,empty.size()));
        }
    }
}
