import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel{
    private static final int CELL_SIZE = 10;
    private int gridWidth;
    private int gridHeight;
    int leftMargin;
    int topMargin;

    public GameBoard(){
        setBackground(Color.WHITE);
        //setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;
//        g2.fillRect(10, 10, 80, 80);

        int width = getWidth();
        int height = getHeight();

        gridWidth = (width/CELL_SIZE) - 1;
        gridHeight= (height/CELL_SIZE) - 1;

        int xSpare = width - (gridWidth * CELL_SIZE);
        int ySpare = height - (gridWidth * CELL_SIZE);

        leftMargin = xSpare / 3;
        topMargin = ySpare / 3;

        g2.setColor(Color.RED);
        g2.fillRect(leftMargin, topMargin, width-xSpare, height - ySpare);
        g2.setColor(Color.BLACK);
        g2.drawLine(40,90,500,90);
//
//        g2.setColor(Color.BLACK);
//        for(int gridy = 0; gridy < gridHeight; gridy++){
//            for(int gridx = 0; gridy < gridWidth; gridx++){
//                int x = gridx * CELL_SIZE + leftMargin;
//                int y = gridy * CELL_SIZE + topMargin;
//
//                g2.fillRect(x, y, CELL_SIZE -1 , CELL_SIZE - 1);
//            }
//        }

    }

}
