package Testing;

import StatusEffects.Stun;
import Items.Item;

public class SampleStunSkill implements Item {

    private int duration = 2;
    private int currentQuantity; // 1. Replaced 'used' with quantity

    // 2. Add the Constructor
    public SampleStunSkill(int startingQuantity) {
        this.currentQuantity = startingQuantity;
    }

    @Override
    public String getName() {
        return "Stun Grenade (x" + this.currentQuantity + ")";
    }

    @Override
    public String getDescription() {
        return "Stuns the target for " + duration + " turns.";
    }

    // 3. Fulfill the new Interface Contract!
    @Override
    public int getQuantity() {
        return this.currentQuantity;
    }

    @Override
    public void use(Player target) {}

    @Override
    public void use(Enemy target) {
        if (this.currentQuantity <= 0) {
            System.out.println("You are out of Stun Grenades!");
            return;
        }

        System.out.println("BANG! " + getName() + "!");

        // Apply the status effect (Note: You don't need to cast to Enemy anymore
        // because the method parameter is already 'Enemy target'!)
        target.addStatusEffect(new Stun(this.duration));

        // Subtract 1 from the stack!
        this.currentQuantity--;
    }
}