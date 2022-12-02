package Model;

import java.util.Random;

public class DifficultyEasy implements AIDifficulty {

    private final double bombChance;

    public DifficultyEasy() {
        Random rand = new Random();
        this.bombChance = rand.nextInt(0,100);
    }
    @Override
    public GridItem AIMove(SweeperBoard model) {
        // Initialize griditem variables
        GridItem bomb = null;
        GridItem bonus = null;
        GridItem empty = null;
        // Iterate through the current SweeperBoard and find an instance of a bomb, bonus life, and empty
        for (int row = 0; row <= model.height; row++) {
            for (int col = 0; col <= model.width; col++) {
                if (model.sweeperGrid[row][col] instanceof Bomb) {
                    bomb = model.sweeperGrid[row][col];
                }
                if (model.sweeperGrid[row][col] instanceof BonusLife) {
                    bonus = model.sweeperGrid[row][col];
                }
                if (model.sweeperGrid[row][col] instanceof Empty) {
                    empty = model.sweeperGrid[row][col];
                }
            }
        }
        // On easy mode, there is a 20% chance that the AI clicks a bomb on their turn
        if (0 <= this.bombChance && this.bombChance <= 20) {
            assert bomb != null;
            bomb.applygridItem();
            return bomb;
        }
        else if (this.bombChance > 20 && this.bombChance <= 35) {
            assert bonus != null;
            bonus.applygridItem();
            return bonus;
        }
        else {
            assert empty != null;
            empty.applygridItem();
            return empty;
        }
    }
}