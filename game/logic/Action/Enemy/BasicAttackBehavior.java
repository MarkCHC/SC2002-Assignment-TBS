package game.logic.Action.Enemy;
import game.entities.Enemy.Enemy;
import game.entities.Player.Player;
import game.logic.Action.Player.Action;
import game.logic.Action.Player.BasicAttack;

public class BasicAttackBehavior implements EnemyBehavior {
    public String getName() {
        return "Basic Attack";
    }

    // in the real implementation this would return Action
    // and the action itself would be execute via the action class
    // so in implementation it simply return BasicAttack
    public Action getAction() {
        return new BasicAttack();
    }
}