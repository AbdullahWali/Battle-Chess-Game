package GameEntities.Abilities;

import GameLogic.GameBoard;

/**
 * Created by Abdullah Wali on 23/12/2016.
 */
public class Fortify extends Ability {

    public int fortifies;

    public Fortify() {
        fortifies = 0;
        isPassive = false;
        isTargeted = false;
        cooldown = 6;
        cooldownLeft = 0;
        name = this.getClass().getSimpleName();
        desc = "The Rook fortifies itself for 1 future attack and reduces the incoming attack by %75. This effect can stack if the Rook have unused charges on it.";
    }

    @Override
    public boolean useAbility(GameBoard board, int... coordinates) {

        //Checks for valid cooldown and resets if valid
        if (!super.useAbility(board,coordinates)) return false;
        fortifies++;
        return true;

    }
}
