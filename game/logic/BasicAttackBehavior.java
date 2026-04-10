package game.logic;
import game.entities.Enemy;
import game.entities.Player;

public class BasicAttackBehavior implements EnemyBehavior {
    public String getName() {
        return "Basic Attack";
    }

    // in the real implementation this would return Action
    // and the action itself would be execute via the action class
    // so in implementation it simply return BasicAttack
    public void act(Enemy enemy, Player player) {
        int damage = Math.max(0,player.getAttack()-enemy.getDefense());
        System.out.println(enemy.getName() + " uses Basic Attack on " + player.getName()
                + " for " + damage + " damage.");
    // all this would be    
    // return BasicAttack
    }
}