package game.entities;
import java.util.ArrayList;
import java.util.List;
import game.interfaces.*;
import game.logic.*;
import game.ui.*;

public class BattleManagement {
    private static int round = 1;
    private static List<State> states = new ArrayList<State>();

    public BattleManagement(Player p, String diff, List<Item> items) {
        
    }

    public void showActions(Combatant c) {
        if (c.isPlayer()) {
            // player actions
        } else {
            // enemy actions
        }
    }

    public void executePlayerAction() {
        // user input
        int x = 1;
        // call the action method
        switch(x) {
            case 1:
                // eg. Basic Attack
                break;
            default:
                break;
        }
    }

    // public endTurn(): void
    // public endRound(): void

    public static void saveState(State s) { // append state
        states.addLast(s); // no need deep copy
    }

    public static State nextState() { // deep copy from last state
        State s = states.getLast();
        State copy = new State(s);
        return copy;
    }

    public static Wave getNextWave(State s) { // modifies state
        List<Wave> w = s.wavesLeft;
        if (w.size() > 0) {
            Wave nextWave = w.getFirst();
            w.removeFirst();
            return nextWave;
        }
        return new Wave();
    }

    // public static Enemy get
 
    public boolean isPlayer() {
        return true;
    }

    public static boolean isWaveAlive(State s) {
        List<Enemy> currentWave = s.enemyState.stream().filter(e -> e.isAlive()).toList();
        if (currentWave.size() == 0) {
            return false;
        }
        return true;
    }

    public static boolean allEnemiesDead() {
        return true;
    }
    
    public static boolean isPlayerAlive() {
        return true;
    }

    public static void main() {
        // Initiation
        Initiation.showLoadingScreen();
        List<Wave> initWaves = Initiation.chooseDifficulty();
        List<Player> initCharacters = new ArrayList<Player>(); // ?
        for (Player character: initCharacters) {
            character = Initiation.chooseClass();
        }
        Initiation.chooseItems();
        State initialState = new State(0);
        initialState.wavesLeft = initWaves;
        initialState.playerState = initCharacters;
        states.addFirst(initialState);
        // Battling
        while (true) { // condition probably look at enemies in current wave
            // new round = new state (deep copy)
            State s = nextState();
            // if wave cleared
            if (!isWaveAlive(s)) {
                Wave wave = getNextWave(s);
                for (Enemy e: wave.getEnemies()) {
                    s.enemyState.add(new Enemy(e));
                }
            }
            // get next Combatant in TO
            s.turnOrder.getNextCombatant();
            // (if user)
                // display actions available
                // showActions();
                // ask for user input
                // execute action
            // (else enemy)
                // logic to select behaviour
                // execute behaviour
            // end turn
            s.turnOrder.endTurn();
            // endRound(); // handle new TO, round += 1
            // Game Over
            if (!isPlayerAlive() || allEnemiesDead()) {
                // some logic
                GameCompletion.playerWin();
                // GameCompletion.playerLose();
                // play again logic - reset to first state
            }
            break;
        }
    }
}