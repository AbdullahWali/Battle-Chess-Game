package GameEntities.Abilities;

import GameEntities.Pieces.Piece;
import GameLogic.GameBoard;

/**
 * Created by Abdullah Wali on 19/12/2016.
 */
public class Prayer extends Ability {

    public Prayer() {
        isPassive = false;
        isTargeted = false;
        cooldown = 10;
        cooldownLeft = 0;
        name = this.getClass().getSimpleName();
    }

    @Override
    public boolean useAbility(GameBoard board, int... coordinates) {
        if (coordinates.length < 2 ) return false;
        int curX = coordinates[0];
        int curY = coordinates[1];
        Piece.PieceColor curColour = board.getPiece(curX,curY).getColor();

        //Checks for valid cooldown and resets if valid
        if (!super.useAbility(board,coordinates)) return false;

        //loop over a square around curX,curY
        for (int i = curX - 1; i <= curX +1; i++ ) {
            for (int j = curY - 1; j <= curY +1; j++ ) {
                if ( i != curX && j!=curY ) {
                    Piece piece = board.getPiece(i,j);
                    if (piece != null && piece.getColor() == curColour)
                        piece.changeHP(20);
                }
            }
        }
        return true;
    }
}
