package game.logic;
import java.util.List;
import java.util.ArrayList;

import game.entities.Combatant;
import game.entities.State;

public class TurnOrderStrategy {
    private List<Combatant> turnOrder;

    public TurnOrderStrategy(State s) {
        turnOrder = new ArrayList<>();
        // shallow copy because we want to interact with them
        for (Combatant p: s.playerState) {
            if (p.isAlive())
                turnOrder.add(p);
        }
        for (Combatant e: s.enemyState) {
            if (e.isAlive())
                turnOrder.add(e);
        }
    }

    public void endTurn() {
        if (turnOrder.size() > 0)
            turnOrder.removeFirst();
    }

    public Combatant getNextCombatant() {
        if (turnOrder.size() > 0) {
            return turnOrder.getFirst();
        } else {
            return null;
        }
    }

    public void sort() { // desc
        turnOrder.sort((Combatant c1, Combatant c2) -> Integer.compare(c2.getSpeed(), c1.getSpeed()));
    }

    // public static void main() { // for testing
    //     TurnOrderStrategy TO = new TurnOrderStrategy();
    //     Combatant one = new Combatant(1);
    //     Combatant two = new Combatant(2);
    //     Combatant three = new Combatant(3);
        
    //     TO.add(one);
    //     TO.add(three);
    //     TO.add(two);
    //     TO.sort();

    //     for (Combatant c: TO.getTurnOrder()) {
    //         System.out.println(c.getSpeed());
    //     }
    // }
}