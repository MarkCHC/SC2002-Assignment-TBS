package StatusEffects;

public class Stealth implements StatusEffect{

    private String name = "Stealth";
    private String description = "Hidden in smoke. Takes 0 damage.";
    private int duration;

    public Stealth(int duration) {
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
        System.out.println("Player in stealth. 0 damage taken.");
        return 0;
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
        return false;
    }
}