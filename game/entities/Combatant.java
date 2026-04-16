package game.entities;
import java.util.List;
import java.util.ArrayList;
import game.entities.StatusEffect.StatusEffect;
import game.logic.Action.SpecialSkills.SpecialSkill;

public abstract class Combatant {
    // the current special skill is just to simulate the name & description
    // need special skill description and name inside the SpecialSkill class
    // along with it's getter method if private, in the actual implementation
    private final String name;
    private final int maxHp, attack, defense, speed;
    private int hp;
    private SpecialSkill specialSkill;
    protected List<StatusEffect> activeEffects = new ArrayList<>();

    protected Combatant(String name, int maxHp, int hp, int attack, int defense, int speed, SpecialSkill sp, List<StatusEffect> se) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.specialSkill = sp;
        if (se != null) {
            this.activeEffects = new ArrayList<>(se);
        }

    }

    protected abstract Combatant createCopy();

    public abstract Boolean isPlayer();

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public SpecialSkill getSpecialSkill() {
        return specialSkill;
    }

    public List<StatusEffect> getActiveEffects() {
        return activeEffects
                .stream()
                .filter(e -> e.isActive())
                .toList();
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int takeDamage(int rawDamage) {
        int damage = Math.max(0, rawDamage);
        hp = Math.max(0, hp - damage);
        return damage;
    }
    
    public void heal(int amount){
        hp = Math.min(hp + amount, maxHp);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void addStatusEffect(StatusEffect effect) {
        activeEffects.add(effect);
    }

    public void triggerSpecialSkill() {
        
    }
}