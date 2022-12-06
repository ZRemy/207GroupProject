package Model;


/** Bomb gridItem for MineSweeper
 *
 */
public class Bomb implements GridItem {
    int x;
    int y;
    boolean uncovered;


    /**
     * Constructor for a MineSweeper bomb.

     */
    public Bomb(int a, int b){
            this.uncovered = false;
            this.x = a;
            this.y = b;

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
    @Override
    public void uncover() {
        this.uncovered = true;
    }

    public boolean isUncovered() {
        return uncovered;
    }
}
