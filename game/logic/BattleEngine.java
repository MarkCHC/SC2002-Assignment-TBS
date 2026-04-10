package game.logic;
import java.util.ArrayList;
import java.util.List;
import game.entities.Combatant;
import game.entities.Enemy;
import game.entities.Player;

public class BattleEngine {
    private static int round = 1;
    private static List<State> states = new ArrayList<State>();

    // public BattleManagement(Player p, String diff, List<Item> items) {
        
    // }

    public static List<State> getStates() { //allows direct modification
        return states;
    }

    public static void showActions(Combatant c) {
        if (c.isPlayer()) {
            // player actions
        } else {
            // enemy actions
        }
    }

    public static void executePlayerAction() {
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

    // public static void saveState(State s) { // append state
    //     states.addLast(s); // no need deep copy
    // }

    public static State nextState() { // deep copy from last state
        State s = states.get(states.size()-1);
        State copy = new State(s);
        return copy;
    }

    public static Wave getNextWave(State s) { // modifies state
        List<Wave> w = s.getWavesLeft();
        if (w.size() > 0) {
            Wave nextWave = w.get(0);
            w.remove(0);
            return nextWave;
        }
        return new Wave();
    }

    // public static Enemy get

    public static boolean isWaveAlive(State s) {
        List<Enemy> currentWave = s.getEnemyState().stream().filter(e -> e.isAlive()).toList();
        if (currentWave.size() == 0) {
            return false;
        }
        return true;
    }

    public static boolean isWaveAlive() { // overload, get last state instead
        State s = states.get(states.size() - 1);
        List<Enemy> currentWave = s.getEnemyState().stream().filter(e -> e.isAlive()).toList();
        if (currentWave.size() == 0) {
            return false;
        }
        return true;
    }

    public static boolean areThereEnemiesLeft() {
        return false;
    }
    
    public static boolean isPlayerAlive() {
        return true;
    }
}