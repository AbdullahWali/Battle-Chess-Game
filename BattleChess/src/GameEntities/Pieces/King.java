package GameEntities.Pieces;

/**

 */
public class King extends  Piece{

    @Override
    public boolean isValid(int curX, int curY, int tarX, int tarY) {
        if(!super.isValid(curX, curY, tarX, tarY))
            return false;
        else if (Math.abs(curX - tarX) > 1 || Math.abs(curY - tarY) > 1)
            return false;
        else
            return true;
    }
}
