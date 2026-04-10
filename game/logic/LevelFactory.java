package game.logic;
import java.util.List;
import java.util.ArrayList;
import game.entities.Enemy;
import game.logic.EnemyBehavior;
import game.entities.Wolf;
import game.entities.Goblin;
import game.logic.Wave;

public class LevelFactory {

    // can also use list
    public static List<Wave> createWaves(LevelDifficulty difficulty) {
        EnemyBehavior behavior = new BasicAttackBehavior();

        List<Wave> waves = new ArrayList<>();

        switch (difficulty) {
            case EASY:
                waves.add(0, createWave(1, 3, 0, behavior));
                break;
            case MEDIUM:
                waves.add(0, createWave(1, 1, 1, behavior));
                waves.add(1, createWave(2, 0, 2, behavior));
                break;
            case HARD:
                waves.add(0, createWave(1, 2, 0, behavior));
                waves.add(1, createWave(2, 1, 2, behavior));
                break;
            default: // easy
                waves.add(0, createWave(1, 3, 0, behavior));
                break;
        }

        return waves;
    }

    private static Wave createWave(int waveNumber, int goblins, int wolves, EnemyBehavior behavior) {
        List<Enemy> waveEnemies = new ArrayList<>();

        for (int i = 0; i < goblins; i++) {
            waveEnemies.add(new Goblin("Goblin " + suffix(i), behavior));
        }
        for (int i = 0; i < wolves; i++) {
            waveEnemies.add(new Wolf("Wolf " + suffix(i), behavior));
        }

        // return new Wave(waveNumber, waveEnemies);
        return new Wave(waveEnemies);
    }

    private static String suffix(int index) { // need change prob
        // int zeroBased = index - 1;
        char letter = (char) ('A' + (index % 25));
        int cycle = index / 25;
        if (cycle == 0) {
            return String.valueOf(letter);
        }
        return letter + String.valueOf(cycle + 1);
    }
}
