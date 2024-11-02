import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGeneralGameOver {
    private Board board;

    @Before
    public void setUp() throws Exception{
        board = new GeneralGame();
        board.setGameMode(Board.gameMode.GENERAL);
        int boardSize = 3;
        board.updateGrid(boardSize);
    }

    @Test //7.1
    public void testGeneralWinBlue(){
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(0, 0);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(1, 0);
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(2, 0);

        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(1, 1);
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(2, 1);
        board.setRedMoveChoice(Board.Cell.S);
        board.makeMove(0, 1);

        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(0, 2);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(1, 2);
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(2, 2);

        assertEquals("Blue should win", Board.gameState.BLUE_WON, board.getCurrentGameState());
    }

    @Test //7.2
    public void testGeneralWinRed(){
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(0, 0);
        board.setRedMoveChoice(Board.Cell.S);
        board.makeMove(1, 0);
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(2, 0);

        board.setRedMoveChoice(Board.Cell.S);
        board.makeMove(0, 1);
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(1, 1);
        board.setRedMoveChoice(Board.Cell.S);
        board.makeMove(2, 1);

        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(0, 2);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(1, 2);
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(2, 2);

        assertEquals("Red should win", Board.gameState.RED_WON, board.getCurrentGameState());
    }

    @Test //7.3
    public void testGeneralDraw(){
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(0, 0);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(1, 0);
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(2, 0);

        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(0, 1);
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(1, 1);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(2, 1);

        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(0, 2);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(1, 2);
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(2, 2);

        assertEquals("Draw", Board.gameState.DRAW, board.getCurrentGameState());
    }
}