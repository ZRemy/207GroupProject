package Model;

import java.util.Objects;

public class GriditemFactory {
    public static GridItem createGridItem(String name, int strength, SweeperBoard board, int x, int y){
        if (Objects.equals(name, "bomb")){
            return new Bomb(strength);
        }
        if (Objects.equals(name, "bonuslife")){
            return new BonusLife(strength);
        }
        else {
            return new Empty(board, x, y);
        }
    }
}
