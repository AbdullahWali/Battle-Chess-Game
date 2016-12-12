package GameEntities.Pieces;


public class King extends  Piece{

    public King(PieceColor color) {
        this.color = color;
        this.maxHp = 500;
        this.hp = maxHp;
        this.ap = 100;
    }

    @Override
    public boolean isValid(int curX, int curY, int tarX, int tarY) {
        if(!super.isValid(curX, curY, tarX, tarY))
            return false;
        else if (Math.abs(curX - tarX) <= 1 && Math.abs(curY - tarY) <= 1)
            return true;
        else
            return false;
    }
}
