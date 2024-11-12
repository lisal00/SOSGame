import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestComputerPlayerSimple {
    private Board board;

    @Before
    public void setUp() throws Exception{
        board = new SimpleComputerPlayer();
        board.setGameMode(Board.gameMode.SIMPLE);
        int boardSize = 4;
        board.updateGrid(boardSize);
        board.setCurrentGameState(Board.gameState.PLAYING);
    }


    @Test //ac 8.1
    public void testComputerSOSSimple(){
        board.setBluePlayerMode(Board.playerMode.HUMAN);
        board.setRedPlayerMode(Board.playerMode.COMPUTER);

        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(1, 1);

        board.makeComputerMove();

        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(3, 3);

        board.makeComputerMove();
        assertEquals("red computer has won", Board.gameState.RED_WON, board.getCurrentGameState());
    }

    @Test //ac 8.2
    public void testComputerSMoveSimple(){
        board.setBluePlayerMode(Board.playerMode.COMPUTER);
        assertTrue("S Move not made", board.makeSMove(1,1));
    }

    @Test //ac 8.3
    public void testComputerOMoveSimple(){
        board.setBluePlayerMode(Board.playerMode.COMPUTER);
        assertTrue("O Move not made", board.makeOMove(1,2));
    }
}