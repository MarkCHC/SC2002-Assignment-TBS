package game.entities.Player;
import java.util.List;
import java.util.ArrayList;
import game.entities.Combatant;
import game.entities.StatusEffect.StatusEffect;
import game.logic.Action.SpecialSkills.SpecialSkill;

public abstract class Player extends Combatant {

    protected Player(String name, int maxHp, int attack, int defense, int speed, SpecialSkill sp) {
        super(name, maxHp, maxHp, attack, defense, speed, sp, new ArrayList<>());
    } // for Instantiation - 6 params

    protected Player(String name, int maxHp, int currentHp, int attack, int defense, int speed, SpecialSkill sp, List<StatusEffect> se) {
        super(name, maxHp, currentHp, attack, defense, speed, sp, se);
    } // for deep copy - 8 params

    public abstract Player createCopy();

    public Boolean isPlayer() {
        return true;
    }
}
