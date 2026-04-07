package Items;

import Testing.Enemy;
import Testing.Player;

public class PowerStone implements Item {

    @Override
    public String getName() {
        return "Power Stone";
    }

    @Override
    public String getDescription() {
        return "Gives Player free extra use of Special Skill";
    }

    @Override
    public void use(Enemy target){}
    //here for fulfilling Item interface requirement
    //will be removed once skill is not implementing Item

    @Override
    public void use(Player target) {
        System.out.println("Player used " + getName() + "!");

        int savedCooldown = target.getSpecialSkillCooldown();
        target.setSpecialSkillCooldown(0);
        target.useSpecialSkill();
        target.setSpecialSkillCooldown(savedCooldown);
    }
}


