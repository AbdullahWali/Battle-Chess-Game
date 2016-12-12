package GameLogic;

import GameEntities.Pieces.*;


public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];

        //Construct White Pieces
        board[0][0] = new Rook(Piece.PieceColor.WHITE);
        board[1][0] = new Knight(Piece.PieceColor.WHITE);
        board[2][0] = new Bishop(Piece.PieceColor.WHITE);
        board[3][0] = new King(Piece.PieceColor.WHITE);
        board[4][0] = new Queen(Piece.PieceColor.WHITE);
        board[5][0] = new Bishop(Piece.PieceColor.WHITE);
        board[6][0] = new Knight(Piece.PieceColor.WHITE);
        board[7][0] = new Rook(Piece.PieceColor.WHITE);
        for (int i = 0 ; i < 8; i++ )
            board[i][1] = new Pawn(Piece.PieceColor.WHITE);


        //Construct Black Pieces
        board[0][7] = new Rook(Piece.PieceColor.WHITE);
        board[1][7] = new Knight(Piece.PieceColor.WHITE);
        board[2][7] = new Bishop(Piece.PieceColor.WHITE);
        board[3][7] = new King(Piece.PieceColor.WHITE);
        board[4][7] = new Queen(Piece.PieceColor.WHITE);
        board[5][7] = new Bishop(Piece.PieceColor.WHITE);
        board[6][7] = new Knight(Piece.PieceColor.WHITE);
        board[7][7] = new Rook(Piece.PieceColor.WHITE);
        for (int i = 0 ; i < 8; i++ )
            board[i][6] = new Pawn(Piece.PieceColor.WHITE);
    }

    public boolean isOccupied( int tarX, int tarY) {
        if (board[tarX][tarY] == null ) return false;
        else return true;
    }

    public void removePiece(int tarX, int tarY) {
        //TODO:Review: Check if safety constraints needed
        board[tarX][tarY] = null;
    }

    public Piece getPiece(int tarX, int tarY) {
        return board[tarX][tarY];
    }

    public void movePiece( int curX, int curY, int tarX, int tarY) {
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
                    curPiece.getAbility().updateCooldown();

                }
            }
        }
    }



}
