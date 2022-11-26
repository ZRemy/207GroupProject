package Model;

public class Empty implements GridItem{
    private SweeperBoard board;
    public Empty(SweeperBoard b){
        board = b;
    }
    /*
    This method will return the number of bombs surrounding this particular grid item on the board b.
     */
    @Override
    public int applygridItem() {
        int num_surrounding = 0;
        /*
        ...
         */
        return num_surrounding;
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
