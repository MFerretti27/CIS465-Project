import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.abs;
/*
    Isaac Bouwkamp, Matthew Ferretti
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
            System.out.println("\nPlease enter a valid string: ");
            Scanner scan = new Scanner(System.in);
            String inputString = scan.nextLine();//Waiting for a valid input.
            word = inputString;

            if (inputString.length() % 3 == 0) {
                validInput = 1;
            } else {
                System.out.println("Not a valid string...");
            }
            scan.close();
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
        dataChart[2][1] = 119; //A3 to A2
        dataChart[2][13] = 105; //A3 to B4
        dataChart[2][0] = -1; //A3 to A1
        dataChart[3][1] = 119; //A4 to A2
        dataChart[3][4] = 108; //A4 to A5
        dataChart[3][10] = 105; //A4 to B1
        dataChart[3][0] = -1; //A4 to A1
        dataChart[4][1] = 119; //A5 to A2
        dataChart[4][5] = 101; //A5 to A6
        dataChart[4][10] = 105; //A5 to B1
        dataChart[4][0] = -1; //A5 to A1
        dataChart[5][6] = 119; //A6 to A7
        dataChart[5][15] = 105; //A6 to B6
        dataChart[5][5] = -1; //A6 to A6
        dataChart[6][7] = 104; //A7 to A8
        dataChart[6][15] = 105; //A7 to B6
        dataChart[6][5] = -1; //A7 to A6
        dataChart[7][1] = 119; //A8 to A7
        dataChart[7][18] = 105; //A8 to B9
        dataChart[7][5] = -1; //A8 to A6
        dataChart[8][1] = 119; //A9 to A7
        dataChart[8][9] = 108; //A9 to A10
        dataChart[8][15] = 105; //A9 to B6
        dataChart[8][5] = -1; //A9 to A6
        dataChart[9][1] = 119; //A10 to A7
        dataChart[9][0] = 101; //A10 to A1
        dataChart[9][15] = 105; //A10 to B6
        dataChart[9][5] = -1; //A10 to A6

        dataChart[10][1] = 119; //B1 to A2
        dataChart[10][10] = 105; //B1 to B1 //Added 105
        dataChart[10][30] = 102; //B1 to D1
        dataChart[10][1] = -1; //B1 to A1
        dataChart[11][1] = 119; //B2 to A2
        dataChart[11][2] = 104; //B2 to A3
        dataChart[11][10] = 105; //B2 to B1 //Added 105
        dataChart[11][30] = 102; //B2 to D1
        dataChart[11][0] = -1; //B2 to A1
        dataChart[12][1] = 119; //B3 to A2
        dataChart[12][13] = 105; //B3 to B4 //Added 105
        dataChart[12][30] = 102; //B3 to D1
        dataChart[12][0] = -1; //B3 to A1
        dataChart[13][1] = 119; //B4 to A2
        dataChart[13][4] = 108; //B4 to A5
        dataChart[13][10] = 105; //B4 to B1 //Added 105
        dataChart[13][30] = 102; //B4 to D1
        dataChart[13][0] = -1; //B4 to A1
        dataChart[14][1] = 119; //B5 to A2
        dataChart[14][5] = 101; //B5 to A6
        dataChart[14][10] = 105; //B5 to B1 //Added 105
        dataChart[14][30] = 102; //B5 to D1
        dataChart[14][0] = -1; //B5 to A1
        dataChart[15][6] = 119; //B6 to A7
        dataChart[15][15] = 105; //B6 to B6 //Added 105
        dataChart[15][35] = 102; //B6 to D6
        dataChart[15][5] = -1; //B6 to A6
        dataChart[16][7] = 104; //B7 to A8
        dataChart[16][15] = 105; //B7 to B6 //Added 105
        dataChart[16][35] = 102; //B7 to D6
        dataChart[16][5] = -1; //B7 to A6
        dataChart[17][1] = 119; //B8 to A7
        dataChart[17][18] = 105; //B8 to B9 //Added 105
        dataChart[17][35] = 102; //B8 to D6
        dataChart[17][5] = -1; //B8 to a6
        dataChart[18][1] = 119; //B9 to A7
        dataChart[18][9] = 108; //B9 to A10
        dataChart[18][15] = 105; //B9 to B6 //Added 105
        dataChart[18][35] = 102; //B9 to D6
        dataChart[18][5] = -1; //B9 to A6
        dataChart[19][1] = 119; //B10 to A7
        dataChart[19][0] = 101; //B10 to A1
        dataChart[19][15] = 105; //B10 to B6 //Added 105
        dataChart[19][35] = 102; //B10 to D6
        dataChart[19][5] = -1; //B10 to A6

        dataChart[20][31] = 119; //C1 to D2
        dataChart[20][20] = 105; //C1 to C1 //Added 105
        dataChart[20][0] = 102; //C1 to A1
        dataChart[20][30] = -1; //C1 to D1
        dataChart[21][31] = 119; //C2 to D2
        dataChart[21][32] = 104; //C2 to D3
        dataChart[21][20] = 105; //C2 to C1 //Added 105
        dataChart[21][0] = 102; //C2 to A1
        dataChart[21][30] = -1; //C2 to D1
        dataChart[22][31] = 119; //C3 to D2
        dataChart[22][23] = 105; //C3 to C4 //Added 105
        dataChart[22][0] = 102; //C3 to A1
        dataChart[22][30] = -1; //C3 to D1
        dataChart[23][31] = 119; //C4 to D2
        dataChart[23][34] = 108; //C4 to D5
        dataChart[23][20] = 105; //C4 to C1 //Added 105
        dataChart[23][0] = 102; //C4 to A1
        dataChart[23][30] = -1; //C4 to D1
        dataChart[24][31] = 119; //C5 to D2
        dataChart[24][35] = 101; //C5 to D6
        dataChart[24][20] = 105; //C5 to C1 //Added 105
        dataChart[24][0] = 102; //C5 to A1
        dataChart[24][30] = -1; //C5 to D1
        dataChart[25][36] = 119; //C6 to D7
        dataChart[25][25] = 105; //C6 to C6 //Added 105
        dataChart[25][5] = 102; //C6 to A6
        dataChart[25][35] = -1; //C6 to D6
        dataChart[26][37] = 104; //C7 to D8
        dataChart[26][25] = 105; //C7 to C6 //Added 105
        dataChart[26][5] = 102; //C7 to A6
        dataChart[26][35] = -1; //C7 to D6
        dataChart[27][36] = 119; //C8 to D7
        dataChart[27][28] = 105; //C8 to C9 //Added 105
        dataChart[27][5] = 102; //C8 to A6
        dataChart[27][35] = -1; //C8 to D6
        dataChart[28][39] = 108; //C9 to D10
        dataChart[28][36] = 119; //C9 to D7
        dataChart[28][25] = 105; //C9 to C6
        dataChart[28][5] = 102; //C9 to A6
        dataChart[28][35] = -1; //C9 to D6
        dataChart[29][30] = 101; //C10 to D1
        dataChart[29][25] = 105; //C10 to C6
        dataChart[29][5] = 102; //C10 to A6
        dataChart[29][35] = -1; //C10 to D6

        dataChart[30][31] = 119; //D1 to D2
        dataChart[30][20] = 105; //D1 to C1
        dataChart[30][30] = -1; //D1 to D1
        dataChart[31][31] = 119; //D2 to D2
        dataChart[31][32] = 104; //D2 to D3
        dataChart[31][20] = 105; //D2 to C1
        dataChart[31][30] = -1; //D2 to D1
        dataChart[32][31] = 119; //D3 to D2
        dataChart[32][23] = 105; //D3 to C4
        dataChart[32][30] = -1; //D3 to D1
        dataChart[33][31] = 119; //D4 to D2
        dataChart[33][34] = 108; //D4 to D5
        dataChart[33][20] = 105; //D4 to C1
        dataChart[33][30] = -1; //D4 to D1
        dataChart[34][31] = 119; //D5 to D2
        dataChart[34][35] = 101; //D5 to D6
        dataChart[34][20] = 105; //D5 to C1
        dataChart[34][30] = -1; //D5 to D1
        dataChart[35][36] = 119; //D6 to D7
        dataChart[35][25] = 105; //D6 to C6
        dataChart[35][35] = -1; //D6 to D6
        dataChart[36][37] = 104; //D7 to D8
        dataChart[36][25] = 105; //D7 to C6
        dataChart[36][35] = -1; //D7 to D6
        dataChart[37][28] = 105; //D8 to C9
        dataChart[37][36] = 119; //D8 to D7
        dataChart[37][35] = -1; //D8 to D6
        dataChart[38][39] = 108; //D9 to D10
        dataChart[38][36] = 119; //D9 to D7
        dataChart[38][25] = 105; //D9 to C6
        dataChart[38][35] = -1; //D9 to D6
        dataChart[39][30] = 101; //D10 to D1
        dataChart[39][36] = 119; //D10 to D7
        dataChart[39][25] = 105; //D10 to C6
        dataChart[39][35] = -1; //D10 to D6

        //Master.printChart(dataChart); // Used for debugging 


        int startSub = 0;
        int endSub = 3;
        String currentLetter = "";

        ArrayList<String> listOfLetters = new ArrayList<String>();

        while(endSub <= word.length()){//This just splits up the input string into the letters.
            currentLetter = word.substring(startSub,endSub);
            startSub = startSub + 3;
            endSub = endSub + 3;

            listOfLetters.add(currentLetter);
        }

        System.out.println("\n" + listOfLetters + "\n");//Test

        int currentState = 1;//This is the state that we are at.

        //Reads in the logic to follow the paths around the dataChart.
        System.out.println("Start State: A1");

        for(int i = 0; i < listOfLetters.size(); i++){
            currentState = Master.readNextState(dataChart, currentState, listOfLetters.get(i).toString());//Error -101010 is this state is missing a path

            System.out.print("\t Transition to State: ");

            switch(currentState){
                case 0:
                    System.out.println("A1");
                    break;
                case 1:
                    System.out.println("A2");
                    break;
                case 2:
                    System.out.println("A3");
                    break;
                case 4:
                    System.out.println("A5");
                    break;
                case 5:
                    System.out.println("A6");
                    break;
                case 6:
                    System.out.println("A7");
                    break;
                case 7:
                    System.out.println("A8");
                    break;
                case 9:
                    System.out.println("A10");
                    break;
                case 10:
                    System.out.println("B1");
                    break;
                case 13:
                    System.out.println("B4");
                    break;
                case 15:
                    System.out.println("B6");
                    break;
                case 18:
                    System.out.println("B9");
                    break;
                case 20:
                    System.out.println("C1");
                    break;
                case 23:
                    System.out.println("C4");
                    break;
                case 25:
                    System.out.println("C6");
                    break;
                case 28:
                    System.out.println("C9");
                    break;
                case 30:
                    System.out.println("D1");
                    break;
                case 31:
                    System.out.println("D2");
                    break;
                case 32:
                    System.out.println("D3");
                    break;
                case 34:
                    System.out.println("D5");
                    break;
                case 35:
                    System.out.println("D6");
                    break;
                case 36:
                    System.out.println("D7");
                    break;
                case 37:
                    System.out.println("D8");
                    break;
                case 39:
                    System.out.println("D10");
                    break;
                default:
                    System.out.println("State Error");
            }

        }

        //Sees if we ended at the accept state.
        if(currentState == 5 || currentState == 6 || currentState == 7 || currentState == 8 || currentState == 9 || currentState == 15 || currentState == 16 || currentState == 17 || currentState == 18 || currentState == 19){
            System.out.println("\nString Rejected\n");//Fixed
        }else{
            System.out.println("\n String Accepted\n");//Fixed
        }
    }//End of main
}
