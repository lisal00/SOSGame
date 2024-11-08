package sprint_2.product;

public class GeneralComputerPlayer extends GeneralGame{
    GeneralComputerPlayer(){
        super();

        //do other stuff
    }

    public boolean findSOS(int row, int col){
        boolean flag = false;
        //search grid for S || O
        if (grid[row][col] == Cell.S){
            //search for surrounding O's
            for (int i = -1; i < 2; i++) {
                for (int y = -1; y < 2; y++) {
                    try {
                        if (grid[row + i][col + y] == Cell.O) {
                            flag = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }

        }
        else if (grid[row][col] == Cell.O){
            for (int i = -1; i < 2; i++) {
                for (int y = -1; y < 2; y++) {
                    try {
                        if (grid[row + i][col + y] == Cell.S) {
                            flag = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
            //search for surrounding S's
        }

        //If S found, search for surrounding O's
        //if O found, search for surrounding S's
        return flag;
    }

    public void makeComputerMove(){
        //if computer is selected, make a move based on board...
        for(int row = 0; row < maxVal; row++) {
            for(int col = 0; col < maxVal; col++) {
                //check for possible SOS combinations

                if (findSOS(row, col)) {

                        //then make move...
                        if (turn == 'B' && bluePlayerMode == playerMode.COMPUTER) {

                            grid[row][col] = blueMove;
                            //turn = 'R';
                        } else if (turn == 'R' && redPlayerMode == playerMode.COMPUTER) {
                            grid[row][col] = redMove;
                            //turn = 'B';
                        }

                }

                //if O nearby
                else if (grid[row][col] == Cell.O){

                    //then make S near it

                }
                //if S nearby
                else if (grid[row][col] == Cell.S){
                    //make O near it
                    makeSMove(row, col);
                }


                //or just make a random move
            }
        }
        //make a random move?
    }



    public boolean SOSCombo(int row, int col) {
        boolean flag = false;
        for (int i = -1; i < 2; i++) {
            for (int y = -1; y < 2; y++) {
                try {
                    if (grid[row + i][col + y] == Cell.O) {
                        flag = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return flag;
    }


    public void makeSMove(int row, int col){
        for (int i = -1; i < 2; i++) {
            for (int y = -1; y < 2; y++) {
                if(grid[row + i][col + y] == Cell.NONE){
                    if (turn == 'B' && bluePlayerMode == playerMode.COMPUTER) {
                        blueMove = Cell.S;
                        grid[row + i][col + y] = Cell.S;
                        //turn = 'R';
                    } else if (turn == 'R' && redPlayerMode == playerMode.COMPUTER) {
                        redMove = Cell.S;
                        grid[row + i][col + y] = redMove;
                        //turn = 'B';
                    }
                }
            }
        }
    }

    public boolean makeOMove(int row, int col){
        for (int i = -1; i < 2; i++) {
            for (int y = -1; y < 2; y++) {
                if(grid[row + i][col + y] == Cell.NONE){
                    grid[row + i][col + y] = Cell.O;
                    return true;
                }
            }
        }
        return false;

    }

}
