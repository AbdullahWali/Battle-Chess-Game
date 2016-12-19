package GameLogic;
import GameEntities.Pieces.King;
import GameEntities.Pieces.Piece;

public class GameManager {

    private GameMode gameMode;
    private GameBoard gameBoard;
    private int turn;
    public enum GameMode {
           ELIMINATION, ASSASSINATION
    }

    public GameManager(GameMode gameMode) {
        this.gameMode = gameMode;
        gameBoard = new GameBoard();
        turn = 1;
    }

    //Methods
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
            return (whiteKingDead || blackKingDead);
        }
        System.err.println("GameMode was not set");
        return false;
    }

    public void action(int curX, int curY, int tarX, int tarY){
        if (gameBoard.getPiece(tarX, tarY) == null) {
            move(curX, curY, tarX, tarY);
            System.out.println("move");
        }
        else {
            attack(curX, curY, tarX, tarY);
            System.out.println("attack");
        }
    }

    private void move( int curX, int curY, int tarX, int tarY ){
        Piece selectedPiece = gameBoard.getPiece(curX,curY);

        System.out.println("in");

        if (selectedPiece == null) {
            System.out.println("1");

            return;
        }
        if (!selectedPiece.isValid(curX,curY,tarX, tarY)) {
            System.out.println("2");

            return;
        }
        if (getTurn() != selectedPiece.getColor()) {
            System.out.println("3");

            return;
        }
        if(checkPath(curX, curY, tarX, tarY)) {
            System.out.println("4");

            return;
        }
        System.out.println("ouot");


        gameBoard.movePiece(curX, curY, tarX, tarY);
        endTurn();

        System.out.println("moved");
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
            System.exit(0);
        }
        turn++;
    }

    private boolean checkPath(int curX, int curY, int tarX, int tarY){
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

    public Piece.PieceColor getTurn() {
        if (turn % 2 == 0)
            return Piece.PieceColor.BLACK;
        else
            return Piece.PieceColor.WHITE;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
