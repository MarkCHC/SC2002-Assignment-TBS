package game.entities.Item;
import java.util.List;
import game.logic.Action.Player.Action;
import game.entities.Combatant;
// import game.entities.Enemy.Enemy;
// import game.entities.Player.Player;
import game.entities.StatusEffect.Stealth;

public class SmokeBomb implements Item, Action {

    private int duration = 2;
    private int sbQuantity;

    public SmokeBomb(int startingQuantity) {
        this.sbQuantity = startingQuantity;
    }

    public String getLabel() {
        return "SMOKE_BOMB";
    }

    public String getName() {
        return "Smoke Bomb (x" + this.sbQuantity + ")";
    }

    public String getDescription() {
        return "Player is invulnerable for " + this.duration + " turns";
    }

    public int getQuantity() {
        return this.sbQuantity;
    }

    // public void use(Enemy target) {}
    // //here for fulfilling Item interface requirement
    // //will be removed once skill is not implementing Item

    public void execute(Combatant target, List<Combatant> None) {
    // public void use(Player target) {
        if (this.sbQuantity <= 0) {
            System.out.println("Out of Smoke Bombs!");
            return;
        }

        System.out.println("Threw a " + getLabel() + " at their feet!");

        Stealth effect = new Stealth(this.duration);
        target.addStatusEffect(effect);

        this.sbQuantity--;
        System.out.println("You have " + this.sbQuantity + " Smoke Bombs left.");
    }
}
