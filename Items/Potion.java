package Items;

import Testing.Enemy;
import Testing.Player;

public class Potion implements Item {

    //private attribute(s)
    private int healamount = 100;
    private int potionQuantity;

    public Potion(int startingQuantity) {
        this.potionQuantity = startingQuantity;
    }

    @Override
    public String getName() {
        return "Potion (x" + this.potionQuantity + ")";
    }

    @Override
    public String getDescription() {
        return "Restores 100 HP to player. Remaining: " + this.potionQuantity;
    }

    @Override
    public int getQuantity() {
        return this.potionQuantity;
    }

    @Override
    public void use(Enemy target){}
    //here for fulfilling Item interface requirement
    //will be removed once using combatant

    @Override
    public void use(Player target) {
        if (this.potionQuantity <= 0) {
            System.out.println("Out of Potions!");
            return;
        }

        int currentHP = target.getHP();
        int maxHP = target.getMaxHP();

        if (currentHP + healamount < maxHP){
            target.setHP(currentHP + healamount);
        } else {
            target.setHP(maxHP);
        }

        this.potionQuantity--;

        System.out.println("Character HP Updated. New HP: " + target.getHP());
        System.out.println("You have " + this.potionQuantity + " Potions left.");
    }
}
