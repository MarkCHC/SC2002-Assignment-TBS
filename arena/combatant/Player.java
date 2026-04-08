package arena.combatant;

import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Combatant {
    private final List<String> startingItems;

    protected Player(String name, int maxHp, int attack, int defense, int speed, String specialSkillName, String specialSkillDescription) {
        super(name, maxHp, attack, defense, speed, specialSkillName, specialSkillDescription);
        this.startingItems = new ArrayList<>();
    }

    public void addStartingItem(String itemName) {
        startingItems.add(itemName);
    }

    public List<String> getStartingItems() {
        return startingItems;
    }
}
