package GameEntities.Pieces;

import GameEntities.Abilities.Prayer;

public class Bishop extends Piece
{
    public Bishop (int color)
    {
        this.color = color;
        this.maxHp = 100;
        this.hp = maxHp;
        this.ap = 20;
        this.normalAP = ap;
        this.skill = new Prayer();
    }

    @Override
    public boolean isValid (int curX, int curY, int tarX, int tarY)
    {
        if (!super.isValid(curX, curY, tarX, tarY))
        {
            return false;
        }
        if (Math.abs(curX - tarX) == Math.abs(curY - tarY))
        {
            return true;
        }
        return false;
    }
}
