package UserInterface;

import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ege on 12.12.2016.
 */
public class GameFrame extends JFrame {

    BoardPanel boardPanel;
    GameManager gameManager;

    public GameFrame(){
        super();

        gameManager = new GameManager(GameManager.GameMode.ELIMINATION);
        boardPanel = new BoardPanel(gameManager.getGameBoard());

        getContentPane().add(boardPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
