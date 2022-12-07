package Model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GridItemTests {
    @Test
    void testFactory() {
        SweeperBoard board = new SweeperBoard(16, 16, 40, 9);
        GridItem a = GriditemFactory.createGridItem("bomb", 0, board, 0, 0);
        GridItem b = GriditemFactory.createGridItem("empty", 0, board, 1, 1);
        GridItem c = GriditemFactory.createGridItem("bonuslife", 2, board, 0, 0);
        assert(a instanceof Bomb);
        assert(b instanceof Empty);
        assert(c instanceof BonusLife);
    }
    @Test
    void testGridItemApply() {
        SweeperBoard board = new SweeperBoard(16, 16, 40, 9);
        GridItem a = GriditemFactory.createGridItem("bomb", 0, board, 0, 0);
        GridItem b = GriditemFactory.createGridItem("empty", 0, board, 1, 1);
        GridItem c = GriditemFactory.createGridItem("bonuslife", 2, board, 0, 0);
        assertEquals(-1, a.applyGridItem());
        assertEquals(2, c.applyGridItem());
        assert(b.applyGridItem() >= 0);
    }


}

