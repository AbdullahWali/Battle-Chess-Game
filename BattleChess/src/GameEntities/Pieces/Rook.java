package GameEntities.Pieces;

/**
 * Created by Ege on 11.12.2016.
 */
public class Rook extends Piece {

    @Override
    public boolean isValid(int curX, int curY, int tarX, int tarY) {
        if(!super.isValid(curX, curY, tarX, tarY))
            return false;
        else if (curX != tarX && curY != curY)
            return false;
        else
            return true;
    }
}
