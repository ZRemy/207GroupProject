package Model.AdversarialAI;

import Model.GridItem.Bomb;
import Model.GridItem.BonusLife;
import Model.GridItem.Empty;
import Model.GridItem.GridItem;
import Model.Player;
import Model.Board.SweeperBoard;
import Model.SweeperModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdversarialAITests {

    @Test
    void TestInit() {
        SweeperBoard board = new SweeperBoard(16, 16, 40, 9);
        Player player = new Player(0, "Steven", 1);
        AdversarialAI ai = new AdversarialAI(0, 1);
        SweeperModel model = new SweeperModel(board, 0, 0, player, ai);

        assertEquals(1, ai.lives);
        assertEquals(0, ai.score);
        AIDifficulty difficulty = new DifficultyEasy();
        assertTrue(ai.difficulty instanceof DifficultyEasy);
    }

    @Test
    void TestAIMove() {
        SweeperBoard board = new SweeperBoard(16, 16, 40, 9);
        Player player = new Player(0, "Steven", 1);
        AdversarialAI ai = new AdversarialAI(0, 1);
        ai.setDifficulty("easy");
        SweeperModel model = new SweeperModel(board, 0, 0, player, ai);
        GridItem gridItem = ai.AIMove(model.getBoard());
        gridItem.applyGridItem();
        assertTrue(gridItem instanceof Bomb || gridItem instanceof BonusLife || gridItem instanceof Empty);
        int score = model.uncoverTileAI(gridItem);
        System.out.println(score);
        GridItem gridItem2 = ai.AIMove(model.getBoard());
        GridItem gridItem3 = ai.AIMove(model.getBoard());
        System.out.println(model.uncoverTileAI(gridItem2));
        System.out.println(model.uncoverTileAI(gridItem3));

        // Once SweeperModel is fully implemented to support the AdversarialAI, AIMove can be
        // Properly tested.
    }


}
