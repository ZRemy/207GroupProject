package Model;

/** Represents the MineSweeper player.
 *
 */
public class Player {
    protected int score;
    protected String name;
    protected int lives;

    /**
     * Constructor for a MineSweeper player.
     */
    public  Player(int s, String n, int l){
        score = s;
        name = n;
        lives = l;
    }

    /**
     * Have the player execute a move and apply the grid item to a player
     * @param item grid item uncovered
     */
    protected void move(GridItem item){
        if (item instanceof Bomb || item instanceof BonusLife){
            lives += item.applygridItem();
        }
        else{
            score += item.applygridItem();
        }
    }
}
