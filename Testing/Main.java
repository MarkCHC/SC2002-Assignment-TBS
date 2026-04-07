package Testing;

import Items.Potion;
import Items.SmokeBomb;


public class Main {
    public static void main(String[] args) {

        System.out.println("========== BATTLE START ==========\n");

        // 1. Setup the Combatants
        // Wizard starts with 100 HP, 100 Max HP, and 20 Base Attack
        Player wizard = new Player("Gandalf", 100, 100, 20);

        // Setup enemies
        Enemy goblin = new Enemy("Weak Goblin", 30, 30, 20);
        Enemy orc = new Enemy("Fierce Orc", 1000, 1000, 20);

        // 2. Setup the Inventory
        SampleWizardAttack arcaneScroll = new SampleWizardAttack();
        Potion healthPotion = new Potion();
        SampleStunSkill stunGrenade = new SampleStunSkill();
        SmokeBomb smokeBomb = new SmokeBomb();

        // ----------------- ROUND 1 -----------------
        System.out.println("--- ROUND 1 ---");
        // Wizard uses Arcane Scroll on the weak Goblin to get the buff
        arcaneScroll.castSpell(wizard, goblin);

        // Orc retaliates
        System.out.println("Orc swings its axe!");
        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();


        // ----------------- ROUND 2 -----------------
        System.out.println("--- ROUND 2 ---");
        // Wizard is hurt, so they drink a potion
        wizard.useItem(healthPotion, wizard);

        // Orc attacks again
        System.out.println("Orc swings its axe!");
        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();


        // ----------------- ROUND 3 -----------------
        System.out.println("--- ROUND 3 ---");
        // Wizard is in danger! Throws a Stun Grenade
        wizard.useItem(stunGrenade, orc);

        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();


        // ----------------- ROUND 4 -----------------
        System.out.println("--- ROUND 4 ---");
        // Wizard uses a normal attack.
        // Watch closely: Base attack is 20, but with the ArcaneBoost from Round 1, it should hit for 30!
        wizard.attack(orc);

        // Testing for stun if working
        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println();


        // ----------------- ROUND 5 -----------------
        System.out.println("--- ROUND 5 ---");
        // Wizard is low on health, uses a Smoke Bomb for defense
        wizard.useItem(smokeBomb, wizard);

        // Orc attacks, but the smoke saves the Wizard
        wizard.takeDamage(100); // Massive attack! But smoke should reduce it to 0.

        // Testing for stun if working
        orc.attack(wizard);

        orc.endTurn();
        wizard.endTurn();
        System.out.println("\n========== BATTLE END ==========");
    }
}