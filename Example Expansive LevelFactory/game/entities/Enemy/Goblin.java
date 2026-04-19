package game.entities.Enemy;
import java.util.List;
import java.util.ArrayList;
import game.entities.StatusEffect.StatusEffect;
import game.logic.Action.Enemy.EnemyBehavior;

public class Goblin extends Enemy {
    public Goblin(String name, EnemyBehavior behavior) {
        super(name, 55, 35, 15, 25, behavior);
    }

    public Goblin(String name, int maxHp, int currentHp, int attack, int defense, int speed, EnemyBehavior behavior, List<StatusEffect> se) {
        super(name, maxHp, currentHp, attack, defense, speed, behavior, se);
    }
    
    public Goblin createCopy() {
        List<StatusEffect> sList = new ArrayList<>();
        for (StatusEffect se: activeEffects) {
            sList.add(se.getCopy());
        }
        return new Goblin(
            this.getName(), 
            this.getMaxHp(), 
            this.getHp(), 
            this.getAttack(), 
            this.getDefense(), 
            this.getSpeed(), 
            this.getBehavior(), // need new
            sList
        );
    }
}
