package game.entities.StatusEffect;

public class Stealth implements StatusEffect{

    private String name = "Stealth";
    private String description = "Hidden in smoke. Takes 0 damage.";
    private int duration;

    public Stealth(int duration) {
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
        System.out.println("Player in stealth. 0 damage taken.");
        return 0;
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
        return false;
    }
}
