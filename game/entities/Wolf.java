package game.entities;
import game.logic.EnemyBehavior;

public class Wolf extends Enemy {
    public Wolf(String name, EnemyBehavior behavior) {
        super(name, 40, 45, 5, 35, behavior);
    }

    public Wolf(String name, int maxHp, int currentHp, int attack, int defense, int speed, EnemyBehavior behavior) {
        super(name, maxHp, currentHp, attack, defense, speed, behavior);
    }

    public Wolf createCopy() {
        return new Wolf(
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
