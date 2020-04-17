package srpfacadelab;

public class rpgPlayerFacade {
    RpgPlayer rpgPlayer;
    itemInventoryCalculator i;
    takeDamageCalculator t;

    public rpgPlayerFacade(RpgPlayer rpgPlayer){
        this.rpgPlayer = rpgPlayer;
        i = new itemInventoryCalculator(rpgPlayer);
        t = new takeDamageCalculator(rpgPlayer);
    }

    public void useItem(Item item){
        i.useItem(item);
    }

    public void takeDamage(int damage){
        t.takeDamage(damage);
    }

    public int calculateInventoryWeight(){
        return i.calculateInventoryWeight();
    }

    public boolean checkIfItemExistsInInventory(Item item){
        return i.checkIfItemExistsInInventory(item);
    }


    public boolean pickUpItem(Item item){
        return i.pickUpItem(item);
    }
}
