package Model;

public class DifficultyEasy implements AIDifficulty {

    private double bombChance;

    public DifficultyEasy() {
        this.bombChance = .2;
    }
    @Override
    public void AIMove() {
    }
}
