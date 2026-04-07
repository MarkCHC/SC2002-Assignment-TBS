import java.util.List;
import java.util.ArrayList;

public abstract class Combatant {
    protected int hp, maxHp, attack, defense, speed;
    protected String name;
    protected SpecialSkill specialSkill;

    protected List<StatusEffect> activeEffects = new ArrayList<>();


    //constructor!!!
    public Combatant(String name, int hp, int maxHp, int attack, int defense, int speed, SpecialSkill specialSkill){
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed; 
        this.specialSkill = specialSkill;   
    }
    public boolean isAlive(){
        if (hp > 0){
            return true;
        }
        return false;
    }

    public void takeDamage(int amount){
        hp = hp - amount;
        if (hp < 0){
            hp = 0;
        }
    }

    public void heal(int amount){
        hp = hp + amount;
        if (hp > maxHp){
            hp = maxHp;
        }
    }

    public void addStatusEffect(StatusEffect effect){
        activeEffects.add(effect);
    }

}