package game.entities.Player;

public class Wizard extends Player {
    public Wizard() {
        super("Wizard", 200, 50, 10, 20, "Arcane Blast", "Deal basic attack to all enemies. For each enemy defeated, +10 attack until end of the level.");
    }

    public Wizard(
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

    public Wizard createCopy() {
        return new Wizard(
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
