package GameLogic;

import GameEntities.Pieces.*;

public class GameBoard
{
    private Piece[][] board;

    public GameBoard()
    {
        board = new Piece[8][8];

        //Construct White Pieces
        board[7][0] = new Rook(1);
        board[7][1] = new Knight(1);
        board[7][2] = new Bishop(1);
        board[7][3] = new King(1);
        board[7][4] = new Queen(1);
        board[7][5] = new Bishop(1);
        board[7][6] = new Knight(1);
        board[7][7] = new Rook(1);
        for (int i = 0 ; i < 8; i++ ) {
            board[6][i] = new Pawn(1);
        }

        //Construct Black Pieces
        board[0][0] = new Rook(0);
        board[0][1] = new Knight(0);
        board[0][2] = new Bishop(0);
        board[0][3] = new King(0);
        board[0][4] = new Queen(0);
        board[0][5] = new Bishop(0);
        board[0][6] = new Knight(0);
        board[0][7] = new Rook(0);
        for (int i = 0 ; i < 8; i++ ) {
            board[1][i] = new Pawn(0);
        }
    }

    public boolean isOccupied (int tarX, int tarY)
    {
        if (board[tarX][tarY] == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public void removePiece (int tarX, int tarY)
    {
        board[tarX][tarY] = null;
    }

    public Piece getPiece (int tarX, int tarY)
    {
        if (tarX < 0 || tarX >7  || tarY < 0 || tarY > 7 ) {
            return null;
        }
        else {
            return board[tarX][tarY];
        }
    }

    public void movePiece (int curX, int curY, int tarX, int tarY)
    {
            board[tarX][tarY] = board[curX][curY];
            board[curX][curY] = null;
    }

    public void cleanBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece curPiece = getPiece(i, j);
                if (curPiece != null) {
                    if (curPiece.getHP() <= 0) {
                        removePiece(i, j);
                    }
                    else{
                        //curPiece.getAbility().updateCooldown();
                    }
                }
            }
        }
    }
}
