package UserInterface;

import GameEntities.Abilities.Ability;
import GameEntities.Pieces.Piece;
import GameLogic.GameManager;
import com.sun.deploy.panel.JSmartTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ege & Wali on 15.12.2016.
 */
public class InfoPanel extends JPanel {

    private GameManager gameManager;
    private JTextArea infoText;
    public JTextArea hoverText;
    private JButton skillButton;
    private Piece piece;
    int curX, curY, tarX, tarY;
    boolean targetSkillActivated;

    public InfoPanel (GameManager gameManager, BoardPanel boardPanel){
        this.gameManager = gameManager;
        infoText = new JTextArea();
        hoverText = new JTextArea();
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
                        gameManager.useAbility(curX, curY);
                    }
                    else if (ability.isTargeted() && !targetSkillActivated) {
                        targetSkillActivated = true;
                        boardPanel.skillHighlight(curX, curY, piece);
                    }
                }
            }
        });

        setLayout(new GridLayout(3, 1));
        setPreferredSize(new Dimension(100, 800));

        add(infoText);
        add(hoverText);
        add(skillButton);
    }


    public void updateHover(Piece piece){

        hoverText.setText("asd");

        if(piece != null) {
            hoverText.setText("Name: " + piece.getClass().getSimpleName() +
                    "\nHP: " + piece.getHP() +
                    "\nAP: " + piece.getAP());
            hoverText.setVisible(true);
        }
        else{
            hoverText.setText("Name:\nHP:\nAP:");
            hoverText.setVisible(false);
        }
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


}
