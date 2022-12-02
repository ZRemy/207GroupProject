package Model;

public class DifficultyEasy implements AIDifficulty {

    private double bombChance;
    private double bonusLifeChance;

    public DifficultyEasy() {
        this.bombChance = .2;
        this.bonusLifeChance = .05;
    }
    @Override
    public void AIMove(GridItem model) {

    }
}
