package GameEntities.Pieces;


public class Queen  extends Piece {

    public Queen( PieceColor color) {
        this.color = color;
        this.maxHp = 200;
        this.hp = maxHp;
        this.ap = 50;
    }

    @Override
    public boolean isValid(int curX, int curY, int tarX, int tarY) {
        if(!super.isValid(curX, curY, tarX, tarY))
            return false;
        else if (Math.abs(curX - tarX) == Math.abs(curY - tarY) || (curX == tarX || curY == tarY)) //combination of bishop and rook
            return true;
        else
            return false;
    }
}