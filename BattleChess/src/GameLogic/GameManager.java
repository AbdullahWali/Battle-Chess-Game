package GameLogic;
import GameEntities.Pieces.King;
import GameEntities.Pieces.Knight;
import GameEntities.Pieces.Piece;

import java.awt.*;

public class GameManager
{
    private GameMode gameMode;
    private GameBoard gameBoard;
    private int turn;
    public enum GameMode {
           ELIMINATION, ASSASSINATION
    }

    public GameManager(GameMode gameMode)
    {
        this.gameMode = gameMode;
        gameBoard = new GameBoard();
        turn = 1;
    }

    //Methods
    private boolean isGameOver()
    {
        if  ( gameMode == GameMode.ELIMINATION)
        {
            boolean whiteTeamDead = true;
            boolean blackTeamDead = true;

            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    Piece p = gameBoard.getPiece(i, j);

                    if (p instanceof Piece)
                    {
                        if (p.getColor() == 0)
                        {
                            blackTeamDead = false;
                        }
                        if (p.getColor() == 1)
                        {
                            whiteTeamDead = false;
                        }
                    }
                }
            }
            return (whiteTeamDead || blackTeamDead);
        }
        else if (gameMode == GameMode.ASSASSINATION )
        {
            boolean whiteKingDead = true;
            boolean blackKingDead = true;

            for (int i = 0 ; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    Piece p = gameBoard.getPiece(i, j);

                    if (p instanceof King)
                    {
                        if (p.getColor() == 0)
                        {
                            blackKingDead = false;
                        }
                        if (p.getColor() == 1)
                        {
                            whiteKingDead = false;
                        }
                    }
                }
            }
            return (whiteKingDead || blackKingDead);
        }
        System.err.println("GameMode was not set");
        return false;
    }

    public void action(int curX, int curY, int tarX, int tarY)
    {
        if (gameBoard.getPiece(tarX, tarY) == null) {
            move(curX, curY, tarX, tarY);
            System.out.println("move");
        }
        else {
            attack(curX, curY, tarX, tarY);
            System.out.println("attack");
        }
    }

    public boolean useAbility( int... coordinates) {
        if (gameBoard.getPiece(coordinates[0], coordinates[1]).getAbility().useAbility(gameBoard, coordinates)) {
            endTurn();
            return true;
        }
        return false;
    }

    private void move( int curX, int curY, int tarX, int tarY )
    {
        Piece selectedPiece = gameBoard.getPiece(curX,curY);

        if (selectedPiece == null) {
            System.err.println("Error Move: null");
            return;
        }
        if (!selectedPiece.isValid(curX,curY,tarX, tarY)) {
            System.err.println("Error Move: not valid");
            return;
        }
        if (getTurn() != selectedPiece.getColor()) {
            System.err.println("Error Move: wrong color");
            return;
        }
        if(!checkPath(curX, curY, tarX, tarY)) {
            System.err.println("Error Move: path blocked");
            return;
        }

        gameBoard.movePiece(curX, curY, tarX, tarY);
        endTurn();
    }

    private void attack( int curX, int curY, int tarX, int tarY )
    {
        //Move Validation:
        Piece selectedPiece = gameBoard.getPiece(curX,curY);
        Piece enemyPiece = gameBoard.getPiece(tarX,tarY);

        if (selectedPiece == null || enemyPiece == null) {
            System.err.println("Error Attack: null");
            return;
        }
        if (!selectedPiece.isValid(curX,curY,tarX, tarY)) {
            System.err.println("Error Attack: not valid");
            return;
        }
        if (selectedPiece.getColor() == enemyPiece.getColor()) {
            System.err.println("Error Attack: friendly");
            return;
        }
        if (getTurn() != selectedPiece.getColor()) {
            System.err.println("Error Attack: wrong color");
            return;
        }
        if(!checkPath(curX, curY, tarX, tarY)) {
            System.err.println("Error Attack: path blocked");
            return;
        }

        enemyPiece.changeHP(-(selectedPiece.getAP()));
        endTurn();
    }

    private void endTurn()
    {
        gameBoard.cleanBoard();
        if (isGameOver())
        {
            //TODO: Create game over thingy
            System.exit(0);
        }
        turn++;
    }

    public boolean checkPath(int curX, int curY, int tarX, int tarY)
    {
        if(gameBoard.getPiece(curX, curY) instanceof Knight)
            return true;

        boolean pathClear = true;
        int i = curX;
        int j = curY;

        if(tarX > curX) i = curX + 1;
        if(tarX < curX) i = curX - 1;
        if(tarY > curY) j = curY + 1;
        if(tarY < curY) j = curY - 1;

       while(i != tarX || j != tarY)
       {
           if(gameBoard.isOccupied(i, j))
           {
               pathClear = false;
           }

           if(tarX > curX) i++;
           if(tarX < curX) i--;
           if(tarY > curY) j++;
           if(tarY < curY) j--;
       }
       return pathClear;
    }

    public int getTurn()
    {
        return turn % 2;
    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }
}
