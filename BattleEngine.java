// this is a stub function so i can test my stuff. 
// not to be used in production pls.

public class BattleEngine {

    private int round = 1;
    private int playerHP = 100;
    private boolean gameOver = false;

    public void initialize(int playerChoice, int difficulty) {
        System.out.println("Engine initialized with player " + playerChoice +
                           " and difficulty " + difficulty);
    }

    public void processAction(int action) {
    System.out.println("Processing action: " + action);

    if (action == 1) { // Attack
        playerHP -= 10;
        System.out.println("you attacked the enemy! Enemy HP reduced. you lose HP from counterattack.");
    } else if (action == 2) { // Item
        playerHP += 5;
        System.out.println("you used an item! Your HP increased.");
    } else if (action == 3) { // Defend
        playerHP -= 5;
        System.out.println("you defended against the enemy's attack! Reduced damage taken");
    }

    round++;

    if (playerHP <= 0) {
        gameOver = true;
    }
}
    

    public boolean isGameOver() {
        return gameOver;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public int getRound() {
        return round;
    }
}