package game.entities;
// import java.util.Comparator;

public class Combatant {
    private int speed;
    protected boolean player;
    
    public Combatant(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isPlayer() {
        return player;
    }

    public boolean isAlive() {
        // should check hp against max hp maybe
        return true;
    }
}
