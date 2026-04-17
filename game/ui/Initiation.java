package game.ui;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import game.logic.Generator.PlayerType;
import game.logic.Level.LevelDifficulty;
import game.entities.Item.ItemList;
import game.entities.Item.Item;

public class Initiation {
    //single static scanner for whole class
    private static final Scanner scanner = new Scanner(System.in);

    public static void showLoadingScreen() {
        System.out.println("==================================================================");
        System.out.println("|| ██████ █████▄ ▄█████   █████▄  ▄▄▄ ▄▄▄▄▄▄ ▄▄▄▄▄▄ ▄▄    ▄▄▄▄▄ ||");
        System.out.println("||   ██   ██▄▄██ ▀▀▀▄▄▄   ██▄▄██ ██▀██  ██     ██   ██    ██▄▄  ||");
        System.out.println("||   ██   ██▄▄█▀ █████▀   ██▄▄█▀ ██▀██  ██     ██   ██▄▄▄ ██▄▄▄ ||");
        System.out.println("==================================================================");
    }

    public static int choosePlayers() { // fixed 1 for now, extensibility could be multiple
        return 1;
    }

    public static PlayerType chooseClass() {
        System.out.println("Choose your character:");
        PlayerType[] classes = PlayerType.values();
        for (int i=0; i < classes.length; i++) {
            System.out.println((i+1) + ". " + classes[i].getDisplayInfo());
        }
        
        System.out.print(">> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("You chose Warrior!");
                System.out.println();
                return PlayerType.WARRIOR;
            case 2:
                System.out.println("You chose Wizard!");
                System.out.println();
                return PlayerType.WIZARD;
            default:
                System.out.println("Invalid choice, defaulting to Warrior.");
                System.out.println();
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
        int choice = scanner.nextInt();
        System.out.println();
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

    public static List<Item> chooseItems() { // choose 2 items
        int startingItemCount = 2;
        ItemList[] itemList = ItemList.values();
        for (int i=0; i < itemList.length; i++) {;
            System.out.println((i+1) + ". " + itemList[i]);
        }

        int[] quantity = {0, 0, 0};
        while (startingItemCount > 0) {
            System.out.println("Select "+(startingItemCount == 2? "first":"second")+" item");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1: 
                    quantity[0] += 1;
                    startingItemCount--;
                    break;
                case 2:
                    quantity[1] += 1;
                    startingItemCount--;
                    break;
                case 3:
                    quantity[2] += 1;
                    startingItemCount--;
                    break;
                default:
                    System.out.println("Invalid choice, choose again!");
                    break;
            }
        }
        List<Item> inventory = new ArrayList<Item>();
        for (int i=0; i<itemList.length; i++) {
            inventory.add(itemList[i].create(quantity[i]));
            if (quantity[i] > 0) {
                System.out.println("Added "+quantity[i]+" "+itemList[i]+" into your inventory.");
            }
        }
        return inventory;
    }

    public static void displayActions() {
        System.out.println("Choose action:");
        System.out.println("1. Attack");
        System.out.println("2. Use Item");
        System.out.println("3. Defend");
    }
}
