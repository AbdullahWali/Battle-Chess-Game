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

    Point selected;

    public BoardPanel(GameManager gameManager){
        super();

        this.gameManager = gameManager;
        pieceArray = new PieceButton[8][8];
        setLayout(new GridLayout(8,8));

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                pieceArray[i][j] = new PieceButton(i,j, gameManager.getGameBoard().getPiece(i,j));

                if(gameManager.getGameBoard().getPiece(i,j) != null)
                    pieceArray[i][j].setText(pieceArray[i][j].curX + " " + pieceArray[i][j].curY);
                else
                    pieceArray[i][j].setText(".");

                add(pieceArray[i][j]);
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

                if(selected != null) {
                    gameManager.action(selected.x, selected.y, curX, curY);
                    consoleDraw();
                }
                if(piece != null)
                    selected = new Point(curX, curY);
                else
                    selected = null;

                reDraw();
            }
        }
    }

    private void reDraw(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                pieceArray[i][j].piece = gameManager.getGameBoard().getPiece(i,j);
            }
        }

        repaint();
    }

    private void consoleDraw() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if(pieceArray[i][j].piece != null)
                    System.out.print (pieceArray[i][j].piece.getClass().getSimpleName() + "\t");
                else
                    System.out.print(".\t");
            }
            System.out.print("\n");
        }
    }

}
