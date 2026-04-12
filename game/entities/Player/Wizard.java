package game.entities.Player;
import game.logic.Action.SpecialSkills.SpecialSkill;
import game.logic.Action.SpecialSkills.ArcaneBlast;

public class Wizard extends Player {
    public Wizard() {
        super(
            "Wizard", 
            200, 
            50, 
            10, 
            20,
            new ArcaneBlast()
        );
    }

    public Wizard(
        String name, 
        int maxHp, 
        int currentHp, 
        int attack, 
        int defense, 
        int speed, 
        SpecialSkill sp
    ) 
    {
        super(name, maxHp, currentHp, attack, defense, speed, sp);
    }

    public Wizard createCopy() {
        return new Wizard(
            this.getName(), 
            this.getMaxHp(), 
            this.getHp(), 
            this.getAttack(), 
            this.getDefense(), 
            this.getSpeed(), 
            this.getSpecialSkill().getCopy()
        );
    }
}
