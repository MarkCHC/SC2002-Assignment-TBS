package arena.combatant;

import arena.enemy.EnemyBehavior;

public class Goblin extends Enemy {
    public Goblin(String name, EnemyBehavior behavior) {
        super(name, 55, 35, 15, 25, behavior);
    }
}
