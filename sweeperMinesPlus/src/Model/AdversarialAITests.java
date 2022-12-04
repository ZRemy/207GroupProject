package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdversarialAITests {

    @Test
    void TestInit() {
        SweeperBoard board = new SweeperBoard(16, 16, 40, 9);
        Player player = new Player(0, "Steven", 1);
        SweeperModel model = new SweeperModel(board, 0, 0, player);
        AdversarialAI ai = new AdversarialAI(0, "Computer", 1, "Easy");

        assertEquals(1, ai.lives);
        assertEquals(0, ai.score);
        assertEquals("Computer", ai.name);
        AIDifficulty difficulty = new DifficultyEasy();
        assertTrue(ai.difficulty instanceof DifficultyEasy);
    }

    @Test
    void TestAIMove() {
        SweeperBoard board = new SweeperBoard(16, 16, 40, 9);
        Player player = new Player(0, "Steven", 1);
        SweeperModel model = new SweeperModel(board, 0, 0, player);
        AdversarialAI ai = new AdversarialAI(0, "Computer", 1, "Easy");
        GridItem gridItem = ai.AIMove(model.board);
        // Once SweeperModel is fully implemented to support the AdversarialAI, AIMove can be
        // Properly tested.
    }


}
