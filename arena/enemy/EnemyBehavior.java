package arena.enemy;

import arena.combatant.Enemy;
import arena.combatant.Player;

// idea of this: this should work as a determiner of
// what action that the enemy will use at that round 
// the return / output is an Action
// need the action class to implement

public interface EnemyBehavior {
    String getName();

    // this should return Action
    void act(Enemy enemy, Player player);
}
