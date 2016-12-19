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

    public GameFrame(){
        super();

        gameManager = new GameManager(GameManager.GameMode.ASSASSINATION);
        infoPanel = new InfoPanel(gameManager);
        boardPanel = new BoardPanel(gameManager, infoPanel);

        setLayout(new GridLayout());
        getContentPane().add(boardPanel);
        getContentPane().add(infoPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


}
