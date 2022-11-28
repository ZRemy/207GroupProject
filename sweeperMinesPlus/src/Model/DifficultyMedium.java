package Model;

public class DifficultyMedium implements AIDifficulty{

    private double bombChance;

    public DifficultyMedium() {
        this.bombChance = .1;
    }

    @Override
    public void AIMove(GridItem model) {
    }
}
