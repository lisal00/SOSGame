package sprint_2.product;

public class SimpleGame extends Board{
    SimpleGame(){
        super();
        chosenGameMode = gameMode.SIMPLE;
    }

    SimpleGame(Cell redMove, Cell blueMove){
        super();
        chosenGameMode = gameMode.SIMPLE;
        this.redMove = redMove;
        this.blueMove = blueMove;
    }

    @Override
    public boolean makeMove(int row, int col){
        if (super.makeMove(row, col)){
            System.out.println("USING SIMPLE GAME RULES");
            if (turn == 'B') {
                grid[row][col] = blueMove;
                turnBoard[row][col] = 'B';
                sosCombo = sosFound(row, col);

                if (sosCombo) { //sos found, then blue won
                    currentGameState = gameState.BLUE_WON;
                    System.out.println("BLUE WON");
                } else if (piecesNumber == maxVal * maxVal) {
                    currentGameState = gameState.DRAW;
                } else { //continue the game
                    turn = 'R';
                    turnBoard[row][col] = 'R';
                }

            }
            else if (turn == 'R'){ //if it is red's turn
                grid[row][col] = redMove;
                turnBoard[row][col] = 'R';
                sosCombo = sosFound(row, col);

                if (sosCombo) {//sos found then red won
                    currentGameState = gameState.RED_WON;
                    System.out.println("RED WON");

                } else if (piecesNumber == maxVal * maxVal) { //draw
                    currentGameState = gameState.DRAW;
                } else { //continue the game
                    turn = 'B';
                    turnBoard[row][col] = 'B';
                }
            }
            turnNum++;
            return true;
        }
        return false;
    }


    public Boolean sosFound(int row, int col){
        if(grid[row][col] == Cell.S){
            for (int i = -1; i < 2; i++){
                for (int y = -1; y < 2; y++){
                    try {
                        if (grid[row + i][col + y] == Cell.O && grid[row + i * 2][col + y * 2] == Cell.S) {
                            return true;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
            return false;
        }
        else if(grid[row][col] == Cell.O){
            for (int i = -1; i < 2; i++){
                for (int y = -1; y < 2; y++){
                    try {
                        if (grid[row + i][col + y] == Cell.S && grid[row + (i * -1)][col + (y * -1)] == Cell.S) {
                            return true;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
            return false;
        }
        return null;
    }
}
