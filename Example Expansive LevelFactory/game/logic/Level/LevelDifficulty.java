package game.logic.Level;

import game.logic.Generator.EnemyType;

public enum LevelDifficulty {
    EASY("Easy", new EnemyType[][] {
        {EnemyType.GOBLIN, EnemyType.GOBLIN, EnemyType.GOBLIN}
    }),
    MEDIUM("Medium", new EnemyType[][] {
        {EnemyType.GOBLIN, EnemyType.WOLF},
        {EnemyType.WOLF, EnemyType.WOLF}
    }),
    HARD("Hard", new EnemyType[][] {
        {EnemyType.GOBLIN, EnemyType.GOBLIN},
        {EnemyType.GOBLIN, EnemyType.WOLF, EnemyType.WOLF}
    });

    private final String displayName;
    private final EnemyType[][] waveLayouts;

    LevelDifficulty(String displayName, EnemyType[][] waveLayouts) {
        this.displayName = displayName;
        this.waveLayouts = copyWaveLayouts(waveLayouts);
    }

    public String getDisplayName() {
        return displayName;
    }

    public EnemyType[][] getWaveLayouts() {
        return copyWaveLayouts(waveLayouts);
    }

    private static EnemyType[][] copyWaveLayouts(EnemyType[][] source) {
        EnemyType[][] copy = new EnemyType[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i].clone();
        }
        return copy;
    }
}
