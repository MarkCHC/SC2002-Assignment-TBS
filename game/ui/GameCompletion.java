package game.ui;

public class GameCompletion {

    public static void playerWin() {
        System.out.println("\n==================================================================");
        System.out.println("||                                                              ||");
        System.out.println("||                    V I C T O R Y !                           ||");
        System.out.println("||             All enemy waves have been cleared.               ||");
        System.out.println("||                                                              ||");
        System.out.println("==================================================================\n");
    }

    public static void playerLose() {
        System.out.println("\n==================================================================");
        System.out.println("||                                                              ||");
        System.out.println("||                     D E F E A T .                            ||");
        System.out.println("||              Your Character has been defeated.                  ||");
        System.out.println("||                                                              ||");
        System.out.println("==================================================================\n");
    }
}