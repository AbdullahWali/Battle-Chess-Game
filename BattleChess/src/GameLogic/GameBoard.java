package GameLogic;

import GameEntities.Pieces.*;

public class GameBoard {

    private Piece[][] board;

    public GameBoard() {

        board = new Piece[8][8];

        //Construct White Pieces
        board[7][0] = new Rook(Piece.PieceColor.WHITE);
        board[7][1] = new Knight(Piece.PieceColor.WHITE);
        board[7][2] = new Bishop(Piece.PieceColor.WHITE);
        board[7][3] = new King(Piece.PieceColor.WHITE);
        board[7][4] = new Queen(Piece.PieceColor.WHITE);
        board[7][5] = new Bishop(Piece.PieceColor.WHITE);
        board[7][6] = new Knight(Piece.PieceColor.WHITE);
        board[7][7] = new Rook(Piece.PieceColor.WHITE);
        for (int i = 0 ; i < 8; i++ )
            board[6][i] = new Pawn(Piece.PieceColor.WHITE);

        //Construct Black Pieces
        board[0][0] = new Rook(Piece.PieceColor.BLACK);
        board[0][1] = new Knight(Piece.PieceColor.BLACK);
        board[0][2] = new Bishop(Piece.PieceColor.BLACK);
        board[0][3] = new King(Piece.PieceColor.BLACK);
        board[0][4] = new Queen(Piece.PieceColor.BLACK);
        board[0][5] = new Bishop(Piece.PieceColor.BLACK);
        board[0][6] = new Knight(Piece.PieceColor.BLACK);
        board[0][7] = new Rook(Piece.PieceColor.BLACK);
        for (int i = 0 ; i < 8; i++ )
            board[1][i] = new Pawn(Piece.PieceColor.BLACK);
    }

    public boolean isOccupied (int tarX, int tarY) {
        if (board[tarX][tarY] == null)
            return false;
        else
            return true;
    }

    public void removePiece (int tarX, int tarY) {
        //Review: Check if safety constraints needed
        board[tarX][tarY] = null;
    }

    public Piece getPiece (int tarX, int tarY) {
        return board[tarX][tarY];
    }

    public void movePiece (int curX, int curY, int tarX, int tarY) {
            board[tarX][tarY] = board[curX][curY];
            board[curX][curY] = null;
    }

    //Checks Dead Pieces and removes them
    public void cleanBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece curPiece = getPiece(i, j);
                if (curPiece != null) {
                    if (curPiece.getHP() <= 0)
                        removePiece(i, j);
                    //curPiece.getAbility().updateCooldown();
                }
            }
        }
    }
}
