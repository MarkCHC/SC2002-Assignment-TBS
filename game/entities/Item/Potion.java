package game.entities.Item;
import java.util.List;
import game.logic.Action.Player.Action;
import game.entities.Combatant;
// import game.entities.Enemy.Enemy;
// import game.entities.Player.Player;

public class Potion implements Item, Action {

    //private attribute(s)
    private int healamount = 100;
    private int potionQuantity;

    public Potion(int startingQuantity) {
        this.potionQuantity = startingQuantity;
    }

    public String getLabel() {
        return "POTION";
    }

    public String getName() {
        return "Potion (x" + this.potionQuantity + ")";
    }

    public String getDescription() {
        return "Restores 100 HP to player. Remaining: " + this.potionQuantity;
    }

    public int getQuantity() {
        return this.potionQuantity;
    }

    // public void use(Enemy target){}
    // //here for fulfilling Item interface requirement
    // //will be removed once using combatant

    public void execute(Combatant target, List<Combatant> None) {
    // public void use(Player target) {
        if (this.potionQuantity <= 0) {
            System.out.println("Out of Potions!");
            return;
        }

        int currentHP = target.getHp();
        int maxHP = target.getMaxHp();

        if (currentHP + healamount < maxHP){
            target.setHp(currentHP + healamount);
        } else {
            target.setHp(maxHP);
        }

        this.potionQuantity--;

        System.out.println("Character HP Updated. New HP: " + target.getHp());
        System.out.println("You have " + this.potionQuantity + " Potions left.");
    }
}