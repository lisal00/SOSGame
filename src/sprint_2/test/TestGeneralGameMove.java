import org.junit.Before;
import org.junit.Test;
import product.Board;

import static org.junit.Assert.*;

public class TestGeneralGameMove {
    private Board board;
    int row = 4;
    int col = 1;

    @Before
    public void setUp() throws Exception{
        board = new Board();
        board.setGameMode(Board.gameMode.GENERAL);
    }

    @Test //AC 6.1
    public void testMakingSMoveGeneral(){
        board.setBlueMoveChoice(Board.Cell.S);
        board.makeMove(row, col);
        assertEquals(Board.Cell.S, board.getCell(row,col));
        assertEquals('R', board.getTurn());
    }

    @Test //AC 6.2
    public void testMakingOMoveGeneral(){
        board.setBlueMoveChoice(Board.Cell.O);
        board.makeMove(row, col);
        assertEquals(Board.Cell.O, board.getCell(row,col));
        assertEquals('R', board.getTurn());
    }

    @Test //AC 6.3
    public void testGeneralOccupiedCell() {
        board.setBlueMoveChoice(Board.Cell.S);
        board.setRedMoveChoice(Board.Cell.O);

        board.makeMove(row,col);
        board.makeMove(row,col);
        assertEquals(Board.Cell.S, board.getCell(row,col));
        assertEquals('R', board.getTurn());
    }
}