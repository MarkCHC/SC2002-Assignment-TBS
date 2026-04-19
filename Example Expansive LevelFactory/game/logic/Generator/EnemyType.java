package game.logic.Generator;

import game.entities.Enemy.Enemy;
import game.entities.Enemy.Goblin;
import game.entities.Enemy.Wolf;
import game.logic.Action.Enemy.BasicAttackBehavior;
import game.logic.Action.Enemy.EnemyBehavior;

public enum EnemyType {
    GOBLIN("Goblin") {
        public Enemy createEnemy(String instanceName, EnemyBehavior behavior) {
            return new Goblin(instanceName, behavior);
        }
    },
    WOLF("Wolf") {
        public Enemy createEnemy(String instanceName, EnemyBehavior behavior) {
            return new Wolf(instanceName, behavior);
        }
    };

    private final String displayName;

    EnemyType(String displayName) {
        this.displayName = displayName;
    }

    public abstract Enemy createEnemy(String instanceName, EnemyBehavior behavior);

    public Enemy createEnemy(String instanceName) {
        return createEnemy(instanceName, new BasicAttackBehavior());
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayInfo() {
        Enemy enemy = createEnemy(displayName);
        return enemy.getName()
                + "  HP:" + enemy.getMaxHp()
                + " ATK:" + enemy.getAttack()
                + " DEF:" + enemy.getDefense()
                + " SPD:" + enemy.getSpeed();
    }
}