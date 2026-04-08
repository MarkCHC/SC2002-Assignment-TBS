package arena;

import arena.combatant.Enemy;
import arena.combatant.Player;
import arena.init.ConsoleInitiationUI;
import arena.init.GameInitializer;
import arena.init.GameSetup;
import arena.init.ItemType;
import arena.level.LevelFactory;
import arena.level.Wave;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameInitializer initializer = new GameInitializer(new ConsoleInitiationUI(scanner));
        GameSetup setup = initializer.initializeGame();

        Player player = setup.getPlayerType().createPlayer();
        for (ItemType item : setup.getChosenItems()) {
            player.addStartingItem(item.getDisplayName());
        }

        LevelFactory levelFactory = new LevelFactory();
        List<Wave> waves = levelFactory.createWaves(setup.getDifficulty());

        System.out.println();
        System.out.println("========== GAME SETUP COMPLETE ==========");
        System.out.println("Player: " + player.getName());
        System.out.println("Stats : HP " + player.getHp() + "/" + player.getMaxHp()
                + " | ATK " + player.getAttack()
                + " | DEF " + player.getDefense()
                + " | SPD " + player.getSpeed());
        System.out.println("Special Skill: " + player.getSpecialSkillName() + " - " + player.getSpecialSkillDescription());
        System.out.println("Chosen Items : " + player.getStartingItems());
        System.out.println("Difficulty   : " + setup.getDifficulty().getDisplayName());
        System.out.println();

        for (int i = 0; i < waves.size(); i++) {
            Wave wave = waves.get(i);
            System.out.println("Wave " + wave.getWaveNumber() + ":");
            printWave(wave.getEnemies());

            if (i < waves.size() - 1) {
                System.out.println();
            }
        }

        // can delete this part under
        System.out.println();
        System.out.println("Initialization Done.");
    }

    private static void printWave(List<Enemy> wave) {
        for (Enemy enemy : wave) {
            System.out.println("- " + enemy.getName()
                    + " | HP " + enemy.getHp()
                    + " | ATK " + enemy.getAttack()
                    + " | DEF " + enemy.getDefense()
                    + " | SPD " + enemy.getSpeed()
                    + " | Behavior: " + enemy.getBehavior().getName());
                    // can delete the behavior, this is just to check
        }
    }
}
