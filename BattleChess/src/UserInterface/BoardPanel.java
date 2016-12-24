package UserInterface;

import GameEntities.Pieces.*;
import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Ege & Wali on 12.12.2016.
 */
public class BoardPanel extends JPanel {


    PieceButton[][] pieceArray;
    GameManager gameManager;
    InfoPanel infoPanel;
    GameFrame frame;

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
                    pieceArray[i][j].setIcon(getPieceIcon(pieceArray[i][j].piece));
                else
                    pieceArray[i][j].setText("");

                add(pieceArray[i][j]);
            }
        }

        defaultLook();
    }

    private class MyMouseListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);

            PieceButton source = (PieceButton) e.getSource();
            infoPanel.updateHover(source.piece);
            infoPanel.updateTurnText();

        }
    }

    public class PieceButton extends JButton{

        protected int curX, curY;
        protected Piece piece;

        public PieceButton(int curX, int curY, Piece piece){
            this.curX = curX;
            this.curY = curY;
            this.piece = piece;
            addActionListener(frame);
            addMouseListener(new MyMouseListener());
        }
    }

    public void reDraw(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                pieceArray[i][j].piece = gameManager.getGameBoard().getPiece(i,j);


                if(pieceArray[i][j].piece != null)
                {
                    pieceArray[i][j].setIcon(getPieceIcon(pieceArray[i][j].piece));
                }
                else
                {
                    pieceArray[i][j].setIcon(null);
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

    private  ImageIcon getPieceIcon( Piece p){
        if (p == null ) {
            return null;
        }
        ImageIcon icon = null;
        //Whites
        if (p instanceof King && p.getColor() == 1) icon = new ImageIcon(this.getClass().getResource("/assets/wk.png"));
        if (p instanceof Queen && p.getColor() == 1) icon = new ImageIcon(this.getClass().getResource("/assets/wq.png"));
        if (p instanceof Knight && p.getColor() == 1) icon = new ImageIcon(this.getClass().getResource("/assets/wn.png"));
        if (p instanceof Rook && p.getColor() == 1) icon = new ImageIcon(this.getClass().getResource("/assets/wr.png"));
        if (p instanceof Bishop && p.getColor() == 1) icon = new ImageIcon(this.getClass().getResource("/assets/wb.png"));
        if (p instanceof Pawn && p.getColor() == 1) icon = new ImageIcon(this.getClass().getResource("/assets/wp.png"));

        //Black
        if (p instanceof King && p.getColor() == 0) icon = new ImageIcon(this.getClass().getResource("/assets/bk.png"));
        if (p instanceof Queen && p.getColor() == 0) icon = new ImageIcon(this.getClass().getResource("/assets/bq.png"));
        if (p instanceof Knight && p.getColor() == 0) icon = new ImageIcon(this.getClass().getResource("/assets/bn.png"));
        if (p instanceof Rook && p.getColor() == 0) icon = new ImageIcon(this.getClass().getResource("/assets/br.png"));
        if (p instanceof Bishop && p.getColor() == 0) icon = new ImageIcon(this.getClass().getResource("/assets/bb.png"));
        if (p instanceof Pawn && p.getColor() == 0) icon = new ImageIcon(this.getClass().getResource("/assets/bp.png"));

        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        return icon;
    }

    public void highlight(int curX, int curY, Piece piece){
        if (gameManager.getTurn() != gameManager.getGameBoard().getPiece(curX,curY).getColor()){
            return;
        }
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
                pieceArray[i][j].setBackground(Color.gray);
            }
        }
    }

}
