package xogame.app;

import java.util.Scanner;

public class Setup {
    private Player player;
    private Board board;
    private GameState currentState;
    private UserInput userInput;

    public void initializeAGame() {
        player = new Player("user", Symbol.X);
        currentState = new InitialState(player);
        userInput = new UserInput(new Scanner(System.in)::nextLine);
        applicationLoop();
    }

    private void applicationLoop() {
        while (true) {
            startTurn();
        }
    }

    private void startTurn() {
        this.currentState.showState();
        this.currentState = currentState.nextState(userInput.getData());
    }

}
