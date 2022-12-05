package Model;

import java.util.Random;

/** Minesweeper board for a Minesweeper Game.
 *
 */
public class SweeperBoard {
    protected int width; //board height and width
    protected int height;

    private final int num_bombs;

    private final int num_powerups;
    protected GridItem[][] sweeperGrid;

    /** Initialize the board
     *
     * @param w width of the board
     * @param h height of the board
     * @param num_b number of bombs
     * @param num_p number of powerUps/gridItems
     */
    public SweeperBoard(int w, int h, int num_b, int num_p){
        num_bombs = num_b;
        num_powerups = num_p;
        width = w;
        height = h;
        sweeperGrid = new GridItem[width][height];
        createBoard();
    }

    /** Generate the minesweeper board!
     *
     */
    private void createBoard(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sweeperGrid[x][y] = GriditemFactory.createGridItem("", 0, this, 0, 0);
            }
        }
        int k = 0;
        for (int i = 0; i < num_bombs; i ++){
            sweeperGrid[k][i -(height * k)] = GriditemFactory.createGridItem("bomb", 1, this, 0,0);
            if ((i + 1) % height == 0 ){
                k ++;
            }

        }

        k ++;
        int z = k;
        for (int i = 0; i < num_powerups; i++){
            sweeperGrid[k][i - (height * (k - z))] = GriditemFactory.createGridItem("bonuslife", i % 2 + 1, this, 0, 0);
            if ((i + 1)  % height == 0 ){
                k ++;
            }

        }
        shuffleBoard();
        assignXYVals();
    }

    /**
     * Assigns proper x and y values to the Empty cells on the board.
     */
    private void assignXYVals() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (sweeperGrid[x][y] instanceof Empty){
                    ((Empty)sweeperGrid[x][y]).x = x;
                    ((Empty)sweeperGrid[x][y]).y = y;
                }
                else if (sweeperGrid[x][y] instanceof Bomb){
                    ((Bomb)sweeperGrid[x][y]).x = x;
                    ((Bomb)sweeperGrid[x][y]).y = y;
                }
                else if (sweeperGrid[x][y] instanceof BonusLife){
                    ((BonusLife)sweeperGrid[x][y]).x = x;
                    ((BonusLife)sweeperGrid[x][y]).y = y;
                }
            }
        }
    }
    /**
     * Shuffles the minesweeper board.
     */
    private void shuffleBoard(){
        Random random = new Random();
        for (int i = width - 1; i > 0; i--) {
            for (int j = width - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                GridItem temp = sweeperGrid[i][j];
                sweeperGrid[i][j] = sweeperGrid[m][n];
                sweeperGrid[m][n] = temp;
            }
        }

    }

    /**
     * Getter for the height attribute
     * @return height of the board
     */
    public int getHeight() {
        return height;
    }
    /**
     * Getter for the width attribute
     * @return width of the board
     */
    public int getWidth() {
        return width;
    }
    /**
     * Getter for the sweeperGrid attribute
     * @return The grid contained in the board object.
     */
    public GridItem[][] getSweeperGrid() {
        return sweeperGrid;
    }
}