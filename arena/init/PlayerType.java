package arena.init;

import arena.combatant.Player;
import arena.combatant.Warrior;
import arena.combatant.Wizard;

//storing all player type info, just a helper class

public enum PlayerType {
    WARRIOR {
        public Player createPlayer() {
            return new Warrior();
        }
    },
    WIZARD {
        public Player createPlayer() {
            return new Wizard();
        }
    };

    public abstract Player createPlayer();

    public String getDisplayInfo() {
        Player player = createPlayer();
        return player.getName()
                + "  HP:" + player.getMaxHp()
                + " ATK:" + player.getAttack()
                + " DEF:" + player.getDefense()
                + " SPD:" + player.getSpeed()
                + " | Skill: " + player.getSpecialSkillName()
                + " - " + player.getSpecialSkillDescription();
    }
}