package Model.AdversarialAI;

import Model.GridItem.GridItem;
import Model.Board.SweeperBoard;

/**
 * The Difficulty of the AI
 */
public interface AIDifficulty {

    /**
     * Given a minesweeper board, depending on the difficulty set, the AdversarialAI will take it's turn
     * and make a move on the board, or returns null if there isn't a turn possible
     *
     * @param model       The current board configuration
     */
    public GridItem aiMove(SweeperBoard model);

}
