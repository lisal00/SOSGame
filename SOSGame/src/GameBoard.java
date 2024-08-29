import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame{
    private static final int CELL_SIZE = 100;
    private int gridWidth;
    private int gridHeight;
    int leftMargin;
    int topMargin;

    public GameBoard(){
        setBackground(Color.WHITE);
    }

    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(10, 10, 100, 100);

        int width = getWidth();
        int height = getHeight();

        gridWidth = (width/CELL_SIZE) - 1;
        gridHeight= (height/CELL_SIZE) - 1;

        int xSpare = width - (gridWidth * CELL_SIZE);
        int ySpare = height - (gridWidth * CELL_SIZE);

        leftMargin = xSpare / 2;
        topMargin = ySpare / 2;

        g2.setColor(Color.WHITE);
        g2.fillRect(leftMargin, topMargin, width-xSpare, height - ySpare);

    }

}
