package GameEntities.Pieces;

import GameEntities.Abilities.Accolade;

public class Pawn extends Piece
{
    public Pawn (int color)
    {
        this.color = color;
        this.maxHp = 50;
        this.hp = maxHp;
        this.ap = 20;
        this.normalAP = ap;

        this.skill = new Accolade();
    }

    @Override
    public boolean isValid (int curX, int curY, int tarX, int tarY)
    {
        if (!super.isValid(curX, curY, tarX, tarY))
        {
            return false;
        }
        if (getColor() == 1)
        {
            if ((Math.abs(curY - tarY) <= 1) && (curX - tarX)==1)
            {
                return true;
            }
        }
        if (getColor() == 0)
        {
            if ((Math.abs(curY - tarY) <= 1) && (tarX - curX)==1)
            {
                return true;
            }
        }
        return false;
    }
}
