package game.ui;
// import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;
import game.logic.LevelDifficulty;
import game.logic.PlayerType;

public class Initiation {
    public static void showLoadingScreen() {
        System.out.println("================================================================");
        System.out.println("| ██████ █████▄ ▄█████   █████▄  ▄▄▄ ▄▄▄▄▄▄ ▄▄▄▄▄▄ ▄▄    ▄▄▄▄▄ |");
        System.out.println("|   ██   ██▄▄██ ▀▀▀▄▄▄   ██▄▄██ ██▀██  ██     ██   ██    ██▄▄  |");
        System.out.println("|   ██   ██▄▄█▀ █████▀   ██▄▄█▀ ██▀██  ██     ██   ██▄▄▄ ██▄▄▄ |");
        System.out.println("================================================================");
    }

    public static PlayerType chooseClass() {
        System.out.println("Choose your character:");
        PlayerType[] classes = PlayerType.values();
        for (int i=0; i < classes.length; i++) {
            System.out.println((i+1) + ". " + classes[i].getDisplayInfo());
        }
        
        System.out.print(">> ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("You chose Warrior!");
                return PlayerType.WARRIOR;
            case 2:
                System.out.println("You chose Mage!");
                return PlayerType.WIZARD;
            default:
                System.out.println("Invalid choice, defaulting to Warrior.");
                return PlayerType.WARRIOR;
        }
    }

    public static LevelDifficulty chooseDifficulty() {
        System.out.println("Select difficulty:");
        LevelDifficulty[] difficulties = LevelDifficulty.values();
        for (int i=0; i < difficulties.length; i++) {
            System.out.println((i+1) + ". " + difficulties[i].getDisplayName());
        }

        System.out.print(">> ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch(choice) {
            case 1: 
                return LevelDifficulty.EASY;
            case 2:
                return LevelDifficulty.MEDIUM;
            case 3:
                return LevelDifficulty.HARD;
            default:
                System.out.println("Invalid choice, defaulting to EASY.");
                return LevelDifficulty.EASY;
        }
    }

    public static void chooseItems() { // choose 2 items
        int startingItemCount = 2;
    }
}
