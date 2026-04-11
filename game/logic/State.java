package game.logic;
import java.util.List;
import java.util.ArrayList;

import game.entities.Enemy.Enemy;
import game.entities.Player.Player;

public class State {
    private int round;
    private List<Player> playerState; //maybe use generic Combatant?
    private List<Enemy> enemyState;
    private List<Wave> wavesLeft;
    private TurnOrderStrategy turnOrderState;

    public State(int round) {
        this.round = round;
        playerState = new ArrayList<Player>();
        enemyState = new ArrayList<Enemy>();
        wavesLeft = new ArrayList<Wave>();
    }

    public State(State s) {
        round = s.round;
        playerState = new ArrayList<>();
        for (Player p : s.getPlayerState()) {
            this.playerState.add(p.createCopy());
        }
        enemyState = new ArrayList<>();
        for (Enemy e : s.getEnemyState()) {
            this.enemyState.add(e.createCopy()); 
        }
        wavesLeft = new ArrayList<>();
        for (Wave w : s.getWavesLeft()) {
            this.wavesLeft.add(new Wave(w));
        }
        this.turnOrderState = new TurnOrderStrategy(s);
        this.turnOrderState.sort();
    }

    public State(State s, int round) {}

    public int getRound() {
        return round;
    }

    public List<Player> getPlayerState() {
        return playerState;
    }

    public List<Enemy> getEnemyState() {
        return enemyState;
    }

    public List<Wave> getWavesLeft() {
        return wavesLeft;
    }

    public TurnOrderStrategy getTurnOrderStrategy() {
        return turnOrderState;
    }

    public void initialize(List<Player> playerState, List<Wave> wavesLeft) {
        this.playerState = playerState;
        this.wavesLeft = wavesLeft;
    }
}
