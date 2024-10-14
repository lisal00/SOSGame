import org.junit.Before;
import org.junit.Test;
import product.Board;

import static org.junit.Assert.*;

public class TestBoardSize {
    private Board board;

    @Before
    public void setUp() throws Exception{
        board = new Board();
    }

    @Test //AC 1.1
    public void chooseBoardSize(){
        int boardSize = 5;
        board.updateGrid(boardSize);
        assertEquals(5, board.getGridSize());
    }

     @Test //AC 1.2
     public void outsideBoardRange(){
        int boardSize = 2;
        board.updateGrid(boardSize);
        assertNotEquals(boardSize,board.getGridSize());
     }
}