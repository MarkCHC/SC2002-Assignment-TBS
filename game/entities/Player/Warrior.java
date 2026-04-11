package game.entities.Player;

import game.logic.EnemyBehavior;

public class Warrior extends Player {
    public Warrior() {
        super(
            "Warrior", 
            260, 
            40, 
            20, 
            30, 
            "Shield Bash", 
            "Deal basic attack to one enemy and stun it for the current turn and the next turn."
        );
    }

    public Warrior(
        String name, 
        int maxHp, 
        int currentHp, 
        int attack, 
        int defense, 
        int speed, 
        String specialSkillName, 
        String specialSkillNameDescription
    ) 
    {
        super(name, maxHp, currentHp, attack, defense, speed, specialSkillName, specialSkillNameDescription);
    }

    public Warrior createCopy() {
        return new Warrior(
            this.getName(), 
            this.getMaxHp(), 
            this.getHp(), 
            this.getAttack(), 
            this.getDefense(), 
            this.getSpeed(), 
            this.getSpecialSkillName(),
            this.getSpecialSkillDescription()
        );
    }
}
