package arena.init;

import arena.level.LevelDifficulty;

// Interface for initUI

public interface InitiationUI {
    void showLoadingScreen();

    PlayerType choosePlayerType();

    LevelDifficulty chooseDifficulty();

    ItemType chooseItem(int itemNumber);
}
