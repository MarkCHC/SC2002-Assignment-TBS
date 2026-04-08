package Testing;

import Items.Item;
import StatusEffects.ArcaneBoost;
import StatusEffects.StatusEffect;
import java.util.ArrayList;
import java.util.List;

public class Enemy {

    // --- Core Stats ---
    private String name;
    private int hp;
    private int maxHp;
    private int baseAttack;

    // --- Trackers ---
    private int specialSkillCooldown = 0;

    // The master list that holds any and all status effects (Stun, Smoke, Poison, Buffs)
    private List<StatusEffect> activeEffects;

    // --- Constructor ---
    public Enemy(String name, int startingHP, int maximumHP, int baseAttack) {
        this.name = name;
        this.hp = startingHP;
        this.maxHp = maximumHP;
        this.baseAttack = baseAttack;
        this.activeEffects = new ArrayList<>(); // Always initialize lists!
    }

    // --- Standard Getters & Setters ---
    public String getName() { return this.name; }
    public int getHP() { return this.hp; }
    public int getMaxHP() { return this.maxHp; }
    public void setHP(int newHP) { this.hp = newHP; }

    public int getSpecialSkillCooldown() { return this.specialSkillCooldown; }
    public void setSpecialSkillCooldown(int cooldown) { this.specialSkillCooldown = cooldown; }

    // --- 1. Status Effect Management ---

    public void addStatusEffect(StatusEffect effect) {
        this.activeEffects.add(effect);
        System.out.println("-> [" + this.name + "] gained effect: " + effect.getName());
    }

    // Helper to check if the player is allowed to move (e.g., not Stunned)
    public boolean canAct() {
        for (StatusEffect effect : this.activeEffects) {
            if (effect.skipsTurn()) {
                return false;
            }
        }
        return true;
    }

    // --- 2. Combat Mechanics ---

    // Standard attack calculating base damage + any buffs
// Remove the getArcaneBoost() helper method entirely.

    public void attack(Player target) {
        if (!canAct()) {
            System.out.println(this.name + " is incapacitated and cannot attack!");
            return;
        }

        int totalDamage = this.baseAttack;

        // Ask EVERY effect to modify the damage, just like you do in takeDamage()
        for (StatusEffect effect : this.activeEffects) {
            totalDamage = effect.modifyOutgoingDamage(totalDamage);
        }

        System.out.println(this.name + " attacks " + target.getName() + " for " + totalDamage + " damage!");
        target.takeDamage(totalDamage);
    }

    // Taking damage and allowing defensive effects (like Smoke) to block it
    public void takeDamage(int rawDamage) {
        int finalDamage = rawDamage;

        // Ask every active effect if they want to modify the damage
        for (StatusEffect effect : this.activeEffects) {
            finalDamage = effect.modifyIncomingDamage(finalDamage);
        }

        this.hp -= finalDamage;
        if (this.hp < 0) this.hp = 0; // Prevent negative HP

        if (finalDamage > 0) {
            System.out.println(this.name + " takes " + finalDamage + " damage. Current HP: " + this.hp);
        }
    }

    public void useSpecialSkill() {
        if (!canAct()) {
            System.out.println(this.name + " is incapacitated and cannot use their special skill!");
            return;
        }

        if (this.specialSkillCooldown == 0) {
            System.out.println("BOOM! " + this.name + " unleashes their Special Skill!");
            this.specialSkillCooldown = 3; // Put it on cooldown
        } else {
            System.out.println("Skill failed! Special skill is on cooldown for " + this.specialSkillCooldown + " turns.");
        }
    }

    // --- 3. Item Usage ---

    // A universal way to use any item in the game
    public void useItem(Item item, Enemy target) {
        if (!canAct()) {
            System.out.println(this.name + " is incapacitated and cannot use items!");
            return;
        }
        item.use(target);
    }

    // --- 4. Turn Cycle Management ---

    public void endTurn() {
        System.out.println("--- End of " + this.name + "'s Turn ---");

        // 1. Reduce Cooldowns
        if (this.specialSkillCooldown > 0) {
            this.specialSkillCooldown--;
            if (this.specialSkillCooldown == 0) {
                System.out.println(this.name + "'s Special Skill is ready!");
            }
        }

        // 2. Tick all Status Effects
        List<StatusEffect> expiredEffects = new ArrayList<>();

        // Iterate over a snapshot of the list
        for (StatusEffect effect : new ArrayList<>(this.activeEffects)) {
            effect.passTurn();

            if (!effect.isActive()) {
                expiredEffects.add(effect);
                System.out.println("-> " + effect.getName() + " has worn off " + this.name + ".");
            }
        }

        // 3. Clean up expired effects
        this.activeEffects.removeAll(expiredEffects);
    }
}