package oxgame.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import oxgame.app.exceptions.WrongArgumentException;
import oxgame.app.game.Board;
import oxgame.app.game.Player;
import oxgame.app.game.Symbol;
import oxgame.app.states.GameInProgress;
import oxgame.app.states.GameState;
import oxgame.app.utility.RoundBuffer;

import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * this test class is about testing GameState flow.
 */
public class TestGameState {
    private static GameState gameState;

    @BeforeTest
    private static void initializeTests() throws WrongArgumentException{
        RoundBuffer playerList = new RoundBuffer();
        playerList.addPlayer(new Player("Adam",Symbol.O));
        playerList.addPlayer(new Player("Roman",Symbol.X));

        Consumer<String> output = System.out::println;
        gameState = new GameInProgress(playerList,output,Board.newBoard(3,3));
        gameState.showState();
    }
    @Test
    public static void nextGameStateContainsPlayerWithOtherSymbol() throws WrongArgumentException {
        // Given
        // When
        gameState = gameState.nextState("1");
        Player playerBefore = gameState.showPlayer();
        gameState = gameState.nextState("2");
        Player playerAfter = gameState.showPlayer();
        // Then
        assertNotEquals(playerBefore,playerAfter);
    }
    @Test(expectedExceptions = WrongArgumentException.class)
    public static void whenWrongInputNextStateThrowsIAException() throws WrongArgumentException {
        // Given
        // When
        gameState = gameState.nextState("x");
    }
    @Test
    public static void showPlayerAlwaysReturnsSamePlayerWhenNoSwitch(){
        // Given
        // When
        Player firstPlayer = gameState.showPlayer();
        Player secondPlayer = gameState.showPlayer();
        // Then
        Assert.assertEquals(firstPlayer,secondPlayer);
    }



}
