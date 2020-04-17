package srpfacadelab;

import java.util.List;

public class itemInventoryCalculator {

    RpgPlayer rpgPlayer;

    public itemInventoryCalculator(RpgPlayer rpgPlayer) {
        this.rpgPlayer = rpgPlayer;
    }

    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = rpgPlayer.gameEngine.getEnemiesNear(rpgPlayer);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(Item item) {
        int weight = calculateInventoryWeight();
        if (weight + item.getWeight() > rpgPlayer.getCarryingCapacity())
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            rpgPlayer.setHealth(rpgPlayer.getHealth() + item.getHeal());

            if (rpgPlayer.getHealth() > rpgPlayer.getMaxHealth())
                rpgPlayer.setHealth(rpgPlayer.getMaxHealth());

            if (item.getHeal() > 500) {
                rpgPlayer.gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
            rpgPlayer.gameEngine.playSpecialEffect("cool_swirly_particles");

        if (item.isRare() && item.isUnique()){
            rpgPlayer.gameEngine.playSpecialEffect("blue_swirly");
        }

        rpgPlayer.inventory.add(item);

        rpgPlayer.calculateStats();

        return true;
    }

    public int calculateInventoryWeight() {
        int sum=0;
        for (Item i: rpgPlayer.inventory) {
            sum += i.getWeight();
        }
        return sum;
    }

    public boolean checkIfItemExistsInInventory(Item item) {
        for (Item i: rpgPlayer.inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }


}
