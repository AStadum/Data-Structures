/**
 * Anders Stadum
 * Lab 3 AVL Tree
 * 11/26/2013
 * AVLNode.java
 */

public class AVLNode {
	
	public String iData; 						//data...
	public AVLNode leftChild , rightChild; 		//pointer towards nodes children
	public int height;							//stores a numerical value of the height position of each node
	public AVLNode(String dd) {
		iData = dd;        						//data stored in node
		leftChild = null;  						//set left child to null
		rightChild = null; 						//set right child to null
	}
}
