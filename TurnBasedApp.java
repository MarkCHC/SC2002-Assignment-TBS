import java.util.ArrayList;
import java.util.List;
import game.entities.Combatant;
import game.entities.Enemy.Enemy;
import game.entities.Player.Player;
import game.entities.Item.Item;
import game.logic.BattleEngine;
import game.logic.Generator.PlayerType;
import game.logic.Level.LevelDifficulty;
import game.logic.Level.LevelFactory;
import game.logic.Round.State;
import game.logic.Round.TurnOrderStrategy;
import game.logic.Round.Wave;
import game.ui.*;

public class TurnBasedApp {
    public static void main() {
        // Initiation
        Initiation.showLoadingScreen();
        LevelDifficulty difficulty = Initiation.chooseDifficulty();
        List<Wave> initWaves = LevelFactory.createWaves(difficulty);
        List<Player> initCharacters = new ArrayList<Player>();
        for (int i=0; i<Initiation.choosePlayers(); i++) {
            PlayerType classType = Initiation.chooseClass();
            initCharacters.add(classType.createPlayer()); // issue with player character creation
        }
        List<Item> inventory = Initiation.chooseItems();
        State initialState = new State(1);
        initialState.initialize(initCharacters, initWaves, inventory);
        BattleEngine.getStates().add(0, initialState);
        System.out.println("==================== GAME SETUP COMPLETE =========================");

        // Battle Engine
        do {
            State s = BattleEngine.nextState();
            // System.out.println(s.getTurnOrderStrategy().getTurnOrder().size());
            if (s.getTurnOrderStrategy().getTurnOrder().size() < 1) { // con for ending round = no more turnOrder
                BattleEngine.endRound(); // round += 1 for Engine
                s = BattleEngine.nextState(); // updates round number
                s.resetMap(); // updates TO
            }
            // if wave cleared
            if (!BattleEngine.isWaveAlive(s)) { // replenish wave
                // System.out.println("Replenishing waves...");
                Wave wave = BattleEngine.getNextWave(s);
                for (Enemy e: wave.getEnemies()) {
                    s.getEnemyState().add(e.createCopy());
                }
                s.resetMap(); // check if still need pass
                BattleEngine.endTurn(s);
                // BattleEngine.endRound();
                // pass this iteration because turnOrder would be empty if its duping from a empty enemy state
                continue;
            }
            // Display States
            GameUI.displayGameState(s);

            // get next Combatant in TO
            Combatant c = s.getTurnOrderStrategy().getNextCombatant();
            // System.out.println(c);
            System.out.println(c.getName()+"'s turn");
            if (c.isPlayer()) {
                GameUI.showPlayerActions(c); // display actions available
                GameUI.choosePlayerAction(s); // ask for user input
                // execute action
                // BattleEngine
            } else {
                // logic to select behaviour
                BattleEngine.showEnemyActions();
                // execute behaviour
            }
            // end turn
            s.getTurnOrderStrategy().endTurn(); // remove from TO
            BattleEngine.endTurn(s); // adds modified state
            // consider if the action is actually working,
            //     if the turnOrder returns pointer to same object as those in the State
            System.out.println(s.getTurnOrderStrategy().getTurnOrder().size());

            // Checks for Game Over
            if (!BattleEngine.isPlayerAlive() || !BattleEngine.areThereEnemiesLeft()) { // either this breaks or while true loop
                // some logic
                GameCompletion.playerWin();
                System.out.println("Player win, game ends.");
                System.out.println("Option to restart. Declined.");
                System.out.print("Safely Completed.");
                break;
                // GameCompletion.playerLose();
                // play again logic - reset to first state
            }
            // break;
        } while (true);
        // } while (BattleEngine.isWaveAlive() || BattleEngine.areThereEnemiesLeft());
        // condition probably look at enemies in current wave & wavesLeft
    }
}
