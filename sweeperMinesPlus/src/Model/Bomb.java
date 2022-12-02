package Model;


/** Bomb gridItem for MineSweeper
 *
 */
public class Bomb implements GridItem {



    /**
     * Constructor for a MineSweeper bomb.

     */
    public Bomb(){
    }

    /**
     * Applies the bomb
     * @return the number of lives to be drained from the player, which is always 1.
     */
    @Override
    public int applygridItem() {
        return -1;
    }
    @Override
    public String toString() {
        return "Bomb";
    }
}
