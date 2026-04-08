package arena.level;

import arena.combatant.Enemy;
import arena.combatant.Goblin;
import arena.combatant.Wolf;
import arena.enemy.BasicAttackBehavior;

//storing all enemy type info, just a helper class

public enum EnemyType {
    GOBLIN("Goblin") {
        public Enemy createEnemy(String instanceName) {
            return new Goblin(instanceName, new BasicAttackBehavior());
        }
    },
    WOLF("Wolf") {
        public Enemy createEnemy(String instanceName) {
            return new Wolf(instanceName, new BasicAttackBehavior());
        }
    };

    private final String displayName;

    EnemyType(String displayName) {
        this.displayName = displayName;
    }

    public abstract Enemy createEnemy(String instanceName);

    public String getDisplayInfo() {
        Enemy enemy = createEnemy(displayName);
        return enemy.getName()
                + "  HP:" + enemy.getMaxHp()
                + " ATK:" + enemy.getAttack()
                + " DEF:" + enemy.getDefense()
                + " SPD:" + enemy.getSpeed();
    }
}