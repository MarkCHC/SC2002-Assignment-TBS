package StatusEffects;

public class Stun implements StatusEffect {

    private String name = "Stun";
    private String description = "Enemy is stunned for two turns.";
    private int duration;

    public Stun(int duration) {
        this.duration = duration;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isActive() {
        return this.duration > 0;
    }

    @Override
    public int modifyIncomingDamage(int incomingDamage) {
        return incomingDamage;
    }

    @Override
    public int modifyOutgoingDamage(int baseDamage) {
        return baseDamage;
    }

    @Override
    public void passTurn() {
        if (isActive()) {
            this.duration--;
        }
    }

    @Override
    public boolean skipsTurn() {
        return true;
    }
}
