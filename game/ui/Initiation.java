package game.ui;
import java.util.ArrayList;
import java.util.List;

import game.entities.Player;
import game.entities.Warrior;
import game.entities.Wave;

public class Initiation {
    public static void showLoadingScreen() {} // ascii art
    public static Player chooseClass() {
        Player warrior = new Warrior();
        return warrior;
    } // return Warrior /  Mage class
    public static List<Wave> chooseDifficulty() {
        List<Wave> waves = new ArrayList<>();
        return waves;
    } // user input diff, return list of waves
    public static void chooseItems() {} // choose 2 items
}
