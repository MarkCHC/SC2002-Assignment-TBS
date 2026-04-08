package arena.init;

import arena.level.LevelDifficulty;
import java.util.List;

// to store init info, a helper class

public class GameSetup {
    private final PlayerType playerType;
    private final LevelDifficulty difficulty;
    private final List<ItemType> chosenItems;

    public GameSetup(PlayerType playerType, LevelDifficulty difficulty, List<ItemType> chosenItems) {
        this.playerType = playerType;
        this.difficulty = difficulty;
        this.chosenItems = chosenItems;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public LevelDifficulty getDifficulty() {
        return difficulty;
    }

    public List<ItemType> getChosenItems() {
        return chosenItems;
    }
}
