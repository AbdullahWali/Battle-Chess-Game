package GameLogic;

import GameEntities.Pieces.King;
import GameEntities.Pieces.Piece;

/**

 */
public class GameManager {

    private enum GameMode {
           ELIMINATION, ASSASSINATION
    }

    public GameManager(GameMode gameMode) {
        this.gameMode = gameMode;
        gameBoard = new Board();
        turn = 1;
    }


    private GameMode gameMode;
    private Board gameBoard;
    private int turn;

    


    //Methods
    public boolean isGameOver() {
        if  ( gameMode == GameMode.ELIMINATION) {
            boolean whiteTeamDead = true;
            boolean blackTeamDead = true;

            for (int i = 0; i < 7; i++){
                for (int j = 0; j < 7; j++) {
                    Piece p = gameBoard.getPiece(i, j);
                    if (p instanceof Piece) {
                        if (p.getColor() == Piece.PieceColor.BLACK) blackTeamDead = false;
                        if (p.getColor() == Piece.PieceColor.WHITE) whiteTeamDead = false;
                    }
                }
            }
            return (whiteTeamDead || blackTeamDead);
        }
        else if (gameMode == GameMode.ASSASSINATION ) {
            boolean whiteKingDead = true;
            boolean blackKingDead = true;


            for (int i = 0 ; i < 7; i++){
                for (int j = 0; j < 7; j++) {
                    Piece p = gameBoard.getPiece(i, j);
                    if (p instanceof King) {
                        if (p.getColor() == Piece.PieceColor.BLACK) blackKingDead = false;
                        if (p.getColor() == Piece.PieceColor.WHITE) whiteKingDead = false;
                    }
                }
            }
            return ( whiteKingDead || blackKingDead);
        }
        System.err.println("GameMode was not set");
        return false;
    }


}
