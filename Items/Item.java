package Items;

import Testing.Enemy;
import Testing.Player;

public interface Item {
    String getName();
    String getDescription();
    void use(Player target);
    void use(Enemy target);
}
