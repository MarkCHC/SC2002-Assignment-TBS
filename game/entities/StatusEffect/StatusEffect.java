package game.entities.StatusEffect;

public interface StatusEffect {
    String getName();
    String getDescription();
    boolean isActive();
    int modifyIncomingDamage(int incomingDamage);
    int modifyOutgoingDamage(int baseDamage);
    void passTurn();
    boolean skipsTurn();
    StatusEffect getCopy();
}