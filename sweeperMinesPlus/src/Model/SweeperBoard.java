package Model;

import java.util.Random;

public class SweeperBoard {
    private int width; //board height and width
    private int height;

    private int num_bombs;

    private int num_powerups;
    protected Object[][] sweeperGrid;

    public SweeperBoard(int w, int h, int num_b, int num_p){
        num_bombs = num_b;
        num_powerups = num_p;
        width = w;
        height = h;
        sweeperGrid = new Object[width][height];
        createBoard();
    }
    private void createBoard(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sweeperGrid[x][y] = GriditemFactory.createGridItem("", 0, this);
            }
        }
        int k = 0;
        for (int i = 0; i < num_bombs; i ++){
            if ((i + 1) % height == 0 ){
                k ++;
            }
            sweeperGrid[k][i -(height * k) + 1] = GriditemFactory.createGridItem("bomb", i % 2 + 1, this);
            }

        k ++;
        int z = k;
        for (int i = 0; i < num_powerups; i++){
            if ((i + 1)  % height == 0 ){
                k ++;
            }
            sweeperGrid[k][i - (height * (k - z)) + 1] = GriditemFactory.createGridItem("bonuslife", i % 2 + 1, this);
        }
        shuffleBoard();
    }


    private void shuffleBoard(){
        Random random = new Random();
        for (int i = width - 1; i > 0; i--) {
            for (int j = width - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                Object temp = sweeperGrid[i][j];
                sweeperGrid[i][j] = sweeperGrid[m][n];
                sweeperGrid[m][n] = temp;
            }
        }
    }

}
