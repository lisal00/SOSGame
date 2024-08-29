import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    JRadioButton simpleGButton;
    JRadioButton generalGButton;
    JPanel topPart;
    JLabel SOSLabel;
    JLabel textBoard;
    JTextField boardSizeLabel;

    JPanel leftPanel;
    JLabel bluePlayerLabel;
    JRadioButton blueHumanButton;
    JRadioButton blueSButton;
    JRadioButton blueOButton;
    JRadioButton blueCompButton;
    JCheckBox recordGameBox;

    JPanel rightPanel;
    JLabel redPlayerLabel;
    JRadioButton redHumanButton;
    JRadioButton redSButton;
    JRadioButton redOButton;
    JRadioButton redCompButton;
    JButton replayButton;
    JButton newGameButton;

    public class GameBoard{
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

    public Main() {
        setTitle("SOS Game");
        setSize(900,600);
        topPanel();
        leftSide();
        rightSide();
        GameBoard();
        setVisible(true);
    }

    public void topPanel(){
        textBoard = new JLabel("Board Size: ");
        boardSizeLabel = new JTextField(1);
        SOSLabel = new JLabel("SOS");
        topPart = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        simpleGButton = new JRadioButton("Simple Game");
        generalGButton = new JRadioButton("General Game");

        //topPart.setSize(900,100);
        //topPart.setLayout(new BorderLayout(3, 4));
        //topPart.setBounds(1, 30, 50, 30);
        topPart.setBackground(Color.pink);

        //BorderLayout bLayout = new BorderLayout(3, 4);
        //simpleGButton.setBounds(5,5,20,20);
        //simpleGButton.setText("Simple Game");
        //generalGButton.setText("General Game");


        topPart.add(SOSLabel, BorderLayout.WEST);
        topPart.add(simpleGButton, BorderLayout.WEST);
        topPart.add(generalGButton, BorderLayout.WEST);
        topPart.add(textBoard, BorderLayout.EAST);
        topPart.add(boardSizeLabel, BorderLayout.EAST);
        add(topPart, BorderLayout.NORTH);
    }

    public void leftSide(){
        leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        bluePlayerLabel = new JLabel("Blue Player");
        gbc.gridx = 1;
        gbc.gridy = 0;
        leftPanel.add(bluePlayerLabel, gbc);

        blueHumanButton = new JRadioButton("Human");
        gbc.gridx = 1;
        gbc.gridy = 1;
        leftPanel.add(blueHumanButton, gbc);

        blueSButton = new JRadioButton("S");
        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(blueSButton, gbc);

        blueOButton = new JRadioButton("O");
        gbc.gridx = 1;
        gbc.gridy = 3;
        leftPanel.add(blueOButton, gbc);

        blueCompButton = new JRadioButton("Computer");
        gbc.gridx = 1;
        gbc.gridy = 4;
        leftPanel.add(blueCompButton, gbc);

        recordGameBox = new JCheckBox("Record Game");
        gbc.gridx = 1;
        gbc.gridy = 6;
        leftPanel.add(recordGameBox, gbc);

        add(leftPanel,BorderLayout.WEST);
    }

    public void rightSide(){
        rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        redPlayerLabel = new JLabel("Red Player");
        gbc.gridx = 1;
        gbc.gridy = 0;
        rightPanel.add(redPlayerLabel, gbc);

        redHumanButton = new JRadioButton("Human");
        gbc.gridx = 1;
        gbc.gridy = 1;
        rightPanel.add(redHumanButton, gbc);

        redSButton = new JRadioButton("S");
        gbc.gridx = 2;
        gbc.gridy = 2;
        rightPanel.add(redSButton, gbc);

        redOButton = new JRadioButton("O");
        gbc.gridx = 2;
        gbc.gridy = 3;
        rightPanel.add(redOButton, gbc);

        redCompButton = new JRadioButton("Computer");
        gbc.gridx = 1;
        gbc.gridy = 4;
        rightPanel.add(redCompButton, gbc);

        replayButton = new JButton("Replay");
        gbc.gridx = 0;
        gbc.gridy = 5;
        rightPanel.add(replayButton, gbc);

        newGameButton = new JButton("New Game");
        gbc.gridx = 0;
        gbc.gridy = 7;
        rightPanel.add(newGameButton, gbc);

        add(rightPanel, BorderLayout.EAST);
    }

    public static void main(String[] args){
        new Main();
        new GameBoard();
    }
}