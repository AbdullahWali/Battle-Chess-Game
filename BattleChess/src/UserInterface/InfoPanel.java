package UserInterface;

import GameEntities.Pieces.Piece;
import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ege on 15.12.2016.
 */
public class InfoPanel extends JPanel {

    private GameManager gameManager;
    private JTextArea infoText;
    private JButton skillButton;
    private Piece piece;

    public InfoPanel (GameManager gameManager){
        this.gameManager = gameManager;
        infoText = new JTextArea();
        skillButton = new JButton("Use Ability");

        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(piece != null){
                    piece.getAbility().useAbility(gameManager.getGameBoard());
                }
            }
        });

        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(100, 800));

        add(infoText);
        add(skillButton);
    }

    public void updateInfo(Piece piece)
    {
        this.piece = piece;

        infoText.setText("Name: " + piece.getClass().getSimpleName() +
        "\nHP: " + piece.getHP() +
        "\nAP: " + piece.getAP());

        infoText.setVisible(true);
        skillButton.setVisible(true);
    }

    public void clearInfo(){
        piece = null;

        infoText.setText("");
        infoText.setVisible(false);
        skillButton.setVisible(false);
    }
}
