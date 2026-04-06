import java.util.ArrayList;
import java.util.List;
import game.entities.Combatant;
import game.entities.Enemy;
import game.entities.Player;
import game.entities.Wave;
import game.entities.State;
import game.logic.BattleEngine;
import game.ui.*;

public class TurnBasedApp {
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
        initialState.initialize(initCharacters, initWaves);
        BattleEngine.getStates().add(0, initialState);
        System.out.println("Game Initialized.");
        // Battling
        do { // condition probably look at enemies in current wave & wavesLeft
            // new round = new state (deep copy)
            State s = BattleEngine.nextState();
            // if (0) { // condition for ending round = no more turnOrder
            //     continue;
            // }
            // if wave cleared
            if (!BattleEngine.isWaveAlive(s)) { // replenish wave
                Wave wave = BattleEngine.getNextWave(s);
                for (Enemy e: wave.getEnemies()) {
                    s.getEnemyState().add(new Enemy(e));
                }
            }
            // get next Combatant in TO
            Combatant c = s.getTurnOrderStrategy().getNextCombatant();
            System.out.println("Combatant takes a turn.");
            // (if user)
                // display actions available
                // showActions();
                // ask for user input
                // execute action
            // (else enemy)
                // logic to select behaviour
                // execute behaviour
            // end turn
            s.getTurnOrderStrategy().endTurn();
            System.out.println("Their turn ends.");
            // endRound(); // handle new TO, round += 1
            System.out.println("The round ends when everyone took a turn.");
            // Game Over
            if (!BattleEngine.isPlayerAlive() || !BattleEngine.areThereEnemiesLeft()) { // either this breaks or while true loop
                // some logic
                GameCompletion.playerWin();
                System.out.println("Player win, game ends.");
                System.out.println("Option to restart. Declined.");
                // GameCompletion.playerLose();
                // play again logic - reset to first state
            }
            System.out.print("Safely Completed.");
            break;
        } while (BattleEngine.isWaveAlive() || BattleEngine.areThereEnemiesLeft());
    }
}
