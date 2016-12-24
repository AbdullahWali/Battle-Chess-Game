package GameEntities.Pieces;

public class King extends  Piece
{
    public King (int color)
    {
        this.color = color;
        this.maxHp = 200;
        this.hp = maxHp;
        this.ap = 100;
        this.normalAP = ap;

    }

    @Override
    public boolean isValid (int curX, int curY, int tarX, int tarY)
    {
        if (!super.isValid(curX, curY, tarX, tarY))
        {
            return false;
        }
        if (Math.abs(curX - tarX) <= 1 && Math.abs(curY - tarY) <= 1){
            return true;
        }
        return false;
    }
}
