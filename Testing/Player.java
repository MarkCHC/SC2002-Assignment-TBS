package Testing;

import Items.Item;
import StatusEffects.StatusEffect;
import java.util.ArrayList;
import java.util.List;

public class Player {

    // --- Core Stats ---
    private String name;
    private int hp;
    private int maxHp;
    private int baseAttack;

    // --- Trackers ---
    private int specialSkillCooldown = 0;

    private List<StatusEffect> activeEffects;

    // --- Constructor ---
    public Player(String name, int startingHP, int maximumHP, int baseAttack) {
        this.name = name;
        this.hp = startingHP;
        this.maxHp = maximumHP;
        this.baseAttack = baseAttack;
        this.activeEffects = new ArrayList<>();
    }

    // --- Standard Getters & Setters ---
    public String getName() { return this.name; }
    public int getHP() { return this.hp; }
    public int getMaxHP() { return this.maxHp; }
    public void setHP(int newHP) { this.hp = newHP; }

    public int getSpecialSkillCooldown() { return this.specialSkillCooldown; }
    public void setSpecialSkillCooldown(int cooldown) { this.specialSkillCooldown = cooldown; }

    // 2. Added a getter so items/skills can safely read the effects
    public List<StatusEffect> getActiveEffects() { return this.activeEffects; }

    // --- 1. Status Effect Management ---
    public void addStatusEffect(StatusEffect effect) {
        this.activeEffects.add(effect);
        System.out.println("-> [" + this.name + "] gained effect: " + effect.getName());
    }

    public boolean canAct() {
        for (StatusEffect effect : this.activeEffects) {
            if (effect.skipsTurn()) {
                return false;
            }
        }
        return true;
    }

    // --- 2. Combat Mechanics ---
    public void attack(Enemy target) {
        if (!canAct()) {
            System.out.println(this.name + " is incapacitated and cannot attack!");
            return;
        }

        int totalDamage = this.baseAttack;

        for (StatusEffect effect : this.activeEffects) {
            totalDamage = effect.modifyOutgoingDamage(totalDamage);
        }

        System.out.println(this.name + " attacks " + target.getName() + " for " + totalDamage + " damage!");
        target.takeDamage(totalDamage);
    }

    public void takeDamage(int rawDamage) {
        int finalDamage = rawDamage;

        for (StatusEffect effect : this.activeEffects) {
            finalDamage = effect.modifyIncomingDamage(finalDamage);
        }

        this.hp -= finalDamage;
        if (this.hp < 0) this.hp = 0;

        if (finalDamage > 0) {
            System.out.println(this.name + " takes " + finalDamage + " damage. Current HP: " + this.hp);
        }
    }

    // 3. Removed the 'target' argument to match PowerStone
    public void useSpecialSkill() {
        if (!canAct()) {
            System.out.println(this.name + " is incapacitated and cannot use their special skill!");
            return;
        }

        if (this.specialSkillCooldown > 0) {
            System.out.println("Skill is on cooldown for " + this.specialSkillCooldown + " more turns!");
            return;
        }

        // Call the abstract effect method (no arguments needed now)
        triggerSpecialSkillEffect();

        this.specialSkillCooldown = 3;
    }

    public void triggerSpecialSkillEffect() {
        System.out.println(this.name + " unleashes a basic special attack!");
        // You can leave this completely empty if you want it to do nothing by default,
        // or add some basic damage logic here!
    }

    // --- 3. Item Usage ---
    public void useItem(Item item, Player target) {
        if (!canAct()) {
            System.out.println(this.name + " is incapacitated and cannot use items!");
            return;
        }
        item.use(target);
    }

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

        if (this.specialSkillCooldown > 0) {
            this.specialSkillCooldown--;
            if (this.specialSkillCooldown == 0) {
                System.out.println(this.name + "'s Special Skill is ready!");
            }
        }

        List<StatusEffect> expiredEffects = new ArrayList<>();

        for (StatusEffect effect : new ArrayList<>(this.activeEffects)) {
            effect.passTurn();

            if (!effect.isActive()) {
                expiredEffects.add(effect);
                System.out.println("-> " + effect.getName() + " has worn off " + this.name + ".");
            }
        }

        this.activeEffects.removeAll(expiredEffects);
    }
}