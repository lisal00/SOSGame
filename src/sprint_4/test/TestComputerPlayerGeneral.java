import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestComputerPlayerGeneral{
    private Board board;

    @Before
    public void setUp() throws Exception{
        board = new GeneralComputerPlayer();
        board.setGameMode(Board.gameMode.GENERAL);
        int boardSize = 4;
        board.updateGrid(boardSize);
        board.setCurrentGameState(Board.gameState.PLAYING);
    }


    @Test //ac 9.1
    public void testComputerSOSGeneral(){
        board.setBluePlayerMode(Board.playerMode.HUMAN);
        board.setRedPlayerMode(Board.playerMode.COMPUTER);

        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(1, 1);

        board.makeComputerMove();

        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(3,1);

        board.makeComputerMove();

        assertEquals("Red has 1 sos", 1, board.redSOS);
    }

    @Test //ac 9.2
    public void testComputerSMoveGeneral(){
        board.setBluePlayerMode(Board.playerMode.COMPUTER);
        assertTrue("S Move not made", board.makeSMove(1,1));
    }

    @Test //ac 9.3
    public void testComputerOMoveGeneral(){
        board.setBluePlayerMode(Board.playerMode.COMPUTER);
        assertTrue("O Move not made", board.makeOMove(1,2));
    }
}