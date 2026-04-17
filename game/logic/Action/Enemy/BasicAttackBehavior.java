package game.logic.Action.Enemy;

import game.entities.Combatant;
import game.entities.Enemy.Enemy;
import game.entities.Player.Player;
import game.logic.Action.Player.Action;
import game.logic.Action.Player.BasicAttack;
import game.logic.Round.State;
import java.util.ArrayList;
import java.util.List;

public class BasicAttackBehavior implements EnemyBehavior {
    @Override
    public String getName() {
        return "Basic Attack";
    }

    @Override
    public Action chooseAction(State state, Enemy enemy) {
        return new BasicAttack();
    }

    @Override
    public List<Combatant> chooseTargets(State state, Enemy enemy, Action action) {
        List<Combatant> targets = new ArrayList<>();

        for (Player p : state.getPlayerState()) {
            if (p.isAlive()) {
                targets.add(p);
                break; // for now, just pick the first alive player
            }
        }

        return targets;
    }
}