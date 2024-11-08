//board size will not change if buttons are selected
//general game mode and simple game mode need to decide when to instantiate one or other
package sprint_2.product;

//PARENT CLASS OF GENERALGAME AND SIMPLEGAME, DOES THE OVERALL GAME LOGIC

public abstract class  Board {
    public enum gameMode {SIMPLE, GENERAL}
    public enum Cell {NONE, S, O}
    public enum gameState {PLAYING, DRAW, BLUE_WON, RED_WON};
    public enum playerMode {HUMAN, COMPUTER};

    public Cell[][] grid;
    protected char turn;
    protected int maxVal = 5;
    protected Boolean sosCombo;
    protected gameState currentGameState;
    protected int piecesNumber;
    protected int redSOS;
    protected int blueSOS;
    protected char[][] turnBoard;
    protected playerMode redPlayerMode;
    protected playerMode bluePlayerMode;
    protected boolean player;

    protected Cell redMove;
    protected Cell blueMove;

    protected gameMode chosenGameMode;

    public Board() {
        grid = new Cell[maxVal][maxVal];
        turnBoard = new char[maxVal][maxVal];

        //chosenGameMode = gameMode.SIMPLE;
        turn = 'B';
        piecesNumber = 0;
        currentGameState = gameState.PLAYING;

        for (int row = 0; row < maxVal; ++row) {
            for (int col = 0; col < maxVal; ++col) {
                grid[row][col] = Cell.NONE;
            }
        }
    }

    public void resetGame(){
        turn = 'B';

        currentGameState = gameState.PLAYING;
        piecesNumber = 0;
        blueSOS = 0;
        redSOS = 0;

        for (int row = 0; row < maxVal; ++row) {
            for (int col = 0; col < maxVal; ++col) {
                grid[row][col] = Cell.NONE;
            }
        }

        for (int row = 0; row < maxVal; ++row) {
            for (int col = 0; col < maxVal; ++col) {
                turnBoard[row][col] = ' ';
            }
        }
    }

    public Boolean updateGrid(int size) {
        if (size < 3 || size > 16) {
            return false;
        } else {
            grid = new Cell[size][size];
            maxVal = size;
            for (int row = 0; row < maxVal; ++row) {
                for (int col = 0; col < maxVal; ++col) {
                    grid[row][col] = Cell.NONE;
                }
            }
            return true;
        }
    }

    public boolean makeMove(int row, int column) {
        if (row >= 0 && row < maxVal && column >= 0 && column < maxVal && grid[row][column] == Cell.NONE) {
            piecesNumber++;
            return true;
        }
        return false;
    }

    public void setRedPlayerMode(playerMode redPlayerMode){
        this.redPlayerMode = redPlayerMode;
    }

    public playerMode getRedPlayerMode(){
        return redPlayerMode;
    }

    public void setBluePlayerMode(playerMode bluePlayerMode){
        this.bluePlayerMode = bluePlayerMode;
    }

    public playerMode getBluePlayerMode(){
        return bluePlayerMode;
    }

    public abstract Boolean sosFound(int row, int col);
    public abstract void makeComputerMove();

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    public char getTurnBoard(int row, int col){
        return turnBoard[row][col];
    }

    public void setRedMoveChoice(Cell x) {
        redMove = x;
    }

    public Cell getRedMoveChoice() {
        return redMove;
    }

    public void setBlueMoveChoice(Cell x) {
        blueMove = x;
    }

    public Cell getBlueMoveChoice() {
        return blueMove;
    }

    public gameMode getGameMode() {
        return chosenGameMode;
    }

    public void setGameMode(gameMode chosenGameMode) {
        this.chosenGameMode = chosenGameMode;
    }
    public gameState getCurrentGameState(){
        return currentGameState;
    }

    public int getGridSize() {
        return maxVal;
    }

    public char getTurn() {
        return turn;
    }
}
