/**
 * Anders Stadum
 * Lab 3 AVL Tree
 * 11/26/2013
 * LibraryDriver.java
 */
//import java.io.*;
import java.util.Scanner;

public class LibraryDriver
{
	public static void main(String[] args)
	{	
		String operation, 									//Operation we are using
			   insert = "insert", 							//Exact insert operation
			   find = "find",								//Exact find operation
			   remove = "remove",							//Exact remove operation
			   book = "";									//holds book title
		int numOps = 0;										//number of operations
		AVL tree = new AVL();
	    Scanner keyboard = new Scanner(System.in);
	    numOps = Integer.parseInt(keyboard.nextLine());		//reads user input of number of lines to test
	    String[] results = new String[numOps];				//array to hold the results
	
	   
	        for(int i = 0; i < numOps; i++)
	        {
	        	operation = keyboard.nextLine();			//read the operation in
	    	if(operation.equals(insert))					//Entered "insert"
	    	{
	    		book = keyboard.nextLine();					//book is next line
	    		tree.insert(book);							//insert it into the tree
	    		results[i] = "Inserted " + book + " into the catalog.";//store book
	    	}
	    	else if(operation.equals(find))					//Entered "find"
	    	{
	    		book = keyboard.nextLine();					//book is next line
	    		if(tree.find(book))
	    			results[i] = book + " is in the catalog.";			 //Book was found
	    		else
	    			results[i] = book + " is not in the catalog.";		 //Didn't find book
	    	}
	    	else if(operation.equals(remove))							 //Entered "remove"
	    	{
	    		book = keyboard.nextLine();
	    		if(tree.remove(book))
	    			results[i] = book + " was removed from the catalog.";//Book was removed
	    		else
	    			results[i] = book + " is not in the catalog.";		 //Book wasn't removed
	    	}
	    	else
	    		System.out.println("operation not recognized");//if operation isn't valid
	    }
	
	    for(int i = 0; i < numOps; i++)
	    	System.out.println(results[i]);//print the results
	    keyboard.close();
	}
	
}