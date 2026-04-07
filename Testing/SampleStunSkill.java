package Testing;

import StatusEffects.Stun;
import Items.Item;

public class SampleStunSkill implements Item {

    private int duration = 2; // Usually stuns only last 2 turn!
    private boolean used = false;

    @Override
    public String getName() {
        return "Stun Grenade";
    }

    @Override
    public String getDescription() {
        return "Stuns the target for " + duration + " turn, preventing them from attacking.";
    }

    @Override
    public void use(Player target) {
        //empty for testing purposes. Will use Combatant target for easier integration with code
    }

    @Override
    public void use(Enemy target) {
        if (used) {
            System.out.println("This grenade was already thrown!");
        } else {
            System.out.println("BANG! " + getName() + "!");

            //Cast Skill into an Enemy
            Enemy targetEnemy = (Enemy) target;

            // Apply the status effect
            targetEnemy.addStatusEffect(new Stun(this.duration));
            this.used = true;
        }
    }
}