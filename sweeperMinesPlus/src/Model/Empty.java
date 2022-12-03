package Model;


/** Empty cell for MineSweeper
 *
 */
public class Empty implements GridItem{
    private SweeperBoard board;
    int x;
    int y;

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
    public int applygridItem() {
        Object[][] grid = board.sweeperGrid;
        int num_surrounding = 0;
        if (x - 1 >= 0 && y - 1 >= 0 && grid[x - 1][y - 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (x - 1 >= 0 && grid[x - 1][y] instanceof Bomb){
            num_surrounding ++;
        }
        if (x - 1 >= 0 && y + 1 < board.height && grid[x - 1][y + 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (y - 1 >= 0 && grid[x][y - 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (y + 1 < board.height && grid[x][y + 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (x + 1 < board.width && grid[x + 1][y] instanceof Bomb){
            num_surrounding ++;
        }
        if (x + 1 < board.width && y + 1 < board.height && grid[x + 1][y + 1] instanceof Bomb){
            num_surrounding ++;
        }
        if (x + 1 < board.width && y - 1 >= 0 && grid[x + 1][y - 1] instanceof Bomb){
            num_surrounding ++;
        }

        return num_surrounding;
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
