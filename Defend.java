package game.actions;

import game.entities.Combatant;
import StatusEffects.DefendEffect;
import java.util.List;

public class Defend implements Action {

    private static final int DEFENSE_BOOST = 10;
    private static final int DURATION = 2;

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        actor.addStatusEffect(new DefendEffect(DEFENSE_BOOST, DURATION));
        System.out.println(actor.getName() + " takes a defensive stance! "
                + "(+" + DEFENSE_BOOST + " DEF for " + DURATION + " rounds)");
    }
}