package game.logic.Generator;
import game.entities.Player.Player;
import game.entities.Player.Warrior;
import game.entities.Player.Wizard;

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
                + " | Skill: " + player.getSpecialSkill().getSpecialSkillName()
                + " - " + player.getSpecialSkill().getSpecialSkillDescription();
    }
}
