public class Board {
    private int[][] grid;
    private char turn = 'B';
    private int maxVal = 5;
    public enum moveChoice{S, O}
    private moveChoice redMove;
    private moveChoice blueMove;
    private enum gameMode{SIMPLE, GENERAL}
    private gameMode chosenGameMode;

    public Board(){
        grid = new int[maxVal][maxVal];
        chosenGameMode = gameMode.SIMPLE;
    }

    public int getCell(int row, int column) {
        if (row >= 0 && row < maxVal && column >= 0 && column < maxVal)
            return grid[row][column];
        else
            return -1;
    }

    public void setRedMoveChoice(moveChoice redMoveChoice){
        redMove = redMoveChoice;
    }
    public void setBlueMoveChoice(moveChoice blueMoveChoice){
        blueMove = blueMoveChoice;
    }

    public void getGameMode(){}
    public void setGameMode() {
    }

    public void updateGrid(int size){
        grid = new int[size][size];
        maxVal = size;
    }

    public int getGridSize(){
        return maxVal;
    }

    public char getTurn(){
        return turn;
    }

    public void makeMove(int row, int column) {
        if(chosenGameMode == gameMode.SIMPLE){
            if (row >= 0 && row < maxVal && column >= 0 && column < maxVal && grid[row][column] == 0) {
                grid[row][column] = (turn == 'B')? 1 : 2;
                turn = (turn == 'B')? 'R' : 'B';
            }
        }   else if (chosenGameMode == gameMode.GENERAL){
            if (row >= 0 && row < maxVal && column >= 0 && column < maxVal && grid[row][column] == 0) {
                grid[row][column] = (turn == 'B')? 1 : 2;
                turn = (turn == 'B')? 'R' : 'B';
            }
        }
    }

}