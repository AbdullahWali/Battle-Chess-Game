package GameEntities.Pieces;

import GameEntities.Abilities.Whirlwind;

public class Knight extends Piece
{
    public Knight (int color)
    {
        this.color = color;
        this.maxHp = 125;
        this.hp = maxHp;
        this.ap = 50;
        this.normalAP = ap;

        this.skill = new Whirlwind();
    }

    @Override
    public boolean isValid (int curX, int curY, int tarX, int tarY)
    {
        if (!super.isValid(curX, curY, tarX, tarY))
        {
            return false;
        }
        if (curX != tarX && curY != tarY && Math.abs(curX - tarX) + Math.abs(curY - tarY) == 3)
        {
            return true;
        }
        return false;
    }
}
