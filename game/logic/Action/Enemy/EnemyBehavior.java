package game.logic.Action.Enemy;

import game.entities.Combatant;
import game.entities.Enemy.Enemy;
import game.logic.Action.Player.Action;
import game.logic.Round.State;
import java.util.List;

public interface EnemyBehavior {
    String getName();
    Action chooseAction(State state, Enemy enemy);
    List<Combatant> chooseTargets(State state, Enemy enemy, Action action);
}