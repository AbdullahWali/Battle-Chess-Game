package UserInterface;

import GameEntities.Pieces.Piece;
import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Ege on 12.12.2016.
 */
public class BoardPanel extends JPanel {


    PieceButton[][] pieceArray;
    GameManager gameManager;
    InfoPanel infoPanel;
    GameFrame frame;
    Point selected;

    public BoardPanel(GameManager gameManager, InfoPanel infoPanel, GameFrame frame){
        super();

        this.frame = frame;
        this.gameManager = gameManager;
        this.infoPanel = infoPanel;
        pieceArray = new PieceButton[8][8];

        setLayout(new GridLayout(8,8));
        setPreferredSize(new Dimension(800,800));


        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                pieceArray[i][j] = new PieceButton(i,j, gameManager.getGameBoard().getPiece(i,j));

                if(gameManager.getGameBoard().getPiece(i,j) != null)
                    pieceArray[i][j].setText(pieceArray[i][j].piece.getClass().getSimpleName());
                else
                    pieceArray[i][j].setText("");

                add(pieceArray[i][j]);
            }
        }

        defaultLook();
    }

    public class PieceButton extends JButton{

        protected int curX, curY;
        protected Piece piece;

        public PieceButton(int curX, int curY, Piece piece){
            this.curX = curX;
            this.curY = curY;
            this.piece = piece;
            addActionListener(frame);
        }
    }

    public void reDraw(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                pieceArray[i][j].piece = gameManager.getGameBoard().getPiece(i,j);

                if(pieceArray[i][j].piece != null)
                {
                    pieceArray[i][j].setText(pieceArray[i][j].piece.getClass().getSimpleName());
                }
                else
                {
                    pieceArray[i][j].setText("");
                }
            }
        }
        defaultLook();

    }

    public void consoleDraw() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if(pieceArray[i][j].piece != null)
                    System.out.print (pieceArray[i][j].piece.getClass().getSimpleName() + "\t");
                else
                    System.out.print(".....\t");
            }
            System.out.print("\n");
        }
    }

    public void highlight(int curX, int curY, Piece piece){
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(piece.isValid(curX, curY, i, j)){
                    if(gameManager.checkPath(curX, curY, i, j) && gameManager.getGameBoard().getPiece(i, j) == null){
                        pieceArray[i][j].setBackground(Color.GREEN);
                    }
                    else if(gameManager.checkPath(curX, curY, i, j) && gameManager.getGameBoard().getPiece(i, j) != null && gameManager.getGameBoard().getPiece(i, j).getColor() != piece.getColor()){
                        pieceArray[i][j].setBackground(Color.RED);
                    }
                }
            }
        }
    }

    public void skillHighlight(int curX, int curY, Piece piece){
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(piece.isValid(curX, curY, i, j)) {
                    pieceArray[i][j].setBackground(Color.PINK);
                }
            }
        }
    }

    public void defaultLook(){
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceArray[i][j].piece != null){
                    if(pieceArray[i][j].piece.getColor() == 1){
                        pieceArray[i][j].setBackground(Color.white);
                        pieceArray[i][j].setForeground(Color.black);
                    }
                    else{
                        pieceArray[i][j].setBackground(Color.black);
                        pieceArray[i][j].setForeground(Color.white);
                    }
                }
                else{
                    pieceArray[i][j].setBackground(Color.gray);
                }
            }
        }
    }
}
