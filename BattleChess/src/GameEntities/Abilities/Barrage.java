package GameEntities.Abilities;

import GameEntities.Pieces.Piece;
import GameLogic.GameBoard;
import GameLogic.GameManager;

/**
 * Created by Ege & Wali on 19/12/2016.
 */
public class Barrage extends Ability{

    public Barrage() {
        isPassive = false;
        isTargeted = true;
        cooldown = 6;
        cooldownLeft = 0;
        name = this.getClass().getSimpleName();
        desc = "The Queen can Barrage an area that it can attack. When a square is barraged, that square and every square neighbouring it receives 20 damage if it is occupied by an enemy.";
    }

    @Override
    public boolean useAbility(GameBoard board, int... coordinates) {
        if (coordinates.length < 2 ) return false;
        int curX = coordinates[0];
        int curY = coordinates[1];
        int tarX = coordinates[2];
        int tarY = coordinates[3];
        int curColour = board.getPiece(curX,curY).getColor();

        //Check that nothing obstructs the attack
        if (!board.getPiece(curX,curY).isValid(curX,curY,tarX,tarY))
            return false;

        //Checks for valid cooldown and resets if valid
        if (!super.useAbility(board,coordinates))
            return false;

        //loop over a square around curX,curY
        for (int i = tarX - 1; i <= tarX +1; i++ ) {
            for (int j = tarY - 1; j <= tarY +1; j++ ) {
                Piece piece = board.getPiece(i,j);
                if (piece != null && piece.getColor() != curColour )
                    piece.changeHP(-20);
            }
        }
        System.out.println("Ability was used: Barrage");
        return true;
    }

}
