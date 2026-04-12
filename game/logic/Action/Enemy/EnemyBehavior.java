package game.logic.Action.Enemy;
import game.entities.Enemy.Enemy;
import game.entities.Player.Player;

public interface EnemyBehavior {
    String getName();

    // this should return Action
    void act(Enemy enemy, Player player);
}