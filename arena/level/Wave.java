package arena.level;

import arena.combatant.Enemy;
import java.util.List;

public class Wave {
    private final int waveNumber;
    private final List<Enemy> enemies;

    public Wave(int waveNumber, List<Enemy> enemies) {
        this.waveNumber = waveNumber;
        this.enemies = enemies;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isCleared() {
        for (Enemy enemy : enemies) {
            if (enemy.getHp() > 0) {
                return false;
            }
        }
        return true;
    }
}