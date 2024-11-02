//package sprint_2.product;

public class GeneralGame extends Board{
    GeneralGame() {
        super();
        chosenGameMode = gameMode.GENERAL;
        redSOS = 0;
        blueSOS = 0;
        System.out.println("GENERALGAME CLASS");
    }

    @Override
    public boolean makeMove(int row, int col){
        if (super.makeMove(row, col)){
            System.out.println("USING GENERAL GAME RULES");
            if (turn == 'B') {
                grid[row][col] = blueMove;
                turnBoard[row][col] = 'B';
                sosCombo = sosFound(row, col);
                System.out.println("Blue: " + blueSOS + " Red: " + redSOS);
                if (!sosCombo) { //sos found
                    turn = 'R';
                    turnBoard[row][col] = 'R';
                }
            }
            else if (turn == 'R'){ //if it is red's turn
                grid[row][col] = redMove;
                turnBoard[row][col] = 'R';
                sosCombo = sosFound(row, col);
                System.out.println("Blue: " + blueSOS + " Red: " + redSOS);
                if (!sosCombo) { //sos found
                    turn = 'B';
                    turnBoard[row][col] = 'B';
                }
            }

            if (piecesNumber == maxVal * maxVal){
                if (redSOS < blueSOS){
                    currentGameState = gameState.BLUE_WON;
                    System.out.println("Blue: " + blueSOS + " Red: " + redSOS);
                }
                else if (redSOS > blueSOS){
                    currentGameState = gameState.RED_WON;
                    System.out.println("Blue: " + blueSOS + " Red: " + redSOS);
                }
                else{
                    currentGameState = gameState.DRAW;
                    System.out.println("Blue: " + blueSOS + " Red: " + redSOS);
                }
            }
            return true;
        }
        return false;
    }


    public Boolean sosFound(int row, int col){
        boolean flag = false;
        if(grid[row][col] == Cell.S){
            for (int i = -1; i < 2; i++){
                for (int y = -1; y < 2; y++){
                    try {
                        if (grid[row + i][col + y] == Cell.O && grid[row + i * 2][col + y * 2] == Cell.S) {
                            if(turn == 'B') {
                                blueSOS++;
                            }
                            else if (turn == 'R'){
                                redSOS++;
                            }
                            flag = true;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
        }
        else if(grid[row][col] == Cell.O){
            for (int i = -1; i < 2; i++){
                for (int y = -1; y < 2; y++){
                    try {
                        if (grid[row + i][col + y] == Cell.S && grid[row + (i * -1)][col + (y * -1)] == Cell.S) {
                            if(turn == 'B') {
                                blueSOS++;
                            }
                            else if (turn == 'R'){
                                redSOS++;
                            }
                            flag = true;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
        }
        return flag;
    }
}