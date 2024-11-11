package sprint_2.product;

import java.util.Random;

public class SimpleComputerPlayer extends SimpleGame {
    Random rand = new Random();

    SimpleComputerPlayer() {
        super();
    }

    @Override
    public boolean makeComputerMove() {
        boolean flag = false;
        for (int row = 0; row < maxVal; row++) {
            for (int col = 0; col < maxVal; col++) {
                if (grid[row][col] == Cell.O && flag == false) {
                    if (makeSMove(row, col)) {
                        flag = true;
                    }
                    else if (grid[row][col] == Cell.S && flag == false) {
                        //make O near it
                        if (makeOMove(row, col)) {
                            flag = true;
                        }
                    }
                }
            }
        }
        if (flag == false) {
            makeRandomMove();
        }
        return flag;
    }

    @Override
    public boolean makeSMove(int row, int col){
        boolean flag = false;

        int x = rand.nextInt(-1, 2);
        int y = rand.nextInt(-1, 2);

        try {
            if (grid[row + x][col + y] == Cell.NONE && flag == false) {
                if (turn == 'B' && bluePlayerMode == playerMode.COMPUTER) {
                    System.out.println("make computer move S");
                    blueMove = Cell.S;
                    grid[row + x][col + y] = blueMove;
                    turnBoard[row+x][col+y] = 'B';
                    flag = true;
                    sosCombo = findSOS(row+x, col+y);
                    if(sosCombo){
                        currentGameState = gameState.BLUE_WON;
                    }
                    switchTurn();
                } else if (turn == 'R' && redPlayerMode == playerMode.COMPUTER) {
                    System.out.println("make computer move S");
                    redMove = Cell.S;
                    grid[row + x][col + y] = redMove;
                    turnBoard[row+x][col+y] = 'R';
                    flag = true;
                    sosCombo = findSOS(row+x, col+y);
                    if(sosCombo){
                        currentGameState = gameState.RED_WON;
                    }
                    switchTurn();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        return flag;
    }

    @Override
    public boolean makeOMove(int row, int col){
        boolean flag = false;

        int x = rand.nextInt(-1, 2);
        int y = rand.nextInt(-1, 2);

        try {
            if (grid[row + x][col + y] == Cell.NONE && flag == false) {
                if (turn == 'B' && bluePlayerMode == playerMode.COMPUTER) {
                    System.out.println("make computer move O");

                    blueMove = Cell.O;
                    grid[row + x][col + y] = blueMove;
                    turnBoard[row+x][col+y] = 'B';
                    flag = true;
                    sosCombo = findSOS(row+x, col+y);
                    if(sosCombo){
                        currentGameState = gameState.BLUE_WON;
                    }
                    switchTurn();
                } else if (turn == 'R' && redPlayerMode == playerMode.COMPUTER) {
                    System.out.println("make computer move O");

                    redMove = Cell.O;
                    grid[row + x][col + y] = redMove;
                    turnBoard[row+x][col+y] = 'R';
                    flag = true;
                    sosCombo = findSOS(row+x, col+y);
                    if(!sosCombo){
                        currentGameState = gameState.RED_WON;
                    }
                    switchTurn();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return flag;
    }

    @Override
    public boolean makeRandomMove(){
        boolean flag = false;
        int row = rand.nextInt(0, maxVal);
        int col = rand.nextInt(0, maxVal);

        if (grid[row][col] == Cell.NONE) {
            if (turn == 'B' && bluePlayerMode == playerMode.COMPUTER) {
                System.out.println("make computer move random ");
                blueMove = Cell.O;
                grid[row][col] = blueMove;
                turnBoard[row][col] = 'B';
                flag = true;
                sosCombo = findSOS(row, col);
                if(sosCombo){
                    currentGameState = gameState.BLUE_WON;
                }
                switchTurn();
            } else if (turn == 'R' && redPlayerMode == playerMode.COMPUTER) {
                System.out.println("make computer move random ");

                redMove = Cell.O;
                grid[row][col] = redMove;
                turnBoard[row][col] = 'R';
                flag = true;
                sosCombo = findSOS(row, col);
                if(sosCombo){
                    currentGameState = gameState.RED_WON;
                }
                switchTurn();
            }
        }
        return flag;
    }

    public void switchTurn(){
        if(turn == 'B' && getBluePlayerMode() == playerMode.COMPUTER){
            turn = 'R';
        } else if (turn == 'R' && getRedPlayerMode() == playerMode.COMPUTER){
            turn = 'B';
        }
    }

    public boolean findSOS(int row, int col){
        boolean flag = false;
        if(grid[row][col] == Cell.S && ((turnBoard[row][col] == 'B' && bluePlayerMode == playerMode.COMPUTER)
                || (turnBoard[row][col] == 'R' && redPlayerMode == playerMode.COMPUTER))){
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
        }
        else if(grid[row][col] == Cell.O && ((turnBoard[row][col] == 'B' && bluePlayerMode == playerMode.COMPUTER)
                || (turnBoard[row][col] == 'R' && redPlayerMode == playerMode.COMPUTER))){
            for (int i = -1; i < 2; i++) {
                for (int y = -1; y < 2; y++) {
                    try {
                        if ((grid[row + i][col + y] == Cell.S && grid[row + (i * -1)][col + (y * -1)] == Cell.S)) {
                            return true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }

        }

        return flag;
    }


}
