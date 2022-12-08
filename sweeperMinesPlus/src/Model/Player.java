package Model;

import Model.GridItem.Bomb;
import Model.GridItem.BonusLife;
import Model.GridItem.GridItem;

/** Represents the MineSweeper player.
 *
 */
public class Player {
    protected int score;
    public String name;
    protected int lives;

    /**
     * Constructor for a MineSweeper player.
     * @param s Player's score
     * @param n Player's name
     * @param l Player's current lives
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
            lives += item.applyGridItem();
        }
        else{
            score += item.applyGridItem();
            if (item.applyGridItem() == 0){
                score += 1;
            }
        }
    }

    /**
     * Getter for the player's score
     * @return Player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for the player's current lives.
     * @return Player's live count.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Getter for the player's name. This is used by the FXML controller
     * @return player name.
     */
    public String getName(){return name;}
}
