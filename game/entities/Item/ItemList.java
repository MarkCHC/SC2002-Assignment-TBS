package game.entities.Item;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum ItemList {
    POTION(Potion::new),
    POWER_STONE(PowerStone::new),
    SMOKE_BOMB(SmokeBomb::new);
    // add more items here

    private final Function<Integer, Item> factory;
    private static final Map<String, ItemList> INDEX = new HashMap<>();
    static {
        for (ItemList i : values()) {
            INDEX.put(i.name(), i);
        }
    }

    ItemList(Function<Integer, Item> factory) {
        this.factory = factory;
    }

    // function to create with specific quantities
    public Item create(int quantity) {
        return factory.apply(quantity);
    }

    // for indexing
    public static ItemList get(String name) {
        return INDEX.get(name);
    }
}