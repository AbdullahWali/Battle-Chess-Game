package UserInterface;

import GameEntities.Pieces.Piece;
import GameLogic.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ege on 12.12.2016.
 */
public class BoardPanel extends JPanel {

    JButton[] pieceArray;

    public BoardPanel(Board board){
        super();

        pieceArray = new JButton[64];
        setLayout(new GridLayout(8,8));

        for(int i = 0; i < 64; i++){
            pieceArray[i] = new JButton("" + i);
        }

        pieceArray[1].setText("ege");

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board.getPiece(i,j) != null)
                    pieceArray[(i*8) + j].setText(board.getPiece(i,j).getClass().toString());
                add(pieceArray[(i*8) + j]);
            }
        }
    }


}
