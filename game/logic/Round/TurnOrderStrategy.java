package game.logic.Round;
// import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import game.entities.Combatant;

public class TurnOrderStrategy {
    private List<Combatant> turnOrder;
    private Map<String, Integer> turnLog;

    public TurnOrderStrategy(List<Combatant> cList, Integer round) { // initialize  
        turnOrder = cList;
        Map<String, Integer> cMap = new HashMap<String, Integer>();
        for (Combatant c: cList) {
            cMap.put(c.getName(), round);
        }
        turnLog = cMap;
    }

    public TurnOrderStrategy(List<Combatant> cList, Map<String, Integer> cMap) {
        // shallow copies because we want to interact with them       
        turnOrder = cList;
        turnLog = new HashMap<String, Integer>(cMap);
    }

    public List<Combatant> getTurnOrder() {
        return turnOrder;
    }

    public Map<String, Integer> getTurnLog() {
        return turnLog;
    }

    public void endTurn() {
        if (turnOrder.size() > 0) {
            Combatant c = turnOrder.get(0);
            turnLog.put(c.getName(), turnLog.get(c.getName())+1); // Map[name] += 1
            // turnLog.put(c.getName(), turnLog.get(c.getName())+1); // ^ = current round
            turnOrder.remove(0);
        }
    }

    public Combatant getNextCombatant() {
        if (turnOrder.size() > 0) {
            return turnOrder.get(0);
        }
        return null;
    }

    public void sort() { // desc
        turnOrder.sort((Combatant c1, Combatant c2) -> Integer.compare(c2.getSpeed(), c1.getSpeed()));
    }
}