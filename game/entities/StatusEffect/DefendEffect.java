package game.entities.StatusEffect;

public class DefendEffect implements StatusEffect {
    private String name = "Defending";
    private String description;
    private int defenseBoost;
    private int duration;

    public DefendEffect(int defenseBoost, int duration) {
        this.defenseBoost = defenseBoost;
        this.duration = duration;
        this.description = "Defense increased by " + defenseBoost + " for " + duration + " turns.";
    }

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }

    public boolean isActive() { return this.duration > 0; }

    // Reduces incoming damage by the defense boost amount
    public int modifyIncomingDamage(int incomingDamage) {
        return incomingDamage - this.defenseBoost;
    }

    public int modifyOutgoingDamage(int baseDamage) {
        return baseDamage;
    }

    public void passTurn() {
        if (isActive()) {
            this.duration--;
        }
    }

    public boolean skipsTurn() { return false; }
}
