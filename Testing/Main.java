package Testing;

import Items.Potion;
import Items.SmokeBomb;
import Items.PowerStone; // Make sure this is imported!

public class Main {
    public static void main(String[] args) {

        System.out.println("========== BATTLE START ==========\n");

        // 1. Setup the Combatants
        Player wizard = new Player("Gandalf", 100, 100, 20);
        Enemy goblin = new Enemy("Weak Goblin", 30, 30, 20);
        Enemy orc = new Enemy("Fierce Orc", 1000, 1000, 20);

        // 2. Setup the Inventory
        SampleWizardAttack arcaneScroll = new SampleWizardAttack();
        Potion healthPotion = new Potion(2);
        SampleStunSkill stunGrenade = new SampleStunSkill(2);
        SmokeBomb smokeBomb = new SmokeBomb(2);

        // ADDED: The new Power Stone!
        PowerStone powerStone = new PowerStone(2);

        // ----------------- ROUND 1 -----------------
        System.out.println("--- ROUND 1 ---");
        arcaneScroll.castSpell(wizard, goblin);

        System.out.println("Orc swings its axe!");
        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();

        // ----------------- ROUND 2 -----------------
        System.out.println("--- ROUND 2 ---");
        wizard.useItem(healthPotion, wizard);

        System.out.println("Orc swings its axe!");
        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();

        // ----------------- ROUND 3 -----------------
        System.out.println("--- ROUND 3 ---");
        wizard.useItem(stunGrenade, orc);

        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();

        // ----------------- ROUND 4 -----------------
        System.out.println("--- ROUND 4 ---");
        wizard.attack(orc);

        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();

        // ----------------- ROUND 5 -----------------
        System.out.println("--- ROUND 5 ---");
        wizard.useItem(smokeBomb, wizard);

        // Massive attack! But smoke should reduce it to 0.
        wizard.takeDamage(100);

        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();

        // ----------------- ROUND 6 (NEW: Special Skills) -----------------
        System.out.println("--- ROUND 6 ---");
        wizard.useSpecialSkill(); // Should trigger default effect and set cooldown to 3

        System.out.println("Orc prepares a devastating blow!");
        orc.useSpecialSkill(); // Should trigger Enemy special and set cooldown to 3

        orc.endTurn();
        wizard.endTurn();
        System.out.println();

        // ----------------- ROUND 7 (NEW: Cooldowns & Power Stone) --------
        System.out.println("--- ROUND 7 ---");

        System.out.println("Wizard tries to use special skill again...");
        // This should fail and print "Skill is on cooldown for 2 more turns!"
        wizard.useSpecialSkill();

        System.out.println("\nWizard uses a Power Stone instead!");
        // This should bypass the cooldown restriction and trigger the effect anyway!
        wizard.useItem(powerStone, wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println("\n========== BATTLE END ==========");
    }
}