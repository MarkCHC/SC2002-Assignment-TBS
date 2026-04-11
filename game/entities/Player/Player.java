package game.entities.Player;
import java.util.List;
import java.util.ArrayList;
import game.logic.Action.SpecialSkill;

import game.entities.Combatant;

public abstract class Player extends Combatant {
    private final List<String> startingItems;
    // private SpecialSkill specialSkill;

    protected Player(String name, int maxHp, int attack, int defense, int speed, String specialSkillName, String specialSkillDescription) {
        super(name, maxHp, maxHp, attack, defense, speed, specialSkillName, specialSkillDescription);
        this.startingItems = new ArrayList<>();
    } // for Instantiation - 7 params

    protected Player(String name, int maxHp, int currentHp, int attack, int defense, int speed, String specialSkillName, String specialSkillDescription) {
        super(name, maxHp, currentHp, attack, defense, speed, specialSkillName, specialSkillDescription);
        this.startingItems = new ArrayList<>();
    } // for deep copy - 8 params

    public abstract Player createCopy();

    public Boolean isPlayer() {
        return true;
    }

    public void addStartingItem(String itemName) {
        startingItems.add(itemName);
    }

    public List<String> getStartingItems() {
        return startingItems;
    }

    // public getSpecialSkill
}
