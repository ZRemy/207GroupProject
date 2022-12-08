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
        AdversarialAI ai = new AdversarialAI();
        SweeperModel model = new SweeperModel(board, 0, 0, player, ai);

        assertEquals(1, ai.lives);

    }

    @Test
    void TestAIMove() {
        SweeperBoard board = new SweeperBoard(16, 16, 40, 9);
        Player player = new Player(0, "Steven", 1);
        AdversarialAI ai = new AdversarialAI();
        ai.setDifficulty("easy");
        SweeperModel model = new SweeperModel(board, 0, 0, player, ai);
        GridItem gridItem = ai.move(model.getBoard());
        gridItem.applyGridItem();
        assertTrue(gridItem instanceof Bomb || gridItem instanceof BonusLife || gridItem instanceof Empty);
        int score = model.uncoverTileAI(gridItem);
        System.out.println(score);
        GridItem gridItem2 = ai.move(model.getBoard());
        GridItem gridItem3 = ai.move(model.getBoard());
        System.out.println(model.uncoverTileAI(gridItem2));
        System.out.println(model.uncoverTileAI(gridItem3));

        // Once SweeperModel is fully implemented to support the AdversarialAI, AIMove can be
        // Properly tested.
    }


}
