package game.logic.Level;
import game.entities.Enemy.Enemy;
import game.logic.Action.Enemy.BasicAttackBehavior;
import game.logic.Action.Enemy.EnemyBehavior;
import game.logic.Generator.EnemyType;
import game.logic.Round.Wave;
import java.util.ArrayList;
import java.util.List;

public class LevelFactory {
    private LevelFactory() {
    }

    public static List<Wave> createWaves(LevelDifficulty difficulty) {
        EnemyBehavior defaultBehavior = new BasicAttackBehavior();
        List<Wave> waves = new ArrayList<>();

        for (EnemyType[] waveLayout : difficulty.getWaveLayouts()) {
            waves.add(createWave(waveLayout, defaultBehavior));
        }

        return waves;
    }

    private static Wave createWave(EnemyType[] waveLayout, EnemyBehavior behavior) {
        List<Enemy> waveEnemies = new ArrayList<>();
        int[] typeCounts = new int[EnemyType.values().length];

        for (EnemyType enemyType : waveLayout) {
            int index = enemyType.ordinal();
            String instanceName = enemyType.getDisplayName() + " " + suffix(typeCounts[index]);
            waveEnemies.add(enemyType.createEnemy(instanceName, behavior));
            typeCounts[index]++;
        }

        return new Wave(waveEnemies);
    }

    private static String suffix(int index) {
        char letter = (char) ('A' + (index % 26));
        int cycle = index / 26;
        if (cycle == 0) {
            return String.valueOf(letter);
        }
        return letter + String.valueOf(cycle + 1);
    }
}
