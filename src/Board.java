public class Board {
    private int[][] grid;
    private char turn = 'B';
    int maxVal = 5;

    public Board(){
        grid = new int[maxVal][maxVal];
    }

    public int getCell(int row, int column) {
        if (row >= 0 && row < maxVal && column >= 0 && column < maxVal)
            return grid[row][column];
        else
            return -1;
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
        if (row >= 0 && row < maxVal && column >= 0 && column < maxVal && grid[row][column] == 0) {
            grid[row][column] = (turn == 'B')? 1 : 2;
            turn = (turn == 'B')? 'R' : 'B';
        }
    }

}
