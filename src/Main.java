import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame{
    //components for the top panel
    JRadioButton simpleGButton;
    JRadioButton generalGButton;
    JPanel topPanel;
    JLabel SOSLabel;
    JLabel textBoard;
    JSpinner boardSize;
    ButtonGroup G1;

    //components for the left panel
    JPanel leftPanel;
    JLabel bluePlayerLabel;
    JRadioButton blueHumanButton;
    JRadioButton blueSButton;
    JRadioButton blueOButton;
    JRadioButton blueCompButton;
    JCheckBox recordGameBox;
    ButtonGroup G2;
    ButtonGroup G3;

    //components for the right panel
    JPanel rightPanel;
    JLabel redPlayerLabel;
    JRadioButton redHumanButton;
    JRadioButton redSButton;
    JRadioButton redOButton;
    JRadioButton redCompButton;
    JButton replayButton;
    JButton newGameButton;
    ButtonGroup G4;
    ButtonGroup G5;
    Board board;

    //components for the bottom panel
    JPanel bottomPanel;
    JLabel currentTurnText;

    //grid
    GameBoard boardGrid;

    public Main() {
        setTitle("SOS Game");
        setSize(1200,700);
        board = new Board();
        boardGrid = new GameBoard(board);
        topSide();
        leftSide();
        rightSide();
        bottomSide();



        add(boardGrid);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void center(){

    }
    public void topSide(){
        SpinnerModel model = new SpinnerNumberModel(5, 3, 20, 1);
        boardSize = new JSpinner(model);

        boardSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner boardSize = (JSpinner) e.getSource();
                int value = (int)boardSize.getValue();
                //System.out.println("Value is " + value);
                board.updateGrid(value);
                //System.out.println(board.getGridSize());
                repaint();
            }
        });

        textBoard = new JLabel("Board Size: ");
        SOSLabel = new JLabel("SOS");
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        simpleGButton = new JRadioButton("Simple Game");
        generalGButton = new JRadioButton("General Game");
        G1 = new ButtonGroup();

        //topPanel.setBackground(Color.pink);
        topPanel.add(SOSLabel, BorderLayout.WEST);
        topPanel.add(simpleGButton, BorderLayout.WEST);
        topPanel.add(generalGButton, BorderLayout.WEST);
        topPanel.add(textBoard, BorderLayout.EAST);
        topPanel.add(boardSize, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        G1.add(simpleGButton);
        G1.add(generalGButton);
    }

    public void leftSide(){
        leftPanel = new JPanel(new GridBagLayout());
        G2 = new ButtonGroup();
        G3 = new ButtonGroup();
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
        G2.add(blueHumanButton);
        G2.add(blueCompButton);
        G3.add(blueSButton);
        G3.add(blueOButton);
    }

    public void rightSide(){
        rightPanel = new JPanel(new GridBagLayout());
        G4 = new ButtonGroup();
        G5 = new ButtonGroup();
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
        G4.add(redHumanButton);
        G4.add(redCompButton);
        G5.add(redSButton);
        G5.add(redOButton);
    }

    public void bottomSide(){
        bottomPanel = new JPanel();
        currentTurnText = new JLabel("Current Turn: " );


        if (board.getTurn() == 'B'){
            currentTurnText.setText("Current Turn: Blue");
        }
        else
            currentTurnText.setText("Current Turn: Red");


        bottomPanel.add(currentTurnText, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args){
        new Main();
    }
}
