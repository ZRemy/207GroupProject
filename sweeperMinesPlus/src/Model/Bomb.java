package Model;


/** Bomb gridItem for MineSweeper
 *
 */
public class Bomb implements GridItem {

    private int strength;

    /**
     * Constructor for a MineSweeper bomb.

     * @param s strength of the griditem
     */
    public Bomb(int s){
        strength = s;
    }

    /**
     * Applies the bomb
     * @return the number of lives to be drained from the player
     */
    @Override
    public int applygridItem() {
        return -strength;
    }
    @Override
    public String toString() {
        return "Bomb";
    }
}
