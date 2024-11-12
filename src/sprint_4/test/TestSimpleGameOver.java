import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSimpleGameOver {
    private Board board;

    @Before
    public void setUp() throws Exception{
        board = new SimpleGame();
        board.setGameMode(Board.gameMode.SIMPLE);
        int boardSize = 3;
        board.updateGrid(boardSize);
    }
    @Test // AC 5.1 CHATGPT
    public void testSimpleWinRed() {
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(0, 0);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(1, 0);
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(2, 2);

        // Red's winning move to form SOS at (2, 0)
        board.setRedMoveChoice(Board.Cell.S);
        board.makeMove(2, 0);

        assertEquals("Red should win immediately", Board.gameState.RED_WON, board.getCurrentGameState());
    }


    @Test // AC 5.2 CHATGPT
    public void testSimpleWinBlue() {
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(0, 0);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(1, 0);

        // This move by Blue should complete SOS and win immediately
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(2, 0);

        assertEquals("Blue should win immediately", Board.gameState.BLUE_WON, board.getCurrentGameState());
    }

//    @Test //5.1
//    public void testSimpleWinBlue(){
//        board.setBlueMoveChoice(Board.Cell.S);
//        board.makeMove(0, 0);
//        board.setRedMoveChoice(Board.Cell.O);
//        board.makeMove(1, 0);
//        board.setBlueMoveChoice(Board.Cell.S);
//        board.makeMove(2, 0);
//
//        assertEquals("Blue should win", Board.gameState.BLUE_WON, board.getCurrentGameState());
//    }
//
//    @Test //5.2
//    public void testSimpleWinRed(){
//        board.setBlueMoveChoice(Board.Cell.O);
//        board.makeMove(1, 1);
//        board.setRedMoveChoice(Board.Cell.S);
//        board.makeMove(2, 1);
//        board.setBlueMoveChoice(Board.Cell.S);
//        board.makeMove(2, 2);
//        board.setRedMoveChoice(Board.Cell.S);
//        board.makeMove(0, 1);
//
//        assertEquals("Red should win", Board.gameState.RED_WON, board.getCurrentGameState());
//    }

    @Test //5.3
    public void testSimpleDraw(){
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