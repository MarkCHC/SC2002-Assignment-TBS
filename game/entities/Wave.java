package game.entities;
import java.util.List;
import java.util.ArrayList;

public class Wave {
    private List<Enemy> enemies;
    public Wave() {
        enemies = new ArrayList<>();
    }
    public Wave(Wave w) {
        for(Enemy e: w.getEnemies()) {
            this.enemies.add(new Enemy(e));
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
