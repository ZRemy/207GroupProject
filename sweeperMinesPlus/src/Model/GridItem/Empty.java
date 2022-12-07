package Model.GridItem;


import Model.Board.SweeperBoard;

/** Empty cell for MineSweeper
 *
 */
public class Empty implements GridItem{
    protected SweeperBoard board;
    public int x;
    public int y;
    public boolean uncovered;


    /**
     * Constructor for Empty GridItem
     * @param b board
     */
    public Empty(SweeperBoard b, int x, int y){
        board = b;
        this.x = x;
        this.y = y;
    }

    /**
     * Applies an Empty cell
     * @return the number of surrounding bombs.
     */
    @Override
    public int applyGridItem() {
        Object[][] grid = board.getSweeperGrid();
        int num_surrounding = 0;
        if (x - 1 >= 0 && y - 1 >= 0 && grid[x - 1][y - 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (x - 1 >= 0 && grid[x - 1][y] instanceof Bomb){
            num_surrounding ++;
        }
        if (x - 1 >= 0 && y + 1 < board.getHeight() && grid[x - 1][y + 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (y - 1 >= 0 && grid[x][y - 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (y + 1 < board.getHeight() && grid[x][y + 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (x + 1 < board.getWidth() && grid[x + 1][y] instanceof Bomb){
            num_surrounding ++;
        }
        if (x + 1 < board.getWidth() && y + 1 < board.getHeight() && grid[x + 1][y + 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (x + 1 < board.getWidth() && y - 1 >= 0 && grid[x + 1][y - 1] instanceof Bomb){
            num_surrounding ++;
        }

        return num_surrounding;
    }
    /**
     * Uncovers the tile containing this Bomb.
     */
    @Override
    public void uncover() {
        this.uncovered = true;
    }

    /**
     * String representation of a Empty GridItem.
     * @return the name of the GridItem
     */
    @Override
    public String toString() {
        return "Empty";
    }

    /**
     * Checks if the Empty GridItem has been previously uncovered.
     * @return Whether the Empty cell has been uncovered.
     */
    public boolean isUncovered() {
        return uncovered;
    }
}
