package game.logic.Action.SpecialSkills;
import java.util.List;
import game.entities.Combatant;
import game.entities.StatusEffect.Stun;

public class ShieldBash extends SpecialSkill {

    public ShieldBash() {
        super(
            "Arcane Blast",
            "Deal basic attack to all enemies. For each enemy defeated, +10 attack until end of the level."
        );
    }

    public ShieldBash(int cooldown) {
        super(
            "Arcane Blast",
            "Deal basic attack to all enemies. For each enemy defeated, +10 attack until end of the level.",
            cooldown
        );
    }

    public SpecialSkill getCopy() {
        return new ShieldBash(
            this.getRemainingCooldown()
        );
    }

    @Override
    protected void performSkill(Combatant actor, List<Combatant> targets) {
        Combatant target = targets.get(0);

        int damage = Math.max(0, actor.getAttack() - target.getDefense());
        target.takeDamage(damage);

        // Stun lasts current turn + next turn = 2
        target.addStatusEffect(new Stun(2));

        System.out.println(actor.getName() + " uses Shield Bash on " + target.getName()
                + "! Deals " + damage + " damage and applies STUN (2 turns).");
    }
}