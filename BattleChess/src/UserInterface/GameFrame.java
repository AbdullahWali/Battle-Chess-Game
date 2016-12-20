package UserInterface;

import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Ege on 12.12.2016.
 */
public class GameFrame extends JFrame implements ActionListener{

    BoardPanel boardPanel;
    GameManager gameManager;
    InfoPanel infoPanel;
    Point selected = null;

    JButton elimination;
    JButton assassination;

    GameFrame self;


    @Override
    public void actionPerformed(ActionEvent e) {
        BoardPanel.PieceButton source = (BoardPanel.PieceButton) e.getSource();

        boardPanel.reDraw();

        if (!infoPanel.targetSkillActivated) {
            if (selected != null) {
                gameManager.action(selected.x, selected.y, source.curX, source.curY);
                selected = null;
                boardPanel.reDraw();
            } else if (source.piece != null) {
                selected = new Point(source.curX, source.curY);
                infoPanel.updateInfo(source.piece, source.curX, source.curY);
                boardPanel.highlight(source.curX, source.curY, source.piece);
            } else {
                selected = null;
                infoPanel.clearInfo();
                boardPanel.reDraw();

            }
        }

        //If Ability button was clicked, attack target
        else if (infoPanel.targetSkillActivated && (source.curX != infoPanel.curX || source.curY != infoPanel.curY)) {
            int tarX = source.curX;
            int tarY = source.curY;
            gameManager.getGameBoard().getPiece(infoPanel.curX, infoPanel.curY).getAbility().useAbility(gameManager.getGameBoard(), infoPanel.curX, infoPanel.curY, tarX, tarY );
            boardPanel.reDraw();
        }

        //Reset The Ability Click
        infoPanel.targetSkillActivated = false;

        //boardPanel.consoleDraw();
        validate();
        repaint();
    }

    /*private class MyMouseListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);

            BoardPanel.PieceButton source = (BoardPanel.PieceButton) e.getSource();
            infoPanel.updateHover(source.piece);
        }
    }*/




    public GameFrame(){
        super();

        self = this;

        gameManager = new GameManager(GameManager.GameMode.ASSASSINATION);
        infoPanel = new InfoPanel(gameManager, self.boardPanel);
        boardPanel = new BoardPanel(gameManager, infoPanel, this);

        setLayout(new GridLayout(2,1));
        setPreferredSize(new Dimension(900,900));
        setResizable(false);

        elimination = new JButton("Elimination");
        elimination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager = new GameManager(GameManager.GameMode.ELIMINATION);

                infoPanel = new InfoPanel(gameManager, self.boardPanel);

                boardPanel = new BoardPanel(gameManager, infoPanel, self);

                setLayout(new BorderLayout());


                boardPanel.setVisible(true);
                infoPanel.setVisible(true);

                getContentPane().add(boardPanel, BorderLayout.CENTER);
                getContentPane().add(infoPanel, BorderLayout.EAST);

                getContentPane().remove(elimination);
                getContentPane().remove(assassination);


                validate();
                repaint();
            }
        });

        assassination = new JButton("Assassination");
        assassination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager = new GameManager(GameManager.GameMode.ASSASSINATION);

                infoPanel = new InfoPanel(gameManager, self.boardPanel);

                boardPanel = new BoardPanel(gameManager, infoPanel, self);

                setLayout(new BorderLayout());


                boardPanel.setVisible(true);
                infoPanel.setVisible(true);

                getContentPane().add(boardPanel, BorderLayout.CENTER);
                getContentPane().add(infoPanel, BorderLayout.EAST);

                getContentPane().remove(elimination);
                getContentPane().remove(assassination);

                validate();
                repaint();
            }
        });

        getContentPane().add(elimination);
        getContentPane().add(assassination);

       // boardPanel.addMouseListener(new MyMouseListener());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
