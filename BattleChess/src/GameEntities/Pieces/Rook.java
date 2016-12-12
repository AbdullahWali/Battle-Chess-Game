package GameEntities.Pieces;


public class Rook extends Piece {

    public Rook( PieceColor color ){
        this.color = color;
        this.maxHp = 100;
        this.hp = maxHp;
        this.ap = 20;
    }

    @Override
    public boolean isValid(int curX, int curY, int tarX, int tarY) {
        if(!super.isValid(curX, curY, tarX, tarY))
            return false;
        else if (curX == tarX || curY == curY)
            return true;
        else
            return false;
    }
}
