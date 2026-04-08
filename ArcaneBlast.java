package game.actions;

import game.entities.Combatant;
import StatusEffects.ArcaneBoost;
import StatusEffects.StatusEffect;
import java.util.List;

public class ArcaneBlast extends SpecialSkill {

    @Override
    protected void performSkill(Combatant actor, List<Combatant> targets) {
        int killCount = 0;

        // Find or create the ArcaneBoost effect on the actor
        ArcaneBoost boost = null;
        for (StatusEffect effect : actor.getActiveEffects()) {
            if (effect instanceof ArcaneBoost) {
                boost = (ArcaneBoost) effect;
                break;
            }
        }
        // If first time using Arcane Blast, add the ArcaneBoost effect
        if (boost == null) {
            boost = new ArcaneBoost(0);
            actor.addStatusEffect(boost);
        }

        System.out.println(actor.getName() + " unleashes Arcane Blast!");

        for (Combatant target : targets) {
            if (!target.isAlive()) continue;

            int rawDamage = actor.getAttack() - target.getDefense();

            // Apply actor's outgoing effects (ArcaneBoost adds to damage here)
            for (StatusEffect effect : actor.getActiveEffects()) {
                rawDamage = effect.modifyOutgoingDamage(rawDamage);
            }

            int finalDamage = Math.max(0, rawDamage);
            target.takeDamage(finalDamage);

            System.out.println("  → Hits " + target.getName() + " for " + finalDamage + " damage!"
                    + " (HP: " + target.getHP() + ")");

            if (!target.isAlive()) {
                killCount++;
                System.out.println("  → " + target.getName() + " is eliminated!");
            }
        }

        // Each kill this cast adds +10 to future attacks
        for (int i = 0; i < killCount; i++) {
            boost.addKill();
        }

        if (killCount > 0) {
            System.out.println("Arcane Boost: +" + (killCount * 10) + " ATK added from this cast!");
        }
    }
}