package UserInterface;

import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ege on 12.12.2016.
 */
public class GameFrame extends JFrame implements ActionListener{

    BoardPanel boardPanel;
    GameManager gameManager;
    InfoPanel infoPanel;
    Point selected = null;


    @Override
    public void actionPerformed(ActionEvent e) {
        BoardPanel.PieceButton source = (BoardPanel.PieceButton) e.getSource();

        if (!infoPanel.targetSkillActivated) {
            if (selected != null) {
                gameManager.action(selected.x, selected.y, source.curX, source.curY);
                selected = null;
            } else if (source.piece != null) {
                selected = new Point(source.curX, source.curY);
                infoPanel.updateInfo(source.piece, source.curX, source.curY);
                boardPanel.highlight(source.curX, source.curY, source.piece);
            } else {
                selected = null;
                infoPanel.clearInfo();
            }
        }

        //If Ability button was clicked, attack target
        else if (infoPanel.targetSkillActivated && (source.curX != infoPanel.curX || source.curY != infoPanel.curY)) {
            int tarX = source.curX;
            int tarY = source.curY;
            gameManager.getGameBoard().getPiece(infoPanel.curX, infoPanel.curY).getAbility().useAbility(gameManager.getGameBoard(), infoPanel.curX, infoPanel.curY, tarX, tarY );
        }

        //Reset The Ability Click
        infoPanel.targetSkillActivated = false;


        boardPanel.consoleDraw();
        boardPanel.reDraw();
        validate();
        repaint();
    }

    public GameFrame(){
        super();
        gameManager = new GameManager(GameManager.GameMode.ASSASSINATION);
        infoPanel = new InfoPanel(gameManager);
        boardPanel = new BoardPanel(gameManager, infoPanel, this);


        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900,900));
        setResizable(false);
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(infoPanel , BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


}
