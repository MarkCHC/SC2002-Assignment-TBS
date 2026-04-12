package game.logic.Round;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import game.entities.Combatant;
import game.entities.Enemy.Enemy;
import game.entities.Item.Item;
import game.entities.Item.ItemList;
import game.entities.Player.Player;

public class State {
    private int round;
    private List<Player> playerState;
    private List<Enemy> enemyState;
    private List<Wave> wavesLeft;
    private List<Item> inventory;
    private TurnOrderStrategy turnOrderState;

    public State(int round) {
        this.round = round;
        playerState = new ArrayList<Player>();
        enemyState = new ArrayList<Enemy>();
        wavesLeft = new ArrayList<Wave>();
        inventory = new ArrayList<Item>();
    }

    public State(State s, int round) { // deep copy
        this.round = round;
        List<Combatant> turnOrder = new ArrayList<Combatant>();
        Map<String, Integer> oldMap = s.getTurnOrderStrategy().getTurnLog();

        playerState = new ArrayList<Player>();
        for (Player p : s.getPlayerState()) {
            Player pCopy = p.createCopy();
            this.playerState.add(pCopy);
            if (oldMap.get(pCopy.getName()) == round)
                turnOrder.add(pCopy);
        }
        enemyState = new ArrayList<Enemy>();
        for (Enemy e : s.getEnemyState()) {
            Enemy eCopy = e.createCopy();
            this.enemyState.add(eCopy); 
            if (oldMap.get(eCopy.getName()) == round)
                turnOrder.add(eCopy);
        }
        wavesLeft = new ArrayList<Wave>();
        for (Wave w : s.getWavesLeft()) {
            this.wavesLeft.add(new Wave(w));
        }
        inventory = new ArrayList<Item>();
        for (Item item : s.getInventory()) {
            ItemList type = ItemList.get(item.getLabel());
            this.inventory.add(type.create(item.getQuantity()));
        }
        this.turnOrderState = new TurnOrderStrategy(turnOrder, oldMap); 
        this.turnOrderState.sort();
    }

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

    public List<Item> getInventory() {
        return inventory;
    }

    public TurnOrderStrategy getTurnOrderStrategy() {
        return turnOrderState;
    }

    public void resetMap() {
        List<Combatant> cList = new ArrayList<>();
        for (Combatant c: this.playerState) {
            cList.add(c);
        }
        for (Combatant c: this.enemyState) {
            cList.add(c);
        }
        TurnOrderStrategy TO = new TurnOrderStrategy(cList, this.round);
        this.turnOrderState = TO;
        this.turnOrderState.sort(); // needed?
    }

    public void initialize(List<Player> playerState, List<Wave> wavesLeft, List<Item> inventory) {
        this.playerState = playerState;
        this.wavesLeft = wavesLeft;
        this.inventory = inventory;
        resetMap();
    }
    
}
