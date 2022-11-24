package Model;

import java.util.Objects;

public class GriditemFactory {
    private GridItem createGridItem(String name, int strength){
        if (Objects.equals(name, "bomb")){
            return new Bomb(name, strength);
        }
        if (Objects.equals(name, "bonuslife")){
            return new BonusLife(name, strength);
        }
        else {
            return new Empty();
        }
    }
}
