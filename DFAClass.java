import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.abs;
/*
//Isaac Bouwkamp,
*/
public class DFAClass {

    public void printChart(int[][] dataChart){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(dataChart[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] resetDataChart(int[][] dataChart){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                dataChart[i][j] = 1111;
            }
        }
        return dataChart;
    }

    public int readNextState(int[][] dataChart, int currentState, String letter){
        int letterInt = Integer.parseInt(letter);
        for(int i = 0; i < 5; i++) {
            if(letterInt == (dataChart[currentState][i])){//If letter is the correct letter.
                return i;
            }
        }

        for(int i = 0; i < 5; i++) {
            if(dataChart[currentState][i] < 0 && letterInt != (abs(dataChart[currentState][i]))){//If there is another way to go that doesn't include the already possible ways.
                return i;
            }
        }
        return -1;//This should never get here
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
        int[][] dataChart = new int[5][5];//all possible words and where they connect to. 5 states with 5 possible connections

        dataChart = Master.resetDataChart(dataChart);

        //Add in the top machine
        //Epsilon == 1000
        //Blank == 1111
        //Negative num == Everything except that number
        //state 0 is the starting state
        dataChart[0][1] = 1000; //Row 0
        dataChart[1][1] = -105; //Row 1
        dataChart[1][2] = 105; //Row 1
        dataChart[2][1] = -102; //Row 2
        dataChart[2][3] = 102; //Row 2
        dataChart[3][3] = -105; //Row 3
        dataChart[3][4] = 105; //Row 3
        dataChart[4][1] = 102; //Row 4
        dataChart[4][3] = -102; //Row 4

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
            currentState = Master.readNextState(dataChart, currentState, listOfLetters.get(i).toString());
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
