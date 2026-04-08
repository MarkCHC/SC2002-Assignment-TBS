package arena.level;

import arena.combatant.Enemy;
import arena.combatant.Goblin;
import arena.combatant.Wolf;
import arena.enemy.BasicAttackBehavior;
import arena.enemy.EnemyBehavior;
import java.util.ArrayList;
import java.util.List;

// still not expansible / still hard coded on 
// the enemy count for naming, and the createWave
public class LevelFactory {
    private int goblinCount;
    private int wolfCount;

    // can also use list
    public List<Wave> createWaves(LevelDifficulty difficulty) {
        goblinCount = 0;
        wolfCount = 0;
        EnemyBehavior behavior = new BasicAttackBehavior();

        List<Wave> waves = new ArrayList<>();

        switch (difficulty) {
            case EASY:
                waves.add(createWave(1, 3, 0, behavior));
                break;
            case MEDIUM:
                waves.add(createWave(1, 1, 1, behavior));
                waves.add(createWave(2, 0, 2, behavior));
                break;
            case HARD:
                waves.add(createWave(1, 2, 0, behavior));
                waves.add(createWave(2, 1, 2, behavior));
                break;
            default:
                waves.add(createWave(1, 3, 0, behavior));
                break;
        }

        return waves;
    }

    private Wave createWave(int waveNumber, int goblins, int wolves, EnemyBehavior behavior) {
        List<Enemy> waveEnemies = new ArrayList<>();

        for (int i = 0; i < goblins; i++) {
            goblinCount++;
            waveEnemies.add(new Goblin("Goblin " + suffix(goblinCount), behavior));
        }
        for (int i = 0; i < wolves; i++) {
            wolfCount++;
            waveEnemies.add(new Wolf("Wolf " + suffix(wolfCount), behavior));
        }

        return new Wave(waveNumber, waveEnemies);
    }

    private String suffix(int index) {
        int zeroBased = index - 1;
        char letter = (char) ('A' + (zeroBased % 26));
        int cycle = zeroBased / 26;
        if (cycle == 0) {
            return String.valueOf(letter);
        }
        return letter + String.valueOf(cycle + 1);
    }
}
