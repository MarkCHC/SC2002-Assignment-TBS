package game.entities.Player;
import game.logic.Action.SpecialSkills.SpecialSkill;
import java.util.List;
import java.util.ArrayList;
import game.entities.StatusEffect.StatusEffect;
import game.logic.Action.SpecialSkills.ShieldBash;

public class Warrior extends Player {
    public Warrior() {
        super(
            "Warrior", 
            260, 
            40, 
            20, 
            30,
            new ShieldBash()
        );
    }

    public Warrior(
        String name, 
        int maxHp, 
        int currentHp, 
        int attack, 
        int defense, 
        int speed, 
        SpecialSkill sp,
        List<StatusEffect> activeEffects
    ) 
    {
        super(name, maxHp, currentHp, attack, defense, speed, sp, activeEffects);
    }

    public Warrior createCopy() {
        List<StatusEffect> sList = new ArrayList<>();
        for (StatusEffect se: activeEffects) {
            sList.add(se.getCopy());
        }
        return new Warrior(
            this.getName(), 
            this.getMaxHp(), 
            this.getHp(), 
            this.getAttack(), 
            this.getDefense(), 
            this.getSpeed(), 
            this.getSpecialSkill().getCopy(),
            sList
        );
    }
}
