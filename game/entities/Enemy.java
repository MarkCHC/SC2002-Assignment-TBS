package game.entities;
public class Enemy extends Combatant {
    // assume all other stats here or inherited    
    protected boolean player = false; //some sort of indicator

    public Enemy() { //param = all stats
        super(0);
    }

    public Enemy(Enemy e) { //deep copy constructor
        super(e.getSpeed());
    }
}
