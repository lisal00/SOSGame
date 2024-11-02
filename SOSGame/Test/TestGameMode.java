import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameMode {
    private Board board;

    @Before
    public void setUp() throws Exception{
        board = new SimpleGame();
        board = new GeneralGame();
    }

    @Test //AC 2.1
    public void testSimpleGameChoice(){
        board.setGameMode(Board.gameMode.SIMPLE);
        assertEquals(Board.gameMode.SIMPLE, board.getGameMode());
    }

    @Test //AC 2.2
    public void testGeneralGameChoice(){
        board.setGameMode(Board.gameMode.GENERAL);
        assertEquals(Board.gameMode.GENERAL, board.getGameMode());
    }
}