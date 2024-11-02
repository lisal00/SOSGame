import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameStart {
    private Board board;

    @Before
    public void setUp() throws Exception{
        board = new SimpleGame();
    }

    @Test //AC 3.1
    public void testSuccessfulGameStart(){
        int boardSize = 5;
        board.setGameMode(Board.gameMode.SIMPLE);
        board.updateGrid(boardSize);

        assertEquals(boardSize, board.getGridSize());
        assertEquals(Board.gameMode.SIMPLE, board.getGameMode());
    }

    @Test //AC 3.2
    public void testUnsuccessfulGameStart(){
        int boardSize = 2;
        board.setGameMode(Board.gameMode.SIMPLE);
        board.updateGrid(boardSize);

        assertNotEquals(boardSize,board.getGridSize());
        assertEquals(Board.gameMode.SIMPLE, board.getGameMode());
    }
}