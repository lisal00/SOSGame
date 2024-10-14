import org.junit.Before;
import org.junit.Test;
import product.Board;

import static org.junit.Assert.*;

public class TestSimpleGameMove {
    private Board board;

    @Before
    public void setUp() throws Exception{
        board = new Board();
        board.setGameMode(Board.gameMode.SIMPLE); // Set to SIMPLE game mode
    }

    @Test //AC 4.1 CHATGPT
    public void testValidSMove(){
        assertEquals('B', board.getTurn()); // Ensure it's blue's turn
        board.setBlueMoveChoice(Board.Cell.S); // Set blue player to move 'S'
        board.makeMove(0, 0); // Blue makes a move at (0, 0)

        assertEquals(Board.Cell.S, board.getCell(0, 0)); // Check that 'S' is placed
        assertEquals('R', board.getTurn()); // Check that the turn has changed to red
    }

    @Test //AC 4.2 CHATGPT
    public void testValidOMove(){
        assertEquals('B', board.getTurn()); // Ensure it's blue's turn
        board.setBlueMoveChoice(Board.Cell.O); // Change blue player to move 'O'
        board.makeMove(1, 1); // Blue makes a move at (1, 1)

        assertEquals(Board.Cell.O, board.getCell(1, 1)); // Check that 'O' is placed
        assertEquals('R', board.getTurn()); // Check that the turn has changed to red
    }
    
    @Test //AC 4.3
    public void testSimpleOccupiedCell() {
        int row = 2;
        int column = 3;

        board.setBlueMoveChoice(Board.Cell.S);
        board.setRedMoveChoice(Board.Cell.O);
        board.makeMove(row,column);
        board.makeMove(row,column);
        assertEquals(Board.Cell.S, board.getCell(row,column));
        assertEquals('R', board.getTurn());
    }

//    @Test
//    public void testMakingSMoveSimple(){
//        int row = 4;
//        int col = 1;
//        board.setBlueMoveChoice(Board.Cell.S);
//        board.makeMove(row, col);
//        assertEquals("Incorrect move placement", Board.Cell.S, board.getCell(row,col));
//        assertEquals("Turn didn't switch", 'R', board.getTurn());
//    }
//
//    @Test
//    public void testMakingOMoveSimple(){
//        int row = 4;
//        int col = 1;
//        board.setBlueMoveChoice(Board.Cell.O);
//        board.makeMove(row, col);
//        assertEquals("Incorrect move placement", Board.Cell.O, board.getCell(row,col));
//        assertEquals("Turn didn't switch", 'R', board.getTurn());
//    }

}