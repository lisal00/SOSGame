package product;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private CenterPanel centerPanel;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;

    private Board board;
    private GameBoard gameBoard;
    private JPanel panel;

    public GUI() {
        setContentPane();
        setTitle("SOS Game");
        setSize(1200, 800);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setContentPane(){
        board = new Board();
        topPanel = new TopPanel();
        leftPanel = new LeftPanel(board);
        rightPanel = new RightPanel(board);
        bottomPanel = new BottomPanel(board);
        centerPanel = new CenterPanel(board);
        Container contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout());

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(leftPanel, BorderLayout.WEST);
        contentPane.add(rightPanel, BorderLayout.EAST);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    class CenterPanel extends JPanel{
        private Board board;
        private GameBoard gameBoard;

        CenterPanel(Board board){
            gameBoard = new GameBoard(board);

            add(gameBoard, BorderLayout.CENTER);
            //add(centerPanel, BorderLayout.CENTER);
        }
    }

    class TopPanel extends JPanel{
        private JRadioButton simpleGButton;
        private JRadioButton generalGButton;
        private JLabel SOSLabel;
        private JLabel textBoard;
        private JSpinner boardSize;
        private ButtonGroup gameModeGroup;

        TopPanel(){
            SpinnerModel model = new SpinnerNumberModel(5, 3, 15, 1);
            boardSize = new JSpinner(model);

            boardSize.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSpinner boardSize = (JSpinner) e.getSource();
                    int value = (int) boardSize.getValue();
                    board.updateGrid(value);

                    repaint();
                }
            });

            textBoard = new JLabel("Board Size: ");
            SOSLabel = new JLabel("SOS");
            //topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
            simpleGButton = new JRadioButton("Simple Game");
            generalGButton = new JRadioButton("General Game");
            gameModeGroup = new ButtonGroup();

            //setBackground(Color.pink);
            add(SOSLabel);
            add(simpleGButton);
            add(generalGButton);
            add(textBoard);
            add(boardSize);
            //add(topPanel, BorderLayout.NORTH);
            gameModeGroup.add(simpleGButton);
            gameModeGroup.add(generalGButton);

            simpleGButton.addActionListener(new TopPanel.simpleGameListener());
            generalGButton.addActionListener(new TopPanel.generalGameListener());
        }
        private class simpleGameListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setGameMode(Board.gameMode.SIMPLE);
            }
        }
        private class generalGameListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setGameMode(Board.gameMode.GENERAL);
            }
        }

    }

    class LeftPanel extends JPanel{
        //private JPanel leftPanel;
        private JLabel bluePlayerLabel;
        private JRadioButton blueHumanButton;
        private JRadioButton blueSButton;
        private JRadioButton blueOButton;
        private JRadioButton blueCompButton;
        private JCheckBox recordGameBox;
        private ButtonGroup G2;
        private ButtonGroup G3;
        private Board board;

        LeftPanel(Board board){
            this.board = board;

            setLayout(new GridBagLayout());
            G2 = new ButtonGroup();
            G3 = new ButtonGroup();
            GridBagConstraints gbc = new GridBagConstraints();

            bluePlayerLabel = new JLabel("Blue Player");
            gbc.gridx = 1;
            gbc.gridy = 0;
            add(bluePlayerLabel, gbc);

            blueHumanButton = new JRadioButton("Human");
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(blueHumanButton, gbc);

            blueSButton = new JRadioButton("S");
            gbc.gridx = 1;
            gbc.gridy = 2;
            add(blueSButton, gbc);

            blueOButton = new JRadioButton("O");
            gbc.gridx = 1;
            gbc.gridy = 3;
            add(blueOButton, gbc);

            blueCompButton = new JRadioButton("Computer");
            gbc.gridx = 1;
            gbc.gridy = 4;
            add(blueCompButton, gbc);

            recordGameBox = new JCheckBox("Record Game");
            gbc.gridx = 1;
            gbc.gridy = 6;
            add(recordGameBox, gbc);

            //add(leftPanel, BorderLayout.WEST);
            G2.add(blueHumanButton);
            G2.add(blueCompButton);
            G3.add(blueSButton);
            G3.add(blueOButton);

            blueSButton.addActionListener(new LeftPanel.SButtonListener());
            blueOButton.addActionListener(new LeftPanel.OButtonListener());
        }

        private class SButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setBlueMoveChoice(Board.Cell.S);
            }
        }
        private class OButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setBlueMoveChoice(Board.Cell.O);
            }
        }
    }


    class RightPanel extends JPanel{
        //private JPanel rightPanel;
        private JLabel redPlayerLabel;
        private JRadioButton redHumanButton;
        private JRadioButton redSButton;
        private JRadioButton redOButton;
        private JRadioButton redCompButton;
        private JButton replayButton;
        private JButton newGameButton;
        private ButtonGroup G4;
        private ButtonGroup G5;
        private Board board;

        RightPanel(Board board){
            this.board = board;
            setLayout(new GridBagLayout());

            //rightPanel = new JPanel(new GridBagLayout());
            G4 = new ButtonGroup();
            G5 = new ButtonGroup();
            GridBagConstraints gbc = new GridBagConstraints();

            redPlayerLabel = new JLabel("Red Player");
            gbc.gridx = 1;
            gbc.gridy = 0;
            add(redPlayerLabel, gbc);

            redHumanButton = new JRadioButton("Human");
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(redHumanButton, gbc);

            redSButton = new JRadioButton("S");
            gbc.gridx = 2;
            gbc.gridy = 2;
            add(redSButton, gbc);

            redOButton = new JRadioButton("O");
            gbc.gridx = 2;
            gbc.gridy = 3;
            add(redOButton, gbc);

            redCompButton = new JRadioButton("Computer");
            gbc.gridx = 1;
            gbc.gridy = 4;
            add(redCompButton, gbc);

            replayButton = new JButton("Replay");
            gbc.gridx = 0;
            gbc.gridy = 5;
            add(replayButton, gbc);

            newGameButton = new JButton("New Game");
            gbc.gridx = 0;
            gbc.gridy = 7;
            add(newGameButton, gbc);

            //add(rightPanel, BorderLayout.EAST);
            G4.add(redHumanButton);
            G4.add(redCompButton);
            G5.add(redSButton);
            G5.add(redOButton);

            redSButton.addActionListener(new SButtonListener());
            redOButton.addActionListener(new OButtonListener());
        }

        private class SButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setRedMoveChoice(Board.Cell.S);
            }
        }

        private class OButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setRedMoveChoice(Board.Cell.O);
            }
        }
    }

    class BottomPanel extends JPanel{
        private static JLabel currentTurnText;
        private static Board board;

        BottomPanel(Board board){
            this.board = board;

            //bottomPanel = new JPanel();
            currentTurnText = new JLabel("Current Turn: Blue");

            add(currentTurnText, BorderLayout.CENTER);
            //add(bottomPanel, BorderLayout.SOUTH);

        }

        public static void setTurnText(){
            if (board.getTurn() == 'B') {
                currentTurnText.setText("Current Turn: Blue");
            } else
                currentTurnText.setText("Current Turn: Red");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}