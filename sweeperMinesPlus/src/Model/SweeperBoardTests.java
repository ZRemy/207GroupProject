package Model;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SweeperBoardTests {
    @Test
    void testInit() {
        SweeperBoard board1 = new SweeperBoard(16, 16, 40, 9);
        SweeperBoard board2 = new SweeperBoard(16, 16, 40, 9);
        String grid1 = Arrays.deepToString(board1.sweeperGrid).replace("], ", "]\n").replace("[[", "[").replace("]]", "]");
        String grid2 = Arrays.deepToString(board2.sweeperGrid).replace("], ", "]\n").replace("[[", "[").replace("]]", "]");

        int x = 0;
        int y = 0;
        for (int row = 0; row < board1.sweeperGrid.length; row++) {
            for (int col = 0; col < board1.sweeperGrid[row].length; col++) {
                if (board1.sweeperGrid[row][col].toString() == "Bomb"){
                    x++;
                }
                if (board1.sweeperGrid[row][col].toString() == "BonusLife"){
                    y++;
                }

            }
        }
        assertEquals(9, y);
        assertEquals(40, x);
        assertNotEquals(grid1, grid2);// Make sure that the board shuffles correctly.
        System.out.println(grid1);
        System.out.println();

        System.out.println();

        System.out.println(grid2);

    }
    }

