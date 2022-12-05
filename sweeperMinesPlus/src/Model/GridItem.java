package Model;

/** A GridItem for a minesweeper game.
 *
 */
public interface GridItem {

    /**
     * Applies the current GridItem, either a Bomb, BonusLife, or Empty item to the game/player.
     * @return The number of surrounding bombs if the GridItem is of type Empty, The number of lives to remove if the
     * type of the GridItem is Bomb, or the number of lives to restore if the GridItem is of type BonusLife.
     */
    int applygridItem();

    /**
     * Sets the grid item to an uncovered state.
     */
    void uncover();

    /**
     *
     * @return if the grid item is uncovered.
     */
    boolean isUncovered();
}
