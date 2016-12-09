package GameLogic;

import GameEntities.Pieces.Piece;

/**

 */
public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
    }

    public boolean isOccupied( int tarX, int tarY) {
        if (board[tarX][tarY] == null ) return false;
        else return true;
    }

    public void removePiece(int tarX, int tarY) {
        //Review: Check if safety constraints needed
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
