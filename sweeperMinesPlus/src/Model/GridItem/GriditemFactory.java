package Model.GridItem;

import Model.Board.SweeperBoard;

import java.util.Objects;

/** A GridItemFactory for minesweeper. Manufactures different variations of GridItem objects.
 *
 */
public class GriditemFactory {
    /**
     *
     * @param name The name of the GridItem
     * @param strength The effectiveness of the GridItem, specifically used by the BonusLife GridItem
     * @param board The minesweeper board,s specifically attached to the Empty GridItem to be able to return the number
     *             of surrounding bombs on the grid.
     * @param x The x coordinate of the GridItem on the board.
     * @param y The y coordinate of the GridItem on the board.
     * @return a GridItem based on what the name attribute contains.
     */
    public static GridItem createGridItem(String name, int strength, SweeperBoard board, int x, int y){
        if (Objects.equals(name, "bomb")){
            return new Bomb(x, y);
        }
        if (Objects.equals(name, "bonuslife")){
            return new BonusLife(strength, x, y);
        }
        else {
            return new Empty(board, x, y);
        }
    }
}
