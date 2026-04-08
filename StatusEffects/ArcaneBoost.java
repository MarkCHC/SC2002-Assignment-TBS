package StatusEffects;

public class ArcaneBoost implements StatusEffect {

    private String name = "Arcane Attack Boost";
    private int enemiesDefeated;
    private int bonusPerKill = 10;

    public ArcaneBoost(int startingKills) {
        this.enemiesDefeated = startingKills;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        int currentBonus = this.enemiesDefeated * this.bonusPerKill;
        return "Base attack power is increased by +" + currentBonus + ".";
    }

    public boolean isActive() {
        return true;
    }

    public int modifyIncomingDamage(int incomingDamage) {
        return incomingDamage;
    }

    public int modifyOutgoingDamage(int baseDamage) {
        int extraDamage = this.enemiesDefeated * this.bonusPerKill;
        return baseDamage + extraDamage;
    }

    public void passTurn() {
    }

    public boolean skipsTurn() {
        return false;
    }

    public void addKill() {
    this.enemiesDefeated++;
    System.out.println("Wizard base attack grows");
    }
}