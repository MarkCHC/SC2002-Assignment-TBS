package game.entities.Enemy;
import game.logic.Action.Enemy.EnemyBehavior;

public class Goblin extends Enemy {
    public Goblin(String name, EnemyBehavior behavior) {
        super(name, 55, 35, 15, 25, behavior);
    }

    public Goblin(String name, int maxHp, int currentHp, int attack, int defense, int speed, EnemyBehavior behavior) {
        super(name, maxHp, currentHp, attack, defense, speed, behavior);
    }
    
    public Goblin createCopy() {
        return new Goblin(
            this.getName(), 
            this.getMaxHp(), 
            this.getHp(), 
            this.getAttack(), 
            this.getDefense(), 
            this.getSpeed(), 
            this.getBehavior() // need new
        );
    }
}
