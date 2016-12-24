package GameEntities.Abilities;

import GameLogic.GameBoard;

/**
 * Created by Ege on 24.12.2016.
 */
public class Inspire extends Ability{

    public Inspire() {
        isPassive = true;
        isTargeted = false;
        cooldown = 0;
        cooldownLeft = 0;
        name = this.getClass().getSimpleName();
        desc = "King passively boosts the nearby friendly pieces' attack power";
    }

    @Override
    public boolean useAbility(GameBoard board, int... coordinates) {
        /*public void inspireKing(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != null){
                    if(board[i][j] instanceof King){
                        for(int k = i-1; k < i+2; k++){
                            for(int l = j-1; l < l+2; l++){
                                if(k > 0 && k < 8 && l > 0 && l < 8){
                                    if( board[k][l] != null) {
                                        if (board[i][j].getColor() == board[k][l].getColor() && board[k][l].getAP() == board[k][l].getNormalAP())
                                            board[k][l].increaseAP(20);
                                        else if (board[i][j].getColor() == board[k][l].getColor() && board[k][l].getAP() == board[k][l].getNormalAP() + 20)
                                            board[k][l].resetAP();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }*/

        return false;
    }
}
