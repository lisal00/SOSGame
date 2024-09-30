import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameBoard extends JPanel {
    public static final int GRID_WIDTH = 5;
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;

    public static final int SYMBOL_STROKE_WIDTH = 8;

    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;

    private GameBoardCanvas gameBoardCanvas;
    private Board board;

    int CELL_SIZE;
    int CELL_PADDING;
    int SYMBOL_SIZE;

    GameBoard(Board board) {
        gameBoardCanvas = new GameBoardCanvas();
        this.board = board;
        setPanel();

        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                int rowSelected = e.getY() / CELL_SIZE ;
                int colSelected = e.getX() / CELL_SIZE - 1;
                System.out.println(board.getTurn());
                board.makeMove(rowSelected, colSelected);

                repaint();
            }
        });
    }

    private void setPanel(){
        gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        gameBoardCanvas.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        add(gameBoardCanvas, BorderLayout.CENTER);
    }

    class GameBoardCanvas extends JPanel {
        GameBoardCanvas(){}

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.LIGHT_GRAY);
            drawGridLines(g);
            drawMoves(g);
        }

        /**
         * Draws the game board grid
         **/
        private void drawGridLines(Graphics g){
            g.setColor(Color.BLACK);
            CELL_SIZE = CANVAS_HEIGHT / board.getGridSize();
            for (int row = 1; row < board.getGridSize(); row++) {
                g.fillRect(0,  (CELL_SIZE * row) - GRID_WIDTH_HALF, CANVAS_WIDTH-1, GRID_WIDTH);
            }
            for (int col = 1; col < board.getGridSize(); col++) {
                g.fillRect(CELL_SIZE * col - GRID_WIDTH_HALF, 0, GRID_WIDTH, CANVAS_HEIGHT-1);
            }
        }

        /**
         * Draws either an S or an O
        **/
        private void drawMoves(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            CELL_SIZE = CANVAS_HEIGHT / board.getGridSize();
            CELL_PADDING = CELL_SIZE / 6;
            SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
            g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            for (int row = 0; row < board.getGridSize(); row++) {
                for (int col = 0; col < board.getGridSize(); col++) {
                    int x1 = col * CELL_SIZE + CELL_PADDING;
                    int y1 = row * CELL_SIZE + CELL_PADDING;

                    if (board.getCell(row,col) == 1) {
                        g2d.setColor(Color.RED);
                        //int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
                        int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
                        g.setFont(new Font("Arial", Font.BOLD, SYMBOL_SIZE));
                        g2d.drawString("S", x1, y2);

                    } else if (board.getCell(row,col) == 2) {
                        g2d.setColor(Color.BLUE);

                        g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                    }
                }
            }
        }
    }
}
