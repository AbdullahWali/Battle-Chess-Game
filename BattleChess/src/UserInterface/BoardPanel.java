package UserInterface;

import GameEntities.Pieces.Piece;
import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ege on 12.12.2016.
 */
public class BoardPanel extends JPanel {


    PieceButton[][] pieceArray;
    GameManager gameManager;

    Point selectedCurrent;
    Piece selectedTarget;


    public BoardPanel(GameManager gameManager){
        super();

        this.gameManager = gameManager;
        pieceArray = new PieceButton[8][8];
        setLayout(new GridLayout(8,8));

        for(int j = 7; j >= 0; j--){
            for(int i = 0; i < 8; i++) {
                pieceArray[i][j] = new PieceButton(i,j, gameManager.getGameBoard().getPiece(i,j));
                add(pieceArray[i][j]);
            }
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(gameManager.getGameBoard().getPiece(i,j) != null)
                    pieceArray[i][j].setText(pieceArray[i][j].curX + " " + pieceArray[i][j].curY);
            }
        }
    }

    public class PieceButton extends JButton{

        protected int curX, curY;
        protected Piece piece;

        public PieceButton(int curX, int curY, Piece piece){
            this.curX = curX;
            this.curY = curY;
            this.piece = piece;

            addActionListener(new ButtonListener());

        }

        public class ButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedCurrent != null){
                    gameManager.action(selectedCurrent.x, selectedCurrent.y, curX, curY);
                }
                else
                    selectedCurrent = new Point(curX, curY);

                redraw();
            }
        }
    }

    private void redraw(){
        for(int j = 7; j >= 0; j--){
            for(int i = 0; i < 8; i++) {
                pieceArray[i][j].piece = gameManager.getGameBoard().getPiece(i,j);
            }
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(gameManager.getGameBoard().getPiece(i,j) != null)
                    pieceArray[i][j].setText(pieceArray[i][j].curX + " " + pieceArray[i][j].curY);
            }
        }

        for(int j = 7; j >= 0; j--){
            for(int i = 0; i < 8; i++) {
                pieceArray[i][j] = new PieceButton(i,j, gameManager.getGameBoard().getPiece(i,j));
            }
        }


        repaint();
    }


}
