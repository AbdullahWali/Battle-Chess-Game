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

    Point selected;

    public BoardPanel(GameManager gameManager, InfoPanel infoPanel){
        super();

        this.gameManager = gameManager;
        this.infoPanel = infoPanel;
        pieceArray = new PieceButton[8][8];

        setLayout(new GridLayout(8,8));
        setPreferredSize(new Dimension(800,800));

        int index = 0; //For Color Distribtuion
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                Color color = index % 2 == 0 ? Color.BLACK : Color.WHITE;
                pieceArray[i][j] = new PieceButton(i,j, gameManager.getGameBoard().getPiece(i,j), color);
                index++;

                if(gameManager.getGameBoard().getPiece(i,j) != null)
                    pieceArray[i][j].setText(pieceArray[i][j].piece.getColor() + pieceArray[i][j].piece.getClass().getSimpleName());
                else
                    pieceArray[i][j].setText("");

                add(pieceArray[i][j]);
            }
            index++;
        }
    }

    public class PieceButton extends JButton{

        protected int curX, curY;
        protected Piece piece;

        public PieceButton(int curX, int curY, Piece piece, Color color){
            this.curX = curX;
            this.curY = curY;
            this.piece = piece;
            setBackground(color);
            addActionListener(new ButtonListener());
            addActionListener(infoPanel);
            
        }

        public class ButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

                if(selected != null && !infoPanel.targetSkillActivated) {
                    gameManager.action(selected.x, selected.y, curX, curY);
                    selected = null;
                }

                if(piece != null) {
                    selected = new Point(curX, curY);
                    infoPanel.updateInfo(piece, curX, curY);
                    highlight(curX, curY, piece);
                }
                else
                {
                    selected = null;
                    infoPanel.clearInfo();
                }

                consoleDraw();
                reDraw();

                validate();
                repaint();
            }
        }
    }

    private void reDraw(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                pieceArray[i][j].piece = gameManager.getGameBoard().getPiece(i,j);

                if(pieceArray[i][j].piece != null)
                {
                    pieceArray[i][j].setText(pieceArray[i][j].piece.getColor() + pieceArray[i][j].piece.getClass().getSimpleName());
                }
                else
                {
                    pieceArray[i][j].setText("");
                }
            }
        }
    }

    private void consoleDraw() {
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

    private void highlight(int curX, int curY, Piece piece){
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(piece.isValid(curX, curY, i, j)){
                    pieceArray[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }
}
