package GameLogic;

import GameEntities.Pieces.King;
import GameEntities.Pieces.Piece;

public class GameManager {

    public enum GameMode {
           ELIMINATION, ASSASSINATION
    }

    public GameManager(GameMode gameMode) {
        this.gameMode = gameMode;
        gameBoard = new Board();
        turn = 1;
    }


    private GameMode gameMode;

    public Board getGameBoard() {
        return gameBoard;
    }

    private Board gameBoard;
    private int turn;



    //Methods
    public Piece.PieceColor getTurn() {
        if (turn % 2 == 0)
            return Piece.PieceColor.BLACK;
        else
            return Piece.PieceColor.WHITE;
    }

    private boolean isGameOver() {
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

    public void action(int curX, int curY, int tarX, int tarY){
        if (gameBoard.getPiece(tarX, tarY) == null)
            move(curX, curY, tarX, tarY);
        else
            attack(curX , curY , tarX ,tarY);
    }

    private void move( int curX, int curY, int tarX, int tarY ){
        Piece selectedPiece = gameBoard.getPiece(curX,curY);

        if (selectedPiece == null)
            return;
        if (!selectedPiece.isValid(curX,curY,tarX, tarY))
            return;
        if (getTurn() != selectedPiece.getColor())
            return;
        if(checkPath(curX, curY, tarX, tarY))
            return;

        gameBoard.movePiece(curX, curY, tarX, tarY);
        endTurn();
    }

    private void attack( int curX, int curY, int tarX, int tarY ){
        //Move Validation:
        Piece selectedPiece = gameBoard.getPiece(curX,curY);
        Piece enemyPiece = gameBoard.getPiece(tarX,tarY);
        if (selectedPiece == null || enemyPiece == null)
            return;
        if (!selectedPiece.isValid(curX,curY,tarX, tarY))
            return;
        if (selectedPiece.getColor() == enemyPiece.getColor())
            return;
        if (getTurn() != selectedPiece.getColor())
            return;
        if(checkPath(curX, curY, tarX, tarY))
            return;

        //Attaaaaaack
        enemyPiece.changeHP(-(selectedPiece.getAP()));
        endTurn();
    }

    private void endTurn() {
        gameBoard.cleanBoard();
        if (isGameOver()) {
            //TODO: Create game over thingy
        }
        turn++;
    }

    private boolean checkPath(int curX, int curY, int tarX, int tarY){
        Piece p = gameBoard.getPiece(curX, curY);
        boolean pathClear = true;

        int i = curX;
        int j = curY;

        if(tarX > curX)
            i = curX + 1;
        if(tarX < curX)
            i = curX - 1;
        if(tarY > curY)
            j = curY + 1;
        if(tarY < curY)
            j = curY - 1;

       while(i != tarX && j != tarY){
           if(gameBoard.isOccupied(i, j))
               pathClear = false;

           if(tarX > curX)
               i++;
           if(tarX < curX)
               i--;
           if(tarY > curY)
               j++;
           if(tarY < curY)
               j--;
       }

       return pathClear;
    }

}