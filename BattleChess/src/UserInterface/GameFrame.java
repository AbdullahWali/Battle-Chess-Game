package UserInterface;

import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ege on 12.12.2016.
 */
public class GameFrame extends JFrame {

    BoardPanel boardPanel;
    GameManager gameManager;
    InfoPanel infoPanel;
    Point selected;


    public GameFrame(){
        super();
        gameManager = new GameManager(GameManager.GameMode.ASSASSINATION);
        infoPanel = new InfoPanel(gameManager);
        boardPanel = new BoardPanel(gameManager, infoPanel);


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
