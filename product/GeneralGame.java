package sprint_2.product;
//not tallying up correctly double tallying?
//drawing wrong color lines, but tallying correctly
public abstract class GeneralGame extends Board{
    int localBlue;
    int localRed;

    GeneralGame() {
        super();
        chosenGameMode = gameMode.GENERAL;
        redSOS = 0;
        blueSOS = 0;
        localBlue = 0;
        localRed = 0;

        System.out.println("CONSTRUCTOR GENERAL CLASS");
    }

    @Override
    public boolean makeMove(int row, int col){
        if (super.makeMove(row, col)){
            System.out.println("USING GENERAL GAME RULES");

            if (turn == 'B') {
                grid[row][col] = blueMove;
                turnBoard[row][col] = 'B';
                System.out.println(turnBoard[row][col]);

                sosCombo = sosFound(row, col);
                //System.out.println("Blue: " + blueSOS + " Red: " + redSOS);
                if (!sosCombo) { //sos not found, then changes turn to other player
                    turn = 'R';
                    //turnBoard[row][col] = 'R';
                }
            }
            else if (turn == 'R'){ //if it is red's turn
                grid[row][col] = redMove;
                turnBoard[row][col] = 'R';
                System.out.println(turnBoard[row][col]);

                sosCombo = sosFound(row, col);
                if (!sosCombo) { //sos not found, then changes turn to other player
                    turn = 'B';
                    //turnBoard[row][col] = 'B';
                }
            }
            System.out.println("Blue: " + blueSOS + " Red: " + redSOS);

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
            for (int i = -1; i < 2; i++) {
                for (int y = -1; y < 2; y++) {
                    try {
                        if ((grid[row + i][col + y] == Cell.S && grid[row + (i * -1)][col + (y * -1)] == Cell.S)) {
//                            if (flag && (grid[row + i][col + y] == Cell.S && grid[row + (i * -1)][col + (y * -1)] == Cell.S)){
//                                continue;
//                            }
                            if (turn == 'B') {
                                localBlue++;
                                //blueSOS++;
                            } else if (turn == 'R') {
                                localRed++;
                                //redSOS++;
                            }
                            flag = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
            div(turn);
        }
        return flag;
    }



    public void div(char turn){
        if (turn == 'B') {
            if(localBlue > 1 && localBlue % 2 == 0){
                localBlue = localBlue/2;
                blueSOS = localBlue + blueSOS;
            }
        } else if (turn == 'R') {
            if(localRed > 1 && localRed % 2 == 0){
                localRed = localRed / 2;
                redSOS = localRed + redSOS;
            }
        }
        localBlue = 0;
        localRed = 0;
    }
}

