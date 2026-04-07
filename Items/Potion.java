package Items;

import Testing.Enemy;
import Testing.Player;

public class Potion implements Item {

    //private attribute(s)
    private int healamount = 100;

    @Override
    public String getName() {
        return "Potion";
    }

    @Override
    public String getDescription() {
        return "Restores 100 HP to player";
    }

    @Override
    public void use(Enemy target){}
    //here for fulfilling Item interface requirement
    //will be removed once using combatant

    @Override
    public void use(Player target) {
        int currentHP = target.getHP();
        int maxHP = target.getMaxHP();

        if (currentHP + healamount < maxHP){
            target.setHP(currentHP + healamount);
        } else {
            target.setHP(maxHP);
        }

        System.out.println("Character HP Updated. New HP: " + target.getHP());
    }
}
