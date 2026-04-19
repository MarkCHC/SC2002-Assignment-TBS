package game.entities.Item;
import java.util.List;
import game.logic.Action.Player.Action;
import game.logic.Action.SpecialSkills.SpecialSkill;
import game.entities.Combatant;

public class PowerStone implements Item, Action {

    private int psQuantity;

    public PowerStone(int startingQuantity) {
        this.psQuantity = startingQuantity;
    }

    public String getLabel() {
        return "POWER_STONE";
    }

    public String getName() {
        return "Power Stone (x" + this.psQuantity + ")";
    }

    public String getDescription() {
        return "Gives Player free extra use of Special Skill";
    }

    public int getQuantity() {
        return this.psQuantity;
    }

    public void execute(Combatant actor, List<Combatant> targets) {
        if (this.psQuantity <= 0){
            System.out.println("Out of Power Stones!");
            return;
        }

        System.out.println("Player used " + getLabel() + "!");

        actor.getSpecialSkill().triggerEffectOnly(actor, targets);

        this.psQuantity--;
        System.out.println("You have " + this.psQuantity + " Power Stones left.");
    }
}
