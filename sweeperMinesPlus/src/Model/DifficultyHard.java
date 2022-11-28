package Model;

public class DifficultyHard implements AIDifficulty{

    private double bombChance;

    public DifficultyHard() {
        this.bombChance = .05;
    }

    @Override
    public void AIMove(GridItem model) {
    }
}
