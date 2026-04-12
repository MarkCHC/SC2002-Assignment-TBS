package game.logic.Action.SpecialSkills;
import java.util.List;
import game.entities.Combatant;
import game.logic.Action.Player.Action;

public abstract class SpecialSkill implements Action {

    private static final int COOLDOWN = 3;
    private int remainingCooldown = 0;
    private final String specialSkillName, specialSkillDescription;

    protected SpecialSkill(String specialSkillName, String specialSkillDescription) {
        this.specialSkillName = specialSkillName;
        this.specialSkillDescription = specialSkillDescription;
    }

    protected SpecialSkill(String spName, String spDesc, int cd) {
        this.specialSkillName = spName;
        this.specialSkillDescription = spDesc;
        this.remainingCooldown = cd;
    } // for deep copy

    public String getSpecialSkillName() {
        return specialSkillName;
    };

    public String getSpecialSkillDescription() {
        return specialSkillDescription;
    };

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
    public abstract SpecialSkill getCopy();
}