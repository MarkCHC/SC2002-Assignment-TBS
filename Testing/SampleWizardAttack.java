package Testing;

import Items.Item;
import StatusEffects.ArcaneBoost;

public class SampleWizardAttack implements Item {

    private int blastDamage = 40;

    @Override
    public String getName() {
        return "Arcane Blast";
    }

    @Override
    public String getDescription() {
        return "Deals 40 damage. Defeating an enemy grants +10 permanent attack.";
    }

    // Fulfilling the Item contract, but spells don't run out!
    @Override
    public int getQuantity() {
        return 1;
    }

    // Since our Item interface requires use(Player target), let's assume
    // the target here is the enemy, and we pass the Wizard to track the buff.
    // (Note: You might need to update your Item interface later to handle both a caster and a target!)
    public void castSpell(Player wizard, Enemy targetEnemy) {
        System.out.println(wizard.getName() + " casts " + getName() + "!");

        targetEnemy.takeDamage(this.blastDamage);

        // Check if the spell killed the enemy
        if (targetEnemy.getHP() <= 0) {
            System.out.println("Enemy defeated! Wizard absorbs arcane energy.");

            // Check if the wizard already has the Arcane Boost effect
            ArcaneBoost existingBoost = wizard.getArcaneBoost();

            if (existingBoost != null) {
                // If they already have it, just upgrade it
                existingBoost.addBonus(10);
            } else {
                // If this is their first kill, give them the new effect
                wizard.addStatusEffect(new ArcaneBoost(10));
            }
        }
    }

    // Fulfilling the Item interface just in case
    @Override
    public void use(Player target) {
        System.out.println("You need to cast this at an enemy!");
    }

    @Override
    public void use(Enemy target) {}
}