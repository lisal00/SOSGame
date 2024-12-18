import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameBoard extends JPanel {
    public static final int GRID_WIDTH = 5;
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;

    public static final int SYMBOL_STROKE_WIDTH = 8;

    public int CANVAS_WIDTH = 600;
    public int CANVAS_HEIGHT = 600;

    //private GameBoardCanvas gameBoardCanvas;
    private Board board;
    private GUI gui;
    //private int[] storedCombos;

    private int CELL_SIZE;
    private int CELL_PADDING;
    private int SYMBOL_SIZE;

    GameBoard(Board board, GUI gui) {
        this.board = board;
        this.gui = gui;

        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                handleClick(e);
            }
        });
    }

    public void handleClick(MouseEvent e) {
        if (this.board.getCurrentGameState() == Board.gameState.PLAYING) {
            int rowSelected = e.getY() / CELL_SIZE;
            int colSelected = e.getX() / CELL_SIZE;
            this.board.makeMove(rowSelected, colSelected);

            GUI.BottomPanel.setTurnText();
        }
    }

    public void setBoard(Board newBoard) {
        this.board = newBoard;
        repaint();
        revalidate();
        //removeAll();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        drawGridLines(g);
        drawMoves(g);
        gui.computerMoves();
    }

    /**
     * Draws the game board grid
     **/
    private void drawGridLines(Graphics g) {
        g.setColor(Color.BLACK);
        CELL_SIZE = CANVAS_HEIGHT / board.getGridSize();
        CELL_PADDING = CELL_SIZE / board.getGridSize();

        for (int row = 1; row < board.getGridSize(); row++) {
            g.fillRect(0, (CELL_SIZE * row) - GRID_WIDTH_HALF, CANVAS_WIDTH - 1, GRID_WIDTH);
        }
        for (int col = 1; col < board.getGridSize(); col++) {
            g.fillRect(CELL_SIZE * col - GRID_WIDTH_HALF, 0, GRID_WIDTH, CANVAS_HEIGHT - 1);
        }
        setSize(new Dimension(CELL_SIZE * board.getGridSize(), CELL_SIZE * board.getGridSize()));
        repaint();
    }

    /**
     * Draws either an S or an O
     **/
    private void drawMoves(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        CELL_SIZE = CANVAS_HEIGHT / board.getGridSize();
        CELL_PADDING = CELL_SIZE / 6;
        SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
        g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setFont(new Font("Arial", Font.BOLD, SYMBOL_SIZE));


        for (int row = 0; row < board.getGridSize(); row++) {
            for (int col = 0; col < board.getGridSize(); col++) {
                int x1 = col * CELL_SIZE + CELL_PADDING;
                int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;

                if (board.getCell(row, col) == Board.Cell.O) {
                    g.drawString("O", x1, y2);
                } else if (board.getCell(row, col) == Board.Cell.S) {
                    g.drawString("S", x1, y2);
                }
            }
        }
        for (int row = 0; row < board.getGridSize(); row++) {
            for (int col = 0; col < board.getGridSize(); col++) {
                if (board.getCell(row, col) == Board.Cell.O) {
                    drawLineO(g2d, row, col);
                } else if (board.getCell(row, col) == Board.Cell.S) {
                    drawLineS(g2d,row,col);
                }
            }
        }
    }
    //    private void drawCombos(Graphics g2d, int row, int col){
