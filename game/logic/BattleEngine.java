package game.logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import game.entities.Combatant;
import game.entities.Enemy.Enemy;
import game.entities.Player.Player;
import game.entities.StatusEffect.StatusEffect;
import game.logic.Round.State;
import game.logic.Round.Wave;

public class BattleEngine {
    private static int round = 1;
    private static List<State> states = new ArrayList<State>();

    public static List<State> getStates() { //allows direct modification
        return states;
    }

    public static void showEnemyActions() {
        try {
            Thread.sleep(1000); // simulate loading
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted");
        }
    }

    public static void executePlayerAction() {}

    public static void removeDead() {
        State s = states.get(states.size()-1);
        Iterator<Enemy> it = s.getEnemyState().iterator();
        while (it.hasNext()) {
            Combatant e = it.next();
            if (!e.isAlive()) {
                it.remove();
                System.out.println(e.getName()+" has died.");
            }
        }
        Iterator<Player> it2 = s.getPlayerState().iterator();
        while (it2.hasNext()) {
            Combatant e = it2.next();
            if (!e.isAlive()) {
                it2.remove();
                System.out.println(e.getName()+" has died.");
            }
        }
    }

    public static void endTurn(State s) {
        states.add(s);
    }

    public static void endRound() {
        round += 1;
    }

    public static void decreaseCD(State s) {
        for (Player c: s.getPlayerState()) {
            List<StatusEffect> sList = c.getActiveEffects();
            System.out.println(c.getActiveEffects());
            for (StatusEffect se: sList) {
                se.passTurn();
            }
            c.getSpecialSkill().reduceCooldown();
        }
        for (Enemy c: s.getEnemyState()) {
            List<StatusEffect> sList = c.getActiveEffects();
            for (StatusEffect se: sList) {
                se.passTurn();
            }
        }
    }

    public static void undoTurn() { // pop and jump back in states
        
    }

    public static Map<String, Integer> getLastScenario() {
        State s = states.get(states.size()-1);
        return s.getTurnOrderStrategy().getTurnLog();
    }

    public static State nextState() { // deep copy from last state
        State s = states.get(states.size()-1);
        State copy = new State(s, BattleEngine.round);
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
        State s = states.get(states.size()-1);
        if (s.getEnemyState().size() == 0 && s.getWavesLeft().size() == 0) {
            return false;
        }
        return true;
    }
    
    public static boolean isPlayerAlive() {
        State s = states.get(states.size()-1);
        if (s.getPlayerState().size() == 0) {
            return false;
        }
        return true;
    }

    public static void restartGame() {

    }
}