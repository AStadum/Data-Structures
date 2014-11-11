/*Anders Stadum
 * 313 Lab1
 * 10/17/2013
 * PalindromeDriver.java
 * 
 */

import java.util.Scanner; //for Scanner input
import java.io.*; //for IOException

public class PalindromeDriver {

    /* Main method: reads input and calls palindrome test, prints results */
    public static void main(String[] args) throws IOException
    {
        String[] results; //stores augmented lines
        int num_phrases;  //number of phrases you would like to enter
        String line;	  //input value variable place holder
        
        Scanner keyboard = new Scanner(System.in);
        num_phrases = Integer.parseInt(keyboard.nextLine()); //parse int 
        results = new String[num_phrases]; //stores results as a string array
        
       for (int i = 0; i < results.length; i++) {  //Replace all non characters/numbers in string
        	line = keyboard.nextLine(); 
        	results[i] = line.replaceAll("[^A-Za-z0-9]","").toLowerCase(); // store inputed information in results array
       }
       keyboard.close(); //close scanner
       
       for (int n = 0; n < results.length; n++) { //going through inputed results 
    	   if(is_palindrome(results[n]) == true)  //check if palindrome is true
    	   {
    		   System.out.println("Palindrome");
    	   }
    	   else //check if palindrome is false
    	   {
    		   System.out.println("Not a palindrome");
    	   }
       }
        
               

        //TODO Write a loop that reads num_phrases input lines from KEYBOARD,
        //     and checks whether each line is a palindrome or not.
        //     Use the is_palindrome subroutine to check each line....
        //     you will have to do some preprocessing to the lines first.
        //     Store the results of each line in results array.

        
        
        
        //TODO Write a loop that iterates through the results array and
        //     prints all the results to stdout.
    }

    /* is_palindrome: checks whether input is palindrome, returns true if so,
     *                false otherwise.
     */
    public static boolean is_palindrome(String input) {
    	Stack sk = new Stack(); //create an instance of the stack class
    	Queue qu = new Queue(); //crease an instance of the queue queue
    	
    	for (int r = 0; r < input.length(); r++){ //push and insert results into stack/queue
    		sk.push(input.charAt(r));
        	qu.enqueue(input.charAt(r));
        	//System.out.println(input.charAt(r));
    	}
    	
    	while(!sk.isEmpty() && !qu.isEmpty()) { //check if the stack/queue is empty 
        	
    		if (sk.pop() != qu.dequeue()) // if the element at the top of the stack is equal to the element at the front of the queue
    		{
    			return false;
    		}
    	}
    	return true;
    }
}