    package Testing;

    import Items.Item;
    import StatusEffects.ArcaneBoost;
    import StatusEffects.StatusEffect;
    import java.util.ArrayList;
    import java.util.List;

    public class Player {

        // --- Core Stats ---
        protected String name;
        protected int hp;
        protected int maxHp;
        protected int baseAttack;

        // --- Trackers ---
        protected int specialSkillCooldown = 0;
        protected List<StatusEffect> statusEffects; // Unified name

        // --- Constructor ---
        public Player(String name, int startingHP, int maximumHP, int baseAttack) {
            this.name = name;
            this.hp = startingHP;
            this.maxHp = maximumHP;
            this.baseAttack = baseAttack;
            this.statusEffects = new ArrayList<>();
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
            this.statusEffects.add(effect);
            System.out.println("-> [" + this.name + "] gained effect: " + effect.getName());
        }

        public boolean canAct() {
            for (StatusEffect effect : this.statusEffects) {
                if (effect.skipsTurn()) {
                    return false;
                }
            }
            return true;
        }

        // --- 2. Combat Mechanics ---

        // 2. The perfectly clean, unified attack!
        public void attack(Combatant target) {
            if (!canAct()) {
                System.out.println(this.name + " is incapacitated and cannot attack!");
                return;
            }

            int finalDamage = this.baseAttack;

            // The Player blindly asks every effect to modify the outgoing damage!
            for (StatusEffect effect : this.statusEffects) {
                finalDamage = effect.modifyOutgoingDamage(finalDamage);
            }

            System.out.println(this.name + " attacks " + target.getName() + " for " + finalDamage + " damage!");
            target.takeDamage(finalDamage);
        }

        public void takeDamage(int rawDamage) {
            int finalDamage = rawDamage;

            // Ask every active effect if they want to modify incoming damage (like Stealth)
            for (StatusEffect effect : this.statusEffects) {
                finalDamage = effect.modifyIncomingDamage(finalDamage);
            }

            this.hp -= finalDamage;
            if (this.hp < 0) this.hp = 0;

            if (finalDamage > 0) {
                System.out.println(this.name + " takes " + finalDamage + " damage. Current HP: " + this.hp);
            }
        }

        // --- 3. Special Skills ---

        public void useSpecialSkill(Combatant target) {
            if (!canAct()) {
                System.out.println(this.name + " is incapacitated and cannot use their special skill!");
                return;
            }

            if (this.specialSkillCooldown > 0) {
                System.out.println("Skill is on cooldown for " + this.specialSkillCooldown + " more turns!");
                return;
            }

            // Call the abstract effect method
            triggerSpecialSkillEffect(target);

            // Start the cooldown timer
            this.specialSkillCooldown = 3;
        }

        // 3. The Abstract "Action" method that Wizard and Warrior will fill out!
        public abstract void triggerSpecialSkillEffect(Combatant target);


        // --- 4. Item Usage ---

        // 4. One single unified method for ANY target!
        public void useItem(Item item, Combatant target) {
            if (!canAct()) {
                System.out.println(this.name + " is incapacitated and cannot use items!");
                return;
            }
            item.use(target);
        }

        // --- 5. Turn Cycle Management ---

        public void endTurn() {
            System.out.println("--- End of " + this.name + "'s Turn ---");

            if (this.specialSkillCooldown > 0) {
                this.specialSkillCooldown--;
                if (this.specialSkillCooldown == 0) {
                    System.out.println(this.name + "'s Special Skill is ready!");
                }
            }

            List<StatusEffect> expiredEffects = new ArrayList<>();

            for (StatusEffect effect : this.statusEffects) {
                effect.passTurn();
                if (!effect.isActive()) {
                    expiredEffects.add(effect);
                    System.out.println("-> " + effect.getName() + " has worn off " + this.name + ".");
                }
            }

            this.statusEffects.removeAll(expiredEffects);
        }

        // The End of Level Wipe!
        public void resetForNewLevel() {
            this.statusEffects.clear();
            System.out.println("The level ends. " + this.getName() + " takes a breath and readies for the next challenge.");
        }
    }