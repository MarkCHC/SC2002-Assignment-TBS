package game.entities;

public abstract class Combatant {
    // the current special skill is just to simulate the name & description
    // need special skill description and name inside the SpecialSkill class
    // along with it's getter method if private, in the actual implementation
    private final String name, specialSkillName, specialSkillDescription;
    private final int maxHp, attack, defense, speed;
    private int hp;

    protected Combatant(String name, int maxHp, int hp, int attack, int defense, int speed, String specialSkillName, String specialSkillDescription) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.specialSkillName = specialSkillName;
        this.specialSkillDescription = specialSkillDescription;
    }

    protected abstract Combatant createCopy();

    public abstract Boolean isPlayer();

    public String getName() {
        return name;
    }

    public String getSpecialSkillName() {
        return specialSkillName;
    }

    public String getSpecialSkillDescription() {
        return specialSkillDescription;
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

    public boolean isAlive() {
        return hp > 0;
    }

    public int takeDamage(int rawDamage) {
        int damage = Math.max(0, rawDamage);
        hp = Math.max(0, hp - damage);
        return damage;
    }
    
    public void heal(int amount){
        hp = Math.max(hp + amount, maxHp);
    }
}