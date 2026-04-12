package game.ui;
import java.util.List;
import java.util.Scanner;
import game.logic.Round.State;
import game.entities.Combatant;
import game.entities.Player.Player;
import game.entities.Enemy.Enemy;
import game.entities.Item.Item;

public class GameUI {
    public static void choosePlayerAction(State s) {
        int choice = askInput(1, 4);
        int target;
        switch(choice) {
            case 1: // eg. Basic Attack
                // attack action
                target = pickEnemy(s.getEnemyState()); // pick enemy
                break;
            case 2: // eg. Defend

                break;
            case 3: // eg. Items
                showInventory(s.getInventory());
                break;
            case 4: // eg. SpecialSkill
                break;
            default:
                
                break;
        }
    }

    public static void showPlayerActions(Combatant c) {
        System.out.println("==================================================================");
        // Basic Attack
        System.out.println("1. Basic Attack");
        // Defend
        System.out.println("2. Defend");
        // Item
        System.out.println("3. Items");
        // Special Skill
        System.out.print("4. " + c.getSpecialSkill().getSpecialSkillName());
        if (c.getSpecialSkill().getRemainingCooldown() > 0) {
            System.out.println(" (CD: "+c.getSpecialSkill().getRemainingCooldown()+")");
        } else {
            System.out.println(" (CD: Ready)");
        }
        System.out.println("==================================================================");
    }

    public static void displayGameState(State s) {
        System.out.println("==================================================================");
        System.out.println("-------------------------------");
        System.out.println("|| Round " + s.getRound() + "      Waves Left:" + s.getWavesLeft().size() + " ||");
        System.out.println("-------------------------------");
        displayEnemyState(s.getEnemyState());
        System.out.println("----------------------");
        displayPlayerState(s.getPlayerState());
        System.out.println("----------------------");
        System.out.println("==================================================================");
    }

    // HELPER FUNCTIONS
    public static int pickEnemy(List<Enemy> enemies) {
        System.out.println("Select enemy to attack:");
        int choice = 1;
        for (Enemy e: enemies) {
            System.out.println(choice+". "+e.getName()
                + "  HP:" + e.getHp()
                + "/" + e.getMaxHp());
            choice++;
        }
        choice = askInput(1, enemies.size());
        return choice;
    }

    public static void showInventory(List<Item> inventory) {
        Boolean avail = false;
        for (Item i: inventory) {
            if (i.getQuantity() > 0) {
                System.out.println(i.getName());
                avail = true;
            }
        }
        if (!avail) {
            System.out.println("No Items available.");
        }
    }

    public static void displayPlayerState(List<Player> players) {
        for (Player p: players) {
            System.out.println(
                p.getName()
                + "  HP:" + p.getHp()
                + "/" + p.getMaxHp()
            );
        }
    }

    public static void displayEnemyState(List<Enemy> enemies) {
        for (Enemy e: enemies) {
            System.out.println(
                e.getName()
                + "  HP:" + e.getHp()
                + "/" + e.getMaxHp()
            );
        }
    }

    public static int askInput(int start, int end) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.print(">> ");
            choice = scanner.nextInt();
            if (choice < start || choice > end)
                System.out.println("Invalid choice, pick again!");
        } while (choice < start || choice > end);
        return choice;
    }
}
