import java.util.Scanner;
// marcus part. i handle the UI and loading screens, as well as the main game loop. 
// send and receive data from backend engine, this does not handle any game logic, just display and user input.
public class GameUI {

    private Scanner scanner;
    private BattleEngine engine; // connects UI to engine

    // Constructor
    public GameUI(BattleEngine engine) {
        this.engine = engine;
        this.scanner = new Scanner(System.in);
    }

    // ======================
    // LOADING / INIT SECTION
    // ======================

    public void showLoadingScreen() {
        System.out.println("Loading game...");
        try {
            Thread.sleep(1000); // simulate loading
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted");
        }
        System.out.println("Game Loaded!\n");
    }

    public int choosePlayer() {
        System.out.println("Choose your character:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");

        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("You chose Warrior!");
        } else if (choice == 2) {
            System.out.println("You chose Mage!");
        } else {
            System.out.println("Invalid choice, defaulting to Warrior.");
            choice = 1;
        }   
        return choice;
    }

    public int chooseDifficulty() {
        System.out.println("Select difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Hard");

        return scanner.nextInt();
    }

    // ======================
    // GAME LOOP UI
    // ======================

    public void displayGameState() {
        System.out.println("----------------------");
        System.out.println("Round: " + engine.getRound());
        System.out.println("Player HP: " + engine.getPlayerHP());
        System.out.println("----------------------");
    }

    public void displayActions() {
        System.out.println("Choose action:");
        System.out.println("1. Attack");
        System.out.println("2. Use Item");
        System.out.println("3. Defend");
    }

    public int chooseAction() {
        return scanner.nextInt();
    }

    // ======================
    // MAIN GAME FLOW
    // ======================

    public void startGame() {
        showLoadingScreen();

        int playerChoice = choosePlayer();
        int difficulty = chooseDifficulty();

        // Pass choices to engine (stub for now)
        engine.initialize(playerChoice, difficulty);

        // Main loop
        while (!engine.isGameOver()) {
            displayGameState();
            displayActions();

            int action = chooseAction();
            engine.processAction(action);
        }

        System.out.println("Game Over!");
    }

    public static void main(String[] args) {
        BattleEngine engine = new BattleEngine();
        GameUI ui = new GameUI(engine);
        ui.startGame();
    }
}

