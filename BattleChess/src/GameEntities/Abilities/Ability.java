package GameEntities.Abilities;


public abstract class Ability {

    private int cooldown;
    private int cooldownLeft;
    private String name;


    //Methods
    public String getName() {
        return name;
    }

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
    public boolean useAbility() {
        if (cooldownLeft != 0) {
            return false;
        }
        else {
            resetCooldownLeft();
            return true;
        }
    }


}
