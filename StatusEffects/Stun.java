package StatusEffects;

public class Stun implements StatusEffect {

    private String name = "Stun";
    private String description = "Enemy is stunned for two turns.";
    private int duration;

    public Stun(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isActive() {
        return this.duration > 0;
    }

    public int modifyIncomingDamage(int incomingDamage) {
        return incomingDamage;
    }

    public int modifyOutgoingDamage(int baseDamage) {
        return baseDamage;
    }

    public void passTurn() {
        if (isActive()) {
            this.duration--;
        }
    }

    public boolean skipsTurn() {
        return true;
    }
}
