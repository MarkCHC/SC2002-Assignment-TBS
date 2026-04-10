package game.logic;

import game.entities.Player;
import game.entities.Warrior;
import game.entities.Wizard;

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
