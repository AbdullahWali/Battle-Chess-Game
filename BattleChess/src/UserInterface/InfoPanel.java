package UserInterface;

import GameLogic.GameManager;

import javax.swing.*;

/**
 * Created by Ege on 15.12.2016.
 */
public class InfoPanel extends JPanel {

    GameManager gameManager;

    public InfoPanel (GameManager gameManager){
        this.gameManager = gameManager;
    }
}
