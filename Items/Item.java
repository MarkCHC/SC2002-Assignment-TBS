package Items;

import Testing.Enemy;
import Testing.Player;

public interface Item {
    String getName();
    String getDescription();
    int getQuantity();
    void use(Player target);
    void use(Enemy target);
}
