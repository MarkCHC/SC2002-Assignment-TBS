package game.entities.Enemy;
import java.util.List;
import java.util.ArrayList;
import game.entities.Combatant;
import game.entities.StatusEffect.StatusEffect;
import game.logic.Action.Enemy.EnemyBehavior;

public abstract class Enemy extends Combatant {
    private final EnemyBehavior behavior;

    protected Enemy(String name, int maxHp, int attack, int defense, int speed, EnemyBehavior behavior) {
        super(name, maxHp, maxHp, attack, defense, speed, null, new ArrayList<>());
        this.behavior = behavior;
    } // for LevelFactory - 6 params

    protected Enemy(String name, int maxHp, int currentHp, int attack, int defense, int speed, EnemyBehavior behavior, List<StatusEffect> se) {
        super(name, maxHp, currentHp, attack, defense, speed, null, se);
        this.behavior = behavior;
    } // deep copy - 7 params

    public abstract Enemy createCopy();

    public Boolean isPlayer() {
        return false;
    }

    public EnemyBehavior getBehavior() {
        return behavior;
    }
}