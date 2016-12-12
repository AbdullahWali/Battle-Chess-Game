package GameEntities.Pieces;


public class Bishop extends Piece {

    public Bishop(PieceColor color ) {
        this.color = color;
        this.maxHp = 100;
        this.hp = maxHp;
        this.ap = 20;
    }


    @Override
    public boolean isValid(int curX, int curY, int tarX, int tarY) {
        if(!super.isValid(curX, curY, tarX, tarY))
            return false;
        else if (Math.abs(curX - tarX) != Math.abs(curY - tarY))
            return false;
        else
            return true;
    }
}
