package arena.combatant;

import arena.enemy.EnemyBehavior;

public abstract class Enemy extends Combatant {
    private final EnemyBehavior behavior;

    protected Enemy(String name, int maxHp, int attack, int defense, int speed, EnemyBehavior behavior) {
        super(name, maxHp, attack, defense, speed, null, null);
        this.behavior = behavior;
    }

    public EnemyBehavior getBehavior() {
        return behavior;
    }
}
