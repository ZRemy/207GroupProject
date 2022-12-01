package Model;

public class DifficultyMedium implements AIDifficulty{

    private double bombChance;
    private double bonusLifeChance;

    public DifficultyMedium() {
        this.bombChance = .1;
        this.bonusLifeChance = .1;
    }

    @Override
    public void AIMove(GridItem model) {
    }
}
