package game.ui;

public class GameCompletion {

    public static void playerWin() {
        System.out.println("\n==================================================================");
        System.out.println("||                                                              ||");
        System.out.println("||               C O N G R A T U L A T I O N S                  ||");
        System.out.println("||             You have defeated all your enemies.              ||");
        System.out.println("||                                                              ||");
        System.out.println("==================================================================\n");
    }

    public static void playerLose() {
        System.out.println("\n==================================================================");
        System.out.println("||                                                              ||");
        System.out.println("||                     D E F E A T E D.                         ||");
        System.out.println("||                 Don't give up, try again!                    ||");
        System.out.println("||                                                              ||");
        System.out.print("==================================================================\n");
    }

    public static void winStats(int remaining, int survived) {
        System.out.println("||       Remaining HP: "+remaining+"                                       ||");
        System.out.println("||       Total Rounds: "+survived+"                                        ||");
        System.out.println("==================================================================\n");
    }

    public static void loseStats(int remaining, int survived) {
        System.out.println("||       Enemies Remaining: "+remaining+"                                  ||");
        System.out.println("||       Total Rounds: "+survived+"                                        ||");
        System.out.println("==================================================================\n");
    }
}