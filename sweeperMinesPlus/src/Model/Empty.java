package Model;

public class Empty implements GridItem{
    private SweeperBoard board;
    public Empty(SweeperBoard b){
        throw new UnsupportedOperationException();
    }
    /*
    This method will return the number of bombs surrounding this particular grid item on the board b.
     */
    @Override
    public int applygridItem() {
        throw new UnsupportedOperationException();
    }
}
