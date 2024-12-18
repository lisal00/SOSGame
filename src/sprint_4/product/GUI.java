
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

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

    private void setContentPane() {
        board = new SimpleComputerPlayer();

        topPanel = new TopPanel(board);
        leftPanel = new LeftPanel(board);
        rightPanel = new RightPanel(board);
        bottomPanel = new BottomPanel(board);
        centerPanel = new CenterPanel(board, this);
        Container contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(leftPanel, BorderLayout.WEST);
        contentPane.add(rightPanel, BorderLayout.EAST);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

//    public void setBoard(Board newBoard) {
//        board = newBoard;
//    }

    public void setGameModePanels(Board newBoard) {
        // Set the board to the new game
        this.board = newBoard;

        leftPanel.setBoard(newBoard);
        rightPanel.setBoard(newBoard);
        bottomPanel.setBoard(newBoard);
        topPanel.setBoard(newBoard);
        centerPanel.setBoard(newBoard);

        revalidate();
        repaint();
    }
    public void computerMoves(){
        if(board.getCurrentGameState() == Board.gameState.PLAYING) {
            if ((board.getTurn() == 'B' && board.getBluePlayerMode() == Board.playerMode.COMPUTER)
                    || (board.getTurn() == 'R' && board.getRedPlayerMode() == Board.playerMode.COMPUTER)) {
                board.makeComputerMove();
                centerPanel.setBoard(board);
                BottomPanel.setTurnText();
                centerPanel.repaint();
                //paintComputerMoves();
            }
        }
    }

    public void paintComputerMoves(){
            try {
                Thread.sleep(250);
                SwingUtilities.invokeAndWait(() ->
                {
                    centerPanel.repaint();
                    //computerMoves();
                });
            }catch(InterruptedException | InvocationTargetException e){
                e.printStackTrace(System.out);
            }
    }

    class CenterPanel extends JPanel{
        private Board board;
        private GUI gui;
        private GameBoard gameBoard;

        CenterPanel(Board board, GUI gui){
            this.board = board;
            this.gui = gui;
            this.gameBoard = new GameBoard(board, gui);
            add(gameBoard, BorderLayout.CENTER);
        }
        public void setBoard(Board newBoard) {
            this.board = newBoard;
            gameBoard.setBoard(newBoard);
            revalidate();
            repaint();
        }
    }

    class TopPanel extends JPanel{
        private JRadioButton simpleGButton;
        private JRadioButton generalGButton;
        private JLabel SOSLabel;
        private JLabel textBoard;
        private JSpinner boardSize;
        private ButtonGroup gameModeGroup;
        private Board board;

        TopPanel(Board board){
            this.board = board;
            SpinnerModel model = new SpinnerNumberModel(5, 3, 15, 1);
            boardSize = new JSpinner(model);

            boardSize.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSpinner boardSize = (JSpinner) e.getSource();
                    int value = (int) boardSize.getValue();
                    topPanel.board.updateGrid(value);

                    //repaint();
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
//                board.setGameMode(Board.gameMode.SIMPLE);
                board = new SimpleComputerPlayer();
                setGameModePanels(board);
                //System.out.println(board.getGameMode());
            }
        }
        private class generalGameListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
//                board.setGameMode(Board.gameMode.GENERAL);
                board = new GeneralComputerPlayer();
                setGameModePanels(board);
            }
        }
        public void setBoard(Board newBoard){
            this.board = newBoard;
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
            blueHumanButton.addActionListener(new LeftPanel.blueHumanButtonListener());
            blueCompButton.addActionListener(new LeftPanel.blueComputerButtonListener());
        }
        private class blueHumanButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setBluePlayerMode(Board.playerMode.HUMAN);

            }
        }
        private class blueComputerButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setBluePlayerMode(Board.playerMode.COMPUTER);
                G3.clearSelection();
//                board = new GeneralComputerPlayer();
                setGameModePanels(board);
            }
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
        private void setBoard(Board newBoard) {
            this.board = newBoard;
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

            newGameButton.addActionListener(new newGameButtonListener());
            redSButton.addActionListener(new SButtonListener());
            redOButton.addActionListener(new OButtonListener());
            redHumanButton.addActionListener(new redHumanButtonListener());
            redCompButton.addActionListener(new redComputerButtonListener());

        }
        private class newGameButtonListener implements  ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.resetGame();
                leftPanel.G2.clearSelection();
                leftPanel.G3.clearSelection();
                G4.clearSelection();
                G5.clearSelection();
                topPanel.gameModeGroup.clearSelection();
                //board.updateGrid(5);
                //TopPanel.boardSize.setValue(5);
                BottomPanel.currentTurnText.setText("Current Turn: Blue");
            }
        }
        private class redHumanButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setRedPlayerMode(Board.playerMode.HUMAN);
                board.setCurrentGameState(Board.gameState.PLAYING);
            }
        }
        private class redComputerButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                board.setRedPlayerMode(Board.playerMode.COMPUTER);
                G5.clearSelection();
                //board = new GeneralComputerPlayer();
                setGameModePanels(board);
                board.setCurrentGameState(Board.gameState.PLAYING);
                computerMoves();
            }
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
        private void setBoard(Board newBoard) {
            board = newBoard;
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
        }

        public static void setTurnText(){
            if (board.getTurn() == 'B') {
                currentTurnText.setText("Current Turn: Blue");
            } else if (board.getTurn() == 'R')
                currentTurnText.setText("Current Turn: Red");

            if (board.getCurrentGameState() == Board.gameState.DRAW){
                currentTurnText.setText("The game is a draw.");
            } else if (board.getCurrentGameState() == Board.gameState.BLUE_WON){
                currentTurnText.setText("Blue has won.");
            }else if (board.getCurrentGameState() == Board.gameState.RED_WON){
                currentTurnText.setText("Red has won.");
            }
        }
        public void setBoard(Board newBoard) {
            board = newBoard;
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