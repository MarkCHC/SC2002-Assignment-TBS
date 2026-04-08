package Items;

import StatusEffects.Stealth;
import Testing.Enemy;
import Testing.Player;

public class SmokeBomb implements Item{

    private int duration = 2;
    private int sbQuantity;

    public SmokeBomb(int startingQuantity) {
        this.sbQuantity = startingQuantity;
    }

    public String getName() {
        return "Smoke Bomb";
    }

    public String getDescription() {
        return "Player is invulnerable for " + this.duration + " turns";
    }

    public int getQuantity() {
        return this.sbQuantity;
    }

    public void use(Enemy target) {}
    //here for fulfilling Item interface requirement
    //will be removed once skill is not implementing Item

    public void use(Player target) {
        if (this.sbQuantity <= 0) {
            System.out.println("Out of Smoke Bombs!");
            return;
        }

        System.out.println("Threw a " + getName() + " at their feet!");

        Stealth effect = new Stealth(this.duration);
        target.addStatusEffect(effect);

        this.sbQuantity--;
        System.out.println("You have " + this.sbQuantity + " Smoke Bombs left.");
    }
}

