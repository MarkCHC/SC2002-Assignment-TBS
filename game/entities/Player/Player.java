package game.entities.Player;
import java.util.List;
import java.util.ArrayList;
import game.entities.Combatant;
import game.logic.Action.SpecialSkills.SpecialSkill;

public abstract class Player extends Combatant {
    private final List<String> startingItems;
    // private SpecialSkill specialSkill;

    protected Player(String name, int maxHp, int attack, int defense, int speed, SpecialSkill sp) {
        super(name, maxHp, maxHp, attack, defense, speed, sp);
        this.startingItems = new ArrayList<>();
    } // for Instantiation - 6 params

    protected Player(String name, int maxHp, int currentHp, int attack, int defense, int speed, SpecialSkill sp) {
        super(name, maxHp, currentHp, attack, defense, speed, sp);
        this.startingItems = new ArrayList<>();
    } // for deep copy - 7 params

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
