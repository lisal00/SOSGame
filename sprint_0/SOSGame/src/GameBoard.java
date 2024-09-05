import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GameBoard extends JPanel {

    public static final int CELL_SIZE = 100;
    public static final int GRID_WIDTH = 8;
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;

    private int CANVAS_WIDTH;
    private int CANVAS_HEIGHT;

    private GameBoardCanvas gameBoardCanvas;
    private Board board;

    public GameBoard() {
        gameBoardCanvas = new GameBoardCanvas();
        setPanel();
        setBorder(BorderFactory.createLineBorder(Color.BLACK,8));
    }

    private void setPanel(){
        CANVAS_WIDTH = CELL_SIZE * 4;
        CANVAS_HEIGHT = CELL_SIZE * 4;
        gameBoardCanvas.setPreferredSize(new Dimension(500, 500));
        //setLayout(new BorderLayout());
        add(gameBoardCanvas, BorderLayout.CENTER);
    }

    class GameBoardCanvas extends JPanel {

        GameBoardCanvas(){}

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.LIGHT_GRAY);
            drawGridLines(g);
        }

        private void drawGridLines(Graphics g){
            g.setColor(Color.BLACK);
            for (int row = 1; row < 4; row++) {
                g.fillRect(0, CELL_SIZE * row - GRID_WIDTH_HALF,
                        CANVAS_WIDTH-1, GRID_WIDTH);
            }
            for (int col = 1; col < 4; col++) {
                g.fillRect(CELL_SIZE * col - GRID_WIDTH_HALF, 0,
                        GRID_WIDTH, CANVAS_HEIGHT-1);
            }
        }
    }
}
