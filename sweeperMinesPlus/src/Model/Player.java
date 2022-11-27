package Model;

public class Player {
    protected int score;
    protected String name;
    protected int lives;

    public  Player(int s, String n, int l){
        score = s;
        name = n;
        lives = l;
    }

    protected void move(GridItem item){
        if (item instanceof Bomb || item instanceof BonusLife){
            lives += item.applygridItem();
        }
        else{
            score += item.applygridItem();
        }
    }
}
