package GameLogic;

import GameEntities.Pieces.Piece;

/**

 */
public class Board {

    private Piece[][] board = new Piece[8][8];

    public boolean isOccupied( int tarX, int tarY) {
        if (board[tarX][tarY] == null ) return false;
        else return true;
    }

    public void removePiece(int tarX, int tarY) {
        //Review: Check if safety constraints needed
        board[tarX][tarY] = null;
    }



}
