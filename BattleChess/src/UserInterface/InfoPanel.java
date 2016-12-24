package UserInterface;

import GameEntities.Abilities.Ability;
import GameEntities.Pieces.King;
import GameEntities.Pieces.Pawn;
import GameEntities.Pieces.Piece;
import GameLogic.GameManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ege & Wali on 15.12.2016.
 */
public class InfoPanel extends JPanel {

    private JLabel turnText;
    private GameManager gameManager;
    private JTextArea infoText;
    private JTextArea hoverText;
    private JTextArea gameText;

    private JButton skillButton;
    private Piece piece;
    int curX, curY;
    boolean targetSkillActivated;

    public InfoPanel (GameManager gameManager, BoardPanel boardPanel){
        this.gameManager = gameManager;
        turnText = new JLabel();
        turnText.setHorizontalTextPosition(SwingConstants.CENTER);
        turnText.setOpaque(true);
        gameText = new JTextArea();
        gameText.setWrapStyleWord(true);
        gameText.setLineWrap(true);
        infoText = new JTextArea();
        hoverText = new JTextArea();
        hoverText.setEnabled(false);
        hoverText.setDisabledTextColor(Color.RED);
        skillButton = new JButton("Use Ability");
        skillButton.setVisible(false);
        targetSkillActivated = false;
        setBackground(Color.WHITE);

        ImageIcon abilityIcon = new ImageIcon(getClass().getResource("/assets/useAbility.png"));
        Image img = abilityIcon.getImage() ;
        img = img.getScaledInstance( 70, 70,  java.awt.Image.SCALE_SMOOTH ) ;
        abilityIcon = new ImageIcon( img );

        skillButton.setIcon(abilityIcon);
        skillButton.setContentAreaFilled(false);
        skillButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        skillButton.setHorizontalTextPosition(SwingConstants.CENTER);
        skillButton.addActionListener(e -> {
            if(piece != null){
                Ability ability =  piece.getAbility();
                if (ability == null || ability.isPassive()) {
                    boardPanel.frame.boardPanel.defaultLook();
                    boardPanel.frame.selected = null;
                }
                else if (!ability.isTargeted()) {
                    gameManager.useAbility(curX, curY);
                    boardPanel.frame.boardPanel.defaultLook();
                    boardPanel.frame.selected = null;
                }
                else if (ability.isTargeted() && !targetSkillActivated) {
                    targetSkillActivated = true;
                    if (ability.getCooldownLeft() == 0) {
                        boardPanel.frame.boardPanel.skillHighlight(curX, curY, piece);
                    }
                }
            }
        });

        setLayout(new GridLayout(4, 1));
        setPreferredSize(new Dimension(120, 800));

        JPanel turnGameText = new JPanel();
        turnGameText.setBackground(Color.white);
        turnGameText.add(turnText);
        turnGameText.add(gameText);


        add(turnGameText);
        add(hoverText);
        add(infoText);
        add(skillButton);
    }

    public void updateTurnText(){
        if(gameManager.getTurn() == 1){
            turnText.setText("White's Turn");
            turnText.setForeground(Color.gray);

        }
        else if (gameManager.getTurn() == 0){
            turnText.setText("Black's Turn");
            turnText.setForeground(Color.black);
        }
    }

    public void updateSkillDesc(Piece piece)  {
        if (piece != null) {
            gameText.setText("Skill: " + piece.getAbility().getDesc());
        }
        else {
            gameText.setText("");
        }
    }


    public void updateHover(Piece piece){

        updateSkillDesc(piece);
        if(piece != null) {
            if(piece instanceof Pawn || piece instanceof King) {
                hoverText.setText("Name: " + piece.getClass().getSimpleName() +
                        "\nHP: " + piece.getHP() +
                        "\nAP: " + piece.getAP() +
                        "\nCD: Passive");
                hoverText.setVisible(true);
            }else{
                hoverText.setText("Name: " + piece.getClass().getSimpleName() +
                        "\nHP: " + piece.getHP() +
                        "\nAP: " + piece.getAP() +
                        "\nCD: " + piece.getAbility().getCooldownLeft());
                hoverText.setVisible(true);
            }
        }
        else{
            hoverText.setText("Name:\nHP:\nAP:\nCD:");
           // hoverText.setVisible(false);
        }
    }

    public void updateInfo(Piece piece, int curX, int curY)
    {
        this.piece = piece;
        this.curX = curX;
        this.curY  = curY;

        updateTurnText();

        infoText.setVisible(true);

        if(piece.getAbility().isPassive()){
            infoText.setText("Name: " + piece.getClass().getSimpleName() +
                    "\nHP: " + piece.getHP() +
                    "\nAP: " + piece.getAP() +
                    "\nCD: Passive");
            skillButton.setVisible(false);
        }else {
            infoText.setText("Name: " + piece.getClass().getSimpleName() +
                    "\nHP: " + piece.getHP() +
                    "\nAP: " + piece.getAP() +
                    "\nCD: " + piece.getAbility().getCooldownLeft());
            if(piece.getAbility().getCooldownLeft() == 0) {
                skillButton.setVisible(true);
            }
            else {
                skillButton.setVisible(false);
            }
        }
    }

    public void clearInfo(){
        piece = null;
        infoText.setText("Name:\nHP:\nAP:\nCD:");
        infoText.setVisible(false);
        skillButton.setVisible(false);
    }


}
