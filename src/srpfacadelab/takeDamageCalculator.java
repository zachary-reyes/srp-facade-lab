package srpfacadelab;

public class takeDamageCalculator {

    RpgPlayer rpgPlayer;
    Item i;

    public takeDamageCalculator (RpgPlayer rpgPlayer){
        this.rpgPlayer = rpgPlayer;
    }

    public void takeDamage(int damage) {
        if (damage < rpgPlayer.getArmour()) {
            rpgPlayer.gameEngine.playSpecialEffect("parry");
        }

        if (rpgPlayer.getCarryingCapacity() < (0.5*rpgPlayer.MAX_CARRYING_CAPACITY)) {
            damage *= 0.75;
        }

        int damageToDeal = damage - rpgPlayer.getArmour();
        rpgPlayer.setHealth(rpgPlayer.getHealth() - damageToDeal);

        rpgPlayer.gameEngine.playSpecialEffect("lots_of_gore");
    }
}
