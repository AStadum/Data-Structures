/*
 *Anders Stadum
 *BST.java
 *11/4/2013
 *
 */

public class BST {
	private BSTNode root; //
	private boolean flag; //tells us which case comes back true or false
	
	public void insert(String title){ //adds a book to the catelog
		insert(title, root);		  //recursively calls itself, passing the title of the book and the root node
	}
	
	public boolean find(String title) { //find the title of a book
		return find(title, root);		//recursively call itself, passing the title of the book and the root node
	}
	
	public boolean remove(String title) { //removes a book by its title
		flag = false;					  //flag default set to false
		root = remove(title, root);		  //recursively call itself, passing the title of the book and the root node
		return flag;					  //return whether the speific case has been completed
	}

	private void insert(String title, BSTNode book) //passes book title and a BSTNode object
	{
		if(root == null){ //if the root is empty
			root = new BSTNode(title); //insert a node
		}
		else if(title.compareToIgnoreCase(book.iData) <= -1) //if the title is already inside of the tree
		{
			if(book.leftChild != null) //if the left child of a root exists
				insert(title, book.leftChild); //recursively move to left child
			else
				book.leftChild = new BSTNode(title); //insert new node into the left
		}
		else
		{
			if(root.rightChild != null) //if the right child of a root exists
				insert(title, root.rightChild); //recursively move to right child
			else
				book.rightChild = new BSTNode(title); //insert new node into the right
		}
	}
		
	
	private boolean find(String title, BSTNode book){ 
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

	
	private BSTNode remove(String title, BSTNode book)
	{
		BSTNode ret = null;
		if(book == null) //The tree is empty
		{
			return ret;
		}
		else if(book.iData.equalsIgnoreCase(title)) //Check to see if the title is inside the tree
		{
			if(book.leftChild != null && book.rightChild != null) //both children are full in node
			{
				BSTNode minimumNode = book; 			//holds the smallest values
				minimumNode = minimumNode.leftChild;  	//Set leftchild to the smallest value
				while(minimumNode.rightChild != null)   //While the smallest right child exists
					minimumNode = minimumNode.rightChild; //Set minimum node to right child
				if(minimumNode.leftChild != null){       //If the smallest left child exits
					book.leftChild.rightChild = minimumNode.leftChild; //Sets the roots left child's right child to the smallest leftchild node
					book.iData = minimumNode.iData;		//Set the data within our book node to the data in the smallest node
					flag = true;						//This case was chosen
					return book;						
				}
				else{
					book.iData = minimumNode.iData;     //Set the data within our book node to the data in the smallest node
					minimumNode = null;					//clear smallest node
					flag = true;						//This case was chosen
					return book;
				}
			}
			else if(book.leftChild == null && book.rightChild == null)//both children are empty
			{
				flag = true;
				return ret;
			}
			else if(book.leftChild != null && book.rightChild == null)//left child is full, right child is empty
			{
				flag = true;
				return book.leftChild;
			}
			else if(book.leftChild == null && book.rightChild != null)//left child is empty, right child is full
			{
				flag = true;
				return book.rightChild;
				
			}
			return ret;
		}
		else if(title.compareToIgnoreCase(book.iData) < 0){ //Didn't find the book we were searching for
			book.leftChild = remove(title, book.leftChild); //traverse left
			return book;
		}
		else{
			book.rightChild = remove(title, book.rightChild); //traverse right
			return book;
		}
	}
}
