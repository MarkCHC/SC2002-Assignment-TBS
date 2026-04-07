package StatusEffects;

public class ArcaneBoost implements StatusEffect {

    private String name = "Arcane Attack Boost";
    private int enemiesDefeated;
    private int bonusPerKill = 10;

    public ArcaneBoost(int startingKills) {
        this.enemiesDefeated = startingKills;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        int currentBonus = this.enemiesDefeated * this.bonusPerKill;
        return "Base attack power is increased by +" + currentBonus + ".";
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public int modifyIncomingDamage(int incomingDamage) {
        return incomingDamage;
    }

    @Override
    public int modifyOutgoingDamage(int baseDamage) {
        int extraDamage = this.enemiesDefeated * this.bonusPerKill;
        return baseDamage + extraDamage;
    }

    @Override
    public void passTurn() {
    }

    @Override
    public boolean skipsTurn() {
        return false;
    }

    public void addKill() {
    this.enemiesDefeated++;
    System.out.println("Wizard base attack grows");
    }
}