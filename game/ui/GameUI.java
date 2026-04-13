package game.ui;
import java.util.AbstractMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import game.entities.Combatant;
import game.entities.Player.Player;
import game.entities.StatusEffect.StatusEffect;
import game.entities.Enemy.Enemy;
import game.entities.Item.Item;
import game.entities.Item.ItemList;
import game.logic.Round.State;
import game.logic.Action.Player.Action;
import game.logic.Action.Player.BasicAttack;
import game.logic.Action.Player.Defend;
import game.logic.Action.SpecialSkills.SpecialSkill;

public class GameUI {
    public static List<Combatant> choosePlayerTarget(State s, Map.Entry<Action, Integer> res) {
        List<Combatant> cList = new ArrayList<Combatant>();;
        Enemy target;
        switch (res.getValue()) {
            case 1: // Basic Attack
                target = pickEnemy(s.getEnemyState()); // pick enemy
                cList.add(target);
                return cList;
            case 2: // Defend
                return null; // don't need second target
            case 3: // Item
                Item i = (Item)res.getKey();
                if (i.getLabel() == ItemList.POWER_STONE.create(0).getLabel()) {
                     // shd have better way of implementing but works for now
                     // right now fetching player from state but there could be multiple
                    if (s.getPlayerState().get(0)
                    .getSpecialSkill().getSpecialSkillName() == "Arcane Blast") {
                        for (Combatant c: s.getEnemyState()) {
                            cList.add(c);
                        }
                    } else {
                        target = pickEnemy(s.getEnemyState()); // pick enemy
                        cList.add(target);
                    }
                    return cList;
                }
                return null; // don't need second target
            case 4: // Special Skill
                SpecialSkill sp = (SpecialSkill)res.getKey();
                if (sp.getSpecialSkillName() == "Arcane Blast") { // shd have better way of implementing
                    for (Combatant c: s.getEnemyState()) {
                        cList.add(c);
                    }
                } else {
                    target = pickEnemy(s.getEnemyState()); // pick enemy
                    cList.add(target);
                }   
                return cList;
            default:
                return null;
        }
    }

    public static Map.Entry<Action, Integer> choosePlayerAction(State s, Combatant c) {
        int choice = askInput("Action", 1, 4);
        int target;
        switch(choice) {
            case 1: // eg. Basic Attack
                return new AbstractMap.SimpleEntry<>(new BasicAttack(), 1);
            case 2: // eg. Defend
                return new AbstractMap.SimpleEntry<>(new Defend(), 2);
            case 3: // eg. Items
                showInventory(s.getInventory());
                int items = 0; // available items
                int count = 0; // total quantity of all items
                for (Item i: s.getInventory()) {
                    count += i.getQuantity();
                    items += (i.getQuantity() > 0 ? 1 : 0);
                }
                if (count > 0) {
                    target = askInput("Item", 0, items);
                    return (target == 0)? 
                        null 
                        : 
                        new AbstractMap.SimpleEntry<>(s.getUsableInventory().get(target), 3);
                } else {
                    return null;
                }
            case 4: // eg. SpecialSkill
                if (!c.getSpecialSkill().isAvailable()) {
                    System.out.println("Still on cooldown!");
                    return null;
                } else return
                    new AbstractMap.SimpleEntry<>(c.getSpecialSkill(), 4);
            default:
                return null; // shouldnt reach here
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
        System.out.println("------------------------------------------------------------------");
        System.out.println("|| Round "+s.getRound()+
                            "                                         Waves Left:"
                            +s.getWavesLeft().size()+" ||");
        System.out.println("------------------------------------------------------------------");
        displayEnemyState(s.getEnemyState());
        System.out.println("------------------------------------------------------------------");
        displayPlayerState(s.getPlayerState());
        System.out.println("------------------------------------------------------------------");
        System.out.println("==================================================================");
    }

    // HELPER FUNCTIONS
    // private static handleAOE() {}

    private static Enemy pickEnemy(List<Enemy> enemies) {
        System.out.println("Select enemy to attack:");
        int choice = 1;
        for (Enemy e: enemies) {
            System.out.println(choice+". "+e.getName()
                + "  HP:" + e.getHp()
                + "/" + e.getMaxHp());
            choice++;
        }
        choice = askInput("Enemy", 1, enemies.size());
        return enemies.get(choice-1);
    }

    private static void showInventory(List<Item> inventory) {
        Boolean avail = false;
        int target = 1;
        System.out.println("Inventory:");
        System.out.println("----------");
        for (Item i: inventory) {
            if (i.getQuantity() > 0) {
                System.out.print(target + ". ");
                System.out.println(i.getName());
                avail = true;
                target++;
            }
        }
        if (!avail) {
            System.out.println("No Items available.");
        } else {
            System.out.println("0. Go Back");
        }
    }

    private static void displayPlayerState(List<Player> players) {
        for (Player p: players) {
            System.out.print(
                p.getName()
                + "  HP:" + p.getHp()
                + "/" + p.getMaxHp()
                + "         "                
            );
            displayStatusEffects(p);
            System.out.println();
        }
    }

    private static void displayEnemyState(List<Enemy> enemies) {
        for (Enemy e: enemies) {
            System.out.print(
                e.getName()
                + "  HP:" + e.getHp()
                + "/" + e.getMaxHp()
                + "         "
            );
            displayStatusEffects(e);
            System.out.println();
        }
    }

    private static void displayStatusEffects(Combatant c) {
        // System.out.print(c.getActiveEffects());
        for (StatusEffect se: c.getActiveEffects()) {
            if (se.isActive())
                System.out.print("["+se.getName()+"]");
        }
    }

    private static int askInput(String info, int start, int end) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.print(info+">> ");
            choice = scanner.nextInt();
            if (choice < start || choice > end)
                System.out.println("Invalid choice, pick again!");
        } while (choice < start || choice > end);
        return choice;
    }
}
