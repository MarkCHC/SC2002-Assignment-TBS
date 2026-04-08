package arena.combatant;

import arena.enemy.EnemyBehavior;

public class Wolf extends Enemy {
    public Wolf(String name, EnemyBehavior behavior) {
        super(name, 40, 45, 5, 35, behavior);
    }
}
