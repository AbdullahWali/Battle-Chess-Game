package UserInterface;

import GameEntities.Abilities.Ability;
import GameEntities.Pieces.Piece;
import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ege on 15.12.2016.
 */
public class InfoPanel extends JPanel implements ActionListener {

    private GameManager gameManager;
    private JTextArea infoText;
    private JButton skillButton;
    private Piece piece;
    int curX, curY, tarX, tarY;
    boolean targetSkillActivated;

    public InfoPanel (GameManager gameManager){
        this.gameManager = gameManager;
        infoText = new JTextArea();
        skillButton = new JButton("Use Ability");
        tarX = -1;
        tarY = -1;
        targetSkillActivated = false;


        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(piece != null){
                    Ability ability =  piece.getAbility();
                    if (ability == null || ability.isPassive()) {
                        return;
                    }
                    else if (!ability.isTargeted()) {
                        piece.getAbility().useAbility(gameManager.getGameBoard(), curX, curY);
                    }
                    else if (ability.isTargeted() && !targetSkillActivated) {
                        targetSkillActivated = true;
                    }
                }
            }
        });

        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(100, 800));

        add(infoText);
        add(skillButton);
    }



    public void updateInfo(Piece piece, int curX, int curY)
    {
        this.piece = piece;
        this.curX = curX;
        this.curY  = curY;

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


    @Override
    public void actionPerformed(ActionEvent e) {
        BoardPanel.PieceButton source =  ( BoardPanel.PieceButton) e.getSource();

        //If Ability button was clicked, attack target
        if (targetSkillActivated && (source.curX != curX || source.curY != curY)) {
            tarX = source.curX;
            tarY = source.curY;
            piece.getAbility().useAbility(gameManager.getGameBoard(), curX, curY, tarX, tarY );
        }

        //Reset The Ability Click
        targetSkillActivated = false;

    }
}
