package game.entities;
import java.util.List;
import java.util.ArrayList;
import game.logic.TurnOrderStrategy;

public class State {
    public int round;
    public List<Player> playerState; //maybe use generic Combatant?
    public List<Enemy> enemyState;
    public List<Wave> wavesLeft;
    public TurnOrderStrategy turnOrder;

    public State(int round) {
        this.round = round;
        playerState = new ArrayList<>();
        enemyState = new ArrayList<>();
        wavesLeft = new ArrayList<>();
    }

    public State(State s) {
        round = s.round;
        playerState = new ArrayList<>();
        for (Player p : s.playerState) {
            this.playerState.add(new Player(p));
        }
        enemyState = new ArrayList<>();
        for (Enemy e : s.enemyState) {
            this.enemyState.add(new Enemy(e)); 
        }
        wavesLeft = new ArrayList<>();
        for (Wave w : s.wavesLeft) {
            this.wavesLeft.add(new Wave(w));
        }
        this.turnOrder = new TurnOrderStrategy(s);
        this.turnOrder.sort();
    }

    public State(State s, int round) {}
}
