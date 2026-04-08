package arena.init;

//storing all item type info, just a helper class

public enum ItemType {
    POTION("Potion", "Heal 100 HP"),
    POWER_STONE("Power Stone", "Trigger special skill once for free"),
    SMOKE_BOMB("Smoke Bomb", "Enemy attacks deal 0 damage for current and next turn");

    private final String displayName;
    private final String description;

    ItemType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
