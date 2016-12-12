package GameEntities.Pieces;


public class Pawn extends Piece{

    public Pawn( PieceColor color ) {
        this.color = color;
        this.maxHp = 50;
        this.hp = maxHp;
        this.ap = 20;
    }


    @Override
    public boolean isValid(int curX, int curY, int tarX, int tarY) {
        if ( !super.isValid(curX, curY, tarX, tarY))
            return false;
        if ( getColor() == PieceColor.WHITE) {
            if (curX == tarX && (tarY - curY ==1))
                return true;
            if ( (Math.abs(tarX-curX) == 1) && (tarY-curY)==1)
                return true;
        }
        if ( getColor() == PieceColor.BLACK) {
            if (curX == tarX && (curY - tarY ==1))
                return true;
            if ( (Math.abs(tarX-curX) == 1) && (curY-tarY)==1)
                return true;
        }
        return false;
    }



}
