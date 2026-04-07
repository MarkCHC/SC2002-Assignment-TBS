package StatusEffects;

public class ArcaneBoost implements StatusEffect {

    private String name = "Arcane Attack Boost";
    private int attackBonus;

    public ArcaneBoost(int startingBonus) {
        this.attackBonus = startingBonus;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return "Infused with arcane energy. Attack power is increased by +" + this.attackBonus + ".";
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
    public void passTurn() {
    }

    @Override
    public boolean skipsTurn() {
        return false;
    }

    public void addBonus(int amount) {
        this.attackBonus += amount;
        System.out.println("Total attack bonus is now +" + this.attackBonus);
    }

    public int getAttackBonus() {
        return this.attackBonus;
    }
}