package game.actions;

import game.entities.Combatant;
import java.util.List;

public interface Action {
    void execute(Combatant actor, List<Combatant> targets);
}