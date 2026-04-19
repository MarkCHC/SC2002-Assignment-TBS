package game.logic.Round;
import java.util.List;
import game.entities.Enemy.Enemy;
import java.util.ArrayList;

public class Wave {
    private List<Enemy> enemies;

    // constructors
    public Wave() {
        this.enemies = new ArrayList<Enemy>();
    }
    public Wave(Wave w) { // used to deep copy waves
        this.enemies = new ArrayList<Enemy>();
        for(Enemy e: w.getEnemies()) {
            this.enemies.add(e.createCopy());
        }
    }
    public Wave(List<Enemy> wavEnemies) { // used in LevelFactory
        this.enemies = new ArrayList<Enemy>();
        for(Enemy e: wavEnemies) {
            this.enemies.add(e.createCopy());
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
