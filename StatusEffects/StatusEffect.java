package StatusEffects;

public interface StatusEffect {
    String getName();
    String getDescription();
    boolean isActive();
    int modifyIncomingDamage(int incomingDamage);
    void passTurn();
    boolean skipsTurn();
}