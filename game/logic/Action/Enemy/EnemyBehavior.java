package game.logic.Action.Enemy;
import game.entities.Enemy.Enemy;
import game.entities.Player.Player;
import game.logic.Action.Player.Action;

public interface EnemyBehavior {
    String getName();

    // this should return Action
    Action getAction();
}