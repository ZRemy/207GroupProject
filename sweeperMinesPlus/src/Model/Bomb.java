package Model;

public class Bomb implements GridItem {
    private String name;
    private int strength;
    public Bomb(String n, int s){
        name = n;
        strength = s;
    }
    @Override
    public int applygridItem() {

        return 0;
    }
}