//        for (int i = -1; i < 2; i++) {
//            for (int y = -1; y < 2; y++) {
//                try {
//                    if (board.getCell(row, col) == Board.Cell.O) {
//                        if (board.grid[row + i][col + y] == Board.Cell.S && board.grid[row + (i * -1)][col + (y * -1)] == Board.Cell.S) {
//                            int x1 = (col * CELL_SIZE + (CELL_SIZE / 2));
//                            int y1 = (row * CELL_SIZE + (CELL_SIZE / 2));
//                            int x2 = ((col + -1 * y) * CELL_SIZE + (CELL_SIZE / 2));
//                            int y2 = ((row + -1 * i) * CELL_SIZE + (CELL_SIZE / 2));
//                            if(board.getTurnBoard(row, col) == 'B') {
//                                g2d.setColor(Color.BLUE);
//                            }
//                            else{
//                                g2d.setColor(Color.RED);
//                            }
//                            //g2d.setColor(currentPlayerColor);
//                            g2d.drawLine(x1, y1, x2, y2);
//                            g2d.setColor(Color.BLACK);
//                        }
//                    }
//                    else if (board.getCell(row, col) == Board.Cell.S) {
//                        if (board.grid[row + i][col + y] == Board.Cell.O && board.grid[row + i * 2][col + y * 2] == Board.Cell.S) {
//                            int x1 = (col * CELL_SIZE + (CELL_SIZE / 2));
//                            int y1 = (row * CELL_SIZE + (CELL_SIZE / 2));
//                            int x2 = ((col + 2 * y) * CELL_SIZE + (CELL_SIZE / 2));
//                            int y2 = ((row + 2 * i) * CELL_SIZE + (CELL_SIZE / 2));
//                            //System.out.println("test: " + row + " " + col);
//                            if(board.getTurnBoard(row, col) == 'B') {
//                                g2d.setColor(Color.RED);
//                                //System.out.println("BLue test");
//                            }
//                            else if (board.getTurnBoard(row, col) == 'R'){
//                                g2d.setColor(Color.BLUE);
//                                //System.out.println("red test" + row + " " + col);
//
//                            }
//                            g2d.drawLine(x1, y1, x2, y2);
//                            g2d.setColor(Color.BLACK);
//                        }
//                    }
//                }
//                catch (ArrayIndexOutOfBoundsException e) {
//                }
//            }
//        }
//    }
    private void drawLineO(Graphics g2d, int row, int col) {
        for (int i = -1; i < 2; i++) {
            for (int y = -1; y < 2; y++) {
                try {
                    if (board.grid[row + i][col + y] == Board.Cell.S && board.grid[row + (i * -1)][col + (y * -1)] == Board.Cell.S) {
                        int x1 = (col * CELL_SIZE + (CELL_SIZE / 2));
                        int y1 = (row * CELL_SIZE + (CELL_SIZE / 2));
                        int x2 = ((col + -1 * y) * CELL_SIZE + (CELL_SIZE / 2));
                        int y2 = ((row + -1 * i) * CELL_SIZE + (CELL_SIZE / 2));
                        if(board.getTurnBoard(row, col) == 'B') {
                            g2d.setColor(Color.BLUE);
                        }
                        else{
                            g2d.setColor(Color.RED);
                        }
                        //g2d.setColor(currentPlayerColor);
                        g2d.drawLine(x1, y1, x2, y2);
                        g2d.setColor(Color.BLACK);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
    }

    private void drawLineS(Graphics g2d, int row, int col) {
        for (int i = -1; i < 2; i++) {
            for (int y = -1; y < 2; y++) {
                try {
                    if (board.grid[row + i][col + y] == Board.Cell.O && board.grid[row + i * 2][col + y * 2] == Board.Cell.S) {
                        int x1 = (col * CELL_SIZE + (CELL_SIZE / 2));
                        int y1 = (row * CELL_SIZE + (CELL_SIZE / 2));
                        int x2 = ((col + 2 * y) * CELL_SIZE + (CELL_SIZE / 2));
                        int y2 = ((row + 2 * i) * CELL_SIZE + (CELL_SIZE / 2));
                        //System.out.println("test: " + row + " " + col);
                        if(board.getTurnBoard(row, col) == 'B') {
                            g2d.setColor(Color.BLUE);
                            //System.out.println("BLue test");
                        }
                        else if (board.getTurnBoard(row, col) == 'R'){
                            g2d.setColor(Color.RED);
                            //System.out.println("red test" + row + " " + col);

                        }
                        g2d.drawLine(x1, y1, x2, y2);
                        g2d.setColor(Color.BLACK);

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
    }
}