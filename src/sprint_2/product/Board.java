package product;

public class Board {
    private Cell[][] grid;
    private char turn;
    private int maxVal = 5;

    public enum Cell {NONE, S, O}

    //public char[] moves = {'S', 'O'};
    private Cell redMove;
    private Cell blueMove;

    public enum gameMode {SIMPLE, GENERAL}

    private gameMode chosenGameMode;
    //private boolean redBool;
    //private char redMoveChar;
    //private char blueMoveChar;

    public Board() {
        grid = new Cell[maxVal][maxVal];
        chosenGameMode = gameMode.SIMPLE;
        turn = 'B';
        //redBool = false;
        for (int row = 0; row < maxVal; ++row) {
            for (int col = 0; col < maxVal; ++col) {
                grid[row][col] = Cell.NONE;
            }
        }
    }
//    public boolean getRedBool(){
//        return redBool;
//    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
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

    public boolean updateGrid(int size) {
        if (size < 3 || size > 16) {
            return false;
        } else {
            grid = new Cell[size][size];
            maxVal = size;
            return true;
        }
    }

    public int getGridSize() {
        return maxVal;
    }

    public char getTurn() {
        return turn;
    }

    public void makeMove(int row, int column) {
        if (row >= 0 && row < maxVal && column >= 0 && column < maxVal && grid[row][column] == Cell.NONE) {
            if (chosenGameMode == gameMode.SIMPLE) {
//                grid[row][column] = (turn == 'B')? blueMove : redMove;
//                turn = (turn == 'B')? 'R' : 'B';
                if (turn == 'B') {
                    grid[row][column] = blueMove; // Place blue player's move (either S or O)
                    turn = 'R'; // Change turn to red
                } else {
                    grid[row][column] = redMove; // Place red player's move (either S or O)
                    turn = 'B'; // Change turn to blue
                }
            } else if (chosenGameMode == gameMode.GENERAL) {
//                grid[row][column] = (turn == 'B')? blueMove : redMove;
//                turn = (turn == 'B')? 'R' : 'B';
                if (turn == 'B') {
                    grid[row][column] = blueMove; // Place blue player's move (either S or O)
                    turn = 'R'; // Change turn to red
                } else {
                    grid[row][column] = redMove; // Place red player's move (either S or O)
                    turn = 'B'; // Change turn to blue
                }
            }
        }
    }
}