/**
 * Anders Stadum
 * Lab 3 AVL Tree
 * 11/26/2013
 * AVL.java
 */


import java.lang.Math;

public class AVL 
{
	private AVLNode root; 						//Root of the AVL tree
	private boolean flag = true; 				//tells us which case comes back true or false
	
	public void insert(String title){ 			//adds a book to the catalog
		root = insert(title, root);		 		//recursively calls itself, passing the title of the book and the root node
	}
	
	public boolean find(String title) {			//find the title of a book
		return find(title, root);				//recursively call itself, passing the title of the book and the root node
	}
	
	public boolean remove(String title) { 		//removes a book by its title
		root = remove(title, root);		  		//recursively call itself, passing the title of the book and the root node
		return flag;					  		//return whether the specific case has been completed
	} 
	
	private AVLNode insert(String title, AVLNode book)
	{
		if(book == null) 						//if the root is empty
		{ 
			book = new AVLNode(title); 			//update root with new node
			return book;			   			//returns to root
		}
		else if(book.iData.equals(title))		//check for duplicates
		{
			return book;						//returns to root if duplicate is found
		}
		else
		{
			if(title.compareToIgnoreCase(book.iData) < 0) //insert node in left child
			{
				book.leftChild = insert(title, book.leftChild); //recursively move to left child
			}
			else if(title.compareToIgnoreCase(book.iData) > 0) //insert node in right child
			{
				book.rightChild = insert(title, book.rightChild); //recursively move to right child
			}
			return balance(book);   //updates all nodes at root
		}
	}
	
	private AVLNode remove(String title, AVLNode book)
	{
		AVLNode ret = null;  //null return statement
		if(book == null) //The tree is empty
		{
			flag = false;
			return ret;
		}
		else if(book.iData.equalsIgnoreCase(title)) //Check to see if the title is inside the tree
		{
			if(book.leftChild != null && book.rightChild != null) //both children are full in node
			{
				AVLNode minimumNode = book; 			  //holds the smallest values
				minimumNode = minimumNode.leftChild;  	  //Set leftchild to the smallest value
				while(minimumNode.rightChild != null)     //While the smallest right child exists
					minimumNode = minimumNode.rightChild; //Set minimum node to right child
				if(minimumNode.leftChild != null)
				{        //If the smallest left child exits
					book.leftChild.rightChild = minimumNode.leftChild; //Sets the roots left child's right child to the smallest leftchild node
					book.iData = minimumNode.iData;		  //Set the data within our book node to the data in the smallest node
					return book;						
				}
				else
				{
					book.iData = minimumNode.iData;       //Set the data within our book node to the data in the smallest node
					minimumNode = null;					  //clear smallest node
					return book;
				}
			}
			else if(book.leftChild == null && book.rightChild == null)//both children are empty
			{
				return ret;
			}
			else if(book.leftChild != null && book.rightChild == null)//left child is full, right child is empty
			{
				return book.leftChild;
			}
			else if(book.leftChild == null && book.rightChild != null)//left child is empty, right child is full
			{
				return book.rightChild;
			}
		}
		if(title.compareToIgnoreCase(book.iData) < 0){ //Didn't find the book we were searching for
			book.leftChild = remove(title, book.leftChild); //traverse left
		}
		else if(title.compareToIgnoreCase(book.iData) > 0){
			book.rightChild = remove(title, book.rightChild); //traverse right
		}
		heightFix(book); //fix height
		return balance(book); //balance current node
	}
	
	
	private boolean find(String title, AVLNode book){ 
		if(book == null) //The tree has no children
			return false;
		if(book.iData.equalsIgnoreCase(title)) //Found a node with a matching title
			return true;
		else if(title.compareToIgnoreCase(book.iData) <= -1){ //Is title in left or right?
			if(book.leftChild != null) //if the left child exists
				return find(title, book.leftChild); //recursively call itself. Passing the left child in the parameter. Left Child is found
			else
				return false; //Not here!
		}
		else{
			if(book.rightChild != null) //if the right child exists
				return find(title, book.rightChild); //recursively call itself passing the right child in the parameter. Right child is found
			else
				return false; //Not here!
		}	
	}
	
