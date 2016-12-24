package GameEntities.Pieces;

import GameEntities.Abilities.Fortify;

public class Rook extends Piece
{
    public Rook (int color)
    {
        this.color = color;
        this.maxHp = 100;
        this.hp = maxHp;
        this.ap = 20;
        this.normalAP = ap;

        this.skill = new Fortify();
    }

    public void changeHP (int amount)
    {
        //If Heal, continue as normal
        if (amount >= 0) {
            super.changeHP(amount);
            return;
        }

        //If attack
        int newAmount =  (int)(amount*0.75);
        Fortify fortSkill = (Fortify)skill;
        if (fortSkill.fortifies == 0) {
            super.changeHP(amount);
            return;
        }
        else {
            super.changeHP(newAmount);
            fortSkill.fortifies--;
            return;
        }
    }

    @Override
    public boolean isValid (int curX, int curY, int tarX, int tarY)
    {
        if (!super.isValid(curX, curY, tarX, tarY))
        {
            return false;
        }
        if (curX == tarX || curY == tarY)
        {
            return true;
        }
        return false;
    }
}
