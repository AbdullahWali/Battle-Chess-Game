package GameEntities.Abilities;
import GameLogic.GameBoard;

public abstract class Ability {

    protected boolean isPassive;
    protected boolean isTargeted; //User Needs to click on a target to use ability
    protected int cooldown;
    protected int cooldownLeft;
    protected String name;


    //Methods
    public String getName() {
        return name;
    }
    public boolean isPassive() { return isPassive;}
    public void updateCooldown(){
        if (cooldownLeft > 0)
            cooldownLeft--;
    }

    public int getCooldownLeft(){
        return cooldownLeft;
    }


    protected void resetCooldownLeft(){
        cooldownLeft = cooldown;
    }



    //Will be overloaded
    public boolean useAbility( GameBoard board, int... coordinates) {
        if (cooldownLeft != 0) {
            return false;
        }
        else {
            resetCooldownLeft();
            return true;
        }
    }


}
