package GameEntities.Pieces;

import GameEntities.Abilities.Barrage;

public class Queen  extends Piece
{
    public Queen (int color)
    {
        this.color = color;
        this.maxHp = 200;
        this.hp = maxHp;
        this.ap = 50;
        this.skill = new Barrage();
    }

    @Override
    public boolean isValid (int curX, int curY, int tarX, int tarY)
    {
        if (!super.isValid(curX, curY, tarX, tarY))
        {
            return false;
        }
        if (Math.abs(curX - tarX) == Math.abs(curY - tarY) || (curX == tarX || curY == tarY))
        {
            return true;
        }
        return false;
    }
}
