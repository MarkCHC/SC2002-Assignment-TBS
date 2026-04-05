package game.entities;
public class Player extends Combatant {
    // assume all other stats here or inherited    
    protected boolean player = true; //some sort of indicator

    public Player() {
        super(0);
    }

    public Player(Player p) {
        super(p.getSpeed());
    }
}
