package Items;

import Testing.Enemy;
import Testing.Player;

public class PowerStone implements Item {

    private int psQuantity;

    public PowerStone(int startingQuantity) {
        this.psQuantity = startingQuantity;
    }

    @Override
    public String getName() {
        return "Power Stone";
    }

    @Override
    public String getDescription() {
        return "Gives Player free extra use of Special Skill";
    }

    @Override
    public int getQuantity() {
        return this.psQuantity;
    }

    @Override
    public void use(Enemy target){}
    //here for fulfilling Item interface requirement
    //will be removed once skill is not implementing Item

    @Override
    public void use(Player target) {
        if (this.psQuantity <= 0){
            System.out.println("Out of Power Stones!");
            return;
        }

        System.out.println("Player used " + getName() + "!");

        target.triggerSpecialSkillEffect();

        this.psQuantity--;
        System.out.println("You have " + this.psQuantity + " Power Stones left.");
    }
}


