package Items;

import StatusEffects.Stealth;
import Testing.Enemy;
import Testing.Player;

public class SmokeBomb implements Item{

    private int duration = 2;
    private boolean isConsumed = false;

    @Override
    public String getName() {
        return "Smoke Bomb";
    }

    @Override
    public String getDescription() {
        return "Player is invulnerable for " + this.duration + " turns";
    }

    @Override
    public void use(Enemy target) {}
    //here for fulfilling Item interface requirement
    //will be removed once skill is not implementing Item

    @Override
    public void use(Player target) {
        if (this.isConsumed) {
            System.out.println("Smoke Bomb has already been used");
            return;
        }

        System.out.println("Threw a " + getName() + "!");

        Stealth effect = new Stealth(this.duration);
        target.addStatusEffect(effect);

        this.isConsumed = true;
    }

    public boolean isConsumed() {
        return this.isConsumed;
    }
}

