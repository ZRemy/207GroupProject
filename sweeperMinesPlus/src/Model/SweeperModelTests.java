package Model;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SweeperModelTests {
    @Test
    void testModel() {
        Player p = new Player(0, "Remy", 1);
        Player p2 = new Player(0, "Marc", 1);
        Player p3 = new Player(0, "Bennet", 1);
        Player p4 = new Player(0, "Steven", 1);

        SweeperBoard board = new SweeperBoard(16,16,40,9);
        AdversarialAI computer = new AdversarialAI(0, 1);
        SweeperModel model = new SweeperModel(board, 0, 0, p, computer);
        SweeperModel model1 = new SweeperModel(board, 0, 0, p2, computer);
        SweeperModel model2 = new SweeperModel(board, 0, 0, p3, computer);
        SweeperModel model3 = new SweeperModel(board, 0, 0, p4, computer);
        for (int row = 0; row < board.sweeperGrid.length; row++) {
            for (int col = 0; col < board.sweeperGrid[row].length; col++) {
                if (board.sweeperGrid[row][col] instanceof Empty){
                    if (row % 4 == 0){
                        model.uncoverTile(row, col);
                    }
                    if (row % 2 == 0){
                        model1.uncoverTile(row,col);
                    }
                    if (row % 5== 0){
                        model3.uncoverTile(row,col);
                    }
                    if (row % 3 == 0){
                        model2.uncoverTile(row,col);
                    }


                }
            }
        }
        /**
        model.updateLeaderboard();
        model1.updateLeaderboard();
        model2.updateLeaderboard();
        model3.updateLeaderboard();
        assert(model.leaderboard.playerScores.get("Marc") > model.leaderboard.playerScores.get("Bennet") &&
                model.leaderboard.playerScores.get("Bennet") > model.leaderboard.playerScores.get("Remy") &&
                model.leaderboard.playerScores.get("Remy") > model.leaderboard.playerScores.get("Steven"));
         **/
    }


    public void sortLeaderboardScores(Leaderboard leaderboard){
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : leaderboard.playerScores.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        Collections.reverse(list);
        for (Integer val : list) {
            for (Map.Entry<String, Integer> entry : leaderboard.playerScores.entrySet()) {
                if (entry.getValue().equals(val)) {
                    sortedMap.put(entry.getKey(), val);
                }
            }
        }
        leaderboard.playerScores = sortedMap;
    }
    @Test
    void leaderBoardScores(){

        Leaderboard board = Leaderboard.getInstance();
        board.playerScores.put("Remy", 50);
        board.playerScores.put("Marc", 50000);
        board.playerScores.put("Steven", 5000);
        board.playerScores.put("Bennet", 500);
        sortLeaderboardScores(board);
        System.out.println(board.playerScores);

    }

}