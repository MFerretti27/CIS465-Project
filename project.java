/************************************************************************************
 * Build a foundation to understanding the halting problem. 
 * For the DFA assume that the alphabet is {000, 001, 002, . . . , 997, 998, 999}. 
 * This is an alphabet with 1000 letters and each single letter is three digits long
 ************************************************************************************/

import java.util.Scanner;

/*************************************************************************************
Using your favorite programming language, implement a deterministic finite automaton M 
that recognizes the language:
   
L1 = {w|w contains a odd number of the two letter sequence 105102 OR 
    an even number of the five letter sequence 119104105108101 }
    
       
    Your output should consist of
    1. A sequence of states that the machine goes through,
    2. An output of ACCEPT or REJECT, depending on the input string

************************************************************************************/



public class project{


public static void main(String[] args) {

    int validInput = 0;//This is a flag for the valid string length
    String originalWord = "";


        while(validInput == 0) {
            System.out.println("Please enter a valid string: ");
            Scanner scan = new Scanner(System.in);
            String inputString = scan.nextLine();//Waiting for a valid input.
            originalWord = inputString;

            if (inputString.length() % 3 == 0) {
                validInput = 1;
            } else {
                System.out.println("Not a valid string...");
            }
        }//End of checking for a valid sting length.

        int firstStringOccurrence = 0;


        String firstTempWord = originalWord;
        System.out.println("\nLength of word: " + firstTempWord.length());

        for(int i = 3; firstTempWord.length() >= i; i = i+3){
            System.out.println("\nI is " + i + " Length is " + firstTempWord.length());
            
            String currentIteration = firstTempWord.substring(0, i);
            System.out.println("\tLooking through: " + currentIteration);

            String currentState = firstTempWord.substring(i-3, i);
            System.out.println("\tCurrent State: " + currentState);

            if (currentIteration.contains("105102")){
                firstStringOccurrence = firstStringOccurrence + 1;
                System.out.println("\nFound Occurrence of 105102");
                firstTempWord = firstTempWord.substring(i, firstTempWord.length());
                System.out.println("\tNew String: " + firstTempWord);
                i = 0;
            }
        }

        System.out.println("\nOccurrences: " + firstStringOccurrence);

        if(firstStringOccurrence % 2 == 0){
            //System.out.println("\n\n This string is accepted as" + 
            //    " it contains a odd number of the two letter sequence 105102");
        }
    
}

}






