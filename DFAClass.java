import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.abs;
/*
//Isaac Bouwkamp, Matthew Farretti
*/
public class DFAClass {
    private int numberOfStates = 40;

    public int getNumberOfStates(){
        return this.numberOfStates;
    }

    public void printChart(int[][] dataChart){
        for(int i = 0; i < this.numberOfStates; i++){
            for(int j = 0; j < this.numberOfStates; j++){
                System.out.print(dataChart[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] resetDataChart(int[][] dataChart){
        for(int i = 0; i < this.numberOfStates; i++){
            for(int j = 0; j < this.numberOfStates; j++){
                dataChart[i][j] = 1111;
            }
        }
        return dataChart;
    }

    public int readNextState(int[][] dataChart, int currentState, String letter){
        int letterInt = Integer.parseInt(letter);
        for(int i = 0; i < this.numberOfStates; i++) {
            if(letterInt == (dataChart[currentState][i])){//If letter is the correct letter.//Testing
                return i;
            }
        }

        for(int i = 0; i < this.numberOfStates; i++) {
            if(dataChart[currentState][i] < 0 && letterInt != (abs(dataChart[currentState][i]))){//If there is another way to go that doesn't include the already possible ways.
                return i;
            }
        }
        return -101010;//This should never get here, error in the paths that you created
    }

    public static void main(String[] args) {

        DFAClass Master = new DFAClass();//Just an object to use as a holding space for methods

        int validInput = 0;//This is a flag for the valid string length

        String word = "";
        while(validInput == 0) {
            System.out.println("Please enter a valid string: ");
            Scanner scan = new Scanner(System.in);
            String inputString = scan.nextLine();//Waiting for a valid input.
            word = inputString;

            if (inputString.length() % 3 == 0) {
                validInput = 1;
            } else {
                System.out.println("Not a valid string...");
            }
        }//End of checking for a valid sting length.

        //top down from 0 to 4 for the states.
        int[][] dataChart = new int[Master.getNumberOfStates()][Master.getNumberOfStates()];//all possible words and where they connect to. 5 states with 5 possible connections

        dataChart = Master.resetDataChart(dataChart);

        //Add in the top machine
        //Blank == 1111
        //Negative num == Everything except that number
        //state 0 is the starting state

        //dataChart[0 - 9][0 - 9]; //Row A1
        //dataChart[10 - 19][10 -19]; //Row B1
        //dataChart[20 - 29][20 - 29]; //Row C1
        //dataChart[30 - 39][30 - 39]; //Row D1

        dataChart[0][1] = 119; //A1 to A2
        dataChart[0][10] = 105; //A1 to B1
        dataChart[0][0] = -1; //A1 to A1
        dataChart[1][1] = 119; //A2 to A2
        dataChart[1][2] = 104; //A2 to A3
        dataChart[1][10] = 105; //A2 to B1
        dataChart[1][0] = -1; //A2 to A1
        dataChart[2][13] = 105; //A3 to B4
        dataChart[2][0] = -1; //A3 to A1
        dataChart[3][4] = 108; //A4 to A5
        dataChart[3][10] = 105; //A4 to B1
        dataChart[3][0] = -1; //A4 to A1
        dataChart[4][5] = 101; //A5 to A6
        dataChart[4][10] = 1; //A5 to B1
        dataChart[4][0] = -1; //A5 to A1
        dataChart[5][6] = 119; //A6 to A7
        dataChart[5][15] = 105; //A6 to B6
        dataChart[5][5] = -1; //A6 to A6
        dataChart[6][7] = 104; //A7 to A8
        dataChart[6][15] = 105; //A7 to B6
        dataChart[6][5] = -1; //A7 to A6
        dataChart[7][18] = 105; //A8 to B9
        dataChart[7][5] = -1; //A8 to A6
        dataChart[8][9] = 108; //A9 to A10
        dataChart[8][15] = 105; //A9 to B6
        dataChart[8][5] = -1; //A9 to A6
        dataChart[9][0] = 101; //A10 to A1
        dataChart[9][15] = 105; //A10 to B6
        dataChart[9][5] = -1; //A10 to A6

        dataChart[10][1] = 119; //B1 to A2
        dataChart[10][30] = 102; //B1 to D1
        dataChart[10][1] = -1; //B1 to A1
        dataChart[11][1] = 119; //B2 to A2
        dataChart[11][2] = 104; //B2 to A3
        dataChart[11][30] = 102; //B2 to D1
        dataChart[11][0] = -1; //B2 to A1
        dataChart[12][3] = 105; //B3 to A4
        dataChart[12][30] = 102; //B3 to D1
        dataChart[12][0] = -1; //B3 to A1
        dataChart[13][4] = 108; //B4 to A5
        dataChart[13][30] = 102; //B4 to D1
        dataChart[13][0] = -1; //B4 to A1
        dataChart[14][5] = 101; //B5 to A6
        dataChart[14][30] = 102; //B5 to D1
        dataChart[14][0] = -1; //B5 to A1
        dataChart[15][6] = 119; //B6 to A7
        dataChart[15][35] = 102; //B6 to D6
        dataChart[15][5] = -1; //B6 to A6
        dataChart[16][7] = 104; //B7 to A8
        dataChart[16][35] = 102; //B7 to D6
        dataChart[16][5] = -1; //B7 to A6
        dataChart[17][8] = 105; //B8 to A9
        dataChart[17][35] = 102; //B8 to D6
        dataChart[17][5] = -1; //B8 to a6
        dataChart[18][9] = 108; //B9 to A10
        dataChart[18][35] = 102; //B9 to D6
        dataChart[18][5] = -1; //B9 to A6
        dataChart[19][0] = 101; //B10 to A1
        dataChart[19][35] = 102; //B10 to D6
        dataChart[19][5] = -1; //B10 to A6






        Master.printChart(dataChart);

        int letterAmount = word.length() / 3;

        int startSub = 0;
        int endSub = 3;
        String currentLetter = "";

        ArrayList listOfLetters = new ArrayList();

        while(endSub <= word.length()){//This just splits up the input string into the letters.
            currentLetter = word.substring(startSub,endSub);
            startSub = startSub + 3;
            endSub = endSub + 3;

            listOfLetters.add(currentLetter);
        }

        System.out.println(listOfLetters);//Test

        int currentState = 1;//This is the state that we are at.

        //Reads in the logic to follow the paths around the dataChart.
        for(int i = 0; i < listOfLetters.size(); i++){
            currentState = Master.readNextState(dataChart, currentState, listOfLetters.get(i).toString());//Error -101010 is this state is missing a path
            System.out.println("End in " + currentState);
        }

        //Sees if we ended at the accept state.
        if(currentState == 3){
            System.out.println("Accept");
        }else{
            System.out.println("Reject");
        }

    }//End of main
}
