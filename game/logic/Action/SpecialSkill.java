package game.logic.Action;
import java.util.List;
import game.entities.Combatant;

public abstract class SpecialSkill implements Action {

    private static final int COOLDOWN = 3;
    private int remainingCooldown = 0;
    // move name here
    // move description here

    public boolean isAvailable() {
        return remainingCooldown == 0;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    // Called by BattleEngine at the end of this combatant's turn
    public void reduceCooldown() {
        if (remainingCooldown > 0) {
            remainingCooldown--;
        }
    }

    // Triggers the skill effect without starting/changing the cooldown.
    // Used by PowerStone.
    public void triggerEffectOnly(Combatant actor, List<Combatant> targets) {
        performSkill(actor, targets);
    }

    @Override
    public final void execute(Combatant actor, List<Combatant> targets) {
        if (!isAvailable()) {
            System.out.println("Special skill on cooldown! (" + remainingCooldown + " turns left)");
            return;
        }
        remainingCooldown = COOLDOWN;
        performSkill(actor, targets);
    }

    // Subclasses implement this — not execute() directly
    protected abstract void performSkill(Combatant actor, List<Combatant> targets);
}