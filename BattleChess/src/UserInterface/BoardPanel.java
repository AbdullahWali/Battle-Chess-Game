package UserInterface;

import GameEntities.Pieces.Piece;
import GameLogic.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ege on 12.12.2016.
 */
public class BoardPanel extends JPanel {

    JButton[][] pieceArray;

    public BoardPanel(Board board){
        super();

        pieceArray = new JButton[8][8];
        setLayout(new GridLayout(8,8));

        for(int j = 7; j >= 0; j--){
            for(int i = 0; i < 8; i++) {
                pieceArray[i][j] =new JButton(i + ", " + j );
                add(pieceArray[i][j]);
            }
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board.getPiece(i,j) != null)
                    pieceArray[i][j].setText(board.getPiece(i,j).getClass().getSimpleName() + board.getPiece(i,j).getColor());
            }
        }
    }


}
