package Model;

public class BonusLife implements GridItem{
    private String name;
    private int strength;

    public BonusLife(String n, int s){
        name = n;
        strength = s;
    }
    @Override
    public int applygridItem() {
        return 0;
    }
}
