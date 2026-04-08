package game.actions;

import game.entities.Combatant;
import StatusEffects.Stun;
import java.util.List;

public class ShieldBash extends SpecialSkill {

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