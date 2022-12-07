package Model.GridItem;


/** Bomb gridItem for MineSweeper
 *
 */
public class Bomb implements GridItem {
    public int x;
    public int y;
    public boolean uncovered;


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
    public int applyGridItem() {
        return -1;
    }

    /**
     * String representation of a Bomb
     * @return "Bomb"
     */
    @Override
    public String toString() {
        return "Bomb";
    }

    /**
     * Uncovers the tile containing this Bomb.
     */
    @Override
    public void uncover() {
        this.uncovered = true;
    }

    /**
     * Checks if the bomb has been previously uncovered.
     * @return Whether the bomb has been uncovered.
     */
    public boolean isUncovered() {
        return uncovered;
    }
}
