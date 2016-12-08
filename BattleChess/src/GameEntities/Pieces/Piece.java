package GameEntities.Pieces;
import GameEntities.Abilities.Ability;

import java.awt.*;

/**

 */
public abstract class  Piece {

    private int maxHp;
    private int hp;
    private int ap;
    private Color color;
    private Ability skill;


    //Methods
    public boolean isValid(int curX, int curY, int tarX, int tarY) {
        if (tarX > 7 || tarX < 0  || tarY > 7 || tarY <0) return false;
        if (curX == tarX && curY == tarY ) return false;
        return true;
    };


}
