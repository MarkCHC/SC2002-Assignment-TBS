import game.entities.Combatant;
import game.entities.Enemy.Enemy;
import game.entities.Item.Item;
import game.entities.Player.Player;
import game.entities.StatusEffect.StatusEffect;
import game.logic.Action.Player.Action;
import game.logic.BattleEngine;
import game.logic.Generator.PlayerType;
import game.logic.Level.LevelDifficulty;
import game.logic.Level.LevelFactory;
import game.logic.Round.State;
import game.logic.Round.Wave;
import game.ui.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnBasedApp {
    public static void main() {
        // Initiation
        Boolean RESTART = true;
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
        //Standardize appending to states list to maintain chronological history
        BattleEngine.getStates().add(initialState);
        System.out.println("==================== GAME SETUP COMPLETE =========================");

        // Battle Engine
        do {
            State s = BattleEngine.nextState();
            if (s.getTurnOrderStrategy().getTurnOrder().size() < 1) { // con for ending round = no more turnOrder
                BattleEngine.endRound(); // round += 1 for Engine
                s = BattleEngine.nextState(); // updates round number
                s.resetMap(); // updates TO
                BattleEngine.decreaseCD(s); // decrease CD
            }
            // if wave cleared
            if (!BattleEngine.isWaveAlive(s)) { // replenish wave
                Wave wave = BattleEngine.getNextWave(s);
                for (Enemy e: wave.getEnemies()) {
                    s.getEnemyState().add(e.createCopy());
                }
                s.resetMap(); // check if still need pass
                BattleEngine.endTurn(s);
                System.out.println("<<<<<<<<<<<<<<<<<<<<<< NEW WAVE SPAWNED >>>>>>>>>>>>>>>>>>>>>>>>>>");
                // pass this iteration because turnOrder would be empty if its duping from a empty enemy state
                continue;
            }
            // Display States
            GameUI.displayGameState(s);
            // Extensibility: display turn order for the round here
            // get next Combatant in TO
            Combatant c = s.getTurnOrderStrategy().getNextCombatant();
            System.out.println(c.getName()+"'s turn");

            // stun check start, find a better way to implement this
            boolean skipTurn = false;
            for (StatusEffect effect : c.getActiveEffects()) {
                if (effect.skipsTurn()) {
                    skipTurn = true;
                    System.out.println(c.getName() + " is stunned and cannot act.");
                    break;
                }
            }
            //should be safe for now but if there is DoT might need to change
            if (skipTurn) {
                s.getTurnOrderStrategy().endTurn();
                BattleEngine.endTurn(s);
                BattleEngine.removeDead();
                continue;
            }
            //end of stun check

            if (c.isPlayer()) {
                GameUI.showPlayerActions(c); // display actions available
                Map.Entry<Action, Integer> result;
                do {
                    result = GameUI.choosePlayerAction(s, c); // ask for user input
                } while (result == null);
                List<Combatant> e = GameUI.choosePlayerTarget(s, result);
                result.getKey().execute(c, e); // execute action
            } else {
                // TO IMPLEMENT: NAISTRA & MATIN
                Enemy enemy = (Enemy) c;
                Action action = enemy.getBehavior().chooseAction(s, enemy);
                List<Combatant> targets = enemy.getBehavior().chooseTargets(s, enemy, action);
                
                BattleEngine.showEnemyActions();
                
                if (action != null && targets != null && !targets.isEmpty()) {
                    action.execute(enemy, targets);
                }
            }
            // end turn
            s.getTurnOrderStrategy().endTurn(); // remove from TO
            BattleEngine.endTurn(s); // adds modified state
            BattleEngine.removeDead();

            // Checks for Game Over
            if (!BattleEngine.areThereEnemiesLeft()) {
                GameCompletion.playerWin();
                // statistics
                GameCompletion.winStats(s.getPlayerState().get(0).getHp(), s.getRound());
                // play again logic - reset to first state
                RESTART = Initiation.chooseRestart();
                if (RESTART)
                    BattleEngine.restartGame();
            }
            if (!BattleEngine.isPlayerAlive()) {
                GameCompletion.playerLose();
                // statistics
                GameCompletion.loseStats(s.getEnemyState().size(), s.getRound());
                // play again logic - reset to first state
                RESTART = Initiation.chooseRestart();
                if (RESTART)
                    BattleEngine.restartGame();
            }
        } while (RESTART); // this checks for restart logic
    }
}
