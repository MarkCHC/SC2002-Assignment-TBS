package game.logic;
import game.entities.Enemy;
import game.entities.Player;

public interface EnemyBehavior {
    String getName();

    // this should return Action
    void act(Enemy enemy, Player player);
}