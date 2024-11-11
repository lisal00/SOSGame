package sprint_2.product;
import java.util.Random;

public class GeneralComputerPlayer extends GeneralGame{
    Random rand = new Random();
    GeneralComputerPlayer(){
        super();

        //do other stuff
    }

    @Override
    public boolean makeComputerMove(){
        boolean flag = false;
        for (int row = 0; row < maxVal; row++) {
            for (int col = 0; col < maxVal; col++) {
                //if O nearby
                if (grid[row][col] == Cell.O && flag == false) {

                    if(makeSMove(row, col)){
                        flag = true;
                    }

                    //sosCombo = findSOS(row, col);
//                    if(!sosCombo && test) {
//                        System.out.println("Turn switched successfully S");
//                        switchTurn();
//
//                    }
//                    if(test){
//                        if(!sosCombo){
//                            System.out.println("Turn switched successfully S");
//                            switchTurn();
//                        }
//                    }
                }
                //if S nearby
                else if (grid[row][col] == Cell.S && flag == false) {
                    //make O near it
                    if (makeOMove(row, col)){
                        flag = true;
                    }
//                    sosCombo = findSOS(row, col);
//                    if(!sosCombo && test) {
//                        System.out.println("Turn switched successfully O");
//                        switchTurn();
//                    }
//                    if(test){
//                        if(!sosCombo){
//                            System.out.println("Turn switched successfully S");
//                            switchTurn();
//                        }
//                    }
                }
            }
        }

        if (flag == false){
            makeRandomMove();
//            if(test) {
//                System.out.println("Turn switched successfully Ran");
//                switchTurn();
//            }
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
                        piecesNumber++;
                        sosCombo = findSOS(row+x, col+y);
                        if(!sosCombo){
                            System.out.println("Turn switched successfully S");
                            switchTurn();
                        }
                        //turn = 'R';
                    } else if (turn == 'R' && redPlayerMode == playerMode.COMPUTER) {
                        System.out.println("make computer move S");
                        redMove = Cell.S;
                        grid[row + x][col + y] = redMove;
                        turnBoard[row+x][col+y] = 'R';
                        flag = true;
                        piecesNumber++;
                        sosCombo = findSOS(row+x, col+y);
                        if(!sosCombo){
                            System.out.println("Turn switched successfully S");
                            switchTurn();
                        }

                        //turn = 'B';
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
                    piecesNumber++;
                    sosCombo = findSOS(row+x, col+y);
                    if(!sosCombo){
                        System.out.println("Turn switched successfully O");
                        switchTurn();
                    }
                    //turn = 'R';
                } else if (turn == 'R' && redPlayerMode == playerMode.COMPUTER) {
                    System.out.println("make computer move O");

                    redMove = Cell.O;
                    grid[row + x][col + y] = redMove;
                    turnBoard[row+x][col+y] = 'R';
                    flag = true;
                    piecesNumber++;
                    sosCombo = findSOS(row+x, col+y);
                    if(!sosCombo){
                        System.out.println("Turn switched successfully O");
                        switchTurn();
                    }
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
                    piecesNumber++;
                    sosCombo = findSOS(row, col);
                    if(!sosCombo){
                        System.out.println("Turn switched successfully Ran");
                        switchTurn();
                    }
                    //turn = 'R';
                } else if (turn == 'R' && redPlayerMode == playerMode.COMPUTER) {
                    System.out.println("make computer move random ");

                    redMove = Cell.O;
                    grid[row][col] = redMove;
                    turnBoard[row][col] = 'R';
                    flag = true;
                    piecesNumber++;
                    sosCombo = findSOS(row, col);
                    if(!sosCombo){
                        System.out.println("Turn switched successfully Ran");
                        switchTurn();
                    }
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
                            if(turn == 'B' && getBluePlayerMode() == playerMode.COMPUTER) {
                                blueSOS++;
                                System.out.println("Blue cmputer???");
                            }
                            else if (turn == 'R' && getRedPlayerMode() == playerMode.COMPUTER){
                                redSOS++;
                                System.out.println("SOS Found where pos S: " + redSOS);
                            }
                            flag = true;
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
                            if (turn == 'B' && getBluePlayerMode() == playerMode.COMPUTER) {
                                localBlue++;
                            } else if (turn == 'R' && getRedPlayerMode() == playerMode.COMPUTER) {
                                localRed++;
                                System.out.println("SOS Found where pos O: " + localRed);
                            }
                            flag = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
            if (turn == 'B' && getBluePlayerMode() == playerMode.COMPUTER) {
                if(localBlue > 1 && localBlue % 2 == 0){
                    localBlue = localBlue/2;
                    blueSOS = localBlue + blueSOS;
                }
            } else if (turn == 'R' && getRedPlayerMode() == playerMode.COMPUTER) {
                if(localRed > 1 && localRed % 2 == 0){
                    localRed = localRed / 2;

                    redSOS = localRed + redSOS;
                    System.out.println("Actual redSOS: " + redSOS);
                }
            }
            localBlue = 0;
            localRed = 0;
        }

        return flag;
    }



}
