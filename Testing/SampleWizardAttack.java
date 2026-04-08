package Testing;

import Items.Item;
import StatusEffects.ArcaneBoost;
import StatusEffects.StatusEffect; // Added missing import

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

    @Override
    public int getQuantity() {
        return 1;
    }

    public void castSpell(Player wizard, Enemy targetEnemy) {
        System.out.println(wizard.getName() + " casts " + getName() + "!");

        targetEnemy.takeDamage(this.blastDamage);

        // Check if the spell killed the enemy
        if (targetEnemy.getHP() <= 0) {
            System.out.println("Enemy defeated! Wizard absorbs arcane energy.");

            // 1. We search the wizard's effects safely instead of using a hardcoded getter
            ArcaneBoost existingBoost = null;
            for (StatusEffect effect : wizard.getActiveEffects()) {
                if (effect instanceof ArcaneBoost) {
                    existingBoost = (ArcaneBoost) effect;
                    break;
                }
            }

            if (existingBoost != null) {
                // If they already have it, just upgrade it
                existingBoost.addKill();
            } else {
                // If this is their first kill, give them the new effect
                wizard.addStatusEffect(new ArcaneBoost(1));
            }
        }
    }

    @Override
    public void use(Player target) {
        System.out.println("You need to cast this at an enemy!");
    }

    @Override
    public void use(Enemy target) {}
}