package UserInterface;

import GameEntities.Pieces.Piece;
import GameLogic.GameManager;

import javax.swing.*;

/**
 * Created by Ege on 15.12.2016.
 */
public class InfoPanel extends JPanel {

    private GameManager gameManager;
    private JTextArea infoText;
    private JButton skillButton;

    public InfoPanel (GameManager gameManager){
        this.gameManager = gameManager;
        infoText = new JTextArea();
        skillButton = new JButton("Use Ability");

        add(infoText);
        add(skillButton);
    }

    public void updateInfo(Piece piece)
    {
        infoText.setText("Name: " + piece.getClass().getSimpleName() +
        "\nHP: " + piece.getHP() +
        "\nAP: " + piece.getAP());
    }

    public void clearInfo(){
        infoText.setText("");
    }
}
