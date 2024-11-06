package sprint_2.product;

public class GeneralComputerPlayer extends GeneralGame{
    GeneralComputerPlayer(){
        super();
        //do other stuff
    }


    public boolean makeComputerMove(int row, int col, int maxVal){
        //if computer is selected, make a move based on board...
        //check for possible SOS combinations
        //if O nearby, make S near it
        //if S nearby, make O near it
        //or just make a random move
        return true;
    }

    public void findSOS(int maxVal){
        //search grid for S || O
        for(int row = 0; row < maxVal; row++) {
            for(int col = 0; col < maxVal; col++) {
                if (grid[row][col] == Cell.S){
                    //search for surrounding O's
                    if (SOSCombo(row, col)){

                    }
                }
                else if (grid[row][col] == Cell.O){
                    //search for surrounding S's

                }
            }
        }
        //If S found, search for surrounding O's
        //if O found, search for surrounding S's
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


    public void makeSMove(){

    }

    public void makeOMove(){

    }

}
