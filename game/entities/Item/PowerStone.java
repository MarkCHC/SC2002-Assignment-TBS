package game.entities.Item;

import game.entities.Enemy.Enemy;
import game.entities.Player.Player;

public class PowerStone implements Item {

    private int psQuantity;

    public PowerStone(int startingQuantity) {
        this.psQuantity = startingQuantity;
    }

    public String getName() {
        return "Power Stone";
    }

    public String getDescription() {
        return "Gives Player free extra use of Special Skill";
    }

    public int getQuantity() {
        return this.psQuantity;
    }

    public void use(Enemy target){}
    //here for fulfilling Item interface requirement
    //will be removed once skill is not implementing Item

    public void use(Player target) {
        if (this.psQuantity <= 0){
            System.out.println("Out of Power Stones!");
            return;
        }

        System.out.println("Player used " + getName() + "!");

        // target.triggerSpecialSkillEffect();
        // call recursive action?

        this.psQuantity--;
        System.out.println("You have " + this.psQuantity + " Power Stones left.");
    }
}
