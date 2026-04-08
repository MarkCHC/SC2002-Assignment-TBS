package arena.init;

import arena.level.LevelDifficulty;
import java.util.ArrayList;
import java.util.List;

// this is the overall flow of the init without all the detail

public class GameInitializer {
    private final InitiationUI ui;

    public GameInitializer(InitiationUI ui) {
        this.ui = ui;
    }

    public GameSetup initializeGame() {
        ui.showLoadingScreen();
        PlayerType playerType = ui.choosePlayerType();
        LevelDifficulty difficulty = ui.chooseDifficulty();

        int startingItemCount = 2;   // NUMBER OF ITEMS maybe need to move up

        List<ItemType> items = new ArrayList<>();
        for (int i = 1; i <= startingItemCount; i++) {
            items.add(ui.chooseItem(i));
        }
        
        return new GameSetup(playerType, difficulty, items);
    }
}
