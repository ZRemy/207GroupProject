package Model;

public class DifficultyHard implements AIDifficulty{

    private double bombChance;
    private double bonusLifeChance;

    public DifficultyHard() {
        this.bombChance = .05;
        this.bonusLifeChance = .15;
    }

    @Override
    public void AIMove(GridItem model) {

    }
}