	private int heightBalance(AVLNode book) //This method checks whether nodes are balanced
	{
		if(book.leftChild == null && book.rightChild == null)				
		{		
			return 0;														//No height difference
		}
		else if(book.leftChild != null && book.rightChild == null)
		{
			return book.leftChild.height;								 	 //Return leftchild minus null node
		}
		else if(book.leftChild == null && book.rightChild != null)
		{
			return book.rightChild.height;									 //Return null node minus rightchild
		}
		else
		{
			return book.leftChild.height - book.rightChild.height;			 //Return leftchild minus right child to determine height balance
		}
	}
	
	private AVLNode balance(AVLNode book) //This method does the actual balancing.
	{
		if(heightBalance(book) == 2) //If the left child is unbalanced
		{
			if(heightBalance(book.leftChild) == 1 || heightBalance(book.leftChild) == 0) //if height balance difference is 1 or 0 rotate right
				book = rotateRight(book); 
			else
				book = doubleRight(book); //if more then double rotate
		}
		else if(heightBalance(book) == -2) //If the right child is unbalanced
		{
			if(heightBalance(book.rightChild) == -1) //If unbalanced with a difference of -1 (rightChild) rotate left
				book = rotateLeft(book);
			else
				book = doubleLeft(book); //if more then double rotate
		}
		heightFix(book);  //fix height
		return book;      //update current node
	}
	
	private void heightFix(AVLNode book) 								//Height fix method corrects the height for the current node
	{
		if(book.leftChild == null && book.rightChild == null) 			//if there is no left or child child set height of node to 1
			book.height = 1;
		else if(book.leftChild == null && book.rightChild != null)		//if there is a right child add one to the height of it
			book.height = (book.rightChild.height) + 1;
		else if(book.leftChild != null && book.rightChild == null) 		//if there is a left child add one to the height of it
			book.height = (book.leftChild.height) + 1;
		else 															//if there's a left & right child, add one to both of the heights
			book.height = Math.max(book.leftChild.height,book.rightChild.height) + 1;
	}
	
	private AVLNode rotateLeft(AVLNode book) //Single rotate left
	{
		AVLNode temp = book;  					//copy current node into new temp node
		book = book.rightChild; 				//Let current node be the right child
		temp.rightChild = book.leftChild; 		//let the new nodes right child be equal to the current nodes left child
		book.leftChild = temp; 					//let the current node's left child be equal to the new node
		heightFix(book.rightChild);				//correct height of current node's right child
		return book; 							//update current node
	}
	
	private AVLNode doubleLeft(AVLNode book)
	{
		book.rightChild = rotateRight(book.rightChild); //rotate left
		return rotateLeft(book); 						//double rotate left
	}
	
	private AVLNode rotateRight(AVLNode book)
	{
		AVLNode temp = book;					//copy current node into new temp node
		book = book.leftChild;					//Let current node be the left child
		temp.leftChild = book.rightChild;		//let the new node's left child be equal to the current nodes right child
		book.rightChild = temp;					//let the current node's right child be equal to the new node
		heightFix(book.leftChild);				//correct height of the current node's left child
		return book;							//update current node
	}
	
	private AVLNode doubleRight(AVLNode book)
	{
		book.leftChild = rotateLeft(book.leftChild); //rotate right
		return rotateRight(book); 					 //double rotate right
	}
	
	
//	private void preorder(AVLNode book) //testing method
//	{
//		if(book != null)
//		{
//			System.out.println(book.iData);
//			preorder(book.leftChild);
//			preorder(book.rightChild);
//		}
//		
//	}
	
}
