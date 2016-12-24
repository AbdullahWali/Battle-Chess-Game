package GameEntities.Abilities;

import GameLogic.GameBoard;

/**
 * Created by Abdullah Wali on 24/12/2016.
 */
public class Accolade extends Ability {

    public Accolade() {
        isPassive = true;
        isTargeted = false;
        cooldown = 0;
        cooldownLeft = 0;
        name = this.getClass().getSimpleName();
        desc = "If a pawn manages to pass to the end tile of enemy side it gets promoted to a Knight";
    }

    @Override
    public boolean useAbility(GameBoard board, int... coordinates) {
        return false;
    }
}
