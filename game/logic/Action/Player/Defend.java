package game.logic.Action.Player;
import java.util.List;
import game.entities.Combatant;
import game.entities.StatusEffect.DefendEffect;

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