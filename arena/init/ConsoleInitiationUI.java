package arena.init;

import arena.level.EnemyType;
import arena.level.LevelDifficulty;
import java.util.Scanner;

// this is how the whole init works

public class ConsoleInitiationUI implements InitiationUI {
    private final Scanner scanner;

    public ConsoleInitiationUI(Scanner scanner) {
        this.scanner = scanner;
    }

    //displaying all info
    public void showLoadingScreen() {
        System.out.println("=== TURN-BASED COMBAT ARENA ===");
        System.out.println();

        System.out.println("Players:");
        PlayerType[] playerTypes = PlayerType.values();
        for (int i = 0; i < playerTypes.length; i++) {
            System.out.println((i + 1) + ". " + playerTypes[i].getDisplayInfo());
        }
        System.out.println();

        System.out.println("Enemies:");
        EnemyType[] enemyTypes = EnemyType.values();
        for (int i = 0; i < enemyTypes.length; i++) {
            System.out.println((i + 1) + ". " + enemyTypes[i].getDisplayInfo());
        }
        System.out.println();

        // for now just hardcode
        System.out.println("Difficulties:");
        System.out.println("1. Easy");
        System.out.println("   Wave 1: 3 Goblins");

        System.out.println("2. Medium");
        System.out.println("   Wave 1: 1 Goblin, 1 Wolf");
        System.out.println("   Wave 2: 2 Wolves");

        System.out.println("3. Hard");
        System.out.println("   Wave 1: 2 Goblins");
        System.out.println("   Wave 2: 1 Goblin, 2 Wolves");
        System.out.println();

        System.out.println("Items:");
        ItemType[] itemTypes = ItemType.values();
        for (int i = 0; i < itemTypes.length; i++) {
            System.out.println((i + 1) + ". " + itemTypes[i].getDisplayName()
                    + " - " + itemTypes[i].getDescription());
        }
        System.out.println();
    }

    public PlayerType choosePlayerType() {
        PlayerType[] playerTypes = PlayerType.values();
        int choice = readInt("Choose player (1-" + playerTypes.length + "): ", 1, playerTypes.length);
        return playerTypes[choice - 1];
    }

    public LevelDifficulty chooseDifficulty() {
        int choice = readInt("Choose difficulty (1-3): ", 1, 3);

        if (choice == 1) {
            return LevelDifficulty.EASY;
        }
        if (choice == 2) {
            return LevelDifficulty.MEDIUM;
        }
        return LevelDifficulty.HARD;
    }

    public ItemType chooseItem(int itemNumber) {
        ItemType[] itemTypes = ItemType.values();
        int choice = readInt("Choose item " + itemNumber + " (1-" + itemTypes.length + "): ", 1, itemTypes.length);
        return itemTypes[choice - 1];
    }


    // helper function
    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }

            System.out.println("Invalid input. Please enter a number from " + min + " to " + max + ".");
        }
    }
}